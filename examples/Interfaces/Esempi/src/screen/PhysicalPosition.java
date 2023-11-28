package screen;

/**
 * Represents a position in physical coordinates
 *
 * @author emanuele
 */
public class PhysicalPosition {

    /**
     * Horizontal and vertical physical coordinates
     */
    public final double horizontal, vertical;

    /**
     * The top-left corner
     */
    public static final PhysicalPosition TOP_LEFT_CORNER = new PhysicalPosition(0, 0);

    /**
     * Constructs a new PhysicalPosition
     *
     * @param horizontal the horizontal coordinate
     * @param vertical the vertical coordinate
     */
    public PhysicalPosition(double horizontal, double vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.horizontal) ^ (Double.doubleToLongBits(this.horizontal) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.vertical) ^ (Double.doubleToLongBits(this.vertical) >>> 32));
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
        final PhysicalPosition other = (PhysicalPosition) obj;
        if (Double.doubleToLongBits(this.horizontal) != Double.doubleToLongBits(other.horizontal)) {
            return false;
        }
        if (Double.doubleToLongBits(this.vertical) != Double.doubleToLongBits(other.vertical)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PhysicalPosition{" + horizontal + ", " + vertical + '}';
    }

}
