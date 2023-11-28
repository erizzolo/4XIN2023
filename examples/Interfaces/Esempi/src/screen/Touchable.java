package screen;

/**
 * A more complete interface with basic gestures
 *
 * Actually it's much more complicated
 *
 * @author emanuele
 */
public interface Touchable extends Tappable {

    /**
     * Move over surface without losing contact
     *
     * @param start initial position where gesture occurs
     * @param end final position where gesture occurs
     *
     */
    void drag(PhysicalPosition start, PhysicalPosition end);

    /**
     * Quickly brush surface
     *
     * @param start initial position where gesture occurs
     * @param end final position where gesture occurs
     *
     */
    void flick(PhysicalPosition start, PhysicalPosition end);

    /**
     * Touch surface for extended period of time
     *
     * @param p position where gesture occurs
     */
    void press(PhysicalPosition p);

    /**
     * Touch surface with two fingers and move them in a clockwise or
     * counterclockwise direction
     *
     * @param center central position where gesture occurs
     * @param angle rotation angle od the gesture
     *
     */
    void rotate(PhysicalPosition center, double angle);

}
