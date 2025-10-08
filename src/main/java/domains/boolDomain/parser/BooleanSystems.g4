grammar BooleanSystems;
system:	(equation ';')* EOF ;
equation: ID '=' expr;
expr:	left=expr '&' right=expr   #and
    |	left=expr '|' right=expr   #or
    |	'ff'            #false
    |   'tt'            #true
    |   ID              #var
    |	'(' expr ')'    #parens
    ;
NEWLINE : [ \t\r\n]+ -> skip;
ID      : [a-zA-Z][a-zA-Z0-9]*;
COMMENT : '<!--' .*? '-->' -> skip ;
