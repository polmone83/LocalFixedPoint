package RegLangEquiv.NFA;
// Generated from RegLangEquiv.NFA.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class NFALexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		END=10, NEWLINE=11, ID=12, MULTILINECOMMENT=13, SINGLELINECOMMENT=14;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"END", "NEWLINE", "ID", "MULTILINECOMMENT", "SINGLELINECOMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'-'", "'->'", "'check'", "':'", "'='", "'<='", "'=>'", "'accept'", 
			"'+'", "'\\n'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, "END", "NEWLINE", 
			"ID", "MULTILINECOMMENT", "SINGLELINECOMMENT"
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


	public NFALexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "RegLangEquiv.NFA.g4"; }

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
		"\u0004\u0000\u000ef\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0001\u0000\u0001\u0000\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001"+
		"\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\b\u0001\b\u0001\t\u0001\t\u0001\n\u0004\n?\b"+
		"\n\u000b\n\f\n@\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0005\u000bG\b"+
		"\u000b\n\u000b\f\u000bJ\t\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0005"+
		"\fP\b\f\n\f\f\fS\t\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0005\r^\b\r\n\r\f\ra\t\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0002Q_\u0000\u000e\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004"+
		"\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017"+
		"\f\u0019\r\u001b\u000e\u0001\u0000\u0003\u0003\u0000\t\n\r\r  \u0002\u0000"+
		"AZaz\u0007\u0000!\"\'\'**09??AZazi\u0000\u0001\u0001\u0000\u0000\u0000"+
		"\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000"+
		"\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000"+
		"\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f"+
		"\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013"+
		"\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017"+
		"\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b"+
		"\u0001\u0000\u0000\u0000\u0001\u001d\u0001\u0000\u0000\u0000\u0003\u001f"+
		"\u0001\u0000\u0000\u0000\u0005\"\u0001\u0000\u0000\u0000\u0007(\u0001"+
		"\u0000\u0000\u0000\t*\u0001\u0000\u0000\u0000\u000b,\u0001\u0000\u0000"+
		"\u0000\r/\u0001\u0000\u0000\u0000\u000f2\u0001\u0000\u0000\u0000\u0011"+
		"9\u0001\u0000\u0000\u0000\u0013;\u0001\u0000\u0000\u0000\u0015>\u0001"+
		"\u0000\u0000\u0000\u0017D\u0001\u0000\u0000\u0000\u0019K\u0001\u0000\u0000"+
		"\u0000\u001bY\u0001\u0000\u0000\u0000\u001d\u001e\u0005-\u0000\u0000\u001e"+
		"\u0002\u0001\u0000\u0000\u0000\u001f \u0005-\u0000\u0000 !\u0005>\u0000"+
		"\u0000!\u0004\u0001\u0000\u0000\u0000\"#\u0005c\u0000\u0000#$\u0005h\u0000"+
		"\u0000$%\u0005e\u0000\u0000%&\u0005c\u0000\u0000&\'\u0005k\u0000\u0000"+
		"\'\u0006\u0001\u0000\u0000\u0000()\u0005:\u0000\u0000)\b\u0001\u0000\u0000"+
		"\u0000*+\u0005=\u0000\u0000+\n\u0001\u0000\u0000\u0000,-\u0005<\u0000"+
		"\u0000-.\u0005=\u0000\u0000.\f\u0001\u0000\u0000\u0000/0\u0005=\u0000"+
		"\u000001\u0005>\u0000\u00001\u000e\u0001\u0000\u0000\u000023\u0005a\u0000"+
		"\u000034\u0005c\u0000\u000045\u0005c\u0000\u000056\u0005e\u0000\u0000"+
		"67\u0005p\u0000\u000078\u0005t\u0000\u00008\u0010\u0001\u0000\u0000\u0000"+
		"9:\u0005+\u0000\u0000:\u0012\u0001\u0000\u0000\u0000;<\u0005\n\u0000\u0000"+
		"<\u0014\u0001\u0000\u0000\u0000=?\u0007\u0000\u0000\u0000>=\u0001\u0000"+
		"\u0000\u0000?@\u0001\u0000\u0000\u0000@>\u0001\u0000\u0000\u0000@A\u0001"+
		"\u0000\u0000\u0000AB\u0001\u0000\u0000\u0000BC\u0006\n\u0000\u0000C\u0016"+
		"\u0001\u0000\u0000\u0000DH\u0007\u0001\u0000\u0000EG\u0007\u0002\u0000"+
		"\u0000FE\u0001\u0000\u0000\u0000GJ\u0001\u0000\u0000\u0000HF\u0001\u0000"+
		"\u0000\u0000HI\u0001\u0000\u0000\u0000I\u0018\u0001\u0000\u0000\u0000"+
		"JH\u0001\u0000\u0000\u0000KL\u0005/\u0000\u0000LM\u0005*\u0000\u0000M"+
		"Q\u0001\u0000\u0000\u0000NP\t\u0000\u0000\u0000ON\u0001\u0000\u0000\u0000"+
		"PS\u0001\u0000\u0000\u0000QR\u0001\u0000\u0000\u0000QO\u0001\u0000\u0000"+
		"\u0000RT\u0001\u0000\u0000\u0000SQ\u0001\u0000\u0000\u0000TU\u0005*\u0000"+
		"\u0000UV\u0005/\u0000\u0000VW\u0001\u0000\u0000\u0000WX\u0006\f\u0000"+
		"\u0000X\u001a\u0001\u0000\u0000\u0000YZ\u0005/\u0000\u0000Z[\u0005/\u0000"+
		"\u0000[_\u0001\u0000\u0000\u0000\\^\t\u0000\u0000\u0000]\\\u0001\u0000"+
		"\u0000\u0000^a\u0001\u0000\u0000\u0000_`\u0001\u0000\u0000\u0000_]\u0001"+
		"\u0000\u0000\u0000`b\u0001\u0000\u0000\u0000a_\u0001\u0000\u0000\u0000"+
		"bc\u0005\n\u0000\u0000cd\u0001\u0000\u0000\u0000de\u0006\r\u0000\u0000"+
		"e\u001c\u0001\u0000\u0000\u0000\u0005\u0000@HQ_\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}