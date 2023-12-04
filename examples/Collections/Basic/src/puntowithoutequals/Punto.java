package puntowithoutequals;

/**
 *
 * @author erizzolo
 */
public class Punto {

    private final double x;

    private final double y;

    public Punto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get the value of x
     *
     * @return the value of x
     */
    public double getX() {
        return x;
    }

    /**
     * Get the value of y
     *
     * @return the value of y
     */
    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Punto{" + getX() + ", " + getY() + '}';
    }

}
