package com.example.antlr;
//Generated from Complex.g4 by ANTLR 4.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ComplexLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__3=1, T__2=2, T__1=3, T__0=4, MUL=5, DIV=6, ADD=7, SUB=8, SIN=9, COS=10, 
		EXP=11, LOG=12, DBL=13, COMPL=14, ID=15, NEWLINE=16, WS=17;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'^'", "')'", "'**'", "'('", "'*'", "'/'", "'+'", "'-'", "'sin'", "'cos'", 
		"'exp'", "'log'", "DBL", "COMPL", "ID", "NEWLINE", "WS"
	};
	public static final String[] ruleNames = {
		"T__3", "T__2", "T__1", "T__0", "MUL", "DIV", "ADD", "SUB", "SIN", "COS", 
		"EXP", "LOG", "DBL", "COMPL", "ID", "NEWLINE", "WS"
	};


	public ComplexLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Complex.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 16: WS_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\23u\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3"+
		"\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16"+
		"\6\16H\n\16\r\16\16\16I\3\16\3\16\6\16N\n\16\r\16\16\16O\5\16R\n\16\3"+
		"\17\6\17U\n\17\r\17\16\17V\3\17\3\17\6\17[\n\17\r\17\16\17\\\5\17_\n\17"+
		"\5\17a\n\17\3\17\3\17\3\20\6\20f\n\20\r\20\16\20g\3\21\5\21k\n\21\3\21"+
		"\3\21\3\22\6\22p\n\22\r\22\16\22q\3\22\3\22\2\23\3\3\1\5\4\1\7\5\1\t\6"+
		"\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31\16\1\33\17\1\35"+
		"\20\1\37\21\1!\22\1#\23\2\3\2\4\4\2C\\c|\4\2\13\13\"\"~\2\3\3\2\2\2\2"+
		"\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2"+
		"\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2"+
		"\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\3%\3\2\2\2"+
		"\5\'\3\2\2\2\7)\3\2\2\2\t,\3\2\2\2\13.\3\2\2\2\r\60\3\2\2\2\17\62\3\2"+
		"\2\2\21\64\3\2\2\2\23\66\3\2\2\2\25:\3\2\2\2\27>\3\2\2\2\31B\3\2\2\2\33"+
		"G\3\2\2\2\35`\3\2\2\2\37e\3\2\2\2!j\3\2\2\2#o\3\2\2\2%&\7`\2\2&\4\3\2"+
		"\2\2\'(\7+\2\2(\6\3\2\2\2)*\7,\2\2*+\7,\2\2+\b\3\2\2\2,-\7*\2\2-\n\3\2"+
		"\2\2./\7,\2\2/\f\3\2\2\2\60\61\7\61\2\2\61\16\3\2\2\2\62\63\7-\2\2\63"+
		"\20\3\2\2\2\64\65\7/\2\2\65\22\3\2\2\2\66\67\7u\2\2\678\7k\2\289\7p\2"+
		"\29\24\3\2\2\2:;\7e\2\2;<\7q\2\2<=\7u\2\2=\26\3\2\2\2>?\7g\2\2?@\7z\2"+
		"\2@A\7r\2\2A\30\3\2\2\2BC\7n\2\2CD\7q\2\2DE\7i\2\2E\32\3\2\2\2FH\4\62"+
		";\2GF\3\2\2\2HI\3\2\2\2IG\3\2\2\2IJ\3\2\2\2JQ\3\2\2\2KM\7\60\2\2LN\4\62"+
		";\2ML\3\2\2\2NO\3\2\2\2OM\3\2\2\2OP\3\2\2\2PR\3\2\2\2QK\3\2\2\2QR\3\2"+
		"\2\2R\34\3\2\2\2SU\4\62;\2TS\3\2\2\2UV\3\2\2\2VT\3\2\2\2VW\3\2\2\2W^\3"+
		"\2\2\2XZ\7\60\2\2Y[\4\62;\2ZY\3\2\2\2[\\\3\2\2\2\\Z\3\2\2\2\\]\3\2\2\2"+
		"]_\3\2\2\2^X\3\2\2\2^_\3\2\2\2_a\3\2\2\2`T\3\2\2\2`a\3\2\2\2ab\3\2\2\2"+
		"bc\7k\2\2c\36\3\2\2\2df\t\2\2\2ed\3\2\2\2fg\3\2\2\2ge\3\2\2\2gh\3\2\2"+
		"\2h \3\2\2\2ik\7\17\2\2ji\3\2\2\2jk\3\2\2\2kl\3\2\2\2lm\7\f\2\2m\"\3\2"+
		"\2\2np\t\3\2\2on\3\2\2\2pq\3\2\2\2qo\3\2\2\2qr\3\2\2\2rs\3\2\2\2st\b\22"+
		"\2\2t$\3\2\2\2\r\2IOQV\\^`gjq";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}