package weighted_transitions_systems.WCCS;
// Generated from WCCS.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link WCCSParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface WCCSVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link WCCSParser#system}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSystem(WCCSParser.SystemContext ctx);
	/**
	 * Visit a parse tree produced by {@link WCCSParser#pDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPDecl(WCCSParser.PDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link WCCSParser#setDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetDecl(WCCSParser.SetDeclContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Nil}
	 * labeled alternative in {@link WCCSParser#process()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNil(WCCSParser.NilContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LabelRename}
	 * labeled alternative in {@link WCCSParser#process()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabelRename(WCCSParser.LabelRenameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Choice}
	 * labeled alternative in {@link WCCSParser#process()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChoice(WCCSParser.ChoiceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RestricSet}
	 * labeled alternative in {@link WCCSParser#process()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRestricSet(WCCSParser.RestricSetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AtomicLabel}
	 * labeled alternative in {@link WCCSParser#process()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomicLabel(WCCSParser.AtomicLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ActionPrefix}
	 * labeled alternative in {@link WCCSParser#process()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActionPrefix(WCCSParser.ActionPrefixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Parens}
	 * labeled alternative in {@link WCCSParser#process()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParens(WCCSParser.ParensContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PName}
	 * labeled alternative in {@link WCCSParser#process()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPName(WCCSParser.PNameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Parallel}
	 * labeled alternative in {@link WCCSParser#process()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParallel(WCCSParser.ParallelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RestricExplicit}
	 * labeled alternative in {@link WCCSParser#process()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRestricExplicit(WCCSParser.RestricExplicitContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Rename}
	 * labeled alternative in {@link WCCSParser#process()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRename(WCCSParser.RenameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InputAction}
	 * labeled alternative in {@link WCCSParser#action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInputAction(WCCSParser.InputActionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code OutputAction}
	 * labeled alternative in {@link WCCSParser#action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOutputAction(WCCSParser.OutputActionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SyncAction}
	 * labeled alternative in {@link WCCSParser#action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSyncAction(WCCSParser.SyncActionContext ctx);
	/**
	 * Visit a parse tree produced by {@link WCCSParser#channelSet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChannelSet(WCCSParser.ChannelSetContext ctx);
	/**
	 * Visit a parse tree produced by {@link WCCSParser#renaming}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRenaming(WCCSParser.RenamingContext ctx);
	/**
	 * Visit a parse tree produced by {@link WCCSParser#labelRenaming}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabelRenaming(WCCSParser.LabelRenamingContext ctx);
}