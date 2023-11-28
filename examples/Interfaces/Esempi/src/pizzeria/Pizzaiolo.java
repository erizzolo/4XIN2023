package pizzeria;

import java.util.Objects;

/**
 * A worker that can prepare Pizza
 *
 * @author erizzolo
 */
public class Pizzaiolo implements Worker {

    private Pizza pizza;

    /**
     * Creates a new Pizzaiolo for the given Pizza
     *
     * @param pizza the Pizza to be done
     */
    public Pizzaiolo(Pizza pizza) {
        this.pizza = Objects.requireNonNull(pizza, "Ordina una pizza vera, per favore!!!");
    }

    @Override
    public void work() {
        if (!isCompleted()) {
            if (!pizza.isPreparata()) {
                debug("Preparazione di " + pizza);
                pizza.prepara();
                debug(pizza + " pronta da infornare");
            }
            pizza.inforna();
            debug("La pizza è in forno...");
        } else {
            debug("La pizza è già pronta, lasciami riposare!!!");
        }
    }

    @Override
    public boolean isCompleted() {
        return pizza.isCotta();
    }

    @Override
    public Object getResult() {
        return isCompleted() ? pizza : null;
    }

}
