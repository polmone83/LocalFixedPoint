package RegLangEquiv.NFA;
// Generated from RegLangEquiv.NFA.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link NFAParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface NFAVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link NFAParser#automaton}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAutomaton(NFAParser.AutomatonContext ctx);
	/**
	 * Visit a parse tree produced by {@link NFAParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(NFAParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link NFAParser#transition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTransition(NFAParser.TransitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Equivalence}
	 * labeled alternative in {@link NFAParser#check}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEquivalence(NFAParser.EquivalenceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Inclusion}
	 * labeled alternative in {@link NFAParser#check}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInclusion(NFAParser.InclusionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Containment}
	 * labeled alternative in {@link NFAParser#check}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContainment(NFAParser.ContainmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link NFAParser#accept}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAccept(NFAParser.AcceptContext ctx);
	/**
	 * Visit a parse tree produced by {@link NFAParser#states}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStates(NFAParser.StatesContext ctx);
	/**
	 * Visit a parse tree produced by {@link NFAParser#labels}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabels(NFAParser.LabelsContext ctx);
}