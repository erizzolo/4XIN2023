package pizzeria;

import java.util.Arrays;
import java.util.Objects;

/**
 * A worker that can prepare multiple Pizzas hiring other Pizzaioli
 *
 * @author emanuele
 */
public class BossPizzaiolo implements Worker {

    private Pizza[] pizza;

    /**
     * Creates a new BossPizzaiolo for the given Pizza[]
     *
     * @param pizza the Pizza[] to be done
     */
    public BossPizzaiolo(Pizza[] pizza) {
        this.pizza = Objects.requireNonNull(pizza, "Ordina delle pizze, per favore!!!");
        for (Pizza p : pizza) {
            Objects.requireNonNull(p, "Ma che razza di pizza è???!!!");
        }
        this.pizza = Arrays.copyOf(pizza, pizza.length);
    }

    @Override
    public void work() {
        // hire one Pizzaiolo for each pizza
        for (Pizza p : pizza) {
            new BackgroundPizzaiolo(p).work();
        }
    }

    @Override
    public boolean isCompleted() {
        for (Pizza p : pizza) {
            if (!p.isCotta()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Object getResult() {
        return isCompleted() ? pizza : null;
    }

}
