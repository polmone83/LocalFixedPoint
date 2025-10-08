package weighted_transitions_systems.WCTL;
// Generated from WCTL.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link WCTLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface WCTLVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link WCTLParser#decls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecls(WCTLParser.DeclsContext ctx);
	/**
	 * Visit a parse tree produced by {@link WCTLParser#formuladecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormuladecl(WCTLParser.FormuladeclContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BoundedUniversalFinally}
	 * labeled alternative in {@link WCTLParser#formula()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoundedUniversalFinally(WCTLParser.BoundedUniversalFinallyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Or}
	 * labeled alternative in {@link WCTLParser#formula()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOr(WCTLParser.OrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExistentialFinally}
	 * labeled alternative in {@link WCTLParser#formula()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExistentialFinally(WCTLParser.ExistentialFinallyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BoundedExistentialNext}
	 * labeled alternative in {@link WCTLParser#formula()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoundedExistentialNext(WCTLParser.BoundedExistentialNextContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Parens}
	 * labeled alternative in {@link WCTLParser#formula()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParens(WCTLParser.ParensContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BoundedExistentialUntil}
	 * labeled alternative in {@link WCTLParser#formula()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoundedExistentialUntil(WCTLParser.BoundedExistentialUntilContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UniversalUntil}
	 * labeled alternative in {@link WCTLParser#formula()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUniversalUntil(WCTLParser.UniversalUntilContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExistentialNext}
	 * labeled alternative in {@link WCTLParser#formula()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExistentialNext(WCTLParser.ExistentialNextContext ctx);
	/**
	 * Visit a parse tree produced by the {@code True}
	 * labeled alternative in {@link WCTLParser#formula()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrue(WCTLParser.TrueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code False}
	 * labeled alternative in {@link WCTLParser#formula()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFalse(WCTLParser.FalseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UniversalFinally}
	 * labeled alternative in {@link WCTLParser#formula()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUniversalFinally(WCTLParser.UniversalFinallyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BoundedExistentialFinally}
	 * labeled alternative in {@link WCTLParser#formula()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoundedExistentialFinally(WCTLParser.BoundedExistentialFinallyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BoundedUniversalUntil}
	 * labeled alternative in {@link WCTLParser#formula()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoundedUniversalUntil(WCTLParser.BoundedUniversalUntilContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExistentialUntil}
	 * labeled alternative in {@link WCTLParser#formula()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExistentialUntil(WCTLParser.ExistentialUntilContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UniversalNext}
	 * labeled alternative in {@link WCTLParser#formula()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUniversalNext(WCTLParser.UniversalNextContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Expression}
	 * labeled alternative in {@link WCTLParser#formula()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(WCTLParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code And}
	 * labeled alternative in {@link WCTLParser#formula()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd(WCTLParser.AndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BoundedUniversalNext}
	 * labeled alternative in {@link WCTLParser#formula()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoundedUniversalNext(WCTLParser.BoundedUniversalNextContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RelExpression}
	 * labeled alternative in {@link WCTLParser#relexpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelExpression(WCTLParser.RelExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Proposition}
	 * labeled alternative in {@link WCTLParser#relexpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProposition(WCTLParser.PropositionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Div}
	 * labeled alternative in {@link WCTLParser#expr()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDiv(WCTLParser.DivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Mult}
	 * labeled alternative in {@link WCTLParser#expr()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMult(WCTLParser.MultContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParensExpr}
	 * labeled alternative in {@link WCTLParser#expr()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParensExpr(WCTLParser.ParensExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Inverse}
	 * labeled alternative in {@link WCTLParser#expr()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInverse(WCTLParser.InverseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Plus}
	 * labeled alternative in {@link WCTLParser#expr()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlus(WCTLParser.PlusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Weight}
	 * labeled alternative in {@link WCTLParser#expr()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWeight(WCTLParser.WeightContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Minus}
	 * labeled alternative in {@link WCTLParser#expr()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMinus(WCTLParser.MinusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PropVar}
	 * labeled alternative in {@link WCTLParser#expr()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPropVar(WCTLParser.PropVarContext ctx);
}