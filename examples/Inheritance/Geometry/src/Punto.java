/**
 * Immutable point
 */
public class Punto {

    private final Posizione posizione;

    public Punto() {
        this(Posizione.ORIGINE);
    }

    public Punto(Posizione posizione) {
        this.posizione = posizione;
    }

    public Posizione getPosizione() {
        return posizione;
    }

    @Override
    public String toString() {
        return "Punto [posizione=" + posizione + "]";
    }

}
