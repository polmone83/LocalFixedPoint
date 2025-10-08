import bddRelations.BDDRel;
import bddRelations.BDDRelStaticEquationSystem;
import bddRelations.BDDRelUniverse;
import com.github.javabdd.*;

import java.math.BigInteger;


public class TestJavaBDD {
    public static void main(String[] args) {
        //testBDDs();
        //testRelations();
        //testOverlap();
        //testVar();
        testMarginals();
    }

    private static void testMarginals(){
        BDDRelUniverse u = new BDDRelUniverse();
        u.init(3,3);
        BDDRel R = u.fullRelation().diff(u.leftSingleton(0));
        R.diffWith(u.leftSingleton(0));
        R.insertPair(0,1);
        System.out.println("R: " + R);

        System.out.println("Right marginal: " + R.rightMarginal());
        System.out.println("Left marginal: " + R.leftMarginal());
    }

    private static void testVar(){
        BDDFactory B = BDDFactory.init(1000, 1000);
        BDD bdd = B.zero();
        //bdd.printSet();
        B.extVarNum(1);
        //B.ithVar(0).printSet();
        B.extVarNum(1);
        //B.ithVar(1).not().printSet();
        bdd = B.ithVar(0);
        //bdd.andWith(B.ithVar(1)).not();

        BDDVarSet vars =
                B.makeSet(new int[]{0,1});
        BDD.BDDIterator iter = bdd.iterator(vars);
        while (iter.hasNext()){
            for (boolean b : iter.nextSat()) {
                System.out.print(b);
            }
            System.out.println();
        }
    }

    /*private static void testRelations() {
        BDDRelUniverse u = new BDDRelUniverse(5);
        BDDRel R = u.emptyRelation();
        //R.insertPair(1,2);
        R.insertPair(1,3);
        R.insertPair(3,2);
        //R.bdd.printSetWithDomains();
        System.out.println("R: " + R);

        BDDVarSet rightComponent = R.bdd.getFactory().getDomain(0).set();
        BDDRel sing = u.leftSingleton(3);
        BDD Re = R.bdd.relprod(sing.bdd,rightComponent);
        System.out.print("Re: ");
        Re.printSetWithDomains();

        //R.elements();

        BDDRel G = u.emptyRelation();
        G.insertPair(2,1);
        G.insertPair(2,4);
        G.insertPair(1,3);
        G.bdd.printSetWithDomains();

        //R.reflexiveClosure();
        R.transitiveClosure();
        R.bdd.printSetWithDomains();

    }*/

    private static void testOverlap(){
        BDDFactory B = BDDFactory.init(1000, 1000);

        int[] domSizes = new int[]{3,3};
        BDDDomain[] domains = B.extDomain(domSizes);

        BDD set = B.zero();
        //insert the pair (1,1)
        set.orWith(B.getDomain(0).ithVar(1)
                .andWith(B.getDomain(1).ithVar(1)));
        set.printSetWithDomains();

        try {
            set.orWith(B.getDomain(0).ithVar(3)
                    .andWith(B.getDomain(1).ithVar(3)));
            set.printSetWithDomains();
        }catch (Exception e){
            System.out.println("Errore");
        }

        // proviamo ad estendere il range dei domini
        //B.getDomain(0).ensureCapacity(6);
        B.overlapDomain(B.getDomain(1),B.getDomain(2));

        try {
            set.orWith(B.getDomain(0).ithVar(3)
                    .andWith(B.getDomain(1).ithVar(3)));
            set.printSetWithDomains();
        }catch (Exception e){
            System.out.println("Errore");
        }




    }

    /*private static void testBDDs() {
        BDDFactory B;
        B = BDDFactory.init(1000, 1000);

        int[] domSizes = new int[]{3,3};
        BDDDomain[] domains = B.extDomain(domSizes);
        BDD all = B.one();
        BDD[] careset = new BDD[domains.length];
        for(int dom= 0; dom< domains.length; dom++) {
            careset[dom] = B.zero();
            for (int i = 0; i < domSizes[dom]; i++) {
                careset[dom].orWith(B.getDomain(dom).ithVar(i));
            }
            all.andWith(careset[dom]);
        }
        all.printSetWithDomains();

        BDD ab = B.getDomain(0).ithVar(0);
        ab.andWith(B.getDomain(1).ithVar(1));
        ab.printSetWithDomains();

        BDD bc = B.getDomain(0).ithVar(1);
        bc.andWith(B.getDomain(1).ithVar(2));
        bc.printSetWithDomains();

        BDD source = ab.exist(B.getDomain(1).set());
        source.printSetWithDomains();
        BDD nextAB = ab.relnext(source, B.getDomain(1).set());
        nextAB.printSetWithDomains();
        BDD nextBC = bc.relnext(nextAB, B.getDomain(1).set());
        nextBC.printSetWithDomains();
        BDDPairing pair = B.makePair(B.getDomain(0),B.getDomain(1));
        nextBC.replaceWith(pair);
        nextBC.printSetWithDomains();

        source.and(nextBC).printSetWithDomains();


        *//*BDD leftSingleton = B.getDomain(0).ithVar(2).and(all);
        leftSingleton.printSetWithDomains();
        BDD source = B.getDomain(0).ithVar(2);
        BDD target = leftSingleton.relnext(source, B.getDomain(1).set());

        //System.out.println(target.support().toString());
        //System.out.println(source.support().toString());
        target.printSetWithDomains();
        //target.printSet();


        BDDPairing pair = B.makePair(B.getDomain(0),B.getDomain(1));
        target.replace(pair);
        target.printSetWithDomains();*//*
        //target.printSet();




        //System.out.println(all.satCount());



        *//*System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);*//*




        //g.printDot(); //first graph diagram

        *//* At this point
         * let say we know the BDD variable v1 = One (true)
         * What is the code that should be inserted to simplify the BDD
         * so that second graph is like the attached image
         *//*

       // g.printDot(); //second graph diagram

    }*/

}
