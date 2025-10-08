package domains.posDomain.groudness;
// Generated from groundSystem.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link groundSystemParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface groundSystemVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link groundSystemParser#system}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSystem(groundSystemParser.SystemContext ctx);
	/**
	 * Visit a parse tree produced by {@link groundSystemParser#equation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEquation(groundSystemParser.EquationContext ctx);
	/**
	 * Visit a parse tree produced by {@link groundSystemParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicate(groundSystemParser.PredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code call}
	 * labeled alternative in {@link groundSystemParser#expr()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCall(groundSystemParser.CallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parens}
	 * labeled alternative in {@link groundSystemParser#expr()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParens(groundSystemParser.ParensContext ctx);
	/**
	 * Visit a parse tree produced by the {@code or}
	 * labeled alternative in {@link groundSystemParser#expr()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOr(groundSystemParser.OrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code var}
	 * labeled alternative in {@link groundSystemParser#expr()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar(groundSystemParser.VarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code and}
	 * labeled alternative in {@link groundSystemParser#expr()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd(groundSystemParser.AndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code false}
	 * labeled alternative in {@link groundSystemParser#expr()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFalse(groundSystemParser.FalseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code true}
	 * labeled alternative in {@link groundSystemParser#expr()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrue(groundSystemParser.TrueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code restriction}
	 * labeled alternative in {@link groundSystemParser#expr()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRestriction(groundSystemParser.RestrictionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code iff}
	 * labeled alternative in {@link groundSystemParser#expr()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIff(groundSystemParser.IffContext ctx);
}