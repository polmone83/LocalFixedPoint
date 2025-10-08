package domains.posDomain.prologparser;
// Generated from PureProlog.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link PurePrologParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface PurePrologVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link PurePrologParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(PurePrologParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link PurePrologParser#clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClause(PurePrologParser.ClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link PurePrologParser#fact}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFact(PurePrologParser.FactContext ctx);
	/**
	 * Visit a parse tree produced by the {@code namedPredicate}
	 * labeled alternative in {@link PurePrologParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamedPredicate(PurePrologParser.NamedPredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code truePredicate}
	 * labeled alternative in {@link PurePrologParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTruePredicate(PurePrologParser.TruePredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code functional}
	 * labeled alternative in {@link PurePrologParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctional(PurePrologParser.FunctionalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code listEnum}
	 * labeled alternative in {@link PurePrologParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListEnum(PurePrologParser.ListEnumContext ctx);
	/**
	 * Visit a parse tree produced by the {@code listPattern}
	 * labeled alternative in {@link PurePrologParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListPattern(PurePrologParser.ListPatternContext ctx);
	/**
	 * Visit a parse tree produced by the {@code constant}
	 * labeled alternative in {@link PurePrologParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(PurePrologParser.ConstantContext ctx);
	/**
	 * Visit a parse tree produced by the {@code var}
	 * labeled alternative in {@link PurePrologParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar(PurePrologParser.VarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code namedVar}
	 * labeled alternative in {@link PurePrologParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamedVar(PurePrologParser.NamedVarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code wildcard}
	 * labeled alternative in {@link PurePrologParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWildcard(PurePrologParser.WildcardContext ctx);
	/**
	 * Visit a parse tree produced by the {@code namedConst}
	 * labeled alternative in {@link PurePrologParser#const_()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamedConst(PurePrologParser.NamedConstContext ctx);
	/**
	 * Visit a parse tree produced by the {@code number}
	 * labeled alternative in {@link PurePrologParser#const_()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(PurePrologParser.NumberContext ctx);
}