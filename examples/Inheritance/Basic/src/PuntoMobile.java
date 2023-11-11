import java.lang.reflect.Constructor;

/**
 * Classe derivata che rappresenta un punto mobile nello spazio bidimensionale,
 * con memoria della distanza percorsa e della distanza da "casa".
 *
 * @author emanuele
 * @version 0.0 first version
 */

public class PuntoMobile extends Punto {

    // attributi aggiuntivi
    /**
     * x and y distance from "home": actual displacement from original position
     */
    private double dx, dy;
    /**
     * Total distance travelled
     */
    private double travelledDistance;
    /**
     * Maximum total distance travelled by any PuntoMobile
     */
    private static double maxTravelledDistance = 0.0;

    // Poiché la classe base Punto non ha un costruttore di default Punto(),
    // se non definiamo un costruttore abbiamo un ERRORE:
    // Implicit super constructor Punto() is undefined for default constructor.
    // Must define an explicit constructor Java(134217868)
    // cioè super() non funziona perché il costruttore Punto() non c'è !!!
    // quindi ne definiamo (almeno) uno (meglio se tutti quelli simili ai
    // super(...))

    // Per ogni costruttore, se non inseriamo un super(...) all'inizio abbiamo un
    // ERRORE:
    // Implicit super constructor Punto() is undefined.
    // Must explicitly invoke another constructor Java(134217871)
    // quindi nei costruttori inseriamo super(...) esplicito

    // complete constructor
    /**
     * Istanzia un punto mobile con attributi ricevuti come parametri.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @throws IllegalArgumentException se le coordinate non sono finite
     */
    public PuntoMobile(double x, double y) {
        super(x, y);
        debug("-> PuntoMobile(" + x + ", " + y + ") (after super(x,y)!!!)");
        // attributes initialized to 0
    }

    // copy constructor
    /**
     * Istanzia un punto mobile identico a quello originale (un clone).
     *
     * @param original the original PuntoMobile
     * @throws NullPointerException se original è null
     */
    public PuntoMobile(PuntoMobile original) {
        super(original); // original is-A Punto !!!
        // copy other attributes
        travelledDistance = original.travelledDistance;
        dx = original.dx;
        dy = original.dy;
    }

    // metodi aggiuntivi
    /**
     * Sposta il punto delle quantità specificate
     * 
     * @param dx x axis movement
     * @param dy y axis movement
     */
    public void move(double dx, double dy) {
        this.dx += dx;
        this.dy += dy;
        travelledDistance += Math.hypot(dx, dy);
        if (maxTravelledDistance < travelledDistance)
            maxTravelledDistance = travelledDistance;
    }

    // getters per attributi aggiuntivi
    public double getTravelledDistance() {
        return travelledDistance;
    }

    public double getHomeDistance() {
        return Math.hypot(dx, dy);
    }

    public static double getMaxTravelledDistance() {
        return maxTravelledDistance;
    }

    // override per i getter della classe base
    @Override
    public double getX() {
        return super.getX() + dx;
    }

    @Override
    public double getY() {
        return super.getY() + dy;
    }

    // a private method ("constructor" ???) becoming public
    /**
     * Restituisce il punto simmetrico rispetto all'origine
     * 
     * @return the PuntoMobile symmetric to this with respect to the origin
     */
    public PuntoMobile getSymmetric() {
        return new PuntoMobile(-getX(), -getY());
    }

    // Fare sempre l'override di toString per comodità in debug, errori e altro
    @Override
    public String toString() {
        return super.toString().replace(']', ',') + " travelledDistance=" + travelledDistance + "]";
    }

    /**
     * Restituisce un punto con coordinate casuali gaussiane
     */
    public static PuntoMobile getRandom() {
        debug("-> getRandom()");
        Punto result = Punto.getRandom();
        result = new PuntoMobile(result.getX(), result.getY());
        debug("getRandom() ->");
        return (PuntoMobile) result;
    }

    // a simple test of this class
    public static PuntoMobile test() {
        // Basic tests of PuntoMobile
        System.out.println("Test of PuntoMobile");
        // no need of "Punto." before static methods/attributes
        // like getRandom, getMedio, ORIGIN
        // because they are inherited
        PuntoMobile p = new PuntoMobile(1.5, 2.5);
        System.out.println("p = new PuntoMobile(1.5, 2.5): " + p);
        Punto r = getRandom();
        System.out.println("r = getRandom(): " + r);
        System.out.println("getMedio(p, r): " + getMedio(p, r));
        System.out.println("ORIGIN: " + ORIGIN);
        System.out.println("distance(ORIGIN, p): " + ORIGIN.getDistanceFrom(p));
        System.out.println("distance(p, ORIGIN): " + p.getDistanceFrom(ORIGIN));
        System.out.println("distance(p, null): " + p.getDistanceFrom(null));
        System.out.println("Moving p to the origin: p.move(-p.getX(), -p.getY());");
        p.move(-p.getX(), -p.getY());
        System.out.println("p: " + p);
        Punto copy = new PuntoMobile(p);
        System.out.println("copy = new PuntoMobile(p): " + copy);
        System.out.println("p.getSymmetric(): " + p.getSymmetric());
        System.out.println("Something doesn't work as expected:");
        System.out.println("distance(ORIGIN, p): " + ORIGIN.getDistanceFrom(p));
        System.out.println("getMedio(p, ORIGIN): " + getMedio(p, ORIGIN));
        System.out.println("Because we didn't use getters when we could have ...");
        return getRandom();
    }

}
