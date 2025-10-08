package bddRelations;
import com.github.javabdd.*;

public class BDDRelUniverse {
    private BDDFactory B;
    private Integer universeSize;
    private int domainSize;
    private BDD all;
    //private BDDRel fullrel;

    private BDDRel diagonal; /** the BDD representing the relation {(x,x) | x \in V} */

    public BDDRelUniverse(){}

    public BDDRel fullRelation(){
        return new BDDRel(this, all.id());
    }

    public BDDRel emptyRelation(){
        return new BDDRel(this, B.zero());
    }

    public BDDRel leftSingleton(int val){
        return new BDDRel(this, B.getDomain(0).ithVar(val));
    }

    public BDDRel rightSingleton(int val){
        return new BDDRel(this, B.getDomain(1).ithVar(val));
    }

    public int getDomainSize(){
        return domainSize;
    }

    public BDDRel diagonal(){
        if(diagonal == null){
            diagonal = emptyRelation();
            for(int i = 0; i < getDomainSize(); i++){
                diagonal.insertPair(i,i);
            }
        }
        return diagonal.copy();
    }

    public void init(int domainSize,int universeSize){
        /*if(B != null){
            B.done();
        }*/
        B = BDDFactory.init(1000, 1000);
        this.universeSize = universeSize;
        this.domainSize = domainSize;
        // compute the care set
        int[] domSizes = new int[]{domainSize,domainSize};
        BDDDomain[] domains = B.extDomain(new int[] {universeSize,universeSize});

        all = B.one();
        BDD[] careset = new BDD[domains.length];
        for(int dom= 0; dom< domains.length; dom++) {
            careset[dom] = B.zero();
            for (int i = 0; i < domSizes[dom]; i++) {
                careset[dom].orWith(B.getDomain(dom).ithVar(i));
            }
            all.andWith(careset[dom]);
        }
        //fullrel = new BDDRel(this, all);

        /** add an extra component (used in BDDRel.composition()) */
        B.extDomain(universeSize);

        // store the diagonal
        diagonal = null;
    }

}
