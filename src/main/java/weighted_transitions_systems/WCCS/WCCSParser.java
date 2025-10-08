package weighted_transitions_systems.WCCS;
// Generated from WCCS.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class WCCSParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, NEWLINE=23, PID=24, 
		ID=25, WEIGHT=26, MULTILINECOMMENT=27, SINGLELINECOMMENT=28;
	public static final int
		RULE_system = 0, RULE_pDecl = 1, RULE_setDecl = 2, RULE_process = 3, RULE_action = 4, 
		RULE_channelSet = 5, RULE_renaming = 6, RULE_labelRenaming = 7;
	private static String[] makeRuleNames() {
		return new String[] {
			"system", "pDecl", "setDecl", "process", "action", "channelSet", "renaming", 
			"labelRenaming"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "':='", "';'", "'set'", "'='", "'\\'", "'['", "','", "']'", "':'", 
			"'<'", "'>'", "'.'", "'|'", "'+'", "'('", "')'", "'!'", "'tau'", "'{'", 
			"'}'", "'->'", "'=>'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, "NEWLINE", 
			"PID", "ID", "WEIGHT", "MULTILINECOMMENT", "SINGLELINECOMMENT"
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
	public String getGrammarFileName() { return "WCCS.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public WCCSParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SystemContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(WCCSParser.EOF, 0); }
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
			if ( visitor instanceof WCCSVisitor ) return ((WCCSVisitor<? extends T>)visitor).visitSystem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SystemContext system() throws RecognitionException {
		SystemContext _localctx = new SystemContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_system);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(20);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2 || _la==PID) {
				{
				setState(18);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case PID:
					{
					setState(16);
					pDecl();
					}
					break;
				case T__2:
					{
					setState(17);
					setDecl();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(22);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(23);
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
		public TerminalNode PID() { return getToken(WCCSParser.PID, 0); }
		public ProcessContext process() {
			return getRuleContext(ProcessContext.class,0);
		}
		public PDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WCCSVisitor ) return ((WCCSVisitor<? extends T>)visitor).visitPDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PDeclContext pDecl() throws RecognitionException {
		PDeclContext _localctx = new PDeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_pDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(25);
			match(PID);
			setState(26);
			match(T__0);
			setState(27);
			process(0);
			setState(28);
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
	public static class SetDeclContext extends ParserRuleContext {
		public TerminalNode PID() { return getToken(WCCSParser.PID, 0); }
		public ChannelSetContext channelSet() {
			return getRuleContext(ChannelSetContext.class,0);
		}
		public SetDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WCCSVisitor ) return ((WCCSVisitor<? extends T>)visitor).visitSetDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SetDeclContext setDecl() throws RecognitionException {
		SetDeclContext _localctx = new SetDeclContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_setDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			match(T__2);
			setState(31);
			match(PID);
			setState(32);
			match(T__3);
			setState(33);
			channelSet();
			setState(34);
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
	public static class ProcessContext extends ParserRuleContext {
		public ProcessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_process; }
	 
		public ProcessContext() { }
		public void copyFrom(ProcessContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NilContext extends ProcessContext {
		public TerminalNode WEIGHT() { return getToken(WCCSParser.WEIGHT, 0); }
		public NilContext(ProcessContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WCCSVisitor ) return ((WCCSVisitor<? extends T>)visitor).visitNil(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LabelRenameContext extends ProcessContext {
		public ProcessContext process() {
			return getRuleContext(ProcessContext.class,0);
		}
		public List<LabelRenamingContext> labelRenaming() {
			return getRuleContexts(LabelRenamingContext.class);
		}
		public LabelRenamingContext labelRenaming(int i) {
			return getRuleContext(LabelRenamingContext.class,i);
		}
		public LabelRenameContext(ProcessContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WCCSVisitor ) return ((WCCSVisitor<? extends T>)visitor).visitLabelRename(this);
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
			if ( visitor instanceof WCCSVisitor ) return ((WCCSVisitor<? extends T>)visitor).visitChoice(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RestricSetContext extends ProcessContext {
		public ProcessContext process() {
			return getRuleContext(ProcessContext.class,0);
		}
		public TerminalNode PID() { return getToken(WCCSParser.PID, 0); }
		public RestricSetContext(ProcessContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WCCSVisitor ) return ((WCCSVisitor<? extends T>)visitor).visitRestricSet(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AtomicLabelContext extends ProcessContext {
		public TerminalNode ID() { return getToken(WCCSParser.ID, 0); }
		public ProcessContext process() {
			return getRuleContext(ProcessContext.class,0);
		}
		public AtomicLabelContext(ProcessContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WCCSVisitor ) return ((WCCSVisitor<? extends T>)visitor).visitAtomicLabel(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ActionPrefixContext extends ProcessContext {
		public ActionContext action() {
			return getRuleContext(ActionContext.class,0);
		}
		public ProcessContext process() {
			return getRuleContext(ProcessContext.class,0);
		}
		public TerminalNode WEIGHT() { return getToken(WCCSParser.WEIGHT, 0); }
		public ActionPrefixContext(ProcessContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WCCSVisitor ) return ((WCCSVisitor<? extends T>)visitor).visitActionPrefix(this);
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
			if ( visitor instanceof WCCSVisitor ) return ((WCCSVisitor<? extends T>)visitor).visitParens(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PNameContext extends ProcessContext {
		public TerminalNode PID() { return getToken(WCCSParser.PID, 0); }
		public PNameContext(ProcessContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WCCSVisitor ) return ((WCCSVisitor<? extends T>)visitor).visitPName(this);
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
			if ( visitor instanceof WCCSVisitor ) return ((WCCSVisitor<? extends T>)visitor).visitParallel(this);
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
			if ( visitor instanceof WCCSVisitor ) return ((WCCSVisitor<? extends T>)visitor).visitRestricExplicit(this);
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
			if ( visitor instanceof WCCSVisitor ) return ((WCCSVisitor<? extends T>)visitor).visitRename(this);
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
			setState(56);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				_localctx = new AtomicLabelContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(37);
				match(ID);
				setState(38);
				match(T__8);
				setState(39);
				process(7);
				}
				break;
			case T__9:
				{
				_localctx = new ActionPrefixContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(40);
				match(T__9);
				setState(41);
				action();
				setState(44);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__6) {
					{
					setState(42);
					match(T__6);
					setState(43);
					match(WEIGHT);
					}
				}

				setState(46);
				match(T__10);
				setState(47);
				match(T__11);
				setState(48);
				process(6);
				}
				break;
			case T__14:
				{
				_localctx = new ParensContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(50);
				match(T__14);
				setState(51);
				process(0);
				setState(52);
				match(T__15);
				}
				break;
			case PID:
				{
				_localctx = new PNameContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(54);
				match(PID);
				}
				break;
			case WEIGHT:
				{
				_localctx = new NilContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(55);
				match(WEIGHT);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(96);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(94);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
					case 1:
						{
						_localctx = new ParallelContext(new ProcessContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_process);
						setState(58);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(59);
						match(T__12);
						setState(60);
						process(6);
						}
						break;
					case 2:
						{
						_localctx = new ChoiceContext(new ProcessContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_process);
						setState(61);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(62);
						match(T__13);
						setState(63);
						process(5);
						}
						break;
					case 3:
						{
						_localctx = new RestricSetContext(new ProcessContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_process);
						setState(64);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(65);
						match(T__4);
						setState(66);
						match(PID);
						}
						break;
					case 4:
						{
						_localctx = new RestricExplicitContext(new ProcessContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_process);
						setState(67);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(68);
						match(T__4);
						setState(69);
						channelSet();
						}
						break;
					case 5:
						{
						_localctx = new RenameContext(new ProcessContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_process);
						setState(70);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(71);
						match(T__5);
						setState(72);
						renaming();
						setState(77);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==T__6) {
							{
							{
							setState(73);
							match(T__6);
							setState(74);
							renaming();
							}
							}
							setState(79);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(80);
						match(T__7);
						}
						break;
					case 6:
						{
						_localctx = new LabelRenameContext(new ProcessContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_process);
						setState(82);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(83);
						match(T__5);
						setState(84);
						labelRenaming();
						setState(89);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==T__6) {
							{
							{
							setState(85);
							match(T__6);
							setState(86);
							labelRenaming();
							}
							}
							setState(91);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(92);
						match(T__7);
						}
						break;
					}
					} 
				}
				setState(98);
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
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InputActionContext extends ActionContext {
		public TerminalNode ID() { return getToken(WCCSParser.ID, 0); }
		public InputActionContext(ActionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WCCSVisitor ) return ((WCCSVisitor<? extends T>)visitor).visitInputAction(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class OutputActionContext extends ActionContext {
		public TerminalNode ID() { return getToken(WCCSParser.ID, 0); }
		public OutputActionContext(ActionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WCCSVisitor ) return ((WCCSVisitor<? extends T>)visitor).visitOutputAction(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SyncActionContext extends ActionContext {
		public SyncActionContext(ActionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WCCSVisitor ) return ((WCCSVisitor<? extends T>)visitor).visitSyncAction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ActionContext action() throws RecognitionException {
		ActionContext _localctx = new ActionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_action);
		try {
			setState(103);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				_localctx = new InputActionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(99);
				match(ID);
				}
				break;
			case 2:
				_localctx = new OutputActionContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(100);
				match(ID);
				setState(101);
				match(T__16);
				}
				break;
			case 3:
				_localctx = new SyncActionContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(102);
				match(T__17);
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
	public static class ChannelSetContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(WCCSParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(WCCSParser.ID, i);
		}
		public ChannelSetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_channelSet; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WCCSVisitor ) return ((WCCSVisitor<? extends T>)visitor).visitChannelSet(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ChannelSetContext channelSet() throws RecognitionException {
		ChannelSetContext _localctx = new ChannelSetContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_channelSet);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			match(T__18);
			setState(106);
			match(ID);
			setState(111);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__6) {
				{
				{
				setState(107);
				match(T__6);
				setState(108);
				match(ID);
				}
				}
				setState(113);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(114);
			match(T__19);
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
		public Token from;
		public Token to;
		public List<TerminalNode> ID() { return getTokens(WCCSParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(WCCSParser.ID, i);
		}
		public RenamingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_renaming; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WCCSVisitor ) return ((WCCSVisitor<? extends T>)visitor).visitRenaming(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RenamingContext renaming() throws RecognitionException {
		RenamingContext _localctx = new RenamingContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_renaming);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			((RenamingContext)_localctx).from = match(ID);
			setState(117);
			match(T__20);
			setState(118);
			((RenamingContext)_localctx).to = match(ID);
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
	public static class LabelRenamingContext extends ParserRuleContext {
		public Token from;
		public Token to;
		public List<TerminalNode> ID() { return getTokens(WCCSParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(WCCSParser.ID, i);
		}
		public LabelRenamingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_labelRenaming; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WCCSVisitor ) return ((WCCSVisitor<? extends T>)visitor).visitLabelRenaming(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LabelRenamingContext labelRenaming() throws RecognitionException {
		LabelRenamingContext _localctx = new LabelRenamingContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_labelRenaming);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
			((LabelRenamingContext)_localctx).from = match(ID);
			setState(121);
			match(T__21);
			setState(122);
			((LabelRenamingContext)_localctx).to = match(ID);
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
			return precpred(_ctx, 5);
		case 1:
			return precpred(_ctx, 4);
		case 2:
			return precpred(_ctx, 11);
		case 3:
			return precpred(_ctx, 10);
		case 4:
			return precpred(_ctx, 9);
		case 5:
			return precpred(_ctx, 8);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u001c}\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0001"+
		"\u0000\u0001\u0000\u0005\u0000\u0013\b\u0000\n\u0000\f\u0000\u0016\t\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003-\b\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u00039\b\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003L\b\u0003"+
		"\n\u0003\f\u0003O\t\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003X\b\u0003\n\u0003\f\u0003"+
		"[\t\u0003\u0001\u0003\u0001\u0003\u0005\u0003_\b\u0003\n\u0003\f\u0003"+
		"b\t\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004"+
		"h\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005"+
		"n\b\u0005\n\u0005\f\u0005q\t\u0005\u0001\u0005\u0001\u0005\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0000\u0001\u0006\b\u0000\u0002\u0004\u0006\b"+
		"\n\f\u000e\u0000\u0000\u0086\u0000\u0014\u0001\u0000\u0000\u0000\u0002"+
		"\u0019\u0001\u0000\u0000\u0000\u0004\u001e\u0001\u0000\u0000\u0000\u0006"+
		"8\u0001\u0000\u0000\u0000\bg\u0001\u0000\u0000\u0000\ni\u0001\u0000\u0000"+
		"\u0000\ft\u0001\u0000\u0000\u0000\u000ex\u0001\u0000\u0000\u0000\u0010"+
		"\u0013\u0003\u0002\u0001\u0000\u0011\u0013\u0003\u0004\u0002\u0000\u0012"+
		"\u0010\u0001\u0000\u0000\u0000\u0012\u0011\u0001\u0000\u0000\u0000\u0013"+
		"\u0016\u0001\u0000\u0000\u0000\u0014\u0012\u0001\u0000\u0000\u0000\u0014"+
		"\u0015\u0001\u0000\u0000\u0000\u0015\u0017\u0001\u0000\u0000\u0000\u0016"+
		"\u0014\u0001\u0000\u0000\u0000\u0017\u0018\u0005\u0000\u0000\u0001\u0018"+
		"\u0001\u0001\u0000\u0000\u0000\u0019\u001a\u0005\u0018\u0000\u0000\u001a"+
		"\u001b\u0005\u0001\u0000\u0000\u001b\u001c\u0003\u0006\u0003\u0000\u001c"+
		"\u001d\u0005\u0002\u0000\u0000\u001d\u0003\u0001\u0000\u0000\u0000\u001e"+
		"\u001f\u0005\u0003\u0000\u0000\u001f \u0005\u0018\u0000\u0000 !\u0005"+
		"\u0004\u0000\u0000!\"\u0003\n\u0005\u0000\"#\u0005\u0002\u0000\u0000#"+
		"\u0005\u0001\u0000\u0000\u0000$%\u0006\u0003\uffff\uffff\u0000%&\u0005"+
		"\u0019\u0000\u0000&\'\u0005\t\u0000\u0000\'9\u0003\u0006\u0003\u0007("+
		")\u0005\n\u0000\u0000),\u0003\b\u0004\u0000*+\u0005\u0007\u0000\u0000"+
		"+-\u0005\u001a\u0000\u0000,*\u0001\u0000\u0000\u0000,-\u0001\u0000\u0000"+
		"\u0000-.\u0001\u0000\u0000\u0000./\u0005\u000b\u0000\u0000/0\u0005\f\u0000"+
		"\u000001\u0003\u0006\u0003\u000619\u0001\u0000\u0000\u000023\u0005\u000f"+
		"\u0000\u000034\u0003\u0006\u0003\u000045\u0005\u0010\u0000\u000059\u0001"+
		"\u0000\u0000\u000069\u0005\u0018\u0000\u000079\u0005\u001a\u0000\u0000"+
		"8$\u0001\u0000\u0000\u00008(\u0001\u0000\u0000\u000082\u0001\u0000\u0000"+
		"\u000086\u0001\u0000\u0000\u000087\u0001\u0000\u0000\u00009`\u0001\u0000"+
		"\u0000\u0000:;\n\u0005\u0000\u0000;<\u0005\r\u0000\u0000<_\u0003\u0006"+
		"\u0003\u0006=>\n\u0004\u0000\u0000>?\u0005\u000e\u0000\u0000?_\u0003\u0006"+
		"\u0003\u0005@A\n\u000b\u0000\u0000AB\u0005\u0005\u0000\u0000B_\u0005\u0018"+
		"\u0000\u0000CD\n\n\u0000\u0000DE\u0005\u0005\u0000\u0000E_\u0003\n\u0005"+
		"\u0000FG\n\t\u0000\u0000GH\u0005\u0006\u0000\u0000HM\u0003\f\u0006\u0000"+
		"IJ\u0005\u0007\u0000\u0000JL\u0003\f\u0006\u0000KI\u0001\u0000\u0000\u0000"+
		"LO\u0001\u0000\u0000\u0000MK\u0001\u0000\u0000\u0000MN\u0001\u0000\u0000"+
		"\u0000NP\u0001\u0000\u0000\u0000OM\u0001\u0000\u0000\u0000PQ\u0005\b\u0000"+
		"\u0000Q_\u0001\u0000\u0000\u0000RS\n\b\u0000\u0000ST\u0005\u0006\u0000"+
		"\u0000TY\u0003\u000e\u0007\u0000UV\u0005\u0007\u0000\u0000VX\u0003\u000e"+
		"\u0007\u0000WU\u0001\u0000\u0000\u0000X[\u0001\u0000\u0000\u0000YW\u0001"+
		"\u0000\u0000\u0000YZ\u0001\u0000\u0000\u0000Z\\\u0001\u0000\u0000\u0000"+
		"[Y\u0001\u0000\u0000\u0000\\]\u0005\b\u0000\u0000]_\u0001\u0000\u0000"+
		"\u0000^:\u0001\u0000\u0000\u0000^=\u0001\u0000\u0000\u0000^@\u0001\u0000"+
		"\u0000\u0000^C\u0001\u0000\u0000\u0000^F\u0001\u0000\u0000\u0000^R\u0001"+
		"\u0000\u0000\u0000_b\u0001\u0000\u0000\u0000`^\u0001\u0000\u0000\u0000"+
		"`a\u0001\u0000\u0000\u0000a\u0007\u0001\u0000\u0000\u0000b`\u0001\u0000"+
		"\u0000\u0000ch\u0005\u0019\u0000\u0000de\u0005\u0019\u0000\u0000eh\u0005"+
		"\u0011\u0000\u0000fh\u0005\u0012\u0000\u0000gc\u0001\u0000\u0000\u0000"+
		"gd\u0001\u0000\u0000\u0000gf\u0001\u0000\u0000\u0000h\t\u0001\u0000\u0000"+
		"\u0000ij\u0005\u0013\u0000\u0000jo\u0005\u0019\u0000\u0000kl\u0005\u0007"+
		"\u0000\u0000ln\u0005\u0019\u0000\u0000mk\u0001\u0000\u0000\u0000nq\u0001"+
		"\u0000\u0000\u0000om\u0001\u0000\u0000\u0000op\u0001\u0000\u0000\u0000"+
		"pr\u0001\u0000\u0000\u0000qo\u0001\u0000\u0000\u0000rs\u0005\u0014\u0000"+
		"\u0000s\u000b\u0001\u0000\u0000\u0000tu\u0005\u0019\u0000\u0000uv\u0005"+
		"\u0015\u0000\u0000vw\u0005\u0019\u0000\u0000w\r\u0001\u0000\u0000\u0000"+
		"xy\u0005\u0019\u0000\u0000yz\u0005\u0016\u0000\u0000z{\u0005\u0019\u0000"+
		"\u0000{\u000f\u0001\u0000\u0000\u0000\n\u0012\u0014,8MY^`go";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}