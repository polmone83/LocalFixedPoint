grammar WCTL;
decls:	formuladecl+ EOF;

formuladecl: FID '=' formula;

formula:  ('tt' | 'True' | 'true')                                          #True
        | ('ff' | 'False' | 'false')                                        #False
        | left=formula ('&&' | 'and') right=formula                         #And
        | left=formula ('||' | 'or') right=formula                          #Or
        | 'A' left=formula 'U' right=formula                                #UniversalUntil
        | 'A' left=formula 'U' '[' '<=' bound=WEIGHT ']' right=formula      #BoundedUniversalUntil
        | 'E' left=formula 'U'  right=formula                               #ExistentialUntil
        | 'E' left=formula 'U' '[' '<=' bound=WEIGHT ']' right=formula      #BoundedExistentialUntil
        | 'AF' formula                                                      #UniversalFinally
        | 'AF' '[' '<=' bound=WEIGHT ']' formula                            #BoundedUniversalFinally
        | 'EF' formula                                                      #ExistentialFinally
        | 'EF' '[' '<=' bound=WEIGHT ']' formula                            #BoundedExistentialFinally
        | 'AX' formula                                                      #UniversalNext
        | 'AX' '[' '<=' bound=WEIGHT ']' formula                            #BoundedUniversalNext
        | 'EX' formula                                                      #ExistentialNext
        | 'EX' '[' '<=' bound=WEIGHT ']' formula                            #BoundedExistentialNext
        | '(' formula ')'                                                   #Parens
        | relexpr                                                           #Expression
        ;
relexpr: expr rel=('<' | '<=' | '>' | '>=' | '!=' | '==') expr  #RelExpression
        |ID                                                     #Proposition
        ;
expr : expr '*' expr #Mult
     | expr '/' expr #Div
     | expr '+' expr #Plus
     | expr '-' expr #Minus
     | '-' expr      #Inverse
     | '(' expr ')'  #ParensExpr
     | ID            #PropVar
     | WEIGHT        #Weight
     ;

NEWLINE : [ \t\r\n]+ -> skip;
FID     : [A-Z][a-zA-Z0-9"'\-_*!?]*;
ID : [a-z][a-zA-Z0-9"'\-_*?]*;
WEIGHT: [0-9]+;
MULTILINECOMMENT : '\\*' .*? '*\\' -> skip ;
SINGLELINECOMMENT : '#' .*? '\n' -> skip ;

