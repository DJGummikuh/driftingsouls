lexer grammar Role;
@header {
        package net.driftingsouls.ds2.net.driftingsouls.ds2.interfaces.server.scripting.roles.parser;
}

ROLENAME : 'role' ;

// $ANTLR src "/home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/net.driftingsouls.ds2.interfaces.server/scripting/roles/parser/Role.g" 48
Location
	:	Digit+ ':' Digit+ '/' Digit+
	;

// $ANTLR src "/home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/net.driftingsouls.ds2.interfaces.server/scripting/roles/parser/Role.g" 52
Identifier
        :       Letter (Letter | Digit)*
        ;

// $ANTLR src "/home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/net.driftingsouls.ds2.interfaces.server/scripting/roles/parser/Role.g" 56
Number  :       '1'..'9' Digit*
        ;

// $ANTLR src "/home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/net.driftingsouls.ds2.interfaces.server/scripting/roles/parser/Role.g" 59
Numeric :       Digit*
        ;

// $ANTLR src "/home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/net.driftingsouls.ds2.interfaces.server/scripting/roles/parser/Role.g" 62
Text    :       '"' (EscapeChar | ~('"' | '\\'))* '"'
        ;

// $ANTLR src "/home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/net.driftingsouls.ds2.interfaces.server/scripting/roles/parser/Role.g" 65
fragment
EscapeChar
        :       '\\' ('"' | '\\')
        ;

// $ANTLR src "/home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/net.driftingsouls.ds2.interfaces.server/scripting/roles/parser/Role.g" 70
fragment
Letter  :       'a'..'z' | 'A'..'Z'
        ;

// $ANTLR src "/home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/net.driftingsouls.ds2.interfaces.server/scripting/roles/parser/Role.g" 74
fragment
Digit   :       '0'..'9'
        ;

// $ANTLR src "/home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/net.driftingsouls.ds2.interfaces.server/scripting/roles/parser/Role.g" 78
EOL     :       ('\r'? '\n')+
        ;

// $ANTLR src "/home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/net.driftingsouls.ds2.interfaces.server/scripting/roles/parser/Role.g" 81
fragment
SPACE   :       ' ' | '\t'
        ;

// $ANTLR src "/home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/net.driftingsouls.ds2.interfaces.server/scripting/roles/parser/Role.g" 85
IS      :       (':' SPACE*)
        ;