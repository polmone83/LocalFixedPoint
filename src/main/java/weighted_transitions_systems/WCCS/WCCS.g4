grammar WCCS;
system:	(pDecl | setDecl)* EOF ;
pDecl: PID ':=' process ';';
setDecl: 'set' PID '=' channelSet ';';
process: process '\\' PID                           #RestricSet
        | process '\\' channelSet                   #RestricExplicit
        | process '[' renaming (','renaming)* ']'   #Rename
        | process '[' labelRenaming (','labelRenaming)* ']'   #LabelRename
        | ID ':' process                            #AtomicLabel
        | '<' action (',' WEIGHT)?'>' '.' process   #ActionPrefix
        | process '|' process                       #Parallel
        | process '+' process                       #Choice
        | '(' process ')'                           #Parens
        | PID                                       #PName
        | WEIGHT                                    #Nil
        ;
action: ID          #InputAction
        | ID '!'    #OutputAction
        | 'tau'     #SyncAction
        ;
channelSet: '{' ID (',' ID)* '}';
renaming : from=ID '->' to=ID;
labelRenaming: from=ID '=>' to=ID;

NEWLINE : [ \t\r\n]+ -> skip;
PID      : [A-Z][a-zA-Z0-9"'\-_*!?]*;
ID : [a-z][a-zA-Z0-9"'\-_*?]*;
WEIGHT: [0-9]+;
MULTILINECOMMENT : '\\*' .*? '*\\' -> skip ;
SINGLELINECOMMENT : '#' .*? '\n' -> skip ;

