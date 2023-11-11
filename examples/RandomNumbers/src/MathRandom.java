import java.util.Random;

/**
 * This class uses Math.random to simulate {@link java.util.Random}
 *
 * Just for demonstrative purpose: DON'T do it!
 */
public class MathRandom extends Random {

    private static final long serialVersionUID = 1L;
    private static final int MINVALUE = Integer.MIN_VALUE;
    private static final long NUMVALUES = ((long) Integer.MAX_VALUE + 1) - Integer.MIN_VALUE;

    @Override
    public double nextDouble() {
        return Math.random();
    }

    @Override
    public int nextInt() {
        return (int) (MINVALUE + Math.random() * NUMVALUES);
    }

    @Override
    public int nextInt(int bound) {
        return (int) (Math.random() * bound);
    }

}
