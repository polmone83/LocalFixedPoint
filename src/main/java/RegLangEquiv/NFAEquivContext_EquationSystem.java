package RegLangEquiv;

import bddRelations.BDDRelEquationSystem;
import bddRelations.soundOracles.BDDRelOracle;
import core.RightHandSide;
import core.SimpleVarSet;
import domains.boolDomain.BoolFormula;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class NFAEquivContext_EquationSystem extends BDDRelEquationSystem<Boolean> {

    private class Pair {
        HashSet<String> left;
        HashSet<String> right;

        public Pair(HashSet<String>first, HashSet<String> second){
            if(first.hashCode() <= second.hashCode()){
                left = first;
                right = second;
            }else{
                left = second;
                right = first;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pair pair)) return false;
            return Objects.equals(left, pair.left) && Objects.equals(right, pair.right);
        }

        @Override
        public int hashCode() {
            return Objects.hash(left, right);
        }

        @Override
        public String toString() {
            StringBuilder pp = new StringBuilder();
            pp.append("{");
            pp.append(left);
            pp.append(",");
            pp.append(right);
            pp.append("}");
            return pp.toString();
        }
    }

    NFAEquiv_Interpreter interpreter;
    HashMap<Integer, Pair> index2Pair;
    HashMap<Pair, Integer> pair2Index;
    Integer varCounter;

    ContextEquiv ctxEquiv;
    /**
    This flag is used in the method {@link #getRHS(Integer)}
     to determine if it is worth keeping generating right-hand sides.
     The flag is initialized to {@code true} by {@link #localSolve(BDDRelOracle)}
    @see #getRHS(Integer)
     */
    private boolean maybeBisimilar;

    public NFAEquivContext_EquationSystem(){
        //initUniverse(new BDDRelUniverse((int)Math.pow(2,11),(int)Math.pow(2,11)));
    }

    @Override
    protected Boolean getBottomElement() {
        return false;
    }

    public void setInterpreter(NFAEquiv_Interpreter interpreter){
        //interpreter = new NFA_Interpreter(code, false);
        this.interpreter = interpreter;
    }

    public Boolean localSolve(BDDRelOracle<Boolean> oracle){
        /*if(! interpreter.getQuery().equals(NFA_Interpreter.Query.EQUIVALENCE))
            throw new RuntimeException("Currently, we only support equivalence checking");*/
        index2Pair = new HashMap<>();
        pair2Index = new HashMap<>();
        varCounter = 0;

        maybeBisimilar = true; // assume the initial pair might be bisimilar
        ctxEquiv = new ContextEquiv((int) Math.pow(2,15));

        // Retrieve the initial pair of states;
        Pair targetVar = new Pair(interpreter.getCheckLeft(), interpreter.getCheckRight());
        //addPair(targetVar);
        // forced addition of the first pair
        // (this is necessary in case the pair is of the form (x,x))
        addTargetPair(targetVar);

        return localSolve(pair2Index.get(targetVar), false, oracle);
    }

    @Override
    public RightHandSide<Integer, Boolean, SimpleVarSet> getRHS(Integer x) {
        Pair xPair = index2Pair.get(x);
        /* Construct the right-hand side formula */
        if (maybeBisimilar && interpreter.isFinal(xPair.left).equals(interpreter.isFinal(xPair.right))){
            HashSet<BoolFormula> subFormulas = new HashSet<>();
            interpreter.alphabetIterator().forEachRemaining(
                    (String symbol) -> {
                        Pair nextPair = new Pair(
                                interpreter.getSuccessors(xPair.left,symbol),
                                interpreter.getSuccessors(xPair.right, symbol));

                        if(addPair(nextPair)) {
                            subFormulas.add(getVariable(nextPair));
                        }
                    }
            );

            return new BoolFormula.Or(subFormulas,this);
        }
        maybeBisimilar = false; // set the flag
        return new BoolFormula.True(this);
    }

    @Override
    public Integer getIndex(String varName) {
        throw new RuntimeException("getIndex not implemented");
        //return 0;
    }

    @Override
    public String getLabel(Integer x) {
        if(index2Pair.containsKey(x)){
            return "x" + x; //index2Var.get(x).toString();
        }
        return "NaL"+x;
    }

    /**
     * Add the pair of states to the list of encountered pairs
     * and check if the pair is NOT redundant w.r.t. context closure
     * @param pair pair of states of the DFA
     * @return true iff the pair is NOT redundant
     */
    private boolean addPair(Pair pair){
        Integer x = pair2Index.get(pair);
        boolean redundant = ctxEquiv.equivalent(pair.left,pair.right);
        if(x == null && !redundant){ // the pair has not been encountered earlier
            pair2Index.put(pair,varCounter);
            index2Pair.put(varCounter,pair);
            varCounter++;

            ctxEquiv.addRules(pair.left,pair.right);
        }

        return !redundant;
    }

    private void addTargetPair(Pair pair){
        pair2Index.put(pair,varCounter);
        index2Pair.put(varCounter,pair);
        varCounter++;

        ctxEquiv.addRules(pair.left,pair.right);
    }


    private BoolFormula.Var getVariable(Pair pair){
        Integer index = pair2Index.get(pair);
        if(pair == null)
            throw new RuntimeException("The pair " + pair + " was not found");

        return new BoolFormula.Var(index,this);
    }
}
