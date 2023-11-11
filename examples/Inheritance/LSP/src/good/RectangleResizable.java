package good;

public class RectangleResizable extends RectangleScalable {

    private double heightScaleFactor;

    public RectangleResizable(double base, double height) {
        super(base, height);
        heightScaleFactor = 1.0;
    }
    public void resize(double baseFactor, double heightFactor) {
        scale(baseFactor);
        heightFactor *= heightFactor / baseFactor;
    }

    @Override
    public double getHeight() {
        return super.getHeight() * heightScaleFactor;
    }
    
}
