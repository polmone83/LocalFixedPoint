package core;

public interface TermExtension<V, D, R extends VarRelation<V,S>, S extends VarSet<V>> {
    R evalExtension(EquationSystem<V,D,R,S> system, R relation, Assignment<V,D> ass);
}
