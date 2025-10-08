package weighted_transitions_systems.WCTL;
// Generated from WCTL.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class WCTLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, NEWLINE=33, FID=34, ID=35, WEIGHT=36, MULTILINECOMMENT=37, SINGLELINECOMMENT=38;
	public static final int
		RULE_decls = 0, RULE_formuladecl = 1, RULE_formula = 2, RULE_relexpr = 3, 
		RULE_expr = 4;
	private static String[] makeRuleNames() {
		return new String[] {
			"decls", "formuladecl", "formula", "relexpr", "expr"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'='", "'tt'", "'True'", "'true'", "'ff'", "'False'", "'false'", 
			"'&&'", "'and'", "'||'", "'or'", "'A'", "'U'", "'['", "'<='", "']'", 
			"'E'", "'AF'", "'EF'", "'AX'", "'EX'", "'('", "')'", "'<'", "'>'", "'>='", 
			"'!='", "'=='", "'*'", "'/'", "'+'", "'-'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, "NEWLINE", "FID", 
			"ID", "WEIGHT", "MULTILINECOMMENT", "SINGLELINECOMMENT"
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
	public String getGrammarFileName() { return "WCTL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public WCTLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclsContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(WCTLParser.EOF, 0); }
		public List<FormuladeclContext> formuladecl() {
			return getRuleContexts(FormuladeclContext.class);
		}
		public FormuladeclContext formuladecl(int i) {
			return getRuleContext(FormuladeclContext.class,i);
		}
		public DeclsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decls; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WCTLVisitor ) return ((WCTLVisitor<? extends T>)visitor).visitDecls(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclsContext decls() throws RecognitionException {
		DeclsContext _localctx = new DeclsContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_decls);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(11); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(10);
				formuladecl();
				}
				}
				setState(13); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==FID );
			setState(15);
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
	public static class FormuladeclContext extends ParserRuleContext {
		public TerminalNode FID() { return getToken(WCTLParser.FID, 0); }
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public FormuladeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formuladecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WCTLVisitor ) return ((WCTLVisitor<? extends T>)visitor).visitFormuladecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormuladeclContext formuladecl() throws RecognitionException {
		FormuladeclContext _localctx = new FormuladeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_formuladecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(17);
			match(FID);
			setState(18);
			match(T__0);
			setState(19);
			formula(0);
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
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BoundedUniversalFinallyContext extends FormulaContext {
		public Token bound;
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public TerminalNode WEIGHT() { return getToken(WCTLParser.WEIGHT, 0); }
		public BoundedUniversalFinallyContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WCTLVisitor ) return ((WCTLVisitor<? extends T>)visitor).visitBoundedUniversalFinally(this);
			else return visitor.visitChildren(this);
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
			if ( visitor instanceof WCTLVisitor ) return ((WCTLVisitor<? extends T>)visitor).visitOr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExistentialFinallyContext extends FormulaContext {
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public ExistentialFinallyContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WCTLVisitor ) return ((WCTLVisitor<? extends T>)visitor).visitExistentialFinally(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BoundedExistentialNextContext extends FormulaContext {
		public Token bound;
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public TerminalNode WEIGHT() { return getToken(WCTLParser.WEIGHT, 0); }
		public BoundedExistentialNextContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WCTLVisitor ) return ((WCTLVisitor<? extends T>)visitor).visitBoundedExistentialNext(this);
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
			if ( visitor instanceof WCTLVisitor ) return ((WCTLVisitor<? extends T>)visitor).visitParens(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BoundedExistentialUntilContext extends FormulaContext {
		public FormulaContext left;
		public Token bound;
		public FormulaContext right;
		public List<FormulaContext> formula() {
			return getRuleContexts(FormulaContext.class);
		}
		public FormulaContext formula(int i) {
			return getRuleContext(FormulaContext.class,i);
		}
		public TerminalNode WEIGHT() { return getToken(WCTLParser.WEIGHT, 0); }
		public BoundedExistentialUntilContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WCTLVisitor ) return ((WCTLVisitor<? extends T>)visitor).visitBoundedExistentialUntil(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UniversalUntilContext extends FormulaContext {
		public FormulaContext left;
		public FormulaContext right;
		public List<FormulaContext> formula() {
			return getRuleContexts(FormulaContext.class);
		}
		public FormulaContext formula(int i) {
			return getRuleContext(FormulaContext.class,i);
		}
		public UniversalUntilContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WCTLVisitor ) return ((WCTLVisitor<? extends T>)visitor).visitUniversalUntil(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExistentialNextContext extends FormulaContext {
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public ExistentialNextContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WCTLVisitor ) return ((WCTLVisitor<? extends T>)visitor).visitExistentialNext(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TrueContext extends FormulaContext {
		public TrueContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WCTLVisitor ) return ((WCTLVisitor<? extends T>)visitor).visitTrue(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FalseContext extends FormulaContext {
		public FalseContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WCTLVisitor ) return ((WCTLVisitor<? extends T>)visitor).visitFalse(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UniversalFinallyContext extends FormulaContext {
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public UniversalFinallyContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WCTLVisitor ) return ((WCTLVisitor<? extends T>)visitor).visitUniversalFinally(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BoundedExistentialFinallyContext extends FormulaContext {
		public Token bound;
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public TerminalNode WEIGHT() { return getToken(WCTLParser.WEIGHT, 0); }
		public BoundedExistentialFinallyContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WCTLVisitor ) return ((WCTLVisitor<? extends T>)visitor).visitBoundedExistentialFinally(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BoundedUniversalUntilContext extends FormulaContext {
		public FormulaContext left;
		public Token bound;
		public FormulaContext right;
		public List<FormulaContext> formula() {
			return getRuleContexts(FormulaContext.class);
		}
		public FormulaContext formula(int i) {
			return getRuleContext(FormulaContext.class,i);
		}
		public TerminalNode WEIGHT() { return getToken(WCTLParser.WEIGHT, 0); }
		public BoundedUniversalUntilContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WCTLVisitor ) return ((WCTLVisitor<? extends T>)visitor).visitBoundedUniversalUntil(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExistentialUntilContext extends FormulaContext {
		public FormulaContext left;
		public FormulaContext right;
		public List<FormulaContext> formula() {
			return getRuleContexts(FormulaContext.class);
		}
		public FormulaContext formula(int i) {
			return getRuleContext(FormulaContext.class,i);
		}
		public ExistentialUntilContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WCTLVisitor ) return ((WCTLVisitor<? extends T>)visitor).visitExistentialUntil(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UniversalNextContext extends FormulaContext {
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public UniversalNextContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WCTLVisitor ) return ((WCTLVisitor<? extends T>)visitor).visitUniversalNext(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends FormulaContext {
		public RelexprContext relexpr() {
			return getRuleContext(RelexprContext.class,0);
		}
		public ExpressionContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WCTLVisitor ) return ((WCTLVisitor<? extends T>)visitor).visitExpression(this);
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
			if ( visitor instanceof WCTLVisitor ) return ((WCTLVisitor<? extends T>)visitor).visitAnd(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BoundedUniversalNextContext extends FormulaContext {
		public Token bound;
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public TerminalNode WEIGHT() { return getToken(WCTLParser.WEIGHT, 0); }
		public BoundedUniversalNextContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WCTLVisitor ) return ((WCTLVisitor<? extends T>)visitor).visitBoundedUniversalNext(this);
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
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_formula, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				_localctx = new TrueContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(22);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 28L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 2:
				{
				_localctx = new FalseContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(23);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 224L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 3:
				{
				_localctx = new UniversalUntilContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(24);
				match(T__11);
				setState(25);
				((UniversalUntilContext)_localctx).left = formula(0);
				setState(26);
				match(T__12);
				setState(27);
				((UniversalUntilContext)_localctx).right = formula(14);
				}
				break;
			case 4:
				{
				_localctx = new BoundedUniversalUntilContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(29);
				match(T__11);
				setState(30);
				((BoundedUniversalUntilContext)_localctx).left = formula(0);
				setState(31);
				match(T__12);
				setState(32);
				match(T__13);
				setState(33);
				match(T__14);
				setState(34);
				((BoundedUniversalUntilContext)_localctx).bound = match(WEIGHT);
				setState(35);
				match(T__15);
				setState(36);
				((BoundedUniversalUntilContext)_localctx).right = formula(13);
				}
				break;
			case 5:
				{
				_localctx = new ExistentialUntilContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(38);
				match(T__16);
				setState(39);
				((ExistentialUntilContext)_localctx).left = formula(0);
				setState(40);
				match(T__12);
				setState(41);
				((ExistentialUntilContext)_localctx).right = formula(12);
				}
				break;
			case 6:
				{
				_localctx = new BoundedExistentialUntilContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(43);
				match(T__16);
				setState(44);
				((BoundedExistentialUntilContext)_localctx).left = formula(0);
				setState(45);
				match(T__12);
				setState(46);
				match(T__13);
				setState(47);
				match(T__14);
				setState(48);
				((BoundedExistentialUntilContext)_localctx).bound = match(WEIGHT);
				setState(49);
				match(T__15);
				setState(50);
				((BoundedExistentialUntilContext)_localctx).right = formula(11);
				}
				break;
			case 7:
				{
				_localctx = new UniversalFinallyContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(52);
				match(T__17);
				setState(53);
				formula(10);
				}
				break;
			case 8:
				{
				_localctx = new BoundedUniversalFinallyContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(54);
				match(T__17);
				setState(55);
				match(T__13);
				setState(56);
				match(T__14);
				setState(57);
				((BoundedUniversalFinallyContext)_localctx).bound = match(WEIGHT);
				setState(58);
				match(T__15);
				setState(59);
				formula(9);
				}
				break;
			case 9:
				{
				_localctx = new ExistentialFinallyContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(60);
				match(T__18);
				setState(61);
				formula(8);
				}
				break;
			case 10:
				{
				_localctx = new BoundedExistentialFinallyContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(62);
				match(T__18);
				setState(63);
				match(T__13);
				setState(64);
				match(T__14);
				setState(65);
				((BoundedExistentialFinallyContext)_localctx).bound = match(WEIGHT);
				setState(66);
				match(T__15);
				setState(67);
				formula(7);
				}
				break;
			case 11:
				{
				_localctx = new UniversalNextContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(68);
				match(T__19);
				setState(69);
				formula(6);
				}
				break;
			case 12:
				{
				_localctx = new BoundedUniversalNextContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(70);
				match(T__19);
				setState(71);
				match(T__13);
				setState(72);
				match(T__14);
				setState(73);
				((BoundedUniversalNextContext)_localctx).bound = match(WEIGHT);
				setState(74);
				match(T__15);
				setState(75);
				formula(5);
				}
				break;
			case 13:
				{
				_localctx = new ExistentialNextContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(76);
				match(T__20);
				setState(77);
				formula(4);
				}
				break;
			case 14:
				{
				_localctx = new BoundedExistentialNextContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(78);
				match(T__20);
				setState(79);
				match(T__13);
				setState(80);
				match(T__14);
				setState(81);
				((BoundedExistentialNextContext)_localctx).bound = match(WEIGHT);
				setState(82);
				match(T__15);
				setState(83);
				formula(3);
				}
				break;
			case 15:
				{
				_localctx = new ParensContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(84);
				match(T__21);
				setState(85);
				formula(0);
				setState(86);
				match(T__22);
				}
				break;
			case 16:
				{
				_localctx = new ExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(88);
				relexpr();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(99);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(97);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
					case 1:
						{
						_localctx = new AndContext(new FormulaContext(_parentctx, _parentState));
						((AndContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_formula);
						setState(91);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(92);
						_la = _input.LA(1);
						if ( !(_la==T__7 || _la==T__8) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(93);
						((AndContext)_localctx).right = formula(17);
						}
						break;
					case 2:
						{
						_localctx = new OrContext(new FormulaContext(_parentctx, _parentState));
						((OrContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_formula);
						setState(94);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(95);
						_la = _input.LA(1);
						if ( !(_la==T__9 || _la==T__10) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(96);
						((OrContext)_localctx).right = formula(16);
						}
						break;
					}
					} 
				}
				setState(101);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
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
	public static class RelexprContext extends ParserRuleContext {
		public RelexprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relexpr; }
	 
		public RelexprContext() { }
		public void copyFrom(RelexprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RelExpressionContext extends RelexprContext {
		public Token rel;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public RelExpressionContext(RelexprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WCTLVisitor ) return ((WCTLVisitor<? extends T>)visitor).visitRelExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PropositionContext extends RelexprContext {
		public TerminalNode ID() { return getToken(WCTLParser.ID, 0); }
		public PropositionContext(RelexprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WCTLVisitor ) return ((WCTLVisitor<? extends T>)visitor).visitProposition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelexprContext relexpr() throws RecognitionException {
		RelexprContext _localctx = new RelexprContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_relexpr);
		int _la;
		try {
			setState(107);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				_localctx = new RelExpressionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(102);
				expr(0);
				setState(103);
				((RelExpressionContext)_localctx).rel = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 520126464L) != 0)) ) {
					((RelExpressionContext)_localctx).rel = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(104);
				expr(0);
				}
				break;
			case 2:
				_localctx = new PropositionContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(106);
				match(ID);
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
	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DivContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public DivContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WCTLVisitor ) return ((WCTLVisitor<? extends T>)visitor).visitDiv(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MultContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public MultContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WCTLVisitor ) return ((WCTLVisitor<? extends T>)visitor).visitMult(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParensExprContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ParensExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WCTLVisitor ) return ((WCTLVisitor<? extends T>)visitor).visitParensExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InverseContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public InverseContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WCTLVisitor ) return ((WCTLVisitor<? extends T>)visitor).visitInverse(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PlusContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public PlusContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WCTLVisitor ) return ((WCTLVisitor<? extends T>)visitor).visitPlus(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class WeightContext extends ExprContext {
		public TerminalNode WEIGHT() { return getToken(WCTLParser.WEIGHT, 0); }
		public WeightContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WCTLVisitor ) return ((WCTLVisitor<? extends T>)visitor).visitWeight(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MinusContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public MinusContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WCTLVisitor ) return ((WCTLVisitor<? extends T>)visitor).visitMinus(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PropVarContext extends ExprContext {
		public TerminalNode ID() { return getToken(WCTLParser.ID, 0); }
		public PropVarContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WCTLVisitor ) return ((WCTLVisitor<? extends T>)visitor).visitPropVar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__31:
				{
				_localctx = new InverseContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(110);
				match(T__31);
				setState(111);
				expr(4);
				}
				break;
			case T__21:
				{
				_localctx = new ParensExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(112);
				match(T__21);
				setState(113);
				expr(0);
				setState(114);
				match(T__22);
				}
				break;
			case ID:
				{
				_localctx = new PropVarContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(116);
				match(ID);
				}
				break;
			case WEIGHT:
				{
				_localctx = new WeightContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(117);
				match(WEIGHT);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(134);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(132);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
					case 1:
						{
						_localctx = new MultContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(120);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(121);
						match(T__28);
						setState(122);
						expr(9);
						}
						break;
					case 2:
						{
						_localctx = new DivContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(123);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(124);
						match(T__29);
						setState(125);
						expr(8);
						}
						break;
					case 3:
						{
						_localctx = new PlusContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(126);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(127);
						match(T__30);
						setState(128);
						expr(7);
						}
						break;
					case 4:
						{
						_localctx = new MinusContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(129);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(130);
						match(T__31);
						setState(131);
						expr(6);
						}
						break;
					}
					} 
				}
				setState(136);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 2:
			return formula_sempred((FormulaContext)_localctx, predIndex);
		case 4:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean formula_sempred(FormulaContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 16);
		case 1:
			return precpred(_ctx, 15);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 8);
		case 3:
			return precpred(_ctx, 7);
		case 4:
			return precpred(_ctx, 6);
		case 5:
			return precpred(_ctx, 5);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001&\u008a\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0001"+
		"\u0000\u0004\u0000\f\b\u0000\u000b\u0000\f\u0000\r\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0003\u0002Z\b\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0005\u0002b\b\u0002\n\u0002\f\u0002e\t\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003"+
		"l\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004w\b\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0005\u0004\u0085\b\u0004\n\u0004\f\u0004\u0088\t\u0004\u0001\u0004\u0000"+
		"\u0002\u0004\b\u0005\u0000\u0002\u0004\u0006\b\u0000\u0005\u0001\u0000"+
		"\u0002\u0004\u0001\u0000\u0005\u0007\u0001\u0000\b\t\u0001\u0000\n\u000b"+
		"\u0002\u0000\u000f\u000f\u0018\u001c\u009e\u0000\u000b\u0001\u0000\u0000"+
		"\u0000\u0002\u0011\u0001\u0000\u0000\u0000\u0004Y\u0001\u0000\u0000\u0000"+
		"\u0006k\u0001\u0000\u0000\u0000\bv\u0001\u0000\u0000\u0000\n\f\u0003\u0002"+
		"\u0001\u0000\u000b\n\u0001\u0000\u0000\u0000\f\r\u0001\u0000\u0000\u0000"+
		"\r\u000b\u0001\u0000\u0000\u0000\r\u000e\u0001\u0000\u0000\u0000\u000e"+
		"\u000f\u0001\u0000\u0000\u0000\u000f\u0010\u0005\u0000\u0000\u0001\u0010"+
		"\u0001\u0001\u0000\u0000\u0000\u0011\u0012\u0005\"\u0000\u0000\u0012\u0013"+
		"\u0005\u0001\u0000\u0000\u0013\u0014\u0003\u0004\u0002\u0000\u0014\u0003"+
		"\u0001\u0000\u0000\u0000\u0015\u0016\u0006\u0002\uffff\uffff\u0000\u0016"+
		"Z\u0007\u0000\u0000\u0000\u0017Z\u0007\u0001\u0000\u0000\u0018\u0019\u0005"+
		"\f\u0000\u0000\u0019\u001a\u0003\u0004\u0002\u0000\u001a\u001b\u0005\r"+
		"\u0000\u0000\u001b\u001c\u0003\u0004\u0002\u000e\u001cZ\u0001\u0000\u0000"+
		"\u0000\u001d\u001e\u0005\f\u0000\u0000\u001e\u001f\u0003\u0004\u0002\u0000"+
		"\u001f \u0005\r\u0000\u0000 !\u0005\u000e\u0000\u0000!\"\u0005\u000f\u0000"+
		"\u0000\"#\u0005$\u0000\u0000#$\u0005\u0010\u0000\u0000$%\u0003\u0004\u0002"+
		"\r%Z\u0001\u0000\u0000\u0000&\'\u0005\u0011\u0000\u0000\'(\u0003\u0004"+
		"\u0002\u0000()\u0005\r\u0000\u0000)*\u0003\u0004\u0002\f*Z\u0001\u0000"+
		"\u0000\u0000+,\u0005\u0011\u0000\u0000,-\u0003\u0004\u0002\u0000-.\u0005"+
		"\r\u0000\u0000./\u0005\u000e\u0000\u0000/0\u0005\u000f\u0000\u000001\u0005"+
		"$\u0000\u000012\u0005\u0010\u0000\u000023\u0003\u0004\u0002\u000b3Z\u0001"+
		"\u0000\u0000\u000045\u0005\u0012\u0000\u00005Z\u0003\u0004\u0002\n67\u0005"+
		"\u0012\u0000\u000078\u0005\u000e\u0000\u000089\u0005\u000f\u0000\u0000"+
		"9:\u0005$\u0000\u0000:;\u0005\u0010\u0000\u0000;Z\u0003\u0004\u0002\t"+
		"<=\u0005\u0013\u0000\u0000=Z\u0003\u0004\u0002\b>?\u0005\u0013\u0000\u0000"+
		"?@\u0005\u000e\u0000\u0000@A\u0005\u000f\u0000\u0000AB\u0005$\u0000\u0000"+
		"BC\u0005\u0010\u0000\u0000CZ\u0003\u0004\u0002\u0007DE\u0005\u0014\u0000"+
		"\u0000EZ\u0003\u0004\u0002\u0006FG\u0005\u0014\u0000\u0000GH\u0005\u000e"+
		"\u0000\u0000HI\u0005\u000f\u0000\u0000IJ\u0005$\u0000\u0000JK\u0005\u0010"+
		"\u0000\u0000KZ\u0003\u0004\u0002\u0005LM\u0005\u0015\u0000\u0000MZ\u0003"+
		"\u0004\u0002\u0004NO\u0005\u0015\u0000\u0000OP\u0005\u000e\u0000\u0000"+
		"PQ\u0005\u000f\u0000\u0000QR\u0005$\u0000\u0000RS\u0005\u0010\u0000\u0000"+
		"SZ\u0003\u0004\u0002\u0003TU\u0005\u0016\u0000\u0000UV\u0003\u0004\u0002"+
		"\u0000VW\u0005\u0017\u0000\u0000WZ\u0001\u0000\u0000\u0000XZ\u0003\u0006"+
		"\u0003\u0000Y\u0015\u0001\u0000\u0000\u0000Y\u0017\u0001\u0000\u0000\u0000"+
		"Y\u0018\u0001\u0000\u0000\u0000Y\u001d\u0001\u0000\u0000\u0000Y&\u0001"+
		"\u0000\u0000\u0000Y+\u0001\u0000\u0000\u0000Y4\u0001\u0000\u0000\u0000"+
		"Y6\u0001\u0000\u0000\u0000Y<\u0001\u0000\u0000\u0000Y>\u0001\u0000\u0000"+
		"\u0000YD\u0001\u0000\u0000\u0000YF\u0001\u0000\u0000\u0000YL\u0001\u0000"+
		"\u0000\u0000YN\u0001\u0000\u0000\u0000YT\u0001\u0000\u0000\u0000YX\u0001"+
		"\u0000\u0000\u0000Zc\u0001\u0000\u0000\u0000[\\\n\u0010\u0000\u0000\\"+
		"]\u0007\u0002\u0000\u0000]b\u0003\u0004\u0002\u0011^_\n\u000f\u0000\u0000"+
		"_`\u0007\u0003\u0000\u0000`b\u0003\u0004\u0002\u0010a[\u0001\u0000\u0000"+
		"\u0000a^\u0001\u0000\u0000\u0000be\u0001\u0000\u0000\u0000ca\u0001\u0000"+
		"\u0000\u0000cd\u0001\u0000\u0000\u0000d\u0005\u0001\u0000\u0000\u0000"+
		"ec\u0001\u0000\u0000\u0000fg\u0003\b\u0004\u0000gh\u0007\u0004\u0000\u0000"+
		"hi\u0003\b\u0004\u0000il\u0001\u0000\u0000\u0000jl\u0005#\u0000\u0000"+
		"kf\u0001\u0000\u0000\u0000kj\u0001\u0000\u0000\u0000l\u0007\u0001\u0000"+
		"\u0000\u0000mn\u0006\u0004\uffff\uffff\u0000no\u0005 \u0000\u0000ow\u0003"+
		"\b\u0004\u0004pq\u0005\u0016\u0000\u0000qr\u0003\b\u0004\u0000rs\u0005"+
		"\u0017\u0000\u0000sw\u0001\u0000\u0000\u0000tw\u0005#\u0000\u0000uw\u0005"+
		"$\u0000\u0000vm\u0001\u0000\u0000\u0000vp\u0001\u0000\u0000\u0000vt\u0001"+
		"\u0000\u0000\u0000vu\u0001\u0000\u0000\u0000w\u0086\u0001\u0000\u0000"+
		"\u0000xy\n\b\u0000\u0000yz\u0005\u001d\u0000\u0000z\u0085\u0003\b\u0004"+
		"\t{|\n\u0007\u0000\u0000|}\u0005\u001e\u0000\u0000}\u0085\u0003\b\u0004"+
		"\b~\u007f\n\u0006\u0000\u0000\u007f\u0080\u0005\u001f\u0000\u0000\u0080"+
		"\u0085\u0003\b\u0004\u0007\u0081\u0082\n\u0005\u0000\u0000\u0082\u0083"+
		"\u0005 \u0000\u0000\u0083\u0085\u0003\b\u0004\u0006\u0084x\u0001\u0000"+
		"\u0000\u0000\u0084{\u0001\u0000\u0000\u0000\u0084~\u0001\u0000\u0000\u0000"+
		"\u0084\u0081\u0001\u0000\u0000\u0000\u0085\u0088\u0001\u0000\u0000\u0000"+
		"\u0086\u0084\u0001\u0000\u0000\u0000\u0086\u0087\u0001\u0000\u0000\u0000"+
		"\u0087\t\u0001\u0000\u0000\u0000\u0088\u0086\u0001\u0000\u0000\u0000\b"+
		"\rYackv\u0084\u0086";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}