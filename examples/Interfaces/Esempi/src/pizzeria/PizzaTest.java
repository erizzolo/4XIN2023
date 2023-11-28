package pizzeria;

import java.util.Arrays;

/**
 *
 * @author emanuele
 */
public class PizzaTest {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        pizzaTest(new BackgroundPizzaiolo(new Pizza(TipoPizza.QUATTRO_STAGIONI, TagliaPizza.GRANDE)));
        pizzaTest(new BossPizzaiolo(new Pizza[]{new Pizza(TipoPizza.ROMANA, TagliaPizza.NORMALE), new Pizza(TipoPizza.QUATTRO_STAGIONI, TagliaPizza.PICCOLA)}));
        pizzaTest(new Pizzaiolo(new Pizza(TipoPizza.MARGHERITA, TagliaPizza.ENORME)));
        pizzaTest(new MultiPizzaiolo(new Pizza[]{new Pizza(TipoPizza.ROMANA, TagliaPizza.PICCOLA), new Pizza(TipoPizza.QUATTRO_STAGIONI, TagliaPizza.NORMALE)}));
    }

    /**
     *
     * @param w
     */
    public static void pizzaTest(Worker w) {
        System.out.println("Testing " + w);
        System.out.println("w.getResult() " + w.getResult());
        System.out.println("w.work() ");
        w.work();
        if (!w.isCompleted()) {
            System.out.println("w.work() ");
            w.work();
        }
        System.out.println("w.work() ");
        w.work();
        System.out.println("w.work() until completed ...");
        while (!w.isCompleted()) {
            w.work();
            Thread.yield();
        }
        Object result = w.getResult();
        if (result instanceof Pizza[]) {
            System.out.println("Result is " + Arrays.toString((Pizza[]) result));
        } else {
            System.out.println("Result is " + result);
        }
    }
}
