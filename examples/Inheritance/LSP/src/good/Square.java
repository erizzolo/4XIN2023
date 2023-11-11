package good;

public class Square extends Rectangle {

    public Square(double side) {
        super(side, side);
    }

    public double getSide() {
        return getBase(); // or getHeight() ???
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [side =" + getSide() + "]";
    }

}
