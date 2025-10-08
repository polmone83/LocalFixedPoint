package labeled_transitions_systems.CCS;
// Generated from CCS.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class CCSParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, NEWLINE=20, ID=21, CHANNEL=22, MULTILINECOMMENT=23, 
		SINGLELINECOMMENT=24;
	public static final int
		RULE_system = 0, RULE_pDecl = 1, RULE_setDecl = 2, RULE_process = 3, RULE_channelSet = 4, 
		RULE_renaming = 5;
	private static String[] makeRuleNames() {
		return new String[] {
			"system", "pDecl", "setDecl", "process", "channelSet", "renaming"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'agent'", "'='", "';'", "'set'", "'0'", "'''", "'.'", "'tau'", 
			"'+'", "'|'", "'\\'", "'['", "','", "']'", "'('", "')'", "'{'", "'}'", 
			"'/'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, "NEWLINE", "ID", "CHANNEL", 
			"MULTILINECOMMENT", "SINGLELINECOMMENT"
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
	public String getGrammarFileName() { return "CCS.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CCSParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SystemContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(CCSParser.EOF, 0); }
		public List<PDeclContext> pDecl() {
			return getRuleContexts(PDeclContext.class);
		}
		public PDeclContext pDecl(int i) {
			return getRuleContext(PDeclContext.class,i);
		}
		public List<SetDeclContext> setDecl() {
			return getRuleContexts(SetDeclContext.class);
		}
		public SetDeclContext setDecl(int i) {
			return getRuleContext(SetDeclContext.class,i);
		}
		public SystemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_system; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CCSVisitor ) return ((CCSVisitor<? extends T>)visitor).visitSystem(this);
			else return visitor.visitChildren(this);
		}

		@Override
		public String toString() {
			PrettyPrint pp = new PrettyPrint();
			return pp.visit(this).toString();
		}
	}

	public final SystemContext system() throws RecognitionException {
		SystemContext _localctx = new SystemContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_system);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(16);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2097170L) != 0)) {
				{
				setState(14);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__0:
				case ID:
					{
					setState(12);
					pDecl();
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
	public static class PDeclContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CCSParser.ID, 0); }
		public ProcessContext process() {
			return getRuleContext(ProcessContext.class,0);
		}
		public PDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CCSVisitor ) return ((CCSVisitor<? extends T>)visitor).visitPDecl(this);
			else return visitor.visitChildren(this);
		}

		@Override
		public String toString() {
			PrettyPrint pp = new PrettyPrint();
			return pp.visit(this).toString();
		}
	}

	public final PDeclContext pDecl() throws RecognitionException {
		PDeclContext _localctx = new PDeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_pDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(22);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(21);
				match(T__0);
				}
			}

			setState(24);
			match(ID);
			setState(25);
			match(T__1);
			setState(26);
			process(0);
			setState(27);
			match(T__2);
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
		public TerminalNode ID() { return getToken(CCSParser.ID, 0); }
		public ChannelSetContext channelSet() {
			return getRuleContext(ChannelSetContext.class,0);
		}
		public SetDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CCSVisitor ) return ((CCSVisitor<? extends T>)visitor).visitSetDecl(this);
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
			setState(29);
			match(T__3);
			setState(30);
			match(ID);
			setState(31);
			match(T__1);
			setState(32);
			channelSet();
			setState(33);
			match(T__2);
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
	public static class ProcessContext extends ParserRuleContext {
		public ProcessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_process; }
	 
		public ProcessContext() { }
		public void copyFrom(ProcessContext ctx) {
			super.copyFrom(ctx);
		}

		@Override
		public String toString() {
			PrettyPrint pp = new PrettyPrint();
			return pp.visit(this).toString();
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NilContext extends ProcessContext {
		public NilContext(ProcessContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CCSVisitor ) return ((CCSVisitor<? extends T>)visitor).visitNil(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ChoiceContext extends ProcessContext {
		public List<ProcessContext> process() {
			return getRuleContexts(ProcessContext.class);
		}
		public ProcessContext process(int i) {
			return getRuleContext(ProcessContext.class,i);
		}
		public ChoiceContext(ProcessContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CCSVisitor ) return ((CCSVisitor<? extends T>)visitor).visitChoice(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RestricSetContext extends ProcessContext {
		public ProcessContext process() {
			return getRuleContext(ProcessContext.class,0);
		}
		public TerminalNode ID() { return getToken(CCSParser.ID, 0); }
		public RestricSetContext(ProcessContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CCSVisitor ) return ((CCSVisitor<? extends T>)visitor).visitRestricSet(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InputActionContext extends ProcessContext {
		public TerminalNode CHANNEL() { return getToken(CCSParser.CHANNEL, 0); }
		public ProcessContext process() {
			return getRuleContext(ProcessContext.class,0);
		}
		public InputActionContext(ProcessContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CCSVisitor ) return ((CCSVisitor<? extends T>)visitor).visitInputAction(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParensContext extends ProcessContext {
		public ProcessContext process() {
			return getRuleContext(ProcessContext.class,0);
		}
		public ParensContext(ProcessContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CCSVisitor ) return ((CCSVisitor<? extends T>)visitor).visitParens(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class OutputActionContext extends ProcessContext {
		public TerminalNode CHANNEL() { return getToken(CCSParser.CHANNEL, 0); }
		public ProcessContext process() {
			return getRuleContext(ProcessContext.class,0);
		}
		public OutputActionContext(ProcessContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CCSVisitor ) return ((CCSVisitor<? extends T>)visitor).visitOutputAction(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SyncActionContext extends ProcessContext {
		public ProcessContext process() {
			return getRuleContext(ProcessContext.class,0);
		}
		public SyncActionContext(ProcessContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CCSVisitor ) return ((CCSVisitor<? extends T>)visitor).visitSyncAction(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PNameContext extends ProcessContext {
		public TerminalNode ID() { return getToken(CCSParser.ID, 0); }
		public PNameContext(ProcessContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CCSVisitor ) return ((CCSVisitor<? extends T>)visitor).visitPName(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParallelContext extends ProcessContext {
		public List<ProcessContext> process() {
			return getRuleContexts(ProcessContext.class);
		}
		public ProcessContext process(int i) {
			return getRuleContext(ProcessContext.class,i);
		}
		public ParallelContext(ProcessContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CCSVisitor ) return ((CCSVisitor<? extends T>)visitor).visitParallel(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RestricExplicitContext extends ProcessContext {
		public ProcessContext process() {
			return getRuleContext(ProcessContext.class,0);
		}
		public ChannelSetContext channelSet() {
			return getRuleContext(ChannelSetContext.class,0);
		}
		public RestricExplicitContext(ProcessContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CCSVisitor ) return ((CCSVisitor<? extends T>)visitor).visitRestricExplicit(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RenameContext extends ProcessContext {
		public ProcessContext process() {
			return getRuleContext(ProcessContext.class,0);
		}
		public List<RenamingContext> renaming() {
			return getRuleContexts(RenamingContext.class);
		}
		public RenamingContext renaming(int i) {
			return getRuleContext(RenamingContext.class,i);
		}
		public RenameContext(ProcessContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CCSVisitor ) return ((CCSVisitor<? extends T>)visitor).visitRename(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProcessContext process() throws RecognitionException {
		return process(0);
	}

	private ProcessContext process(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ProcessContext _localctx = new ProcessContext(_ctx, _parentState);
		ProcessContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_process, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				{
				_localctx = new NilContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(36);
				match(T__4);
				}
				break;
			case T__5:
				{
				_localctx = new OutputActionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(37);
				match(T__5);
				setState(38);
				match(CHANNEL);
				setState(39);
				match(T__6);
				setState(40);
				process(10);
				}
				break;
			case CHANNEL:
				{
				_localctx = new InputActionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(41);
				match(CHANNEL);
				setState(42);
				match(T__6);
				setState(43);
				process(9);
				}
				break;
			case T__7:
				{
				_localctx = new SyncActionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(44);
				match(T__7);
				setState(45);
				match(T__6);
				setState(46);
				process(8);
				}
				break;
			case T__14:
				{
				_localctx = new ParensContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(47);
				match(T__14);
				setState(48);
				process(0);
				setState(49);
				match(T__15);
				}
				break;
			case ID:
				{
				_localctx = new PNameContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(51);
				match(ID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(80);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(78);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
					case 1:
						{
						_localctx = new ChoiceContext(new ProcessContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_process);
						setState(54);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(55);
						match(T__8);
						setState(56);
						process(8);
						}
						break;
					case 2:
						{
						_localctx = new ParallelContext(new ProcessContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_process);
						setState(57);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(58);
						match(T__9);
						setState(59);
						process(7);
						}
						break;
					case 3:
						{
						_localctx = new RestricSetContext(new ProcessContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_process);
						setState(60);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(61);
						match(T__10);
						setState(62);
						match(ID);
						}
						break;
					case 4:
						{
						_localctx = new RestricExplicitContext(new ProcessContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_process);
						setState(63);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(64);
						match(T__10);
						setState(65);
						channelSet();
						}
						break;
					case 5:
						{
						_localctx = new RenameContext(new ProcessContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_process);
						setState(66);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(67);
						match(T__11);
						setState(68);
						renaming();
						setState(73);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==T__12) {
							{
							{
							setState(69);
							match(T__12);
							setState(70);
							renaming();
							}
							}
							setState(75);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(76);
						match(T__13);
						}
						break;
					}
					} 
				}
				setState(82);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
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
	public static class ChannelSetContext extends ParserRuleContext {
		public List<TerminalNode> CHANNEL() { return getTokens(CCSParser.CHANNEL); }
		public TerminalNode CHANNEL(int i) {
			return getToken(CCSParser.CHANNEL, i);
		}
		public ChannelSetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_channelSet; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CCSVisitor ) return ((CCSVisitor<? extends T>)visitor).visitChannelSet(this);
			else return visitor.visitChildren(this);
		}

		@Override
		public String toString() {
			PrettyPrint pp = new PrettyPrint();
			return pp.visit(this).toString();
		}
	}

	public final ChannelSetContext channelSet() throws RecognitionException {
		ChannelSetContext _localctx = new ChannelSetContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_channelSet);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			match(T__16);
			setState(84);
			match(CHANNEL);
			setState(89);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__12) {
				{
				{
				setState(85);
				match(T__12);
				setState(86);
				match(CHANNEL);
				}
				}
				setState(91);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(92);
			match(T__17);
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
	public static class RenamingContext extends ParserRuleContext {
		public Token to;
		public Token from;
		public List<TerminalNode> CHANNEL() { return getTokens(CCSParser.CHANNEL); }
		public TerminalNode CHANNEL(int i) {
			return getToken(CCSParser.CHANNEL, i);
		}
		public RenamingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_renaming; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CCSVisitor ) return ((CCSVisitor<? extends T>)visitor).visitRenaming(this);
			else return visitor.visitChildren(this);
		}

		@Override
		public String toString() {
			PrettyPrint pp = new PrettyPrint();
			return pp.visit(this).toString();
		}
	}

	public final RenamingContext renaming() throws RecognitionException {
		RenamingContext _localctx = new RenamingContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_renaming);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			((RenamingContext)_localctx).to = match(CHANNEL);
			setState(95);
			match(T__18);
			setState(96);
			((RenamingContext)_localctx).from = match(CHANNEL);
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
			return process_sempred((ProcessContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean process_sempred(ProcessContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 7);
		case 1:
			return precpred(_ctx, 6);
		case 2:
			return precpred(_ctx, 5);
		case 3:
			return precpred(_ctx, 4);
		case 4:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0018c\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0001\u0000\u0001\u0000\u0005\u0000\u000f\b\u0000\n"+
		"\u0000\f\u0000\u0012\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0003"+
		"\u0001\u0017\b\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003"+
		"\u00035\b\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0005\u0003H\b\u0003\n\u0003\f\u0003K\t\u0003\u0001\u0003\u0001"+
		"\u0003\u0005\u0003O\b\u0003\n\u0003\f\u0003R\t\u0003\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0005\u0004X\b\u0004\n\u0004\f\u0004[\t"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0000\u0001\u0006\u0006\u0000\u0002\u0004\u0006\b\n"+
		"\u0000\u0000k\u0000\u0010\u0001\u0000\u0000\u0000\u0002\u0016\u0001\u0000"+
		"\u0000\u0000\u0004\u001d\u0001\u0000\u0000\u0000\u00064\u0001\u0000\u0000"+
		"\u0000\bS\u0001\u0000\u0000\u0000\n^\u0001\u0000\u0000\u0000\f\u000f\u0003"+
		"\u0002\u0001\u0000\r\u000f\u0003\u0004\u0002\u0000\u000e\f\u0001\u0000"+
		"\u0000\u0000\u000e\r\u0001\u0000\u0000\u0000\u000f\u0012\u0001\u0000\u0000"+
		"\u0000\u0010\u000e\u0001\u0000\u0000\u0000\u0010\u0011\u0001\u0000\u0000"+
		"\u0000\u0011\u0013\u0001\u0000\u0000\u0000\u0012\u0010\u0001\u0000\u0000"+
		"\u0000\u0013\u0014\u0005\u0000\u0000\u0001\u0014\u0001\u0001\u0000\u0000"+
		"\u0000\u0015\u0017\u0005\u0001\u0000\u0000\u0016\u0015\u0001\u0000\u0000"+
		"\u0000\u0016\u0017\u0001\u0000\u0000\u0000\u0017\u0018\u0001\u0000\u0000"+
		"\u0000\u0018\u0019\u0005\u0015\u0000\u0000\u0019\u001a\u0005\u0002\u0000"+
		"\u0000\u001a\u001b\u0003\u0006\u0003\u0000\u001b\u001c\u0005\u0003\u0000"+
		"\u0000\u001c\u0003\u0001\u0000\u0000\u0000\u001d\u001e\u0005\u0004\u0000"+
		"\u0000\u001e\u001f\u0005\u0015\u0000\u0000\u001f \u0005\u0002\u0000\u0000"+
		" !\u0003\b\u0004\u0000!\"\u0005\u0003\u0000\u0000\"\u0005\u0001\u0000"+
		"\u0000\u0000#$\u0006\u0003\uffff\uffff\u0000$5\u0005\u0005\u0000\u0000"+
		"%&\u0005\u0006\u0000\u0000&\'\u0005\u0016\u0000\u0000\'(\u0005\u0007\u0000"+
		"\u0000(5\u0003\u0006\u0003\n)*\u0005\u0016\u0000\u0000*+\u0005\u0007\u0000"+
		"\u0000+5\u0003\u0006\u0003\t,-\u0005\b\u0000\u0000-.\u0005\u0007\u0000"+
		"\u0000.5\u0003\u0006\u0003\b/0\u0005\u000f\u0000\u000001\u0003\u0006\u0003"+
		"\u000012\u0005\u0010\u0000\u000025\u0001\u0000\u0000\u000035\u0005\u0015"+
		"\u0000\u00004#\u0001\u0000\u0000\u00004%\u0001\u0000\u0000\u00004)\u0001"+
		"\u0000\u0000\u00004,\u0001\u0000\u0000\u00004/\u0001\u0000\u0000\u0000"+
		"43\u0001\u0000\u0000\u00005P\u0001\u0000\u0000\u000067\n\u0007\u0000\u0000"+
		"78\u0005\t\u0000\u00008O\u0003\u0006\u0003\b9:\n\u0006\u0000\u0000:;\u0005"+
		"\n\u0000\u0000;O\u0003\u0006\u0003\u0007<=\n\u0005\u0000\u0000=>\u0005"+
		"\u000b\u0000\u0000>O\u0005\u0015\u0000\u0000?@\n\u0004\u0000\u0000@A\u0005"+
		"\u000b\u0000\u0000AO\u0003\b\u0004\u0000BC\n\u0003\u0000\u0000CD\u0005"+
		"\f\u0000\u0000DI\u0003\n\u0005\u0000EF\u0005\r\u0000\u0000FH\u0003\n\u0005"+
		"\u0000GE\u0001\u0000\u0000\u0000HK\u0001\u0000\u0000\u0000IG\u0001\u0000"+
		"\u0000\u0000IJ\u0001\u0000\u0000\u0000JL\u0001\u0000\u0000\u0000KI\u0001"+
		"\u0000\u0000\u0000LM\u0005\u000e\u0000\u0000MO\u0001\u0000\u0000\u0000"+
		"N6\u0001\u0000\u0000\u0000N9\u0001\u0000\u0000\u0000N<\u0001\u0000\u0000"+
		"\u0000N?\u0001\u0000\u0000\u0000NB\u0001\u0000\u0000\u0000OR\u0001\u0000"+
		"\u0000\u0000PN\u0001\u0000\u0000\u0000PQ\u0001\u0000\u0000\u0000Q\u0007"+
		"\u0001\u0000\u0000\u0000RP\u0001\u0000\u0000\u0000ST\u0005\u0011\u0000"+
		"\u0000TY\u0005\u0016\u0000\u0000UV\u0005\r\u0000\u0000VX\u0005\u0016\u0000"+
		"\u0000WU\u0001\u0000\u0000\u0000X[\u0001\u0000\u0000\u0000YW\u0001\u0000"+
		"\u0000\u0000YZ\u0001\u0000\u0000\u0000Z\\\u0001\u0000\u0000\u0000[Y\u0001"+
		"\u0000\u0000\u0000\\]\u0005\u0012\u0000\u0000]\t\u0001\u0000\u0000\u0000"+
		"^_\u0005\u0016\u0000\u0000_`\u0005\u0013\u0000\u0000`a\u0005\u0016\u0000"+
		"\u0000a\u000b\u0001\u0000\u0000\u0000\b\u000e\u0010\u00164INPY";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}