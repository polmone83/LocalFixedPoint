package RegLangEquiv;

import com.github.javabdd.BDD;
import com.github.javabdd.BDDFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


public class ContextEquiv {

    private class Rule{
        BDD lhs;
        BDD rhs;

        public Rule(BDD lhs, BDD rhs){
            this.lhs = lhs;
            this.rhs = rhs;
        }

        public boolean apply(BDD state){
            BDD checkInclusion = state.or(lhs);
            if(checkInclusion.equalsBDD(state)){ // the lhs is included in the state
                checkInclusion.free();
                BDD after = state.or(rhs);
                boolean changed = ! state.equalsBDD(after);
                if( changed ) {
                    state.orWith(after);
                    return true;
                } else {
                    after.free();
                    return false;
                }
            }else {
                checkInclusion.free();
                return false;
            }
        }
    }

    BDDFactory B;
    ArrayList<Rule> rules;
    HashMap<String, Integer> nfaStateEnc;
    HashMap<String, BDD> nfaNormalform;
    int nfaStatesCount;

    public ContextEquiv(int universeSize){
        B = BDDFactory.init(1000, 1000);
        B.extDomain(universeSize); // create the domain

        nfaStatesCount = 0; // init the counter
        nfaStateEnc = new HashMap<>();
        nfaNormalform = new HashMap<>();
        rules = new ArrayList<>();
    }

    private Integer getNFAStateEncoding(String nfaState){
        Integer enc = nfaStateEnc.get(nfaState);
        if(enc == null){
            enc = nfaStatesCount;
            nfaStateEnc.put(nfaState, enc);
            nfaStatesCount++;
        }
        return enc;
    }

    private BDD getDFAStateEncoding(Set<String> xs){
        BDD dfaState = B.zero();
        for (String nfaState : xs) {
            BDD singleton = B.getDomain(0).ithVar(getNFAStateEncoding(nfaState)).id();
            dfaState.orWith(singleton);
        }
        return dfaState;
    }

    public void addRules(Set<String> xs, Set<String> ys){
        // add the rules x -> x + y and y -> x + y
        BDD lhs1, lhs2, rhs;
        lhs1 = getDFAStateEncoding(xs); // x
        lhs2 = getDFAStateEncoding(ys); // y
        rhs = lhs1.or(lhs2); // x + y
        rules.add(new Rule(lhs1, rhs)); // x -> x + y
        rules.add(new Rule(lhs2, rhs)); // y -> x + y
    }

    private void toNormalForm(BDD state){
        BDD before;
        boolean not_normalform;
        // init
        boolean[] appliedRules = new boolean[rules.size()];
        for(int i = 0; i < rules.size(); i++){
            appliedRules[i] = false;
        }
        // turn the BDD state into a normal form
        do{
            before = state.id();
            for (int i = 0; i < rules.size(); i++) {
                if(!appliedRules[i]) { // the rule was not applied yet
                    // attempt to apply the rule
                    appliedRules[i] = rules.get(i).apply(state);
                }
            }
            not_normalform = !before.equalsBDD(state);
            before.free();
        }while (not_normalform); // check if something has changed
    }

    public boolean equivalent(Set<String> x, Set<String> y){
        BDD xEnc = getDFAStateEncoding(x);
        toNormalForm(xEnc);

        BDD yEnc = getDFAStateEncoding(y);
        toNormalForm(yEnc);

        boolean equivQ = xEnc.equalsBDD(yEnc);
        xEnc.free();
        yEnc.free();
        return equivQ;
    }

}
