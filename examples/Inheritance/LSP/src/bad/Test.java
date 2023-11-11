package bad;

public class Test {

    public Test() {
        test(new Rectangle(1, 1));
        test(new Square(1));
    }

    public static boolean test(Rectangle r) {
        System.out.println("Testing " + r);
        double b = 4, h = 5;
        r.setBase(b);
        r.setHeight(h);
        // area of a rectangle is base * height
        boolean result = r.getArea() == b * h;
        if (!result)
            System.out.println("Test failed!!!");
        else
            System.out.println("Test OK!!!");
        return result;
    }

}
