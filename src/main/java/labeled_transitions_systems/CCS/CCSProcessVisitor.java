package labeled_transitions_systems.CCS;

public interface CCSProcessVisitor<T> {

    default T visit(CCSProcess p){
        return p.accept(this);
    };

    T visitNil(CCSProcess.Nil p);
    T visitAction(CCSProcess.Action p);
    T visitParallel(CCSProcess.Parallel p);
    T visitChoice(CCSProcess.Choice p);
    T visitParens(CCSProcess.ParensProcess p);
    T visitRestriction(CCSProcess.Restriction p);
    T visitRenaming(CCSProcess.Renaming p);
    T visitPName(CCSProcess.PName p);

}
