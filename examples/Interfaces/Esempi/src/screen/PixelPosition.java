package screen;

/**
 * Represents a position in pixel coordinates
 *
 * @author emanuele
 */
public class PixelPosition {

    /**
     * Horizontal and vertical pixel coordinates
     */
    public final int horizontal, vertical;

    /**
     * The top-left corner
     */
    public static final PixelPosition TOP_LEFT_CORNER = new PixelPosition(0, 0);

    /**
     * Constructs a new PixelPosition
     *
     * @param horizontal the horizontal coordinate
     * @param vertical the vertical coordinate
     */
    public PixelPosition(int horizontal, int vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.horizontal;
        hash = 17 * hash + this.vertical;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PixelPosition other = (PixelPosition) obj;
        if (this.horizontal != other.horizontal) {
            return false;
        }
        if (this.vertical != other.vertical) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PixelPosition{" + horizontal + ", " + vertical + '}';
    }

}
