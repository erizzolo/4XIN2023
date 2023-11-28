package summers;

import java.util.Objects;

/**
 * A worker that can compute the sum of the elements of an array
 *
 * @author emanuele
 */
public class ArraySummer implements Worker {

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
     * Creates a new ArraySummer
     *
     * @param values array of values to be summed
     */
    public ArraySummer(double[] values) {
        this.values = Objects.requireNonNull(values, "Give me something real, please!!!");
    }

    @Override
    public void work() {
        if (!isCompleted()) {
            debug("Work needs to be done ...");
            while (!isCompleted()) {
                sum += values[summed++];
            }
            debug("Work is completed...");
        } else {
            debug("There's nothing else to be done !!!");
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
