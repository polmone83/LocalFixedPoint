package RegLangEquiv.Timbuk;
// Generated from Timbuk.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class TimbukLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, NEWLINE=11, ID=12, INT=13, MULTILINECOMMENT=14, SINGLELINECOMMENT=15;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "NEWLINE", "ID", "INT", "MULTILINECOMMENT", "SINGLELINECOMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'Ops'", "':'", "'Automaton'", "'States'", "'Final'", "'Transitions'", 
			"'('", "','", "')'", "'->'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, "NEWLINE", 
			"ID", "INT", "MULTILINECOMMENT", "SINGLELINECOMMENT"
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


	public TimbukLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Timbuk.g4"; }

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
		"\u0004\u0000\u000f\u0081\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006"+
		"\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\t\u0001\t\u0001"+
		"\t\u0001\n\u0004\nS\b\n\u000b\n\f\nT\u0001\n\u0001\n\u0001\u000b\u0001"+
		"\u000b\u0005\u000b[\b\u000b\n\u000b\f\u000b^\t\u000b\u0001\f\u0001\f\u0005"+
		"\fb\b\f\n\f\f\fe\t\f\u0001\r\u0001\r\u0001\r\u0001\r\u0005\rk\b\r\n\r"+
		"\f\rn\t\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0005\u000ey\b\u000e\n\u000e\f\u000e|\t\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0002lz\u0000\u000f\u0001"+
		"\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007"+
		"\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d"+
		"\u000f\u0001\u0000\u0004\u0003\u0000\t\n\r\r  \u0002\u0000AZaz\u0007\u0000"+
		"!\"\'\'**09??AZaz\u0001\u000009\u0085\u0000\u0001\u0001\u0000\u0000\u0000"+
		"\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000"+
		"\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000"+
		"\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f"+
		"\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013"+
		"\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017"+
		"\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b"+
		"\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0001\u001f"+
		"\u0001\u0000\u0000\u0000\u0003#\u0001\u0000\u0000\u0000\u0005%\u0001\u0000"+
		"\u0000\u0000\u0007/\u0001\u0000\u0000\u0000\t6\u0001\u0000\u0000\u0000"+
		"\u000b<\u0001\u0000\u0000\u0000\rH\u0001\u0000\u0000\u0000\u000fJ\u0001"+
		"\u0000\u0000\u0000\u0011L\u0001\u0000\u0000\u0000\u0013N\u0001\u0000\u0000"+
		"\u0000\u0015R\u0001\u0000\u0000\u0000\u0017X\u0001\u0000\u0000\u0000\u0019"+
		"_\u0001\u0000\u0000\u0000\u001bf\u0001\u0000\u0000\u0000\u001dt\u0001"+
		"\u0000\u0000\u0000\u001f \u0005O\u0000\u0000 !\u0005p\u0000\u0000!\"\u0005"+
		"s\u0000\u0000\"\u0002\u0001\u0000\u0000\u0000#$\u0005:\u0000\u0000$\u0004"+
		"\u0001\u0000\u0000\u0000%&\u0005A\u0000\u0000&\'\u0005u\u0000\u0000\'"+
		"(\u0005t\u0000\u0000()\u0005o\u0000\u0000)*\u0005m\u0000\u0000*+\u0005"+
		"a\u0000\u0000+,\u0005t\u0000\u0000,-\u0005o\u0000\u0000-.\u0005n\u0000"+
		"\u0000.\u0006\u0001\u0000\u0000\u0000/0\u0005S\u0000\u000001\u0005t\u0000"+
		"\u000012\u0005a\u0000\u000023\u0005t\u0000\u000034\u0005e\u0000\u0000"+
		"45\u0005s\u0000\u00005\b\u0001\u0000\u0000\u000067\u0005F\u0000\u0000"+
		"78\u0005i\u0000\u000089\u0005n\u0000\u00009:\u0005a\u0000\u0000:;\u0005"+
		"l\u0000\u0000;\n\u0001\u0000\u0000\u0000<=\u0005T\u0000\u0000=>\u0005"+
		"r\u0000\u0000>?\u0005a\u0000\u0000?@\u0005n\u0000\u0000@A\u0005s\u0000"+
		"\u0000AB\u0005i\u0000\u0000BC\u0005t\u0000\u0000CD\u0005i\u0000\u0000"+
		"DE\u0005o\u0000\u0000EF\u0005n\u0000\u0000FG\u0005s\u0000\u0000G\f\u0001"+
		"\u0000\u0000\u0000HI\u0005(\u0000\u0000I\u000e\u0001\u0000\u0000\u0000"+
		"JK\u0005,\u0000\u0000K\u0010\u0001\u0000\u0000\u0000LM\u0005)\u0000\u0000"+
		"M\u0012\u0001\u0000\u0000\u0000NO\u0005-\u0000\u0000OP\u0005>\u0000\u0000"+
		"P\u0014\u0001\u0000\u0000\u0000QS\u0007\u0000\u0000\u0000RQ\u0001\u0000"+
		"\u0000\u0000ST\u0001\u0000\u0000\u0000TR\u0001\u0000\u0000\u0000TU\u0001"+
		"\u0000\u0000\u0000UV\u0001\u0000\u0000\u0000VW\u0006\n\u0000\u0000W\u0016"+
		"\u0001\u0000\u0000\u0000X\\\u0007\u0001\u0000\u0000Y[\u0007\u0002\u0000"+
		"\u0000ZY\u0001\u0000\u0000\u0000[^\u0001\u0000\u0000\u0000\\Z\u0001\u0000"+
		"\u0000\u0000\\]\u0001\u0000\u0000\u0000]\u0018\u0001\u0000\u0000\u0000"+
		"^\\\u0001\u0000\u0000\u0000_c\u0007\u0003\u0000\u0000`b\u0007\u0003\u0000"+
		"\u0000a`\u0001\u0000\u0000\u0000be\u0001\u0000\u0000\u0000ca\u0001\u0000"+
		"\u0000\u0000cd\u0001\u0000\u0000\u0000d\u001a\u0001\u0000\u0000\u0000"+
		"ec\u0001\u0000\u0000\u0000fg\u0005/\u0000\u0000gh\u0005*\u0000\u0000h"+
		"l\u0001\u0000\u0000\u0000ik\t\u0000\u0000\u0000ji\u0001\u0000\u0000\u0000"+
		"kn\u0001\u0000\u0000\u0000lm\u0001\u0000\u0000\u0000lj\u0001\u0000\u0000"+
		"\u0000mo\u0001\u0000\u0000\u0000nl\u0001\u0000\u0000\u0000op\u0005*\u0000"+
		"\u0000pq\u0005/\u0000\u0000qr\u0001\u0000\u0000\u0000rs\u0006\r\u0000"+
		"\u0000s\u001c\u0001\u0000\u0000\u0000tu\u0005/\u0000\u0000uv\u0005/\u0000"+
		"\u0000vz\u0001\u0000\u0000\u0000wy\t\u0000\u0000\u0000xw\u0001\u0000\u0000"+
		"\u0000y|\u0001\u0000\u0000\u0000z{\u0001\u0000\u0000\u0000zx\u0001\u0000"+
		"\u0000\u0000{}\u0001\u0000\u0000\u0000|z\u0001\u0000\u0000\u0000}~\u0005"+
		"\n\u0000\u0000~\u007f\u0001\u0000\u0000\u0000\u007f\u0080\u0006\u000e"+
		"\u0000\u0000\u0080\u001e\u0001\u0000\u0000\u0000\u0006\u0000T\\clz\u0001"+
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