package good;

public class Test {

    public Test() {
        test(new Rectangle(1, 1));
        test(new Square(1));
        test(new RectangleScalable(1, 1));
        test(new SquareScalable(1));
        test(new RectangleResizable(1, 1));
    }

    public static boolean test(Rectangle r) {
        System.out.println("Testing " + r);
        // area of a rectangle is base * height
        boolean result = r.getArea() == r.getBase() * r.getHeight();
        if (!result)
            System.out.println("Test failed!!!");
        else {
            if (r instanceof RectangleScalable) {
                RectangleScalable rs = (RectangleScalable) r;
                rs.scale(5.0);
                result = r.getArea() == r.getBase() * r.getHeight();
                if (!result)
                    System.out.println("Test failed!!!");
                else {
                    if (r instanceof RectangleResizable) {
                        RectangleResizable rr = (RectangleResizable) r;
                        rr.resize(0.2, 5.0);
                        result = rr.getArea() == rr.getBase() * rr.getHeight();
                        if (!result)
                            System.out.println("Test failed!!!");
                    }
                }
            }
        }
        if (result)
            System.out.println("Test OK!!!");
        return result;
    }

}
