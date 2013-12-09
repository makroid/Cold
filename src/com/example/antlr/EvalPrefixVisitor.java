package com.example.antlr;
/***
 * Excerpted from "The Definitive ANTLR 4 Reference",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/tpantlr2 for more book information.
***/

import java.util.ArrayList;

public class EvalPrefixVisitor extends ComplexBaseVisitor<String> {

    public String resultFunc = "";
    public ArrayList<String> variables = new ArrayList<String>();

    public void reset() {
    	resultFunc = "";
    	variables.clear();
    }
    
    /** expr NEWLINE */
    @Override
    public String visitPrintExpr(ComplexParser.PrintExprContext ctx) {
        String value = visit(ctx.expr()); // evaluate the expr child
        resultFunc = value;
        return value;                      
    }
    
    @Override
    public String visitUnaryMinus(ComplexParser.UnaryMinusContext ctx) {
    	String expr = visit(ctx.expr());
    	return "complexMinus(vec2(0.0,0.0)," + expr + ")";
    }

    /** ID */
    @Override
	public String visitId(ComplexParser.IdContext ctx) {
		String id = ctx.ID().getText();
		// z is the "main" variables and not editable!
		if ( ! id.equals("z")) {
			variables.add(id);
		}
		return id;
	}

	/** DBL */
	@Override
	public String visitDouble(ComplexParser.DoubleContext ctx) {
		String dbl = ctx.DBL().getText();
		if (dbl.length() > 0 && isInteger(dbl)) {
			dbl = dbl + ".0";
		}
		return "vec2(" + dbl + ",0.0)";
	}

	/** COMPLEX */
	@Override
	public String visitComplex(ComplexParser.ComplexContext ctx) {
		String complex = ctx.COMPL().getText();
		if (complex.length() > 0 && complex.charAt(complex.length() - 1) == 'i') {
			complex = complex.substring(0, complex.length() - 1);
			if (complex.length() > 0 && isInteger(complex)) {
				complex = complex + ".0";
			}
		}
		if (complex.length() == 0) {
			complex = "1.0";
		}
		return "vec2(0.0," + complex + ")";
	}

	/** expr op=('*'|'/') expr */
	@Override
	public String visitMulDiv(ComplexParser.MulDivContext ctx) {
		String left = visit(ctx.expr(0)); // get value of left subexpression
		String right = visit(ctx.expr(1)); // get value of right subexpression
		if (ctx.op.getType() == ComplexParser.MUL)
			return "complexMult(" + left + "," + right + ")";
		return "complexDiv(" + left + "," + right + ")"; // must be DIV
	}

	/** SIN'('expr')' */
	@Override
	public String visitSine(ComplexParser.SineContext ctx) {
		String value = visit(ctx.expr()); // get value of left subexpression
		return "complexSin(" + value + ")";
	}

	/** COS'('expr')' */
	@Override
	public String visitCos(ComplexParser.CosContext ctx) {
		String value = visit(ctx.expr()); // get value of left subexpression
		return "complexCos(" + value + ")";
	}

	/** EXP'('expr')' */
	@Override
	public String visitExp(ComplexParser.ExpContext ctx) {
		String value = visit(ctx.expr()); // get value of left subexpression
		return "complexExp(" + value + ")";
	}

	/** LOG'('expr')' */
	@Override
	public String visitLog(ComplexParser.LogContext ctx) {
		String value = visit(ctx.expr()); // get value of left subexpression
		return "complexLog(" + value + ")";
	}

	/** expr op=('+'|'-') expr */
	@Override
	public String visitAddSub(ComplexParser.AddSubContext ctx) {
		String left = visit(ctx.expr(0)); // get value of left subexpression
		String right = visit(ctx.expr(1)); // get value of right subexpression
		if (ctx.op.getType() == ComplexParser.ADD)
			return "complexPlus(" + left + "," + right + ")";
		return "complexMinus(" + left + "," + right + ")"; // must be SUB
	}

	/** '(' expr ')' */
	@Override
	public String visitParens(ComplexParser.ParensContext ctx) {
		return visit(ctx.expr()); // return child expr's value
	}

	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		}
		// only got here if we didn't return false
		return true;
	}
}