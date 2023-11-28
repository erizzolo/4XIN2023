package summers;

import java.util.Objects;

/**
 * A worker that can compute the sum of the elements of an array in background
 *
 * @author emanuele
 */
public class BackgroundArraySummer implements Worker, Runnable {

    /**
     * The array of values to be summed
     */
    private final double[] values;
    /**
     * actual result
     */
    private double sum = 0.0;
    /**
     * number of values summed so far
     */
    private int summed = 0;
    /**
     * Whether work has been started
     */
    private boolean started = false;

    /**
     * Creates a new BackgroundArraySummer
     *
     * @param values array of values to be summed
     */
    public BackgroundArraySummer(double[] values) {
        this.values = Objects.requireNonNull(values, "Give me something real, please!!!");
    }

    @Override
    public void work() {
        if (!started) {
            debug("Putting myself to work...");
            // if "this" is runnable (yes it is)
            // new Thread(this).start();
            /* with lambdas
            new Thread(() -> {
                while (!isCompleted()) {
                    sum += values[summed++];
                }
                debug("Work is completed...");
            }).start();
             */
            // creates and start a new thread of execution
            new Thread(new Runnable() {
                // code to be run
                @Override
                public void run() {
                    while (!isCompleted()) {
                        sum += values[summed++];
                    }
                    debug("Work is completed...");
                }
            }).start();
            started = true;
        } else {
            if (!isCompleted()) {
                debug("Still working, please wait a second!!!");
            } else {
                debug("There's nothing else to be done !!!");
            }
        }
    }

    @Override
    public boolean isCompleted() {
        return summed == values.length;
    }

    @Override
    public Object getResult() {
        return isCompleted() ? sum : null;
    }

    /**
     * the code to be run by the new thread
     */
    @Override
    public void run() {
        while (!isCompleted()) {
            sum += values[summed++];
        }
        debug("Work is completed...");
    }

}
