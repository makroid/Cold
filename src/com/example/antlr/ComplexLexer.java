package com.example.antlr;
// Generated from Complex.g4 by ANTLR 4.1
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
		T__4=1, T__3=2, T__2=3, T__1=4, T__0=5, MUL=6, DIV=7, ADD=8, SUB=9, SIN=10, 
		COS=11, EXP=12, LOG=13, ROT=14, INV=15, IDIS=16, DBL=17, COMPL=18, ID=19, 
		NEWLINE=20, WS=21;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'^'", "')'", "','", "'**'", "'('", "'*'", "'/'", "'+'", "'-'", "'sin'", 
		"'cos'", "'exp'", "'log'", "'rot'", "'inv'", "'idist'", "DBL", "COMPL", 
		"ID", "NEWLINE", "WS"
	};
	public static final String[] ruleNames = {
		"T__4", "T__3", "T__2", "T__1", "T__0", "MUL", "DIV", "ADD", "SUB", "SIN", 
		"COS", "EXP", "LOG", "ROT", "INV", "IDIS", "DBL", "COMPL", "ID", "NEWLINE", 
		"WS"
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
		case 20: WS_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\27\u008d\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\3\3\3\3\4\3\4"+
		"\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3"+
		"\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3\17"+
		"\3\17\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\22\6\22`\n\22"+
		"\r\22\16\22a\3\22\3\22\6\22f\n\22\r\22\16\22g\5\22j\n\22\3\23\6\23m\n"+
		"\23\r\23\16\23n\3\23\3\23\6\23s\n\23\r\23\16\23t\5\23w\n\23\5\23y\n\23"+
		"\3\23\3\23\3\24\6\24~\n\24\r\24\16\24\177\3\25\5\25\u0083\n\25\3\25\3"+
		"\25\3\26\6\26\u0088\n\26\r\26\16\26\u0089\3\26\3\26\2\27\3\3\1\5\4\1\7"+
		"\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31\16\1\33"+
		"\17\1\35\20\1\37\21\1!\22\1#\23\1%\24\1\'\25\1)\26\1+\27\2\3\2\4\4\2C"+
		"\\c|\4\2\13\13\"\"\u0096\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2"+
		"\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2"+
		"\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2"+
		"\2\3-\3\2\2\2\5/\3\2\2\2\7\61\3\2\2\2\t\63\3\2\2\2\13\66\3\2\2\2\r8\3"+
		"\2\2\2\17:\3\2\2\2\21<\3\2\2\2\23>\3\2\2\2\25@\3\2\2\2\27D\3\2\2\2\31"+
		"H\3\2\2\2\33L\3\2\2\2\35P\3\2\2\2\37T\3\2\2\2!X\3\2\2\2#_\3\2\2\2%x\3"+
		"\2\2\2\'}\3\2\2\2)\u0082\3\2\2\2+\u0087\3\2\2\2-.\7`\2\2.\4\3\2\2\2/\60"+
		"\7+\2\2\60\6\3\2\2\2\61\62\7.\2\2\62\b\3\2\2\2\63\64\7,\2\2\64\65\7,\2"+
		"\2\65\n\3\2\2\2\66\67\7*\2\2\67\f\3\2\2\289\7,\2\29\16\3\2\2\2:;\7\61"+
		"\2\2;\20\3\2\2\2<=\7-\2\2=\22\3\2\2\2>?\7/\2\2?\24\3\2\2\2@A\7u\2\2AB"+
		"\7k\2\2BC\7p\2\2C\26\3\2\2\2DE\7e\2\2EF\7q\2\2FG\7u\2\2G\30\3\2\2\2HI"+
		"\7g\2\2IJ\7z\2\2JK\7r\2\2K\32\3\2\2\2LM\7n\2\2MN\7q\2\2NO\7i\2\2O\34\3"+
		"\2\2\2PQ\7t\2\2QR\7q\2\2RS\7v\2\2S\36\3\2\2\2TU\7k\2\2UV\7p\2\2VW\7x\2"+
		"\2W \3\2\2\2XY\7k\2\2YZ\7f\2\2Z[\7k\2\2[\\\7u\2\2\\]\7v\2\2]\"\3\2\2\2"+
		"^`\4\62;\2_^\3\2\2\2`a\3\2\2\2a_\3\2\2\2ab\3\2\2\2bi\3\2\2\2ce\7\60\2"+
		"\2df\4\62;\2ed\3\2\2\2fg\3\2\2\2ge\3\2\2\2gh\3\2\2\2hj\3\2\2\2ic\3\2\2"+
		"\2ij\3\2\2\2j$\3\2\2\2km\4\62;\2lk\3\2\2\2mn\3\2\2\2nl\3\2\2\2no\3\2\2"+
		"\2ov\3\2\2\2pr\7\60\2\2qs\4\62;\2rq\3\2\2\2st\3\2\2\2tr\3\2\2\2tu\3\2"+
		"\2\2uw\3\2\2\2vp\3\2\2\2vw\3\2\2\2wy\3\2\2\2xl\3\2\2\2xy\3\2\2\2yz\3\2"+
		"\2\2z{\7k\2\2{&\3\2\2\2|~\t\2\2\2}|\3\2\2\2~\177\3\2\2\2\177}\3\2\2\2"+
		"\177\u0080\3\2\2\2\u0080(\3\2\2\2\u0081\u0083\7\17\2\2\u0082\u0081\3\2"+
		"\2\2\u0082\u0083\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0085\7\f\2\2\u0085"+
		"*\3\2\2\2\u0086\u0088\t\3\2\2\u0087\u0086\3\2\2\2\u0088\u0089\3\2\2\2"+
		"\u0089\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u008c"+
		"\b\26\2\2\u008c,\3\2\2\2\r\2agintvx\177\u0082\u0089";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}