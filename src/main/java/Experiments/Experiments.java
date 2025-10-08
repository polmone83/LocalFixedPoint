package Experiments;

public interface Experiments {

    /**
     * Run a single experiment on a set of arguments (to be found at the given path)
     * @param args input file names
     * @param path path where the input files are (empty if there is no common path)
     * @return the outcome of the experiment
     */
    String runSingle(String[] args, String path);
}
