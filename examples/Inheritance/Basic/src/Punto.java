import java.util.Random;

/**
 * Classe base che rappresenta un punto (immutabile) nello spazio
 * bidimensionale.
 *
 * @author emanuele
 * @version 0.0 first version
 */
public class Punto {

    // per attivare/disattivare output di debug
    public static boolean DEBUG = true;

    public static void debug(String s) {
        if (DEBUG) {
            System.out.println(s);
        }
    }

    // Attributi static, cioè relativi alla categoria dei Punti
    // e final, cioè non modificabili dopo l'assegnazione
    /**
     * The origin of the coordinate system
     */
    public static final Punto ORIGIN = new Punto(0.0, 0.0);
    /**
     * The random generator used by getRandom
     */
    private final static Random RANDOM = new Random();

    // Attributi non static, cioè relativi ai singoli oggetti / istanze
    // Information hiding; attributi private
    /**
     * x coordinate
     */
    private double x;
    /**
     * y coordinate
     */
    private double y;

    // complete constructor
    /**
     * Costruisce ed inizializza, cioè istanzia, un punto con attributi ricevuti
     * come parametri.
     *
     * Usa metodi appositi per effettuare i controlli di validità
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @throws IllegalArgumentException se le coordinate non sono finite
     */
    public Punto(double x, double y) {
        // super(); // implicito
        debug("-> Punto(" + x + "," + y + ")");
        this.x = check(x, "x");
        this.y = check(y, "y");
        debug("Punto(" + x + "," + y + ") ->");
    }

    // copy constructor
    /**
     * Istanzia un punto identico a quello originale (un clone).
     *
     * @param original the original Punto
     * @throws NullPointerException se original è null
     */
    public Punto(Punto original) {
        // super(); // implicito
        debug("-> Punto(" + original + ")");
        this.x = check(original.x, "x");
        this.y = check(original.y, "y");
        debug("Punto(" + original + ") ->");
    }

    // static "constructor"
    /**
     * Restituisce il punto medio del segmento che ha per estremi i punti dati.
     *
     * @param first  the first Punto
     * @param second the second Punto
     * @throws NullPointerException se uno dei due punti è null
     */
    public static Punto getMedio(Punto first, Punto second) {
        debug("-> getMedio(" + first + ", " + second + ")");
        Punto result = new Punto((first.x + second.x) / 2, (first.y + second.y) / 2);
        debug("getMedio(" + first + ", " + second + ") ->");
        return result;
    }

    /**
     * Restituisce un punto con coordinate casuali gaussiane
     */
    public static Punto getRandom() {
        debug("-> getRandom()");
        Punto result = new Punto(RANDOM.nextGaussian(), RANDOM.nextGaussian());
        debug("getRandom() ->");
        return result;
    }

    // Information hiding: getters
    /**
     * Get the value of the x coordinate of this Punto
     *
     * @return the value of the x coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * Get the value of the y coordinate of this Punto
     *
     * @return the value of the y coordinate
     */
    public double getY() {
        return y;
    }

    // static method
    /**
     * Check and returns a coordinate
     *
     * @param value value of the coordinate to be checked
     * @param which name of the coordinate to be checked
     */
    private static double check(double value, String which) {
        debug("-> check(" + value + ")");
        if (!Double.isFinite(value))
            throw new IllegalArgumentException(which + " coordinate cannot be NaN, +Infinity or -Infinity.");
        debug("check(" + value + ") ->");
        return value;
    }

    // Altri getter utili
    /**
     * Get the distance from another Punto
     *
     * @param other the other Punto
     * @return Nan if other is null, otherwise the distance from other and this
     *         Punto
     */
    public double getDistanceFrom(Punto other) {
        return other == null ? Double.NaN : Math.hypot(getX() - other.getX(), getY() - other.getY());
    }

    // a private method ("constructor" ???)
    /**
     * Restituisce il punto simmetrico rispetto all'origine
     * 
     * @return the Punto symmetric to this with respect to the origin
     */
    private Punto getSymmetric() {
        return new Punto(-x, -y);
    }

    @Override
    public String toString() {
        return super.toString() + "[x=" + getX() + ", y=" + getY() + "]";
    }

    // a simple test of this class
    public static Punto test() {
        // Basic tests of Punto
        System.out.println("Test of Punto");
        Punto p = new Punto(1.5, 2.5);
        System.out.println("p = new Punto(1.5, 2.5): " + p);
        Punto r = getRandom();
        System.out.println("r = getRandom(): " + r);
        System.out.println("getMedio(p, r): " + getMedio(p, r));
        System.out.println("ORIGIN: " + ORIGIN);
        System.out.println("distance(ORIGIN, p): " + ORIGIN.getDistanceFrom(p));
        System.out.println("distance(p, ORIGIN): " + p.getDistanceFrom(ORIGIN));
        System.out.println("distance(p, null): " + p.getDistanceFrom(null));
        Punto copy = new Punto(p);
        System.out.println("copy = new Punto(p): " + copy);
        System.out.println("p.getSymmetric(): " + p.getSymmetric());

        return ORIGIN;
    }
}
