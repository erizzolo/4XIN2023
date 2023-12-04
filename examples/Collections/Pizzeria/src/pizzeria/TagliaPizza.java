package pizzeria;

/**
 * Possibili dimensioni pizza, con relativo tempo di preparazione
 *
 * @author emanuele
 */
public enum TagliaPizza {

    /**
     * small: quicker
     */
    PICCOLA(10),
    /**
     * as it should be done
     */
    NORMALE(20),
    /**
     * big: one and a half of the time
     */
    GRANDE(30),
    /**
     * huge: double time to wait
     */
    ENORME(40);

    /**
     * Tempo di cottura in minuti
     */
    public final int tempoDiCottura;

    private TagliaPizza(int tempoDiCottura) {
        this.tempoDiCottura = tempoDiCottura;
    }

}
