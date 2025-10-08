package labeled_transitions_systems;

import core.VarPair;
import labeled_transitions_systems.CCS.CCSProcess;

public class OrderedVarPair extends VarPair<CCSProcess> {

    public OrderedVarPair(CCSProcess left, CCSProcess right){
        if(left.toString().compareTo(right.toString()) < 0){
            this.left = left;
            this.right = right;
        }else{
            this.right = left;
            this.left = right;
        }
    }
}
