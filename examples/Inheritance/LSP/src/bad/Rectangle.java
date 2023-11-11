package bad;

/**
 * Actually a RectangleWithIndependentlyResizableSides
 */
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

    public void setBase(double base) {
        this.base = base;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getArea() {
        return getBase() * getHeight();
    }

    public double getPerimeter() {
        return 2 * (getBase() * getHeight());
    }

    @Override
    public String toString() {
        return "Rectangle [base=" + getBase() + ", height=" + getHeight() + "]";
    }

}
