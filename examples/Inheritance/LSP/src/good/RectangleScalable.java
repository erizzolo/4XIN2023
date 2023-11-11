package good;

public class RectangleScalable extends Rectangle {

    private double scaleFactor;

    public RectangleScalable(double base, double height) {
        super(base, height);
        scaleFactor = 1.0;
    }

    public void scale(double factor) {
        scaleFactor *= factor;
    }

    @Override
    public double getBase() {
        return super.getBase() * scaleFactor;
    }

    @Override
    public double getHeight() {
        return super.getHeight() * scaleFactor;
    }

}
