package RegLangEquiv.MATA;
// Generated from RegLangEquiv.MATA.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class MATALexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, END=13, NEWLINE=14, STATE=15, SYMBOL=16, 
		SINGLELINECOMMENT=17;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "END", "NEWLINE", "STATE", "SYMBOL", "SINGLELINECOMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'@RegLangEquiv.NFA-bits'", "'%'", "'Initial'", "'Final'", "'States-auto'",
			"'Alphabet-utf'", "'Alphabet-auto'", "'!'", "'&'", "'|'", "'('", "')'", 
			"'\\n'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, "END", "NEWLINE", "STATE", "SYMBOL", "SINGLELINECOMMENT"
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


	public MATALexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "RegLangEquiv.MATA.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u0011\u0090\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001"+
		"\b\u0001\t\u0001\t\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001"+
		"\f\u0001\r\u0004\rr\b\r\u000b\r\f\rs\u0001\r\u0001\r\u0001\u000e\u0001"+
		"\u000e\u0005\u000ez\b\u000e\n\u000e\f\u000e}\t\u000e\u0001\u000f\u0001"+
		"\u000f\u0005\u000f\u0081\b\u000f\n\u000f\f\u000f\u0084\t\u000f\u0001\u0010"+
		"\u0001\u0010\u0005\u0010\u0088\b\u0010\n\u0010\f\u0010\u008b\t\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0089\u0000\u0011\u0001"+
		"\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007"+
		"\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d"+
		"\u000f\u001f\u0010!\u0011\u0001\u0000\u0004\u0003\u0000\t\n\r\r  \u0001"+
		"\u0000qq\u0003\u000009AZaz\u0001\u0000aa\u0093\u0000\u0001\u0001\u0000"+
		"\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000"+
		"\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000"+
		"\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000"+
		"\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000"+
		"\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000"+
		"\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000"+
		"\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000"+
		"\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0001"+
		"#\u0001\u0000\u0000\u0000\u0003-\u0001\u0000\u0000\u0000\u0005/\u0001"+
		"\u0000\u0000\u0000\u00077\u0001\u0000\u0000\u0000\t=\u0001\u0000\u0000"+
		"\u0000\u000bI\u0001\u0000\u0000\u0000\rV\u0001\u0000\u0000\u0000\u000f"+
		"d\u0001\u0000\u0000\u0000\u0011f\u0001\u0000\u0000\u0000\u0013h\u0001"+
		"\u0000\u0000\u0000\u0015j\u0001\u0000\u0000\u0000\u0017l\u0001\u0000\u0000"+
		"\u0000\u0019n\u0001\u0000\u0000\u0000\u001bq\u0001\u0000\u0000\u0000\u001d"+
		"w\u0001\u0000\u0000\u0000\u001f~\u0001\u0000\u0000\u0000!\u0085\u0001"+
		"\u0000\u0000\u0000#$\u0005@\u0000\u0000$%\u0005N\u0000\u0000%&\u0005F"+
		"\u0000\u0000&\'\u0005A\u0000\u0000\'(\u0005-\u0000\u0000()\u0005b\u0000"+
		"\u0000)*\u0005i\u0000\u0000*+\u0005t\u0000\u0000+,\u0005s\u0000\u0000"+
		",\u0002\u0001\u0000\u0000\u0000-.\u0005%\u0000\u0000.\u0004\u0001\u0000"+
		"\u0000\u0000/0\u0005I\u0000\u000001\u0005n\u0000\u000012\u0005i\u0000"+
		"\u000023\u0005t\u0000\u000034\u0005i\u0000\u000045\u0005a\u0000\u0000"+
		"56\u0005l\u0000\u00006\u0006\u0001\u0000\u0000\u000078\u0005F\u0000\u0000"+
		"89\u0005i\u0000\u00009:\u0005n\u0000\u0000:;\u0005a\u0000\u0000;<\u0005"+
		"l\u0000\u0000<\b\u0001\u0000\u0000\u0000=>\u0005S\u0000\u0000>?\u0005"+
		"t\u0000\u0000?@\u0005a\u0000\u0000@A\u0005t\u0000\u0000AB\u0005e\u0000"+
		"\u0000BC\u0005s\u0000\u0000CD\u0005-\u0000\u0000DE\u0005a\u0000\u0000"+
		"EF\u0005u\u0000\u0000FG\u0005t\u0000\u0000GH\u0005o\u0000\u0000H\n\u0001"+
		"\u0000\u0000\u0000IJ\u0005A\u0000\u0000JK\u0005l\u0000\u0000KL\u0005p"+
		"\u0000\u0000LM\u0005h\u0000\u0000MN\u0005a\u0000\u0000NO\u0005b\u0000"+
		"\u0000OP\u0005e\u0000\u0000PQ\u0005t\u0000\u0000QR\u0005-\u0000\u0000"+
		"RS\u0005u\u0000\u0000ST\u0005t\u0000\u0000TU\u0005f\u0000\u0000U\f\u0001"+
		"\u0000\u0000\u0000VW\u0005A\u0000\u0000WX\u0005l\u0000\u0000XY\u0005p"+
		"\u0000\u0000YZ\u0005h\u0000\u0000Z[\u0005a\u0000\u0000[\\\u0005b\u0000"+
		"\u0000\\]\u0005e\u0000\u0000]^\u0005t\u0000\u0000^_\u0005-\u0000\u0000"+
		"_`\u0005a\u0000\u0000`a\u0005u\u0000\u0000ab\u0005t\u0000\u0000bc\u0005"+
		"o\u0000\u0000c\u000e\u0001\u0000\u0000\u0000de\u0005!\u0000\u0000e\u0010"+
		"\u0001\u0000\u0000\u0000fg\u0005&\u0000\u0000g\u0012\u0001\u0000\u0000"+
		"\u0000hi\u0005|\u0000\u0000i\u0014\u0001\u0000\u0000\u0000jk\u0005(\u0000"+
		"\u0000k\u0016\u0001\u0000\u0000\u0000lm\u0005)\u0000\u0000m\u0018\u0001"+
		"\u0000\u0000\u0000no\u0005\n\u0000\u0000o\u001a\u0001\u0000\u0000\u0000"+
		"pr\u0007\u0000\u0000\u0000qp\u0001\u0000\u0000\u0000rs\u0001\u0000\u0000"+
		"\u0000sq\u0001\u0000\u0000\u0000st\u0001\u0000\u0000\u0000tu\u0001\u0000"+
		"\u0000\u0000uv\u0006\r\u0000\u0000v\u001c\u0001\u0000\u0000\u0000w{\u0007"+
		"\u0001\u0000\u0000xz\u0007\u0002\u0000\u0000yx\u0001\u0000\u0000\u0000"+
		"z}\u0001\u0000\u0000\u0000{y\u0001\u0000\u0000\u0000{|\u0001\u0000\u0000"+
		"\u0000|\u001e\u0001\u0000\u0000\u0000}{\u0001\u0000\u0000\u0000~\u0082"+
		"\u0007\u0003\u0000\u0000\u007f\u0081\u0007\u0002\u0000\u0000\u0080\u007f"+
		"\u0001\u0000\u0000\u0000\u0081\u0084\u0001\u0000\u0000\u0000\u0082\u0080"+
		"\u0001\u0000\u0000\u0000\u0082\u0083\u0001\u0000\u0000\u0000\u0083 \u0001"+
		"\u0000\u0000\u0000\u0084\u0082\u0001\u0000\u0000\u0000\u0085\u0089\u0005"+
		"#\u0000\u0000\u0086\u0088\t\u0000\u0000\u0000\u0087\u0086\u0001\u0000"+
		"\u0000\u0000\u0088\u008b\u0001\u0000\u0000\u0000\u0089\u008a\u0001\u0000"+
		"\u0000\u0000\u0089\u0087\u0001\u0000\u0000\u0000\u008a\u008c\u0001\u0000"+
		"\u0000\u0000\u008b\u0089\u0001\u0000\u0000\u0000\u008c\u008d\u0005\n\u0000"+
		"\u0000\u008d\u008e\u0001\u0000\u0000\u0000\u008e\u008f\u0006\u0010\u0000"+
		"\u0000\u008f\"\u0001\u0000\u0000\u0000\u0005\u0000s{\u0082\u0089\u0001"+
		"\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}