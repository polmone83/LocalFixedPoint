package labeled_transitions_systems.CCS;

public class CCSProcessPrettyPrint implements CCSProcessVisitor<StringBuilder>{
    StringBuilder pp;

    public CCSProcessPrettyPrint(){
        this.pp = new StringBuilder();
    }

    public static String prettyPrint(CCSProcess p){
        CCSProcessPrettyPrint prettyPrint = new CCSProcessPrettyPrint();
        return prettyPrint.visit(p).toString();
    }

    @Override
    public StringBuilder visitNil(CCSProcess.Nil p) {
        pp.append("0");
        return pp;
    }

    @Override
    public StringBuilder visitAction(CCSProcess.Action p) {
        //pp.append("(");
        if(!p.channel.equals(CCSProcess.TAU) && !p.isInput) pp.append("\'");
        pp.append(p.channel);
        pp.append(".");
        visit(p.process);
        //pp.append(")");
        return pp;
    }

    @Override
    public StringBuilder visitParallel(CCSProcess.Parallel p) {
        if(p.children.size() > 0 ){
            pp.append("(");
            for (CCSProcess child : p.children) {
                visit(child);
                pp.append("|");
            }
            pp.delete(pp.length()-1, pp.length());
            pp.append(")");
        }else{
            // the process is equivalent to Nil
            pp.append('0');
        }
        return pp;
    }

    @Override
    public StringBuilder visitChoice(CCSProcess.Choice p) {
        if(p.children.size() > 0){
            pp.append("(");
            for (CCSProcess child : p.children) {
                visit(child);
                pp.append("+");
            }
            pp.delete(pp.length()-1,pp.length()); //remove the last "+"
            pp.append(")");
        }else{
            pp.append("0");
        }
        return pp;
    }

    @Override
    public StringBuilder visitParens(CCSProcess.ParensProcess p) {
        pp.append("(");
        visit(p.process);
        pp.append(")");
        return pp;
    }

    @Override
    public StringBuilder visitRestriction(CCSProcess.Restriction p) {
        pp.append("(");
        visit(p.process);
        if(p.chanSet.size() > 0){
            pp.append("\\");
            pp.append("{");
            for (String s : p.chanSet) {
                pp.append(s);
                pp.append(",");
            }
            pp.delete(pp.length()-1, pp.length());
            pp.append("}");
        }
        pp.append(")");
        return pp;
    }

    @Override
    public StringBuilder visitRenaming(CCSProcess.Renaming p) {
        pp.append("(");
        visit(p.process);
        if(p.renaming.size() > 0) {
            pp.append("[");
            p.renaming.forEach((String from, String to) -> {
                pp.append(to);
                pp.append("/");
                pp.append(from);
                pp.append(", ");
            });
            pp.delete(pp.length()-2, pp.length());
            pp.append("]");
        }
        pp.append(")");
        return pp;
    }

    @Override
    public StringBuilder visitPName(CCSProcess.PName p) {
        pp.append(p.pName);
        return pp;
    }
}
