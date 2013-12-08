package com.example.antlr;

//Generated from Complex.g4 by ANTLR 4.1
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
* This interface defines a complete generic visitor for a parse tree produced
* by {@link ComplexParser}.
*
* @param <T> The return type of the visit operation. Use {@link Void} for
* operations with no return type.
*/
public interface ComplexVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ComplexParser#id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(@NotNull ComplexParser.IdContext ctx);

	/**
	 * Visit a parse tree produced by {@link ComplexParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp(@NotNull ComplexParser.ExpContext ctx);

	/**
	 * Visit a parse tree produced by {@link ComplexParser#complex}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComplex(@NotNull ComplexParser.ComplexContext ctx);

	/**
	 * Visit a parse tree produced by {@link ComplexParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(@NotNull ComplexParser.ProgContext ctx);

	/**
	 * Visit a parse tree produced by {@link ComplexParser#printExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintExpr(@NotNull ComplexParser.PrintExprContext ctx);

	/**
	 * Visit a parse tree produced by {@link ComplexParser#cos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCos(@NotNull ComplexParser.CosContext ctx);

	/**
	 * Visit a parse tree produced by {@link ComplexParser#sine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSine(@NotNull ComplexParser.SineContext ctx);

	/**
	 * Visit a parse tree produced by {@link ComplexParser#AddSub}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSub(@NotNull ComplexParser.AddSubContext ctx);

	/**
	 * Visit a parse tree produced by {@link ComplexParser#parens}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParens(@NotNull ComplexParser.ParensContext ctx);

	/**
	 * Visit a parse tree produced by {@link ComplexParser#double}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDouble(@NotNull ComplexParser.DoubleContext ctx);

	/**
	 * Visit a parse tree produced by {@link ComplexParser#log}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLog(@NotNull ComplexParser.LogContext ctx);

	/**
	 * Visit a parse tree produced by {@link ComplexParser#MulDiv}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDiv(@NotNull ComplexParser.MulDivContext ctx);
}