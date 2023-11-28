package summers;

/**
 *
 * @author emanuele
 */
public class SummerTest {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        double[] values = {1, 2.5, 3.14, 5, -2};
        summerTest(new BackgroundArraySummer(values));
        summerTest(new LazyArraySummer(values));
        summerTest(new ArraySummer(values));
    }

    /**
     *
     * @param w
     */
    public static void summerTest(Worker w) {
        System.out.println("Testing " + w);
        w.work();
        if (!w.isCompleted()) {
            w.work();
        }
        w.work();
        while (!w.isCompleted()) {
            w.work();
        }
        System.out.println("Result is " + w.getResult());
    }

}
