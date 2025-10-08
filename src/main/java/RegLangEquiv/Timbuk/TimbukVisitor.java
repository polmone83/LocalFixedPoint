package RegLangEquiv.Timbuk;
// Generated from Timbuk.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TimbukParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface TimbukVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link TimbukParser#ops}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOps(TimbukParser.OpsContext ctx);
	/**
	 * Visit a parse tree produced by {@link TimbukParser#labeldecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabeldecl(TimbukParser.LabeldeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link TimbukParser#automaton}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAutomaton(TimbukParser.AutomatonContext ctx);
	/**
	 * Visit a parse tree produced by {@link TimbukParser#states}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStates(TimbukParser.StatesContext ctx);
	/**
	 * Visit a parse tree produced by {@link TimbukParser#finalstates}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFinalstates(TimbukParser.FinalstatesContext ctx);
	/**
	 * Visit a parse tree produced by {@link TimbukParser#transitions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTransitions(TimbukParser.TransitionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link TimbukParser#transition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTransition(TimbukParser.TransitionContext ctx);
}