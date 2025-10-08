package RegLangEquiv.Timbuk;

import org.antlr.v4.runtime.tree.ParseTree;

public class TimbukIncl_Interpreter extends TimbukEquiv_Interpreter{

    @Override
    public void construct(String leftNFA, String rightNFA, Boolean fileFlag) {
        // Construct the NFA and the query
        NFABuilder nfaBuilder = new NFABuilder();
        try {
            ParseTree parseTree = getTimbukParseTree(leftNFA, fileFlag);
            nfaBuilder.prefix = "l";
            checkLeft.add(nfaBuilder.getInitState());
            nfaBuilder.visit(parseTree);

            parseTree = getTimbukParseTree(rightNFA, fileFlag);
            nfaBuilder.prefix = "r";
            checkRight.addAll(checkLeft);
            checkRight.add(nfaBuilder.getInitState());
            nfaBuilder.visit(parseTree);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
