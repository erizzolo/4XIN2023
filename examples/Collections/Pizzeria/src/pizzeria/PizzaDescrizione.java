package pizzeria;

import java.util.Objects;

/**
 * La descrizione immutabile di una pizza, con tipo e taglia, per i menu
 *
 * @author emanuele
 */
public class PizzaDescrizione {

    private final TipoPizza tipo;
    private final TagliaPizza taglia;

    public PizzaDescrizione(TipoPizza tipo, TagliaPizza taglia) {
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

    @Override
    public String toString() {
        return "Pizza " + tipo + " " + taglia;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipo, taglia);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PizzaDescrizione other = (PizzaDescrizione) obj;
        if (this.tipo != other.tipo) {
            return false;
        }
        if (this.taglia != other.taglia) {
            return false;
        }
        return true;
    }

}
