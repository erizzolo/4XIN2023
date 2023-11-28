package screen;

/**
 * Operation of a device (usually a Screen) that can change its orientation
 *
 * @author emanuele
 */
public interface Orientable {

    /**
     * Possible values of orientation
     */
    enum Orientation {

        PORTRAIT, LANDSCAPE;

        static Orientation getDefault() {
            return PORTRAIT;
        }
    }

    /**
     * Get device orientation
     */
    Orientation getOrientation();

    /**
     * Set device orientation
     *
     * @param orientation the (new) orientation
     */
    void setOrientation(Orientation orientation);

    /**
     * Change device orientation to LANDSCAPE
     */
    default void setLandscape() {
        setOrientation(Orientation.LANDSCAPE);
    }

    /**
     * Change device orientation to PORTRAII
     */
    default void setPortrait() {
        setOrientation(Orientation.PORTRAIT);
    }

    /**
     * Reset device orientation to default
     */
    default void resetOrientation() {
        setOrientation(Orientation.getDefault());
    }
}
