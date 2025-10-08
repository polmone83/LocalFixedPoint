grammar PureProlog;
prog:	( (clause | fact) '.')* EOF ;
clause: predicate ':-' predicate (',' predicate)*;
fact : predicate;
predicate : ID '(' ( term (',' term)* )? ')'    #namedPredicate
    | 'true'                                    #truePredicate
    ;
term : ID '(' term (',' term)* ')'              #functional
    | '[' ( term (',' term)* )? ']'             #listEnum
    | '[' term (',' term)* '|' variable ']'     #listPattern
    | const                                     #constant
    | variable                                  #var
    ;
variable : VAR                                  #namedVar
    | '_'                                       #wildcard
    ;
const : ID                                      #namedConst
    | NUMBER                                    #number
    ;

ID      : [a-z_][a-zA-Z0-9_]*;
VAR     : [A-Z_][a-zA-Z0-9_]*;
NUMBER  : [0-9]+;
NEWLINE : [ \t\r\n]+ -> skip;
MULTILINE_COMMENT : '/*' .*? '*/' -> skip ;
INLINE_COMMENT : '%' .*? '\n' -> skip ;
