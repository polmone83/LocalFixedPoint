package core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class SelectionStrategy<V, D, R extends VarRelation<V,S>, S extends VarSet<V>>{
    EquationSystem<V, D, R, S> system;
    ArrayList<V> elements;
    S todoSet;
    S visited;
    HashSet<V> discovered;

    public SelectionStrategy(EquationSystem<V, D, R, S> system){
        this.system = system;
    }

    private void dfsVisit(V x){
        discovered.add(x);
        S adj = system.retrieveRHS(x).getVars();
        adj.allSetElements().forEachRemaining(
                y ->{
                    if(!discovered.contains(y) && visited.contains(y)){
                        dfsVisit(y);
                    }
                }
        );
        if(todoSet.contains(x)){
            elements.add(x);
        }
    }

    public Iterator<V> getTodo(V targetvar, S todoSet, S visited){
        this.todoSet = todoSet;
        this.visited = visited;
        discovered = new HashSet<>();
        elements = new ArrayList<>();
        dfsVisit(targetvar);
        return elements.iterator();
    }
}
