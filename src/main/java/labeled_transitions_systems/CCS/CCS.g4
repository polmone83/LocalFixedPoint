grammar CCS;
system:	(pDecl | setDecl)* EOF ;
pDecl: ('agent')? ID '=' process ';';
setDecl: 'set' ID '=' channelSet ';';
process: '0' #Nil
        | '\'' CHANNEL '.' process      #OutputAction
        | CHANNEL '.' process           #InputAction
        | 'tau' '.' process             #SyncAction
        | process '+' process           #Choice
        | process '|' process           #Parallel
        | process '\\' ID               #RestricSet
        | process '\\' channelSet       #RestricExplicit
        | process '[' renaming (','renaming)* ']'   #Rename
        | '(' process ')'               #Parens
        | ID                            #PName
        ;
channelSet: '{' CHANNEL (',' CHANNEL)* '}';
renaming : to=CHANNEL '/' from=CHANNEL;

NEWLINE : [ \t\r\n]+ -> skip;
ID      : [A-Z][a-zA-Z0-9"'\-*!?_]*;
CHANNEL : [a-z][a-zA-Z0-9"'\-*!?_]*;
MULTILINECOMMENT : '\\*' .*? '*\\' -> skip ;
SINGLELINECOMMENT : '*' .*? '\n' -> skip ;

