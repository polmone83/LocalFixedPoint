package RegLangEquiv.MATA;
// Generated from RegLangEquiv.MATA.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MATAParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MATAVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MATAParser#automaton}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAutomaton(MATAParser.AutomatonContext ctx);
	/**
	 * Visit a parse tree produced by {@link MATAParser#preamle}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPreamle(MATAParser.PreamleContext ctx);
	/**
	 * Visit a parse tree produced by {@link MATAParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(MATAParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MATAParser#keyvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyvalue(MATAParser.KeyvalueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Initital}
	 * labeled alternative in {@link MATAParser#key}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitital(MATAParser.InititalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Final}
	 * labeled alternative in {@link MATAParser#key}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFinal(MATAParser.FinalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatesAuto}
	 * labeled alternative in {@link MATAParser#key}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatesAuto(MATAParser.StatesAutoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Alphabet_utf}
	 * labeled alternative in {@link MATAParser#key}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlphabet_utf(MATAParser.Alphabet_utfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Alphabet_auto}
	 * labeled alternative in {@link MATAParser#key}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlphabet_auto(MATAParser.Alphabet_autoContext ctx);
	/**
	 * Visit a parse tree produced by {@link MATAParser#transition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTransition(MATAParser.TransitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MATAParser#states}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStates(MATAParser.StatesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code neg}
	 * labeled alternative in {@link MATAParser#expr()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNeg(MATAParser.NegContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parens}
	 * labeled alternative in {@link MATAParser#expr()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParens(MATAParser.ParensContext ctx);
	/**
	 * Visit a parse tree produced by the {@code or}
	 * labeled alternative in {@link MATAParser#expr()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOr(MATAParser.OrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code var}
	 * labeled alternative in {@link MATAParser#expr()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar(MATAParser.VarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code and}
	 * labeled alternative in {@link MATAParser#expr()}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd(MATAParser.AndContext ctx);
}