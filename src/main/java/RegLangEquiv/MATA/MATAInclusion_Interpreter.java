package RegLangEquiv.MATA;

import java.util.HashSet;

public class MATAInclusion_Interpreter extends MATAEquiv_Interpreter{

    public MATAInclusion_Interpreter(String lhs, String rhs, boolean fileFlag) {
        super(lhs, rhs, fileFlag);
    }

    public HashSet<String> getCheckLeft() {
        /* We reduce inclusion checking to equivalence checking as follows
                L \subseteq R iff L+R = R
        */
        HashSet<String> initStates = super.getCheckLeft();
        initStates.addAll(super.getCheckRight());
        return initStates;
    }
}
