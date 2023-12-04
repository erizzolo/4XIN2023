package pizzeria;

/**
 * Possibili tipi di pizza, con relativo tempo di preparazione
 *
 * @author emanuele
 */
public enum TipoPizza {

    MARGHERITA(5),
    ROMANA(6),
    QUATTRO_STAGIONI(10);

    public final int tempoDiPreparazione; // minuti

    private TipoPizza(int tempoDiPreparazione) {
        this.tempoDiPreparazione = tempoDiPreparazione;
    }

}
