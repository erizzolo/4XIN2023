package bad;

public class Square extends Rectangle {

    public Square(double side) {
        super(side, side);
    }

    public double getSide() {
        return getBase(); // or getHeight() ???
    }

    public void setSide(double side) {
        super.setBase(side);
        super.setHeight(side);
    }

    @Override
    public void setBase(double base) {
        setSide(base);
    }

    @Override
    public void setHeight(double height) {
        setSide(height);
    }

    @Override
    public String toString() {
        return "Square [side =" + getSide() + "]";
    }

}
