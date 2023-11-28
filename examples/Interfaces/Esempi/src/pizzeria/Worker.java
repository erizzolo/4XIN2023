package pizzeria;

/**
 * An interface for something that can do something (a job)
 *
 * @author Emanuele
 */
public interface Worker {

    /**
     * to get or not to get debug output
     */
    boolean DEBUG = true;   // change to false if needed

    /**
     * Do some work
     */
    void work();

    /**
     * Return whether the job is completed or not
     *
     * @return whether the job is completed or not
     */
    boolean isCompleted();

    /**
     * Return the result of the job if it is completed, null otherwise
     *
     * @return the result of the job if it is completed, null otherwise
     */
    Object getResult();

    /**
     * Print the given String to System.out if DEBUG is true
     *
     * @param what the String to be printed
     */
    default void debug(String what) {
        if (DEBUG) {
            System.out.println(what);
        }
    }
}
