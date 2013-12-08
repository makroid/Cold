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
		T__1=1, T__0=2, MUL=3, DIV=4, ADD=5, SUB=6, SIN=7, COS=8, EXP=9, LOG=10, 
		DBL=11, COMPL=12, ID=13, NEWLINE=14, WS=15;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"')'", "'('", "'*'", "'/'", "'+'", "'-'", "'sin'", "'cos'", "'exp'", "'log'", 
		"DBL", "COMPL", "ID", "NEWLINE", "WS"
	};
	public static final String[] ruleNames = {
		"T__1", "T__0", "MUL", "DIV", "ADD", "SUB", "SIN", "COS", "EXP", "LOG", 
		"DBL", "COMPL", "ID", "NEWLINE", "WS"
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
		case 14: WS_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\21l\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\3\3\3\3\4"+
		"\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3"+
		"\n\3\n\3\13\3\13\3\13\3\13\3\f\6\f?\n\f\r\f\16\f@\3\f\3\f\6\fE\n\f\r\f"+
		"\16\fF\5\fI\n\f\3\r\6\rL\n\r\r\r\16\rM\3\r\3\r\6\rR\n\r\r\r\16\rS\5\r"+
		"V\n\r\5\rX\n\r\3\r\3\r\3\16\6\16]\n\16\r\16\16\16^\3\17\5\17b\n\17\3\17"+
		"\3\17\3\20\6\20g\n\20\r\20\16\20h\3\20\3\20\2\21\3\3\1\5\4\1\7\5\1\t\6"+
		"\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31\16\1\33\17\1\35"+
		"\20\1\37\21\2\3\2\4\4\2C\\c|\4\2\13\13\"\"u\2\3\3\2\2\2\2\5\3\2\2\2\2"+
		"\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2"+
		"\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2"+
		"\2\35\3\2\2\2\2\37\3\2\2\2\3!\3\2\2\2\5#\3\2\2\2\7%\3\2\2\2\t\'\3\2\2"+
		"\2\13)\3\2\2\2\r+\3\2\2\2\17-\3\2\2\2\21\61\3\2\2\2\23\65\3\2\2\2\259"+
		"\3\2\2\2\27>\3\2\2\2\31W\3\2\2\2\33\\\3\2\2\2\35a\3\2\2\2\37f\3\2\2\2"+
		"!\"\7+\2\2\"\4\3\2\2\2#$\7*\2\2$\6\3\2\2\2%&\7,\2\2&\b\3\2\2\2\'(\7\61"+
		"\2\2(\n\3\2\2\2)*\7-\2\2*\f\3\2\2\2+,\7/\2\2,\16\3\2\2\2-.\7u\2\2./\7"+
		"k\2\2/\60\7p\2\2\60\20\3\2\2\2\61\62\7e\2\2\62\63\7q\2\2\63\64\7u\2\2"+
		"\64\22\3\2\2\2\65\66\7g\2\2\66\67\7z\2\2\678\7r\2\28\24\3\2\2\29:\7n\2"+
		"\2:;\7q\2\2;<\7i\2\2<\26\3\2\2\2=?\4\62;\2>=\3\2\2\2?@\3\2\2\2@>\3\2\2"+
		"\2@A\3\2\2\2AH\3\2\2\2BD\7\60\2\2CE\4\62;\2DC\3\2\2\2EF\3\2\2\2FD\3\2"+
		"\2\2FG\3\2\2\2GI\3\2\2\2HB\3\2\2\2HI\3\2\2\2I\30\3\2\2\2JL\4\62;\2KJ\3"+
		"\2\2\2LM\3\2\2\2MK\3\2\2\2MN\3\2\2\2NU\3\2\2\2OQ\7\60\2\2PR\4\62;\2QP"+
		"\3\2\2\2RS\3\2\2\2SQ\3\2\2\2ST\3\2\2\2TV\3\2\2\2UO\3\2\2\2UV\3\2\2\2V"+
		"X\3\2\2\2WK\3\2\2\2WX\3\2\2\2XY\3\2\2\2YZ\7k\2\2Z\32\3\2\2\2[]\t\2\2\2"+
		"\\[\3\2\2\2]^\3\2\2\2^\\\3\2\2\2^_\3\2\2\2_\34\3\2\2\2`b\7\17\2\2a`\3"+
		"\2\2\2ab\3\2\2\2bc\3\2\2\2cd\7\f\2\2d\36\3\2\2\2eg\t\3\2\2fe\3\2\2\2g"+
		"h\3\2\2\2hf\3\2\2\2hi\3\2\2\2ij\3\2\2\2jk\b\20\2\2k \3\2\2\2\r\2@FHMS"+
		"UW^ah";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}