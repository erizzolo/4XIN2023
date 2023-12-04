package pizzeria;

import java.util.Objects;

/**
 * La descrizione immutabile di una pizza, con tipo e taglia, ed il prezzo
 *
 * @author emanuele
 */
public class PizzaOrdinata {

    private final PizzaDescrizione descrizione;
    private final double prezzo;

    public PizzaOrdinata(PizzaDescrizione descrizione, double prezzo) {
        this.descrizione = Objects.requireNonNull(descrizione, "Impossibile ordinare una pizza null");
        if (prezzo <= 0) {
            throw new IllegalArgumentException("Impossibile avere un prezzo non positivo");
        }
        this.prezzo = prezzo;
    }

    /**
     * Get the value of descrizione
     *
     * @return the value of descrizione
     */
    public PizzaDescrizione getDescrizione() {
        return descrizione;
    }

    /**
     * Get the value of prezzo
     *
     * @return the value of prezzo
     */
    public double getPrezzo() {
        return prezzo;
    }
}
