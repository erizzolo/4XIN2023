package good;

public class SquareScalable extends RectangleScalable {

    public SquareScalable(double side) {
        super(side, side);
    }

    public double getSide() {
        return getBase(); // or getHeight() ???
    }

}
