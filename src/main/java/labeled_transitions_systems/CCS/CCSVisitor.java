package labeled_transitions_systems.CCS;
// Generated from CCS.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CCSParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CCSVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CCSParser#system}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSystem(CCSParser.SystemContext ctx);
	/**
	 * Visit a parse tree produced by {@link CCSParser#pDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPDecl(CCSParser.PDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link CCSParser#setDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetDecl(CCSParser.SetDeclContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Nil}
	 * labeled alternative in {@link CCSParser#process()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNil(CCSParser.NilContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Choice}
	 * labeled alternative in {@link CCSParser#process()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChoice(CCSParser.ChoiceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RestricSet}
	 * labeled alternative in {@link CCSParser#process()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRestricSet(CCSParser.RestricSetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InputAction}
	 * labeled alternative in {@link CCSParser#process()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInputAction(CCSParser.InputActionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Parens}
	 * labeled alternative in {@link CCSParser#process()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParens(CCSParser.ParensContext ctx);
	/**
	 * Visit a parse tree produced by the {@code OutputAction}
	 * labeled alternative in {@link CCSParser#process()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOutputAction(CCSParser.OutputActionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SyncAction}
	 * labeled alternative in {@link CCSParser#process()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSyncAction(CCSParser.SyncActionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PName}
	 * labeled alternative in {@link CCSParser#process()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPName(CCSParser.PNameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Parallel}
	 * labeled alternative in {@link CCSParser#process()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParallel(CCSParser.ParallelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RestricExplicit}
	 * labeled alternative in {@link CCSParser#process()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRestricExplicit(CCSParser.RestricExplicitContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Rename}
	 * labeled alternative in {@link CCSParser#process()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRename(CCSParser.RenameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CCSParser#channelSet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChannelSet(CCSParser.ChannelSetContext ctx);
	/**
	 * Visit a parse tree produced by {@link CCSParser#renaming}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRenaming(CCSParser.RenamingContext ctx);
}