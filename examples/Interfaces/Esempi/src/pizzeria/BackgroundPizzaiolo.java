package pizzeria;

import java.util.Objects;

/**
 * A worker that can prepare Pizza in the background
 *
 * @author erizzolo
 */
public class BackgroundPizzaiolo implements Worker {

    private Pizza pizza;
    /**
     * Whether work has been started
     */
    private boolean started = false;

    /**
     * Creates a new BackgroundPizzaiolo for the given Pizza
     *
     * @param pizza the Pizza to be done
     */
    public BackgroundPizzaiolo(Pizza pizza) {
        this.pizza = Objects.requireNonNull(pizza, "Ordina una pizza vera, per favore!!!");
    }

    @Override
    public void work() {
        if (!started) {
            debug("Putting myself to work...");
            started = true;
            // starts a new thread of execution ...
            new Thread(new Runnable() {
                // code to be run ...
                @Override
                public void run() {
                    if (!pizza.isPreparata()) {
                        debug("Preparazione di " + pizza);
                        pizza.prepara();
                        debug(pizza + " pronta da infornare");
                    }
                    pizza.inforna();
                    debug("La pizza è in forno...");
                }
            }).start();
        } else {
            if (!isCompleted()) {
                debug("Still working, please wait a second!!!");
            } else {
                debug("La pizza è già pronta, lasciami riposare!!!");
            }
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
