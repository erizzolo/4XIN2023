package screen;

/**
 * A super complete example of touchscreen
 *
 * @author emanuele
 */
public class SuperTouchScreen extends RealTouchScreen implements Touchable, Orientable {

    private Orientation orientation = Orientation.getDefault();

    /**
     * Constructs a new SuperTouchScreen
     *
     * @param width horizontal dimension
     * @param height vertical dimension
     * @param horizontalResolution horizontal resolution
     * @param verticalResolution vertical resolution
     */
    public SuperTouchScreen(double width, double height, int horizontalResolution, int verticalResolution) {
        super(width, height, horizontalResolution, verticalResolution);
    }

    @Override
    public void drag(PhysicalPosition start, PhysicalPosition end) {
        System.out.println("Dragged from " + getPixelPosition(start) + " to " + getPixelPosition(end));
    }

    @Override
    public void flick(PhysicalPosition start, PhysicalPosition end) {
        System.out.println("Flicked from " + getPixelPosition(start) + " to " + getPixelPosition(end));
    }

    @Override
    public void press(PhysicalPosition p) {
        System.out.println("Pressed at " + getPixelPosition(p));
    }

    @Override
    public void rotate(PhysicalPosition center, double angle) {
        System.out.println("Rotated around " + getPixelPosition(center) + " by " + Math.toDegrees(angle) + " degrees");
    }

    @Override
    public Orientation getOrientation() {
        return orientation;
    }

    @Override
    public void setOrientation(Orientation orientation) {
        if (orientation != this.orientation) {
            // repaint the content, resize windows, ...
        }
        this.orientation = orientation;
    }

    @Override
    public int getVerticalResolution() {
        return orientation == Orientation.LANDSCAPE ? super.getVerticalResolution() : super.getHorizontalResolution();
    }

    @Override
    public int getHorizontalResolution() {
        return orientation == Orientation.PORTRAIT ? super.getVerticalResolution() : super.getHorizontalResolution();
    }

    @Override
    public double getWidth() {
        return orientation == Orientation.LANDSCAPE ? super.getWidth() : super.getHeight();
    }

    @Override
    public double getHeight() {
        return orientation == Orientation.PORTRAIT ? super.getWidth() : super.getHeight();
    }

    @Override
    public PixelPosition getPixelPosition(PhysicalPosition physical) {
        return orientation == Orientation.PORTRAIT
                ? super.getPixelPosition(physical)
                : new PixelPosition((int) (physical.vertical / getWidth() * getHorizontalResolution()), (int) ((getHeight() - physical.horizontal) / getHeight() * getVerticalResolution()));
    }

    @Override
    public String toString() {
        return super.toString().replace('}', ',') + " orientation " + getOrientation() + "]";
    }

}
