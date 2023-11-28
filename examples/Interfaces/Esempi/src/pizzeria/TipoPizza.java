package pizzeria;

/**
 * Posibili tipi di pizza
 *
 * @author emanuele
 */
public enum TipoPizza {

    MARGHERITA(4.50, 5),
    ROMANA(5.50, 6),
    QUATTRO_STAGIONI(7.50, 10);

    public final double prezzo; // prezzo base
    public final int tempoDiPreparazione; // minuti

    private TipoPizza(double prezzo, int tempoDiPreparazione) {
        this.prezzo = prezzo;
        this.tempoDiPreparazione = tempoDiPreparazione;
    }

}
