public class Quadrato extends PoligonoRegolare {

    public Quadrato(Posizione posizione, double lato) {
        super(posizione, 4, lato);
    }

    public Quadrato(Posizione posizione) {
        this(posizione, 1.0);
    }

    public Quadrato() {
        this(Posizione.ORIGINE);
    }

    // just for better precision ...
    @Override
    public double getApotema() {
        return getLato() / 2;
    }

    // just for better precision ...
    @Override
    public Punto[] getVertici() {
        Punto[] result = new Punto[getNumeroLati()];
        double x = getPosizione().getX(), y = getPosizione().getY(), delta = getLato() / 2;
        result[0] = new Punto(new Posizione(x + delta, y - delta));
        result[1] = new Punto(new Posizione(x + delta, y + delta));
        result[2] = new Punto(new Posizione(x - delta, y + delta));
        result[3] = new Punto(new Posizione(x - delta, y - delta));
        return result;
    }

}
