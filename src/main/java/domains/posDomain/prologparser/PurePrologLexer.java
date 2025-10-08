package domains.posDomain.prologparser;
// Generated from PureProlog.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class PurePrologLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, ID=11, VAR=12, NUMBER=13, NEWLINE=14, MULTILINE_COMMENT=15, INLINE_COMMENT=16;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "ID", "VAR", "NUMBER", "NEWLINE", "MULTILINE_COMMENT", "INLINE_COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'.'", "':-'", "','", "'('", "')'", "'true'", "'['", "']'", "'|'", 
			"'_'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, "ID", 
			"VAR", "NUMBER", "NEWLINE", "MULTILINE_COMMENT", "INLINE_COMMENT"
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


	public PurePrologLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "PureProlog.g4"; }

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
		"\u0004\u0000\u0010l\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001"+
		"\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\t\u0001"+
		"\t\u0001\n\u0001\n\u0005\n<\b\n\n\n\f\n?\t\n\u0001\u000b\u0001\u000b\u0005"+
		"\u000bC\b\u000b\n\u000b\f\u000bF\t\u000b\u0001\f\u0004\fI\b\f\u000b\f"+
		"\f\fJ\u0001\r\u0004\rN\b\r\u000b\r\f\rO\u0001\r\u0001\r\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0005\u000eX\b\u000e\n\u000e\f\u000e[\t"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000f\u0001\u000f\u0005\u000fd\b\u000f\n\u000f\f\u000fg\t\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0002Ye\u0000\u0010\u0001\u0001"+
		"\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f"+
		"\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f"+
		"\u001f\u0010\u0001\u0000\u0005\u0002\u0000__az\u0004\u000009AZ__az\u0002"+
		"\u0000AZ__\u0001\u000009\u0003\u0000\t\n\r\r  q\u0000\u0001\u0001\u0000"+
		"\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000"+
		"\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000"+
		"\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000"+
		"\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000"+
		"\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000"+
		"\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000"+
		"\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000"+
		"\u0000\u001f\u0001\u0000\u0000\u0000\u0001!\u0001\u0000\u0000\u0000\u0003"+
		"#\u0001\u0000\u0000\u0000\u0005&\u0001\u0000\u0000\u0000\u0007(\u0001"+
		"\u0000\u0000\u0000\t*\u0001\u0000\u0000\u0000\u000b,\u0001\u0000\u0000"+
		"\u0000\r1\u0001\u0000\u0000\u0000\u000f3\u0001\u0000\u0000\u0000\u0011"+
		"5\u0001\u0000\u0000\u0000\u00137\u0001\u0000\u0000\u0000\u00159\u0001"+
		"\u0000\u0000\u0000\u0017@\u0001\u0000\u0000\u0000\u0019H\u0001\u0000\u0000"+
		"\u0000\u001bM\u0001\u0000\u0000\u0000\u001dS\u0001\u0000\u0000\u0000\u001f"+
		"a\u0001\u0000\u0000\u0000!\"\u0005.\u0000\u0000\"\u0002\u0001\u0000\u0000"+
		"\u0000#$\u0005:\u0000\u0000$%\u0005-\u0000\u0000%\u0004\u0001\u0000\u0000"+
		"\u0000&\'\u0005,\u0000\u0000\'\u0006\u0001\u0000\u0000\u0000()\u0005("+
		"\u0000\u0000)\b\u0001\u0000\u0000\u0000*+\u0005)\u0000\u0000+\n\u0001"+
		"\u0000\u0000\u0000,-\u0005t\u0000\u0000-.\u0005r\u0000\u0000./\u0005u"+
		"\u0000\u0000/0\u0005e\u0000\u00000\f\u0001\u0000\u0000\u000012\u0005["+
		"\u0000\u00002\u000e\u0001\u0000\u0000\u000034\u0005]\u0000\u00004\u0010"+
		"\u0001\u0000\u0000\u000056\u0005|\u0000\u00006\u0012\u0001\u0000\u0000"+
		"\u000078\u0005_\u0000\u00008\u0014\u0001\u0000\u0000\u00009=\u0007\u0000"+
		"\u0000\u0000:<\u0007\u0001\u0000\u0000;:\u0001\u0000\u0000\u0000<?\u0001"+
		"\u0000\u0000\u0000=;\u0001\u0000\u0000\u0000=>\u0001\u0000\u0000\u0000"+
		">\u0016\u0001\u0000\u0000\u0000?=\u0001\u0000\u0000\u0000@D\u0007\u0002"+
		"\u0000\u0000AC\u0007\u0001\u0000\u0000BA\u0001\u0000\u0000\u0000CF\u0001"+
		"\u0000\u0000\u0000DB\u0001\u0000\u0000\u0000DE\u0001\u0000\u0000\u0000"+
		"E\u0018\u0001\u0000\u0000\u0000FD\u0001\u0000\u0000\u0000GI\u0007\u0003"+
		"\u0000\u0000HG\u0001\u0000\u0000\u0000IJ\u0001\u0000\u0000\u0000JH\u0001"+
		"\u0000\u0000\u0000JK\u0001\u0000\u0000\u0000K\u001a\u0001\u0000\u0000"+
		"\u0000LN\u0007\u0004\u0000\u0000ML\u0001\u0000\u0000\u0000NO\u0001\u0000"+
		"\u0000\u0000OM\u0001\u0000\u0000\u0000OP\u0001\u0000\u0000\u0000PQ\u0001"+
		"\u0000\u0000\u0000QR\u0006\r\u0000\u0000R\u001c\u0001\u0000\u0000\u0000"+
		"ST\u0005/\u0000\u0000TU\u0005*\u0000\u0000UY\u0001\u0000\u0000\u0000V"+
		"X\t\u0000\u0000\u0000WV\u0001\u0000\u0000\u0000X[\u0001\u0000\u0000\u0000"+
		"YZ\u0001\u0000\u0000\u0000YW\u0001\u0000\u0000\u0000Z\\\u0001\u0000\u0000"+
		"\u0000[Y\u0001\u0000\u0000\u0000\\]\u0005*\u0000\u0000]^\u0005/\u0000"+
		"\u0000^_\u0001\u0000\u0000\u0000_`\u0006\u000e\u0000\u0000`\u001e\u0001"+
		"\u0000\u0000\u0000ae\u0005%\u0000\u0000bd\t\u0000\u0000\u0000cb\u0001"+
		"\u0000\u0000\u0000dg\u0001\u0000\u0000\u0000ef\u0001\u0000\u0000\u0000"+
		"ec\u0001\u0000\u0000\u0000fh\u0001\u0000\u0000\u0000ge\u0001\u0000\u0000"+
		"\u0000hi\u0005\n\u0000\u0000ij\u0001\u0000\u0000\u0000jk\u0006\u000f\u0000"+
		"\u0000k \u0001\u0000\u0000\u0000\u0007\u0000=DJOYe\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}