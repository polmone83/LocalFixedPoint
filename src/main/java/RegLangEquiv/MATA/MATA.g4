grammar MATA;
automaton:	preamle (END stmt)* EOF ;

preamle: '@RegLangEquiv.NFA-bits';

stmt : transition | keyvalue;

keyvalue: '%' key;

key: 'Initial' states   #Initital
    | 'Final' states    #Final
    | 'States-auto'     #StatesAuto
    | 'Alphabet-utf'    #Alphabet_utf
    | 'Alphabet-auto'   #Alphabet_auto
    ;

transition: from=states expr to=states;

states: STATE*;

expr:  '!' expr                    #neg
	|   left=expr '&' right=expr   #and
    |	left=expr '|' right=expr   #or
    |   SYMBOL                     #var
    |	'(' expr ')'               #parens
    ;

END: '\n';
NEWLINE : [ \t\r\n]+ -> skip;
//ID   : [a-zA-Z][a-zA-Z0-9"'*!?]*;
STATE: [q][a-zA-Z0-9]*;
SYMBOL: [a][a-zA-Z0-9]*;
SINGLELINECOMMENT : '#' .*? '\n' -> skip ;

