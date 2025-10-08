grammar ExperimentBatch;
start:	path output (experiment)* EOF ;

path: 'Path' ID ';';
output: 'Output' ID ';';
experiment: ID* ';';

NEWLINE : [ \t\r\n]+ -> skip;
ID   : [a-zA-Z/_][a-zA-Z0-9"'*!?/._-]*;
SINGLELINECOMMENT : '#' .*? '\n' -> skip ;
MULTILINECOMMENT : '/#' .*? '#/' -> skip ;

