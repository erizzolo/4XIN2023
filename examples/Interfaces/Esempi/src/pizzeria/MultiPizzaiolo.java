package pizzeria;

import java.util.Arrays;
import java.util.Objects;

/**
 * A worker that can prepare multiple Pizzas
 *
 * @author erizzolo
 */
public class MultiPizzaiolo implements Worker {

    private Pizza[] pizza;
    private int preparate = 0;

    /**
     * Creates a new MultiPizzaiolo for the given Pizza[]
     *
     * @param pizza the Pizza[] to be done
     */
    public MultiPizzaiolo(Pizza[] pizza) {
        this.pizza = Objects.requireNonNull(pizza, "Ordina delle pizze, per favore!!!");
        for (Pizza p : pizza) {
            Objects.requireNonNull(p, "Ma che razza di pizza è???!!!");
        }
        this.pizza = Arrays.copyOf(pizza, pizza.length);
    }

    @Override
    public void work() {
        if (preparate < pizza.length) {
            debug("Preparazione della pizza " + pizza[preparate] + ", n. " + (preparate + 1));
            pizza[preparate].prepara();
            pizza[preparate].inforna();
            debug(pizza[preparate] + " è in forno");
            preparate++;
        } else if (isCompleted()) {
            debug("Le pizze sono già pronte, lasciami riposare!!!");
        } else {
            debug("Le pizze devono finire di cuocere ");
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
