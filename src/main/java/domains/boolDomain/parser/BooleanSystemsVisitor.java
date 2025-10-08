package domains.boolDomain.parser;

// Generated from BooleanSystems.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link BooleanSystemsParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface BooleanSystemsVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link BooleanSystemsParser#system}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSystem(BooleanSystemsParser.SystemContext ctx);
	/**
	 * Visit a parse tree produced by {@link BooleanSystemsParser#equation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEquation(BooleanSystemsParser.EquationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parens}
	 * labeled alternative in {@link BooleanSystemsParser#expr()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParens(BooleanSystemsParser.ParensContext ctx);
	/**
	 * Visit a parse tree produced by the {@code or}
	 * labeled alternative in {@link BooleanSystemsParser#expr()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOr(BooleanSystemsParser.OrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code var}
	 * labeled alternative in {@link BooleanSystemsParser#expr()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar(BooleanSystemsParser.VarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code and}
	 * labeled alternative in {@link BooleanSystemsParser#expr()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd(BooleanSystemsParser.AndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code false}
	 * labeled alternative in {@link BooleanSystemsParser#expr()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFalse(BooleanSystemsParser.FalseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code true}
	 * labeled alternative in {@link BooleanSystemsParser#expr()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrue(BooleanSystemsParser.TrueContext ctx);
}