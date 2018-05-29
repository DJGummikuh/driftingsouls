// $ANTLR 3.0.1 /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g 2018-05-28 21:31:26

        package net.driftingsouls.ds2.server.scripting.roles.parser;


public class RoleLexer extends Lexer {
    public static final int Digit=11;
    public static final int EOL=7;
    public static final int IS=5;
    public static final int Text=9;
    public static final int ROLENAME=4;
    public static final int Letter=12;
    public static final int SPACE=15;
    public static final int Numeric=13;
    public static final int Identifier=6;
    public static final int Number=8;
    public static final int EOF=-1;
    public static final int Location=10;
    public static final int EscapeChar=14;
    public static final int Tokens=16;
    public RoleLexer() {;} 
    public RoleLexer(CharStream input) {
        super(input);
    }
    public String getGrammarFileName() { return "/home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g"; }

    // $ANTLR start ROLENAME
    public final void mROLENAME() throws RecognitionException {
        try {
            int _type = ROLENAME;
            // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:6:10: ( 'role' )
            // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:6:12: 'role'
            {
            match("role"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ROLENAME

    // $ANTLR start Location
    public final void mLocation() throws RecognitionException {
        try {
            int _type = Location;
            // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:49:2: ( ( Digit )+ ':' ( Digit )+ '/' ( Digit )+ )
            // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:49:4: ( Digit )+ ':' ( Digit )+ '/' ( Digit )+
            {
            // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:49:4: ( Digit )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='0' && LA1_0<='9')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:49:4: Digit
            	    {
            	    mDigit(); 

            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);

            match(':'); 
            // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:49:15: ( Digit )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:49:15: Digit
            	    {
            	    mDigit(); 

            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);

            match('/'); 
            // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:49:26: ( Digit )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='0' && LA3_0<='9')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:49:26: Digit
            	    {
            	    mDigit(); 

            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end Location

    // $ANTLR start Identifier
    public final void mIdentifier() throws RecognitionException {
        try {
            int _type = Identifier;
            // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:53:9: ( Letter ( Letter | Digit )* )
            // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:53:17: Letter ( Letter | Digit )*
            {
            mLetter(); 
            // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:53:24: ( Letter | Digit )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='0' && LA4_0<='9')||(LA4_0>='A' && LA4_0<='Z')||(LA4_0>='a' && LA4_0<='z')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end Identifier

    // $ANTLR start Number
    public final void mNumber() throws RecognitionException {
        try {
            int _type = Number;
            // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:56:9: ( '1' .. '9' ( Digit )* )
            // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:56:17: '1' .. '9' ( Digit )*
            {
            matchRange('1','9'); 
            // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:56:26: ( Digit )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>='0' && LA5_0<='9')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:56:26: Digit
            	    {
            	    mDigit(); 

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end Number

    // $ANTLR start Numeric
    public final void mNumeric() throws RecognitionException {
        try {
            int _type = Numeric;
            // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:59:9: ( ( Digit )* )
            // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:59:17: ( Digit )*
            {
            // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:59:17: ( Digit )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( ((LA6_0>='0' && LA6_0<='9')) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:59:17: Digit
            	    {
            	    mDigit(); 

            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end Numeric

    // $ANTLR start Text
    public final void mText() throws RecognitionException {
        try {
            int _type = Text;
            // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:62:9: ( '\"' ( EscapeChar | ~ ( '\"' | '\\\\' ) )* '\"' )
            // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:62:17: '\"' ( EscapeChar | ~ ( '\"' | '\\\\' ) )* '\"'
            {
            match('\"'); 
            // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:62:21: ( EscapeChar | ~ ( '\"' | '\\\\' ) )*
            loop7:
            do {
                int alt7=3;
                int LA7_0 = input.LA(1);

                if ( (LA7_0=='\\') ) {
                    alt7=1;
                }
                else if ( ((LA7_0>='\u0000' && LA7_0<='!')||(LA7_0>='#' && LA7_0<='[')||(LA7_0>=']' && LA7_0<='\uFFFE')) ) {
                    alt7=2;
                }


                switch (alt7) {
            	case 1 :
            	    // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:62:22: EscapeChar
            	    {
            	    mEscapeChar(); 

            	    }
            	    break;
            	case 2 :
            	    // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:62:35: ~ ( '\"' | '\\\\' )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFE') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            match('\"'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end Text

    // $ANTLR start EscapeChar
    public final void mEscapeChar() throws RecognitionException {
        try {
            // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:67:9: ( '\\\\' ( '\"' | '\\\\' ) )
            // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:67:17: '\\\\' ( '\"' | '\\\\' )
            {
            match('\\'); 
            if ( input.LA(1)=='\"'||input.LA(1)=='\\' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }


            }

        }
        finally {
        }
    }
    // $ANTLR end EscapeChar

    // $ANTLR start Letter
    public final void mLetter() throws RecognitionException {
        try {
            // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:71:9: ( 'a' .. 'z' | 'A' .. 'Z' )
            // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }


            }

        }
        finally {
        }
    }
    // $ANTLR end Letter

    // $ANTLR start Digit
    public final void mDigit() throws RecognitionException {
        try {
            // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:75:9: ( '0' .. '9' )
            // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:75:17: '0' .. '9'
            {
            matchRange('0','9'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end Digit

    // $ANTLR start EOL
    public final void mEOL() throws RecognitionException {
        try {
            int _type = EOL;
            // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:78:9: ( ( ( '\\r' )? '\\n' )+ )
            // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:78:17: ( ( '\\r' )? '\\n' )+
            {
            // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:78:17: ( ( '\\r' )? '\\n' )+
            int cnt9=0;
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0=='\n'||LA9_0=='\r') ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:78:18: ( '\\r' )? '\\n'
            	    {
            	    // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:78:18: ( '\\r' )?
            	    int alt8=2;
            	    int LA8_0 = input.LA(1);

            	    if ( (LA8_0=='\r') ) {
            	        alt8=1;
            	    }
            	    switch (alt8) {
            	        case 1 :
            	            // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:78:18: '\\r'
            	            {
            	            match('\r'); 

            	            }
            	            break;

            	    }

            	    match('\n'); 

            	    }
            	    break;

            	default :
            	    if ( cnt9 >= 1 ) break loop9;
                        EarlyExitException eee =
                            new EarlyExitException(9, input);
                        throw eee;
                }
                cnt9++;
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end EOL

    // $ANTLR start SPACE
    public final void mSPACE() throws RecognitionException {
        try {
            // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:82:9: ( ' ' | '\\t' )
            // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:
            {
            if ( input.LA(1)=='\t'||input.LA(1)==' ' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }


            }

        }
        finally {
        }
    }
    // $ANTLR end SPACE

    // $ANTLR start IS
    public final void mIS() throws RecognitionException {
        try {
            int _type = IS;
            // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:85:9: ( ( ':' ( SPACE )* ) )
            // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:85:17: ( ':' ( SPACE )* )
            {
            // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:85:17: ( ':' ( SPACE )* )
            // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:85:18: ':' ( SPACE )*
            {
            match(':'); 
            // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:85:22: ( SPACE )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0=='\t'||LA10_0==' ') ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:85:22: SPACE
            	    {
            	    mSPACE(); 

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);


            }


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end IS

    public void mTokens() throws RecognitionException {
        // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:1:8: ( ROLENAME | Location | Identifier | Number | Numeric | Text | EOL | IS )
        int alt11=8;
        alt11 = dfa11.predict(input);
        switch (alt11) {
            case 1 :
                // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:1:10: ROLENAME
                {
                mROLENAME(); 

                }
                break;
            case 2 :
                // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:1:19: Location
                {
                mLocation(); 

                }
                break;
            case 3 :
                // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:1:28: Identifier
                {
                mIdentifier(); 

                }
                break;
            case 4 :
                // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:1:39: Number
                {
                mNumber(); 

                }
                break;
            case 5 :
                // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:1:46: Numeric
                {
                mNumeric(); 

                }
                break;
            case 6 :
                // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:1:54: Text
                {
                mText(); 

                }
                break;
            case 7 :
                // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:1:59: EOL
                {
                mEOL(); 

                }
                break;
            case 8 :
                // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:1:63: IS
                {
                mIS(); 

                }
                break;

        }

    }


    protected DFA11 dfa11 = new DFA11(this);
    static final String DFA11_eotS =
        "\1\5\1\3\1\13\1\uffff\1\5\4\uffff\1\3\1\13\2\uffff\1\3\1\17\1\uffff";
    static final String DFA11_eofS =
        "\20\uffff";
    static final String DFA11_minS =
        "\1\12\1\157\1\60\1\uffff\1\60\4\uffff\1\154\1\60\2\uffff\1\145\1"+
        "\60\1\uffff";
    static final String DFA11_maxS =
        "\1\172\1\157\1\72\1\uffff\1\72\4\uffff\1\154\1\72\2\uffff\1\145"+
        "\1\172\1\uffff";
    static final String DFA11_acceptS =
        "\3\uffff\1\3\1\uffff\1\5\1\6\1\7\1\10\2\uffff\1\4\1\2\2\uffff\1"+
        "\1";
    static final String DFA11_specialS =
        "\20\uffff}>";
    static final String[] DFA11_transitionS = {
            "\1\7\2\uffff\1\7\24\uffff\1\6\15\uffff\1\4\11\2\1\10\6\uffff"+
            "\32\3\6\uffff\21\3\1\1\10\3",
            "\1\11",
            "\12\12\1\14",
            "",
            "\12\4\1\14",
            "",
            "",
            "",
            "",
            "\1\15",
            "\12\12\1\14",
            "",
            "",
            "\1\16",
            "\12\3\7\uffff\32\3\6\uffff\32\3",
            ""
    };

    static final short[] DFA11_eot = DFA.unpackEncodedString(DFA11_eotS);
    static final short[] DFA11_eof = DFA.unpackEncodedString(DFA11_eofS);
    static final char[] DFA11_min = DFA.unpackEncodedStringToUnsignedChars(DFA11_minS);
    static final char[] DFA11_max = DFA.unpackEncodedStringToUnsignedChars(DFA11_maxS);
    static final short[] DFA11_accept = DFA.unpackEncodedString(DFA11_acceptS);
    static final short[] DFA11_special = DFA.unpackEncodedString(DFA11_specialS);
    static final short[][] DFA11_transition;

    static {
        int numStates = DFA11_transitionS.length;
        DFA11_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA11_transition[i] = DFA.unpackEncodedString(DFA11_transitionS[i]);
        }
    }

    class DFA11 extends DFA {

        public DFA11(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 11;
            this.eot = DFA11_eot;
            this.eof = DFA11_eof;
            this.min = DFA11_min;
            this.max = DFA11_max;
            this.accept = DFA11_accept;
            this.special = DFA11_special;
            this.transition = DFA11_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( ROLENAME | Location | Identifier | Number | Numeric | Text | EOL | IS );";
        }
    }
 

}