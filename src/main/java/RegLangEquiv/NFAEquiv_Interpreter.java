package RegLangEquiv;

import java.util.HashSet;
import java.util.Iterator;

public interface NFAEquiv_Interpreter {

    HashSet<String> getCheckLeft();
    HashSet<String> getCheckRight();

    Boolean isFinal(HashSet<String> states);
    HashSet<String> getSuccessors(HashSet<String> states, String symbol);

    Iterator<String> alphabetIterator();

}
