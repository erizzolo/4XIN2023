public abstract class Poligono extends FiguraChiusa {

    private int numeroLati;

    public Poligono(Posizione posizione, int numeroLati) {
        super(posizione);
        this.numeroLati = numeroLati;
    }

    public Poligono(int numeroLati) {
        this(Posizione.ORIGINE, numeroLati);
    }

    
    public int getNumeroLati() {
        return numeroLati;
    }

    public abstract Punto[] getVertici();

    @Override
    public String toString() {
        return "Poligono [numeroLati=" + numeroLati + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + numeroLati;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Poligono other = (Poligono) obj;
        if (numeroLati != other.numeroLati)
            return false;
        return true;
    }

}
