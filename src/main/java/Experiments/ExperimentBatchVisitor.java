package Experiments;
// Generated from ExperimentBatch.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ExperimentBatchParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ExperimentBatchVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ExperimentBatchParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(ExperimentBatchParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExperimentBatchParser#path}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPath(ExperimentBatchParser.PathContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExperimentBatchParser#output}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOutput(ExperimentBatchParser.OutputContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExperimentBatchParser#experiment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExperiment(ExperimentBatchParser.ExperimentContext ctx);
}