package pizzeria;

/**
 * Possibili dimensioni pizza
 *
 * @author emanuele
 */
public enum TagliaPizza {

    /**
     * small: cheaper and quicker
     */
    PICCOLA(0.5, 10),
    /**
     * as it sholud be done
     */
    NORMALE(1.0, 20),
    /**
     * big: double price and one and a half of the time
     */
    GRANDE(2.0, 30),
    /**
     * huge: four times expensive, double time to wait
     */
    ENORME(4.0, 40);

    /**
     *
     */
    public final double fattorePrezzo; // moltiplicatore del prezzo base

    /**
     *
     */
    public final int tempoDiCottura; // minuti

    private TagliaPizza(double fattorePrezzo, int tempoDiCottura) {
        this.fattorePrezzo = fattorePrezzo;
        this.tempoDiCottura = tempoDiCottura;
    }

}
