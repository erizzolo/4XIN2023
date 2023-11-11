package good;

public class Rectangle {
    private double base;
    private double height;

    public Rectangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    public double getBase() {
        return base;
    }

    public double getHeight() {
        return height;
    }

    public double getArea() {
        return getBase() * getHeight();
    }

    public double getPerimeter() {
        return 2 * (getBase() * getHeight());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [base=" + getBase() + ", height=" + getHeight() + "]";
    }

}
