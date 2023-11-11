public abstract class FiguraChiusa {
    
    private Posizione posizione;

    public FiguraChiusa(Posizione posizione) {
        this.posizione = posizione;
    }

    public abstract double getPerimetro();
    public abstract double getArea();

    public Posizione getPosizione() {
        return posizione;
    }

}
