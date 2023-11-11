public class PoligonoRegolare extends Poligono {

    private double lato;

    public PoligonoRegolare(Posizione posizione, int numeroLati, double lato) {
        super(posizione, numeroLati);
        this.lato = lato;
    }

    public PoligonoRegolare(Posizione posizione, int numeroLati) {
        this(posizione, numeroLati, 1.0);
    }

    public PoligonoRegolare(int numeroLati) {
        this(Posizione.ORIGINE, numeroLati);
    }

    public double getLato() {
        return lato;
    }
    
    public double getApotema() {
        // triangolo rettangolo: metà lato (l/2), apotema (a), raggio cerchio
        // circoscritto (r)
        // angolo al centro (ac) = 2 * PI / numeroLati
        // angolo tra apotema e raggio (ar): ac / 2 = PI / numeroLati
        // r * sin(ar) = l/2 --> l = 2 * r * sin(ar) --> r = l / 2 / sin(ar)
        // r * cos(ar) = a --> a = l / 2 * cos(ar) / sin(ar) = l / 2 / tan(ar)
        return lato / 2 / Math.tan(Math.PI / getNumeroLati());
    }

    @Override
    public Punto[] getVertici() {
        Punto[] result = new Punto[getNumeroLati()];
        double ar = Math.PI / getNumeroLati();
        double x = getPosizione().getX(), y = getPosizione().getY(), angle = ar;
        double r = lato / 2 / Math.sin(ar);
        for (int i = 0; i < result.length; i++, angle += ar * 2) {
            Posizione p = new Posizione(x + r * Math.cos(angle), y + r * Math.sin(angle));
            result[i] = new Punto(p);
        }
        return result;
    }

    @Override
    public double getPerimetro() {
        return lato * getNumeroLati();
    }

    @Override
    public double getArea() {
        return getPerimetro() * getApotema() / 2;
    }

    @Override
    public String toString() {
        return "PoligonoRegolare [numeroLati=" + getNumeroLati() + ", lato=" + getLato() + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(lato);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        PoligonoRegolare other = (PoligonoRegolare) obj;
        if (Double.doubleToLongBits(lato) != Double.doubleToLongBits(other.lato))
            return false;
        return true;
    }

}
