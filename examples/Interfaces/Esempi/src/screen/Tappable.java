package screen;

/**
 * A simple interface with only one gesture
 *
 * @author emanuele
 */
public interface Tappable {


    // costanti
    /* public static final */ int DOUBLE_TAP_DELAY = 20;    // meglio
    public static final int TRIPLE_TAP_DELAY = 50;


    // metodi abstract
    /**
     * Briefly touch surface
     *
     * @param p position where gesture occurs
     * @param times number of taps
     */
    /* public abstract */ void tap(PhysicalPosition p, int times);


    // metodi default
    /**
     * Briefly touch surface once
     *
     * @param p position where gesture occurs
     */
    /* public */ default void tap(PhysicalPosition p) {
        tap(p, 1);
    }

    /**
     * Rapidly touch surface twice
     *
     * @param p position where gesture occurs
     */
    /* public */ default void doubleTap(PhysicalPosition p) {
        tap(p, 2);
    }

    /**
     * Same gesture as tap but with explicit coordinates
     *
     * @param horizontal horizontal coordinate where gesture occurs
     * @param vertical vertical coordinate where gesture occurs
     * @param times number of taps
     */
    default void tap(double horizontal, double vertical, int times) {
        tap(new PhysicalPosition(horizontal, vertical), times);
    }

}
