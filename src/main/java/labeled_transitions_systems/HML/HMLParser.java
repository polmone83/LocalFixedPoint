package labeled_transitions_systems.HML;
// Generated from HML.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class HMLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, NEWLINE=28, ID=29, CHANNEL=30, MULTILINECOMMENT=31, 
		SINGLELINECOMMENT=32;
	public static final int
		RULE_decls = 0, RULE_formulaDecl = 1, RULE_setDecl = 2, RULE_formula = 3, 
		RULE_actions = 4, RULE_action = 5;
	private static String[] makeRuleNames() {
		return new String[] {
			"decls", "formulaDecl", "setDecl", "formula", "actions", "action"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'min='", "';'", "'max='", "'set'", "'='", "'{'", "'}'", "'tt'", 
			"'T'", "'ff'", "'F'", "'<'", "'>'", "'<<'", "'>>'", "'['", "']'", "'[['", 
			"']]'", "'and'", "'or'", "'('", "')'", "','", "'-'", "'''", "'tau'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, "NEWLINE", "ID", "CHANNEL", "MULTILINECOMMENT", 
			"SINGLELINECOMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "HML.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public HMLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclsContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(HMLParser.EOF, 0); }
		public List<FormulaDeclContext> formulaDecl() {
			return getRuleContexts(FormulaDeclContext.class);
		}
		public FormulaDeclContext formulaDecl(int i) {
			return getRuleContext(FormulaDeclContext.class,i);
		}
		public List<SetDeclContext> setDecl() {
			return getRuleContexts(SetDeclContext.class);
		}
		public SetDeclContext setDecl(int i) {
			return getRuleContext(SetDeclContext.class,i);
		}
		public DeclsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decls; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HMLVisitor ) return ((HMLVisitor<? extends T>)visitor).visitDecls(this);
			else return visitor.visitChildren(this);
		}

		@Override
		public String toString() {
			PrettyPrint pp = new PrettyPrint();
			return pp.visit(this).toString();
		}
	}

	public final DeclsContext decls() throws RecognitionException {
		DeclsContext _localctx = new DeclsContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_decls);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(16);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3 || _la==ID) {
				{
				setState(14);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ID:
					{
					setState(12);
					formulaDecl();
					}
					break;
				case T__3:
					{
					setState(13);
					setDecl();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(18);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(19);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FormulaDeclContext extends ParserRuleContext {
		public FormulaDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formulaDecl; }
	 
		public FormulaDeclContext() { }
		public void copyFrom(FormulaDeclContext ctx) {
			super.copyFrom(ctx);
		}

		@Override
		public String toString() {
			PrettyPrint pp = new PrettyPrint();
			return pp.visit(this).toString();
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MinDeclContext extends FormulaDeclContext {
		public TerminalNode ID() { return getToken(HMLParser.ID, 0); }
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public MinDeclContext(FormulaDeclContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HMLVisitor ) return ((HMLVisitor<? extends T>)visitor).visitMinDecl(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MaxDeclContext extends FormulaDeclContext {
		public TerminalNode ID() { return getToken(HMLParser.ID, 0); }
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public MaxDeclContext(FormulaDeclContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HMLVisitor ) return ((HMLVisitor<? extends T>)visitor).visitMaxDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormulaDeclContext formulaDecl() throws RecognitionException {
		FormulaDeclContext _localctx = new FormulaDeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_formulaDecl);
		try {
			setState(31);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				_localctx = new MinDeclContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(21);
				match(ID);
				setState(22);
				match(T__0);
				setState(23);
				formula(0);
				setState(24);
				match(T__1);
				}
				break;
			case 2:
				_localctx = new MaxDeclContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(26);
				match(ID);
				setState(27);
				match(T__2);
				setState(28);
				formula(0);
				setState(29);
				match(T__1);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SetDeclContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(HMLParser.ID, 0); }
		public ActionsContext actions() {
			return getRuleContext(ActionsContext.class,0);
		}
		public SetDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HMLVisitor ) return ((HMLVisitor<? extends T>)visitor).visitSetDecl(this);
			else return visitor.visitChildren(this);
		}

		@Override
		public String toString() {
			PrettyPrint pp = new PrettyPrint();
			return pp.visit(this).toString();
		}
	}

	public final SetDeclContext setDecl() throws RecognitionException {
		SetDeclContext _localctx = new SetDeclContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_setDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33);
			match(T__3);
			setState(34);
			match(ID);
			setState(35);
			match(T__4);
			setState(36);
			match(T__5);
			setState(37);
			actions();
			setState(38);
			match(T__6);
			setState(39);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FormulaContext extends ParserRuleContext {
		public FormulaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formula; }
	 
		public FormulaContext() { }
		public void copyFrom(FormulaContext ctx) {
			super.copyFrom(ctx);
		}

		@Override
		public String toString() {
			PrettyPrint pp = new PrettyPrint();
			return pp.visit(this).toString();
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class OrContext extends FormulaContext {
		public FormulaContext left;
		public FormulaContext right;
		public List<FormulaContext> formula() {
			return getRuleContexts(FormulaContext.class);
		}
		public FormulaContext formula(int i) {
			return getRuleContext(FormulaContext.class,i);
		}
		public OrContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HMLVisitor ) return ((HMLVisitor<? extends T>)visitor).visitOr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class WeakForAllContext extends FormulaContext {
		public ActionsContext actions() {
			return getRuleContext(ActionsContext.class,0);
		}
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public WeakForAllContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HMLVisitor ) return ((HMLVisitor<? extends T>)visitor).visitWeakForAll(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParensContext extends FormulaContext {
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public ParensContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HMLVisitor ) return ((HMLVisitor<? extends T>)visitor).visitParens(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AndContext extends FormulaContext {
		public FormulaContext left;
		public FormulaContext right;
		public List<FormulaContext> formula() {
			return getRuleContexts(FormulaContext.class);
		}
		public FormulaContext formula(int i) {
			return getRuleContext(FormulaContext.class,i);
		}
		public AndContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HMLVisitor ) return ((HMLVisitor<? extends T>)visitor).visitAnd(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class WeakExistsContext extends FormulaContext {
		public ActionsContext actions() {
			return getRuleContext(ActionsContext.class,0);
		}
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public WeakExistsContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HMLVisitor ) return ((HMLVisitor<? extends T>)visitor).visitWeakExists(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TrueContext extends FormulaContext {
		public TrueContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HMLVisitor ) return ((HMLVisitor<? extends T>)visitor).visitTrue(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FalseContext extends FormulaContext {
		public FalseContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HMLVisitor ) return ((HMLVisitor<? extends T>)visitor).visitFalse(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StrongExistsContext extends FormulaContext {
		public ActionsContext actions() {
			return getRuleContext(ActionsContext.class,0);
		}
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public StrongExistsContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HMLVisitor ) return ((HMLVisitor<? extends T>)visitor).visitStrongExists(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FormulaNameContext extends FormulaContext {
		public TerminalNode ID() { return getToken(HMLParser.ID, 0); }
		public FormulaNameContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HMLVisitor ) return ((HMLVisitor<? extends T>)visitor).visitFormulaName(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StrongForAllContext extends FormulaContext {
		public ActionsContext actions() {
			return getRuleContext(ActionsContext.class,0);
		}
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public StrongForAllContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HMLVisitor ) return ((HMLVisitor<? extends T>)visitor).visitStrongForAll(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormulaContext formula() throws RecognitionException {
		return formula(0);
	}

	private FormulaContext formula(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		FormulaContext _localctx = new FormulaContext(_ctx, _parentState);
		FormulaContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_formula, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__7:
			case T__8:
				{
				_localctx = new TrueContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(42);
				_la = _input.LA(1);
				if ( !(_la==T__7 || _la==T__8) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case T__9:
			case T__10:
				{
				_localctx = new FalseContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(43);
				_la = _input.LA(1);
				if ( !(_la==T__9 || _la==T__10) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case T__11:
				{
				_localctx = new StrongExistsContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(44);
				match(T__11);
				setState(45);
				actions();
				setState(46);
				match(T__12);
				setState(47);
				formula(8);
				}
				break;
			case T__13:
				{
				_localctx = new WeakExistsContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(49);
				match(T__13);
				setState(50);
				actions();
				setState(51);
				match(T__14);
				setState(52);
				formula(7);
				}
				break;
			case T__15:
				{
				_localctx = new StrongForAllContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(54);
				match(T__15);
				setState(55);
				actions();
				setState(56);
				match(T__16);
				setState(57);
				formula(6);
				}
				break;
			case T__17:
				{
				_localctx = new WeakForAllContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(59);
				match(T__17);
				setState(60);
				actions();
				setState(61);
				match(T__18);
				setState(62);
				formula(5);
				}
				break;
			case T__21:
				{
				_localctx = new ParensContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(64);
				match(T__21);
				setState(65);
				formula(0);
				setState(66);
				match(T__22);
				}
				break;
			case ID:
				{
				_localctx = new FormulaNameContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(68);
				match(ID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(79);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(77);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
					case 1:
						{
						_localctx = new AndContext(new FormulaContext(_parentctx, _parentState));
						((AndContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_formula);
						setState(71);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(72);
						match(T__19);
						setState(73);
						((AndContext)_localctx).right = formula(5);
						}
						break;
					case 2:
						{
						_localctx = new OrContext(new FormulaContext(_parentctx, _parentState));
						((OrContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_formula);
						setState(74);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(75);
						match(T__20);
						setState(76);
						((OrContext)_localctx).right = formula(4);
						}
						break;
					}
					} 
				}
				setState(81);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ActionsContext extends ParserRuleContext {
		public ActionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_actions; }
	 
		public ActionsContext() { }
		public void copyFrom(ActionsContext ctx) {
			super.copyFrom(ctx);
		}

		@Override
		public String toString() {
			PrettyPrint pp = new PrettyPrint();
			return pp.visit(this).toString();
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ActionSequenceContext extends ActionsContext {
		public List<ActionContext> action() {
			return getRuleContexts(ActionContext.class);
		}
		public ActionContext action(int i) {
			return getRuleContext(ActionContext.class,i);
		}
		public ActionSequenceContext(ActionsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HMLVisitor ) return ((HMLVisitor<? extends T>)visitor).visitActionSequence(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class WildcardContext extends ActionsContext {
		public WildcardContext(ActionsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HMLVisitor ) return ((HMLVisitor<? extends T>)visitor).visitWildcard(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SetNameContext extends ActionsContext {
		public TerminalNode ID() { return getToken(HMLParser.ID, 0); }
		public SetNameContext(ActionsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HMLVisitor ) return ((HMLVisitor<? extends T>)visitor).visitSetName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ActionsContext actions() throws RecognitionException {
		ActionsContext _localctx = new ActionsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_actions);
		int _la;
		try {
			setState(92);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__25:
			case T__26:
			case CHANNEL:
				_localctx = new ActionSequenceContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(82);
				action();
				setState(87);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__23) {
					{
					{
					setState(83);
					match(T__23);
					setState(84);
					action();
					}
					}
					setState(89);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case T__24:
				_localctx = new WildcardContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(90);
				match(T__24);
				}
				break;
			case ID:
				_localctx = new SetNameContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(91);
				match(ID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ActionContext extends ParserRuleContext {
		public ActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_action; }
	 
		public ActionContext() { }
		public void copyFrom(ActionContext ctx) {
			super.copyFrom(ctx);
		}

		@Override
		public String toString() {
			PrettyPrint pp = new PrettyPrint();
			return pp.visit(this).toString();
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InputActionContext extends ActionContext {
		public TerminalNode CHANNEL() { return getToken(HMLParser.CHANNEL, 0); }
		public InputActionContext(ActionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HMLVisitor ) return ((HMLVisitor<? extends T>)visitor).visitInputAction(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class OutputActionContext extends ActionContext {
		public TerminalNode CHANNEL() { return getToken(HMLParser.CHANNEL, 0); }
		public OutputActionContext(ActionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HMLVisitor ) return ((HMLVisitor<? extends T>)visitor).visitOutputAction(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SyncContext extends ActionContext {
		public SyncContext(ActionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HMLVisitor ) return ((HMLVisitor<? extends T>)visitor).visitSync(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ActionContext action() throws RecognitionException {
		ActionContext _localctx = new ActionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_action);
		try {
			setState(98);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__25:
				_localctx = new OutputActionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(94);
				match(T__25);
				setState(95);
				match(CHANNEL);
				}
				break;
			case T__26:
				_localctx = new SyncContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(96);
				match(T__26);
				}
				break;
			case CHANNEL:
				_localctx = new InputActionContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(97);
				match(CHANNEL);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 3:
			return formula_sempred((FormulaContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean formula_sempred(FormulaContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 4);
		case 1:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001 e\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002\u0002"+
		"\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002\u0005"+
		"\u0007\u0005\u0001\u0000\u0001\u0000\u0005\u0000\u000f\b\u0000\n\u0000"+
		"\f\u0000\u0012\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0003\u0001 \b\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003F\b\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0005\u0003N\b\u0003\n\u0003\f\u0003Q\t\u0003\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0005\u0004V\b\u0004\n\u0004\f\u0004Y\t\u0004\u0001\u0004"+
		"\u0001\u0004\u0003\u0004]\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0003\u0005c\b\u0005\u0001\u0005\u0000\u0001\u0006\u0006"+
		"\u0000\u0002\u0004\u0006\b\n\u0000\u0002\u0001\u0000\b\t\u0001\u0000\n"+
		"\u000bo\u0000\u0010\u0001\u0000\u0000\u0000\u0002\u001f\u0001\u0000\u0000"+
		"\u0000\u0004!\u0001\u0000\u0000\u0000\u0006E\u0001\u0000\u0000\u0000\b"+
		"\\\u0001\u0000\u0000\u0000\nb\u0001\u0000\u0000\u0000\f\u000f\u0003\u0002"+
		"\u0001\u0000\r\u000f\u0003\u0004\u0002\u0000\u000e\f\u0001\u0000\u0000"+
		"\u0000\u000e\r\u0001\u0000\u0000\u0000\u000f\u0012\u0001\u0000\u0000\u0000"+
		"\u0010\u000e\u0001\u0000\u0000\u0000\u0010\u0011\u0001\u0000\u0000\u0000"+
		"\u0011\u0013\u0001\u0000\u0000\u0000\u0012\u0010\u0001\u0000\u0000\u0000"+
		"\u0013\u0014\u0005\u0000\u0000\u0001\u0014\u0001\u0001\u0000\u0000\u0000"+
		"\u0015\u0016\u0005\u001d\u0000\u0000\u0016\u0017\u0005\u0001\u0000\u0000"+
		"\u0017\u0018\u0003\u0006\u0003\u0000\u0018\u0019\u0005\u0002\u0000\u0000"+
		"\u0019 \u0001\u0000\u0000\u0000\u001a\u001b\u0005\u001d\u0000\u0000\u001b"+
		"\u001c\u0005\u0003\u0000\u0000\u001c\u001d\u0003\u0006\u0003\u0000\u001d"+
		"\u001e\u0005\u0002\u0000\u0000\u001e \u0001\u0000\u0000\u0000\u001f\u0015"+
		"\u0001\u0000\u0000\u0000\u001f\u001a\u0001\u0000\u0000\u0000 \u0003\u0001"+
		"\u0000\u0000\u0000!\"\u0005\u0004\u0000\u0000\"#\u0005\u001d\u0000\u0000"+
		"#$\u0005\u0005\u0000\u0000$%\u0005\u0006\u0000\u0000%&\u0003\b\u0004\u0000"+
		"&\'\u0005\u0007\u0000\u0000\'(\u0005\u0002\u0000\u0000(\u0005\u0001\u0000"+
		"\u0000\u0000)*\u0006\u0003\uffff\uffff\u0000*F\u0007\u0000\u0000\u0000"+
		"+F\u0007\u0001\u0000\u0000,-\u0005\f\u0000\u0000-.\u0003\b\u0004\u0000"+
		"./\u0005\r\u0000\u0000/0\u0003\u0006\u0003\b0F\u0001\u0000\u0000\u0000"+
		"12\u0005\u000e\u0000\u000023\u0003\b\u0004\u000034\u0005\u000f\u0000\u0000"+
		"45\u0003\u0006\u0003\u00075F\u0001\u0000\u0000\u000067\u0005\u0010\u0000"+
		"\u000078\u0003\b\u0004\u000089\u0005\u0011\u0000\u00009:\u0003\u0006\u0003"+
		"\u0006:F\u0001\u0000\u0000\u0000;<\u0005\u0012\u0000\u0000<=\u0003\b\u0004"+
		"\u0000=>\u0005\u0013\u0000\u0000>?\u0003\u0006\u0003\u0005?F\u0001\u0000"+
		"\u0000\u0000@A\u0005\u0016\u0000\u0000AB\u0003\u0006\u0003\u0000BC\u0005"+
		"\u0017\u0000\u0000CF\u0001\u0000\u0000\u0000DF\u0005\u001d\u0000\u0000"+
		"E)\u0001\u0000\u0000\u0000E+\u0001\u0000\u0000\u0000E,\u0001\u0000\u0000"+
		"\u0000E1\u0001\u0000\u0000\u0000E6\u0001\u0000\u0000\u0000E;\u0001\u0000"+
		"\u0000\u0000E@\u0001\u0000\u0000\u0000ED\u0001\u0000\u0000\u0000FO\u0001"+
		"\u0000\u0000\u0000GH\n\u0004\u0000\u0000HI\u0005\u0014\u0000\u0000IN\u0003"+
		"\u0006\u0003\u0005JK\n\u0003\u0000\u0000KL\u0005\u0015\u0000\u0000LN\u0003"+
		"\u0006\u0003\u0004MG\u0001\u0000\u0000\u0000MJ\u0001\u0000\u0000\u0000"+
		"NQ\u0001\u0000\u0000\u0000OM\u0001\u0000\u0000\u0000OP\u0001\u0000\u0000"+
		"\u0000P\u0007\u0001\u0000\u0000\u0000QO\u0001\u0000\u0000\u0000RW\u0003"+
		"\n\u0005\u0000ST\u0005\u0018\u0000\u0000TV\u0003\n\u0005\u0000US\u0001"+
		"\u0000\u0000\u0000VY\u0001\u0000\u0000\u0000WU\u0001\u0000\u0000\u0000"+
		"WX\u0001\u0000\u0000\u0000X]\u0001\u0000\u0000\u0000YW\u0001\u0000\u0000"+
		"\u0000Z]\u0005\u0019\u0000\u0000[]\u0005\u001d\u0000\u0000\\R\u0001\u0000"+
		"\u0000\u0000\\Z\u0001\u0000\u0000\u0000\\[\u0001\u0000\u0000\u0000]\t"+
		"\u0001\u0000\u0000\u0000^_\u0005\u001a\u0000\u0000_c\u0005\u001e\u0000"+
		"\u0000`c\u0005\u001b\u0000\u0000ac\u0005\u001e\u0000\u0000b^\u0001\u0000"+
		"\u0000\u0000b`\u0001\u0000\u0000\u0000ba\u0001\u0000\u0000\u0000c\u000b"+
		"\u0001\u0000\u0000\u0000\t\u000e\u0010\u001fEMOW\\b";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}