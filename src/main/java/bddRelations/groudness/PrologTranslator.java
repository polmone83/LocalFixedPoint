package bddRelations.groudness;

import domains.posDomain.prologparser.PurePrologBaseVisitor;
import domains.posDomain.prologparser.PurePrologLexer;
import domains.posDomain.prologparser.PurePrologParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class PrologTranslator {
    private String eqs;
    private Map<String, StringBuilder> equations;
    private Map<String, PrologPredicate> predicates;

    public PrologTranslator(String code, boolean isFilepath) {
        this.equations = new HashMap<>();
        this.predicates = new HashMap<>();

        // parse the prolog program
        CharStream input;
        if (isFilepath) { // code is a filepath
            try {
                input = CharStreams.fromFileName(code);
            } catch (Exception e) {
                throw new RuntimeException("file not found");
            }
        } else { // code is the prolog program
            input = CharStreams.fromString(code);
        }
        // parse the prolog program
        PurePrologLexer lexer = new PurePrologLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        PurePrologParser parser = new PurePrologParser(tokens);
        ParseTree tree = parser.prog();

        // prepare the maps 'equations' and 'predicates'
        Memory memory = new Memory();
        tree.accept(memory);

        // generate the equation system for groundness analysis
        EqBuilder eqp = new EqBuilder();
        eqs = tree.accept(eqp);
    }

    public String getEqs() {
        return eqs;
    }

    private class Memory extends PurePrologBaseVisitor<Boolean> {
        @Override
        public Boolean visitProg(PurePrologParser.ProgContext ctx) {
            for (PurePrologParser.ClauseContext clauseContext : ctx.clause()) {
                visit(clauseContext.predicate(0));
            }
            for (PurePrologParser.FactContext factContext : ctx.fact()) {
                visit(factContext.predicate());
            }
            return null;
        }

        @Override
        public Boolean visitNamedPredicate(PurePrologParser.NamedPredicateContext ctx) {
            addPredicate(ctx.ID().getText(), ctx.term().size());
            return null;
        }

        private void addPredicate(String name, int arity) {
            PrologPredicate p = new PrologPredicate(name, arity);
            if (!predicates.containsKey(p.getSignature())) {
                predicates.put(p.getSignature(), p);
                equations.put(p.getSignature(), new StringBuilder());
            }
        }

    }

    private class EqBuilder extends PurePrologBaseVisitor<String> {
        int varCounter;
        HashSet<String> termVars;

        public EqBuilder() {
            varCounter = 0;
            termVars = new HashSet<>();
        }

        @Override
        public String visitProg(PurePrologParser.ProgContext ctx) {
            for (PurePrologParser.ClauseContext clauseContext : ctx.clause()) {
                visit(clauseContext);
            }
            for (PurePrologParser.FactContext factContext : ctx.fact()) {
                visit(factContext);
            }

            // gather the collected information
            StringBuilder s = new StringBuilder();
            predicates.forEach(
                    (String key, PrologPredicate p) -> {
                        s.append(p.defaultCallPattern());
                        s.append(" = ");
                        s.append(equations.get(key));
                        s.append(";\n");
                    }
            );
            return s.toString();
        }

        @Override
        public String visitFact(PurePrologParser.FactContext ctx) {
            varCounter = 0;
            if (ctx.predicate() instanceof PurePrologParser.NamedPredicateContext) {
                PurePrologParser.NamedPredicateContext nPredicate = (PurePrologParser.NamedPredicateContext) ctx.predicate();
                StringBuilder eq = getEquation(nPredicate);
                if (!eq.isEmpty()) { // if it is not the first rule for this predicate
                    eq.append("\n\t | ");
                }

                int arity = nPredicate.term().size();

                if(arity > 0) {
                    eq.append("<");
                    // binder of the predicate
                    eq.append(getBinders(nPredicate, arity, 0));
                    eq.append(",[");
                    eq.append(getDefParamList(0, arity)); // X0...Xn
                    eq.append("]>");
                }else {
                    eq.append("tt");
                }
            }
            return null;
        }

        private StringBuilder getEquation(PurePrologParser.NamedPredicateContext ctx) {
            String name = ctx.ID().getText();
            int arity = ctx.term().size();
            PrologPredicate p = new PrologPredicate(name, arity);
            if (equations.containsKey(p.getSignature())) {
                return equations.get(p.getSignature());
            }
            return null;
        }

        @Override
        public String visitClause(PurePrologParser.ClauseContext ctx) {
            if(ctx.predicate(0) instanceof PurePrologParser.NamedPredicateContext) {
                PurePrologParser.NamedPredicateContext p0 = (PurePrologParser.NamedPredicateContext) ctx.predicate(0);
                StringBuilder eq = getEquation(p0);
                if (!eq.isEmpty()) { // if it is not the first rule for this predicate
                    eq.append("\n\t | ");
                }

                int arity = p0.term().size();

                if(arity > 0) {
                    eq.append("<");
                    // binder of the head predicate
                    eq.append(getBinders(p0, arity, 0));
                    varCounter = arity;
                    // visit the predicates in the body of the rule
                    for (int i = 1; i < ctx.predicate().size(); i++) {
                        eq.append(" & ").append(visit(ctx.predicate(i)));
                    }
                    eq.append(",[");
                    eq.append(getDefParamList(0, arity)); // X0...Xn
                    eq.append("]>");
                }else{
                    eq.append("tt");
                }
            }
            return null;
        }

        @Override
        public String visitNamedPredicate(PurePrologParser.NamedPredicateContext ctx) {
            // perform the binding
            StringBuilder pString = new StringBuilder();
            int arity = ctx.term().size();
            int basecounter = varCounter;
            varCounter = varCounter + arity; // update the var counter for the rule

            // p(X1...Xn)
            pString.append(getPredicateCall(ctx,arity,basecounter));
            pString.append(" & ");
            // X1 <=> Vars(t1) & .. & Xn <=> Vars(tn)
            pString.append(getBinders(ctx,arity,basecounter));

            return pString.toString();
        }

        private StringBuilder getPredicateCall(PurePrologParser.NamedPredicateContext ctx, int arity, int basecounter) {
            StringBuilder pString = new StringBuilder();
            pString.append(ctx.ID().getText());
            if (arity > 0) {
                pString.append("(");
                pString.append(getDefParamList(basecounter,arity));
                pString.append(")");
            }
            return pString;
        }

        private StringBuilder getDefParamList(int basecounter, int arity) {
            StringBuilder pString = new StringBuilder();
            pString.append(PrologPredicate.ithDefParam(basecounter));
            //pString.append("X" + basecounter);
            for (int i = 1; i < arity; i++) {
                pString.append(",").append(PrologPredicate.ithDefParam(basecounter + i));
                //pString.append(",X" + (basecounter + i));
            }
            return pString;
        }

        private StringBuilder getBinders(PurePrologParser.NamedPredicateContext ctx, int arity, int basecounter) {
            StringBuilder pString = new StringBuilder();
            for (int i = 0; i < arity; i++) {
                pString.append("(");
                pString.append(PrologPredicate.ithDefParam(basecounter + i)); // Xi
                // pString.append("X" + (basecounter + i));
                termVars.clear(); // empty vars collection
                visit(ctx.term(i)); // collect the variables in the current term
                if (!termVars.isEmpty()) {
                    pString.append("<=>");
                    for (String termVar : termVars) {
                        pString.append(termVar + " & ");
                    }
                    pString.delete(pString.length() - 3, pString.length());
                }
                pString.append(") & ");
            }
            pString.delete(pString.length()-3, pString.length());
            return pString;
        }

        @Override
        public String visitFunctional(PurePrologParser.FunctionalContext ctx) {
            for (PurePrologParser.TermContext termContext : ctx.term()) {
                visit(termContext);
            }
            return null;
        }

        @Override
        public String visitTruePredicate(PurePrologParser.TruePredicateContext ctx) {
            return "tt";
        }

        @Override
        public String visitListEnum(PurePrologParser.ListEnumContext ctx) {
            for (PurePrologParser.TermContext termContext : ctx.term()) {
                visit(termContext);
            }
            return null;
        }

        @Override
        public String visitListPattern(PurePrologParser.ListPatternContext ctx) {
            for (PurePrologParser.TermContext termContext : ctx.term()) {
                visit(termContext);
            }
            visit(ctx.variable());
            return null;
        }

        @Override
        public String visitConstant(PurePrologParser.ConstantContext ctx) {
            return null;
        }

        @Override
        public String visitVar(PurePrologParser.VarContext ctx) {
            visit(ctx.variable());
            return null;
        }

        @Override
        public String visitNamedVar(PurePrologParser.NamedVarContext ctx) {
            termVars.add(ctx.VAR().getText());
            return null;
        }

        @Override
        public String visitWildcard(PurePrologParser.WildcardContext ctx) {
            termVars.add("_" + varCounter++);
            return null;
        }
    }
}
