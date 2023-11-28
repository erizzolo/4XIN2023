package screen;

/**
 * Abstract Touchscreen
 *
 * @author emanuele
 */
public abstract class TouchScreen extends Screen implements Tappable {

    /**
     * Constructs a new TouchScreen
     *
     * @param width horizontal dimension
     * @param height vertical dimension
     * @param horizontalResolution horizontal resolution
     * @param verticalResolution vertical resolution
     */
    public TouchScreen(double width, double height, int horizontalResolution, int verticalResolution) {
        super(width, height, horizontalResolution, verticalResolution);
    }

    @Override
    public void tap(PhysicalPosition p, int times) {
        System.out.println("Tapped " + times + " times position " + p + " = " + getPixelPosition(p));
    }

}
