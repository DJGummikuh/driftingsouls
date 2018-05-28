// $ANTLR 3.0.1 /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g 2018-05-28 21:31:26

        package net.driftingsouls.ds2.server.scripting.roles.parser;
        import org.antlr.runtime.Parser;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;


import org.antlr.runtime.tree.*;

public class RoleParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ROLENAME", "IS", "Identifier", "EOL", "Number", "Text", "Location", "Digit", "Letter", "Numeric", "EscapeChar", "SPACE"
    };
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

        public RoleParser(TokenStream input) {
            super(input);
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "/home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g"; }


            protected void mismatch(IntStream input, int ttype, BitSet follow) throws RecognitionException {
                    throw new MismatchedTokenException(ttype, input);
            }
            
            public void recoverFromMismatchedSet(IntStream input, RecognitionException e, BitSet follow) throws RecognitionException {
                    throw e;
            }


    public static class roleDefinition_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start roleDefinition
    // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:32:1: roleDefinition : ROLENAME IS Identifier ( EOL attributeList )? EOF ;
    public final roleDefinition_return roleDefinition() throws RecognitionException {
        roleDefinition_return retval = new roleDefinition_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ROLENAME1=null;
        Token IS2=null;
        Token Identifier3=null;
        Token EOL4=null;
        Token EOF6=null;
        attributeList_return attributeList5 = null;


        Object ROLENAME1_tree=null;
        Object IS2_tree=null;
        Object Identifier3_tree=null;
        Object EOL4_tree=null;
        Object EOF6_tree=null;

        try {
            // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:33:9: ( ROLENAME IS Identifier ( EOL attributeList )? EOF )
            // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:33:17: ROLENAME IS Identifier ( EOL attributeList )? EOF
            {
            root_0 = (Object)adaptor.nil();

            ROLENAME1=(Token)input.LT(1);
            match(input,ROLENAME,FOLLOW_ROLENAME_in_roleDefinition80); 
            ROLENAME1_tree = (Object)adaptor.create(ROLENAME1);
            adaptor.addChild(root_0, ROLENAME1_tree);

            IS2=(Token)input.LT(1);
            match(input,IS,FOLLOW_IS_in_roleDefinition82); 
            IS2_tree = (Object)adaptor.create(IS2);
            adaptor.addChild(root_0, IS2_tree);

            Identifier3=(Token)input.LT(1);
            match(input,Identifier,FOLLOW_Identifier_in_roleDefinition84); 
            Identifier3_tree = (Object)adaptor.create(Identifier3);
            adaptor.addChild(root_0, Identifier3_tree);

            // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:33:40: ( EOL attributeList )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==EOL) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:33:41: EOL attributeList
                    {
                    EOL4=(Token)input.LT(1);
                    match(input,EOL,FOLLOW_EOL_in_roleDefinition87); 
                    EOL4_tree = (Object)adaptor.create(EOL4);
                    adaptor.addChild(root_0, EOL4_tree);

                    pushFollow(FOLLOW_attributeList_in_roleDefinition89);
                    attributeList5=attributeList();
                    _fsp--;

                    adaptor.addChild(root_0, attributeList5.getTree());

                    }
                    break;

            }

            EOF6=(Token)input.LT(1);
            match(input,EOF,FOLLOW_EOF_in_roleDefinition93); 
            EOF6_tree = (Object)adaptor.create(EOF6);
            adaptor.addChild(root_0, EOF6_tree);


            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

                catch (RecognitionException e) {
                        throw e;
                }
        finally {
        }
        return retval;
    }
    // $ANTLR end roleDefinition

    public static class attributeList_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start attributeList
    // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:36:1: attributeList : attribute ( EOL attribute )* ;
    public final attributeList_return attributeList() throws RecognitionException {
        attributeList_return retval = new attributeList_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EOL8=null;
        attribute_return attribute7 = null;

        attribute_return attribute9 = null;


        Object EOL8_tree=null;

        try {
            // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:37:9: ( attribute ( EOL attribute )* )
            // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:37:17: attribute ( EOL attribute )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_attribute_in_attributeList124);
            attribute7=attribute();
            _fsp--;

            adaptor.addChild(root_0, attribute7.getTree());
            // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:37:27: ( EOL attribute )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==EOL) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:37:28: EOL attribute
            	    {
            	    EOL8=(Token)input.LT(1);
            	    match(input,EOL,FOLLOW_EOL_in_attributeList127); 
            	    EOL8_tree = (Object)adaptor.create(EOL8);
            	    adaptor.addChild(root_0, EOL8_tree);

            	    pushFollow(FOLLOW_attribute_in_attributeList129);
            	    attribute9=attribute();
            	    _fsp--;

            	    adaptor.addChild(root_0, attribute9.getTree());

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

                catch (RecognitionException e) {
                        throw e;
                }
        finally {
        }
        return retval;
    }
    // $ANTLR end attributeList

    public static class attribute_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start attribute
    // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:40:1: attribute : Identifier IS value ;
    public final attribute_return attribute() throws RecognitionException {
        attribute_return retval = new attribute_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Identifier10=null;
        Token IS11=null;
        value_return value12 = null;


        Object Identifier10_tree=null;
        Object IS11_tree=null;

        try {
            // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:41:9: ( Identifier IS value )
            // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:41:17: Identifier IS value
            {
            root_0 = (Object)adaptor.nil();

            Identifier10=(Token)input.LT(1);
            match(input,Identifier,FOLLOW_Identifier_in_attribute162); 
            Identifier10_tree = (Object)adaptor.create(Identifier10);
            adaptor.addChild(root_0, Identifier10_tree);

            IS11=(Token)input.LT(1);
            match(input,IS,FOLLOW_IS_in_attribute164); 
            IS11_tree = (Object)adaptor.create(IS11);
            adaptor.addChild(root_0, IS11_tree);

            pushFollow(FOLLOW_value_in_attribute166);
            value12=value();
            _fsp--;

            adaptor.addChild(root_0, value12.getTree());

            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

                catch (RecognitionException e) {
                        throw e;
                }
        finally {
        }
        return retval;
    }
    // $ANTLR end attribute

    public static class value_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start value
    // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:44:1: value : ( Number | Text | Location ) ;
    public final value_return value() throws RecognitionException {
        value_return retval = new value_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set13=null;

        Object set13_tree=null;

        try {
            // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:45:2: ( ( Number | Text | Location ) )
            // /home/jfrank/git/driftingsouls/src/net/driftingsouls/ds2/server/scripting/roles/parser/Role.g:45:4: ( Number | Text | Location )
            {
            root_0 = (Object)adaptor.nil();

            set13=(Token)input.LT(1);
            if ( (input.LA(1)>=Number && input.LA(1)<=Location) ) {
                input.consume();
                adaptor.addChild(root_0, adaptor.create(set13));
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_value184);    throw mse;
            }


            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

                catch (RecognitionException e) {
                        throw e;
                }
        finally {
        }
        return retval;
    }
    // $ANTLR end value


 

    public static final BitSet FOLLOW_ROLENAME_in_roleDefinition80 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IS_in_roleDefinition82 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_Identifier_in_roleDefinition84 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_EOL_in_roleDefinition87 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_attributeList_in_roleDefinition89 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_roleDefinition93 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attribute_in_attributeList124 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_EOL_in_attributeList127 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_attribute_in_attributeList129 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_Identifier_in_attribute162 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IS_in_attribute164 = new BitSet(new long[]{0x0000000000000700L});
    public static final BitSet FOLLOW_value_in_attribute166 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_value184 = new BitSet(new long[]{0x0000000000000002L});

}