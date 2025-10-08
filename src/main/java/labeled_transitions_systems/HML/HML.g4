grammar HML;
decls:	(formulaDecl | setDecl)* EOF ;

formulaDecl: ID 'min=' formula ';'          #MinDecl
            | ID 'max=' formula ';'         #MaxDecl
            ;
setDecl: 'set' ID '=' '{' actions '}' ';';

formula:  ('tt' | 'T')                      #True
        | ('ff' | 'F')                      #False
        | '<' actions '>' formula           #StrongExists
        | '<<' actions '>>' formula         #WeakExists
        | '[' actions ']' formula           #StrongForAll
        | '[[' actions ']]' formula         #WeakForAll
        | left=formula 'and' right=formula  #And
        | left=formula 'or' right=formula   #Or
        | '(' formula ')'                   #Parens
        | ID                                #FormulaName
        ;

actions: action (',' action)*               #ActionSequence
        | '-'                               #Wildcard
        | ID                                #SetName
        ;
action: '\'' CHANNEL                        #OutputAction
        | 'tau'                             #Sync
        | CHANNEL                           #InputAction
        ;

NEWLINE : [ \t\r\n]+ -> skip;
ID      : [A-Z][a-zA-Z0-9"'\-*!?]*;
CHANNEL : [a-z][a-zA-Z0-9"'\-*!?]*;
MULTILINECOMMENT : '\\*' .*? '*\\' -> skip ;
SINGLELINECOMMENT : '*' .*? '\n' -> skip ;

