package screen;

/**
 * Base abstract class for Screen objects
 *
 * Qualunque screen ha dimensioni fisiche e risoluzione; quest'ultima può
 * opzionalmente cambiare
 *
 * @author emanuele
 */
public abstract class Screen {

    /**
     * Horizontal and vertical physical dimensions
     */
    private final double width, height;

    /**
     * Horizontal and vertical resolution in pixels
     */
    private int horizontalResolution, verticalResolution;

    /**
     * Constructs a new Screen
     *
     * @param width horizontal dimension
     * @param height vertical dimension
     * @param horizontalResolution horizontal resolution
     * @param verticalResolution vertical resolution
     */
    public Screen(double width, double height, int horizontalResolution, int verticalResolution) {
        this.width = width;
        this.height = height;
        this.horizontalResolution = horizontalResolution;
        this.verticalResolution = verticalResolution;
    }

    /**
     * Get the value of height
     *
     * @return the value of height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Get the value of width
     *
     * @return the value of width
     */
    public double getWidth() {
        return width;
    }

    /**
     * Get the value of horizontal resolution
     *
     * @return the value of horizontal resolution
     */
    public int getHorizontalResolution() {
        return horizontalResolution;
    }

    /**
     * Get the value of vertical resolution
     *
     * @return the value of vertical resolution
     */
    public int getVerticalResolution() {
        return verticalResolution;
    }

    /**
     * Get the pixel position corresponding to a given physical position
     *
     * @param physical the given physical position
     * @return the pixel position corresponding to the given physical position
     */
    public PixelPosition getPixelPosition(PhysicalPosition physical) {
        return new PixelPosition((int) (physical.horizontal / getWidth() * getHorizontalResolution()), (int) (physical.vertical / getHeight() * getVerticalResolution()));
    }

    /**
     * Get the physical position corresponding to a given pixel position
     *
     * @param pixel the given pixel position
     * @return the physical position corresponding to the given pixel position
     */
    public PhysicalPosition getPhysicalPosition(PixelPosition pixel) {
        return new PhysicalPosition(pixel.horizontal * getWidth() / getHorizontalResolution(), pixel.vertical * getHeight() / getVerticalResolution());
    }

    /**
     * Set the horizontal and vertical resolution
     *
     * @param horizontal new value of horizontalResolution
     * @param vertical new value of verticalResolution
     */
    protected void setResolution(int horizontal, int vertical) {
        horizontalResolution = horizontal;
        verticalResolution = vertical;
    }

    /**
     * Change the horizontal and vertical resolution
     *
     * @param horizontal new value of horizontalResolution
     * @param vertical new value of verticalResolution
     */
    public abstract void changeResolution(int horizontal, int vertical);

    @Override
    public String toString() {
        return "Screen{" + getWidth() + "x" + getHeight() + " mm, " + getHorizontalResolution() + "x" + getVerticalResolution() + "pixels}";
    }

}
