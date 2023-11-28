package summers;

import java.util.Objects;

/**
 * A worker that can compute the sum of the elements of an array
 *
 * @author emanuele
 */
public class LazyArraySummer implements Worker {

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
     * Creates a new LazyArraySummer
     *
     * @param values array of values to be summed
     */
    public LazyArraySummer(double[] values) {
        this.values = Objects.requireNonNull(values, "Give me something real, please!!!");
    }

    @Override
    public void work() {
        if (!isCompleted()) {
            debug("Going to work a bit...");
            sum += values[summed++];
            if (isCompleted()) {
                debug("Finished...");
            } else {
                debug("Tired now, I need some rest...");
            }
        } else {
            debug("Job is already completed... I'm at rest");
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

}
