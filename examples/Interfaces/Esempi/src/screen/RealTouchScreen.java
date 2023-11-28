package screen;

/**
 * A concrete class representing a basic TouchScreen
 *
 * @author emanuele
 */
public class RealTouchScreen extends TouchScreen {

    /**
     * Constructs a new RealTouchScreen
     *
     * @param width horizontal dimension
     * @param height vertical dimension
     * @param horizontalResolution horizontal resolution
     * @param verticalResolution vertical resolution
     */
    public RealTouchScreen(double width, double height, int horizontalResolution, int verticalResolution) {
        super(width, height, horizontalResolution, verticalResolution);
    }

    @Override
    public void tap(PhysicalPosition p, int times) {
        super.tap(p, times);
    }

    @Override
    public void changeResolution(int horizontal, int vertical) {
        if (isResoutionSupported(horizontal, vertical)) {
            setResolution(horizontal, vertical);
        }
    }

    private boolean isResoutionSupported(int horizontal, int vertical) {
        return true;
    }
}
