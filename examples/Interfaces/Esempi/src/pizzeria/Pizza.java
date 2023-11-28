package pizzeria;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * La pizza
 *
 * @author erizzolo
 */
public class Pizza {

    private final TipoPizza tipo;
    private TagliaPizza taglia;
    private boolean preparata = false;
    private LocalDateTime infornata = null;

    /**
     * Creates a new Pizza
     *
     * @param tipo the kind of pizza
     * @param taglia the size of pizza
     */
    public Pizza(TipoPizza tipo, TagliaPizza taglia) {
        this.tipo = tipo;
        this.taglia = taglia;
    }

    /**
     * Get the value of tipo
     *
     * @return the value of tipo
     */
    public TipoPizza getTipo() {
        return tipo;
    }

    /**
     * Get the value of taglia
     *
     * @return the value of taglia
     */
    public TagliaPizza getTaglia() {
        return taglia;
    }

    /**
     * Get the value of prezzo
     *
     * @return the value of prezzo
     */
    public double getPrezzo() {
        return getTipo().prezzo * getTaglia().fattorePrezzo;
    }

    /**
     * Get the value of preparata
     *
     * @return the value of preparata
     */
    public boolean isPreparata() {
        return preparata;
    }

    /**
     * Get the value of cotta
     *
     * @return the value of cotta
     */
    public boolean isCotta() {
        return infornata == null
                ? false
                : infornata.until(LocalDateTime.now(), ChronoUnit.MICROS) >= getTaglia().tempoDiCottura;
    }

    /**
     * Prepara la pizza... c'è bisogno di tempo
     */
    public void prepara() {
        if (!isPreparata()) {
            try {
                Thread.sleep(getTipo().tempoDiPreparazione);
                preparata = true;
            } catch (InterruptedException ex) {
                System.err.println(ex);
            }
        }
    }

    /**
     * Inforna la pizza...
     */
    public void inforna() {
        if (!isPreparata()) {
            throw new IllegalStateException("Non si infornano pizze non preparate!!");
        } else if (infornata == null) {
            infornata = LocalDateTime.now();
        }
    }

    @Override
    public String toString() {
        return "Pizza " + taglia + " " + tipo + " (" + getPrezzo() + "€) " + (isCotta() ? "pronta da mangiare" : (isPreparata() ? "deve cuocere un po'" : "ancora da preparare"));
    }

}
