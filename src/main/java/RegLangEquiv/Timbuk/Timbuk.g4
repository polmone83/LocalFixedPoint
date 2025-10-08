grammar Timbuk;
/*  <file>            : 'Ops' <label_list> <automaton>

  <label_list>      : <label_decl> <label_decl> ... // a list of label declarations

  <label_decl>      : string ':' int // a symbol declaration (the name and the arity)

  <automaton>       : 'Automaton' string 'States' <state_list> 'Final States' <state_list> 'Transitions' <transition_list>

  <state_list>      : <state> <state> ... // a list of states

  <state>           : string // the name of a state

  <transition_list> : <transition> <transition> ... // a list of transitions

  <transition>      : <label> '(' <state> ',' <state> ',' ... ')' '->' <state> // a transition

  <label>           : string // the name of a label
*/

ops:	'Ops' labeldecl* automaton EOF ;
labeldecl : ID ':' INT;
automaton : 'Automaton' name=ID states finalstates transitions;
states : 'States' state=ID*;
finalstates : 'Final' 'States' ID*;
transitions : 'Transitions' transition*;
transition : label=ID '(' ID? (',' ID)* ')' '->' state=ID;

NEWLINE : [ \t\r\n]+ -> skip;
ID   : [a-zA-Z][a-zA-Z0-9"'*!?]*;
INT : [0-9][0-9]*;
MULTILINECOMMENT : '/*' .*? '*/' -> skip ;
SINGLELINECOMMENT : '//' .*? '\n' -> skip ;

