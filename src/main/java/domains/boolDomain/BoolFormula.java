package domains.boolDomain;

import bddRelations.BDDRel;
import bddRelations.BDDRelEquationSystem;
import bddRelations.termExtensionOracles.BoolFormulaExtension;
import com.google.common.base.Objects;
import core.*;
import org.checkerframework.checker.units.qual.A;
import org.jetbrains.annotations.NotNull;

import java.awt.event.WindowStateListener;
import java.util.*;

public abstract class BoolFormula implements
        RightHandSide<Integer, Boolean, SimpleVarSet> , TermExtension<Integer, Boolean, BDDRel, SimpleVarSet>{ //

    BDDRelEquationSystem<Boolean> system;

    public BoolFormula(BDDRelEquationSystem<Boolean> system) {
        this.system = system;
    }

    @Override
    public SimpleVarSet getVars() {
        VarsVisitor visitor = new VarsVisitor();
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        PrettyPrint pp = new PrettyPrint();
        return pp.visit(this).toString();
    }

    public abstract <T> T accept(BoolFormulaVisitor<T> visitor);

    @Override
    public BDDRel evalExtension(EquationSystem<Integer, Boolean, BDDRel, SimpleVarSet> system, BDDRel relation, Assignment<Integer, Boolean> ass){
        if(system instanceof BDDRelEquationSystem<Boolean> bsystem){
            BoolFormulaExtension e = new BoolFormulaExtension(bsystem,relation,ass);
            return accept(e).toBDDRel();
        }
        throw new RuntimeException("This extension does not work with this RHS type");
    }

    public static class True extends BoolFormula {

        public True(BDDRelEquationSystem<Boolean> system) {
            super(system);
        }

        @Override
        public <T> T accept(BoolFormulaVisitor<T> visitor) {
            return visitor.visitTrue(this);
        }

        @Override
        public Boolean eval(Assignment<Integer, Boolean> ass) {
            return true;
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof True;
        }
    }

    public static class False extends BoolFormula {

        public False(BDDRelEquationSystem<Boolean> system) {
            super(system);
        }

        @Override
        public <T> T accept(BoolFormulaVisitor<T> visitor) {
            return visitor.visitFalse(this);
        }

        @Override
        public Boolean eval(Assignment<Integer, Boolean> ass) {
            return false;
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof False;
        }
    }

    public static class And extends BoolFormula {
        public Set<BoolFormula> subformulas;

        public And(BoolFormula left, BoolFormula right,
                   BDDRelEquationSystem<Boolean> system) {
            super(system);
            subformulas = new HashSet<>();
            subformulas.add(left);
            subformulas.add(right);
        }

        public And(Set<BoolFormula> subformulas,
                   BDDRelEquationSystem<Boolean> system) {
            super(system);
            this.subformulas = subformulas;
        }

        @Override
        public final boolean equals(Object o) {
            if (!(o instanceof And and)) return false;
            return subformulas.equals(and.subformulas);
        }

        @Override
        public int hashCode() {
            return subformulas.hashCode();
        }

        @Override
        public Boolean eval(Assignment<Integer, Boolean> ass) {
            for (BoolFormula subformula : subformulas) {
                if (!subformula.eval(ass))
                    return false;
            }
            //System.out.println("empty and");
            return true;
        }

        @Override
        public <T> T accept(BoolFormulaVisitor<T> visitor) {
            return visitor.visitAnd(this);
        }
    }

    public static class Or extends BoolFormula {
        public Set<BoolFormula> subformulas;

        public Or(BoolFormula left, BoolFormula right,
                  BDDRelEquationSystem<Boolean> system) {
            super(system);
            subformulas = new HashSet<>();
            subformulas.add(left);
            subformulas.add(right);
        }

        public Or(Set<BoolFormula> subformulas,
                  BDDRelEquationSystem<Boolean> system) {
            super(system);
            this.subformulas = subformulas;
        }

        @Override
        public final boolean equals(Object o) {
            if (!(o instanceof Or or)) return false;

            return subformulas.equals(or.subformulas);
        }

        @Override
        public int hashCode() {
            return subformulas.hashCode();
        }

        @Override
        public Boolean eval(Assignment<Integer, Boolean> ass) {
            for (BoolFormula subformula : subformulas) {
                if (subformula.eval(ass))
                    return true;
            }
            return false;
        }


        @Override
        public <T> T accept(BoolFormulaVisitor<T> visitor) {
            return visitor.visitOr(this);
        }
    }

    public static class Var extends BoolFormula {
        public Integer var;

        public Var(Integer var, BDDRelEquationSystem<Boolean> system) {
            super(system);
            this.var = var;
        }

        @Override
        public final boolean equals(Object o) {
            if (!(o instanceof Var var1)) return false;

            return var.equals(var1.var);
        }

        @Override
        public int hashCode() {
            return var.hashCode();
        }

        @Override
        public Boolean eval(Assignment<Integer, Boolean> ass) {
            return ass.getValue(var);
        }

        @Override
        public <T> T accept(BoolFormulaVisitor<T> visitor) {
            return visitor.visitVar(this);
        }
    }

    private class VarsVisitor implements BoolFormulaVisitor<SimpleVarSet> {

        SimpleVarSet vars;

        public VarsVisitor() {
            vars = new SimpleVarSet();
        }

        @Override
        public SimpleVarSet visitAnd(And phi) {
            for (BoolFormula subformula : phi.subformulas) {
                visit(subformula);
            }
            return vars;
        }

        @Override
        public SimpleVarSet visitOr(Or phi) {
            for (BoolFormula subformula : phi.subformulas) {
                visit(subformula);
            }
            return vars;
        }

        @Override
        public SimpleVarSet visitTrue(True phi) {
            return vars;
        }

        @Override
        public SimpleVarSet visitFalse(False phi) {
            return vars;
        }

        @Override
        public SimpleVarSet visitVar(Var phi) {
            vars.add(phi.var);
            return vars;
        }
    }

    private class PrettyPrint implements BoolFormulaVisitor<StringBuilder> {
        private StringBuilder pp;

        public PrettyPrint() {
            pp = new StringBuilder();
        }

        @Override
        public StringBuilder visitAnd(And phi) {
            if (phi.subformulas.isEmpty()) {
                pp.append("tt");
            } else {
                pp.append("And(");
                for (BoolFormula subformula : phi.subformulas) {
                    visit(subformula);
                    pp.append(", ");
                }
                pp.delete(pp.length()-2, pp.length());
                pp.append(")");
            }
            return pp;
        }

        @Override
        public StringBuilder visitOr(Or phi) {
            if (phi.subformulas.isEmpty()) {
                pp.append("ff");
            } else {
                pp.append("Or(");
                for (BoolFormula subformula : phi.subformulas) {
                    visit(subformula);
                    pp.append(", ");
                }
                pp.delete(pp.length()-2, pp.length());
                pp.append(")");
            }
            return pp;
        }

        @Override
        public StringBuilder visitTrue(True phi) {
            pp.append("tt");
            return pp;
        }

        @Override
        public StringBuilder visitFalse(False phi) {
            pp.append("ff");
            return pp;
        }

        @Override
        public StringBuilder visitVar(Var phi) {
            pp.append("x" + phi.var);
            return pp;
        }
    }

}
