grammar NFA;
automaton:	stmt? (END stmt)* EOF ;

stmt : transition | check | accept;

transition: from=states '-' labels '->' to=states;

check: 'check' ':' states '=' states        #Equivalence
      | 'check' ':' states '<=' states      #Inclusion
      | 'check' ':' states '=>' states      #Containment
      ;
accept: 'accept' ':' states;

states: ID*;
labels: ID ('+' ID)*;

END: '\n';
NEWLINE : [ \t\r\n]+ -> skip;
ID   : [a-zA-Z][a-zA-Z0-9"'*!?]*;
MULTILINECOMMENT : '/*' .*? '*/' -> skip ;
SINGLELINECOMMENT : '//' .*? '\n' -> skip ;

