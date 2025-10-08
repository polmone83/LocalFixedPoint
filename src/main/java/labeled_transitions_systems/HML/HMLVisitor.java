package labeled_transitions_systems.HML;
// Generated from HML.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link HMLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface HMLVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link HMLParser#decls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecls(HMLParser.DeclsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MinDecl}
	 * labeled alternative in {@link HMLParser#formulaDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMinDecl(HMLParser.MinDeclContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MaxDecl}
	 * labeled alternative in {@link HMLParser#formulaDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMaxDecl(HMLParser.MaxDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link HMLParser#setDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetDecl(HMLParser.SetDeclContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Or}
	 * labeled alternative in {@link HMLParser#formula()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOr(HMLParser.OrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code WeakForAll}
	 * labeled alternative in {@link HMLParser#formula()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWeakForAll(HMLParser.WeakForAllContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Parens}
	 * labeled alternative in {@link HMLParser#formula()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParens(HMLParser.ParensContext ctx);
	/**
	 * Visit a parse tree produced by the {@code And}
	 * labeled alternative in {@link HMLParser#formula()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd(HMLParser.AndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code WeakExists}
	 * labeled alternative in {@link HMLParser#formula()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWeakExists(HMLParser.WeakExistsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code True}
	 * labeled alternative in {@link HMLParser#formula()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrue(HMLParser.TrueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code False}
	 * labeled alternative in {@link HMLParser#formula()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFalse(HMLParser.FalseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StrongExists}
	 * labeled alternative in {@link HMLParser#formula()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStrongExists(HMLParser.StrongExistsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FormulaName}
	 * labeled alternative in {@link HMLParser#formula()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormulaName(HMLParser.FormulaNameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StrongForAll}
	 * labeled alternative in {@link HMLParser#formula()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStrongForAll(HMLParser.StrongForAllContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ActionSequence}
	 * labeled alternative in {@link HMLParser#actions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActionSequence(HMLParser.ActionSequenceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Wildcard}
	 * labeled alternative in {@link HMLParser#actions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWildcard(HMLParser.WildcardContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SetName}
	 * labeled alternative in {@link HMLParser#actions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetName(HMLParser.SetNameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code OutputAction}
	 * labeled alternative in {@link HMLParser#action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOutputAction(HMLParser.OutputActionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Sync}
	 * labeled alternative in {@link HMLParser#action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSync(HMLParser.SyncContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InputAction}
	 * labeled alternative in {@link HMLParser#action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInputAction(HMLParser.InputActionContext ctx);
}