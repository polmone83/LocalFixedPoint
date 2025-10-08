grammar groundSystem;
system:	(equation ';')* EOF ;
equation: predicate '=' expr;

predicate : ID '(' ( VAR (',' VAR)* )? ')';

expr:   left=expr '&' right=expr            #and
    |   left=expr '|' right=expr            #or
    |	left=expr '<=>' right=expr          #iff
    |	'ff'                                #false
    |   'tt'                                #true
    |   predicate                           #call
    |   '<' expr ',' '[' ( VAR (',' VAR)* )? ']' '>' #restriction
    |   VAR                                 #var
    |	'(' expr ')'                        #parens
    ;

VAR     : [A-Z_][a-zA-Z0-9_]*;
ID      : [a-z_][a-zA-Z0-9_]*;
NEWLINE : [ \t\r\n]+ -> skip;
COMMENT : '<!--' .*? '-->' -> skip ;
