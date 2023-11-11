public class Test {
    public static void main(String[] args) throws Exception {
        // a square as a generic polygon
        {
            PoligonoRegolare square = new PoligonoRegolare(4);
            System.out.println("square = new PoligonoRegolare(4): " + square);
            System.out.println("square.getVertici():");
            for (Punto p : square.getVertici()) {
                System.out.println(p);
            }
            System.out.println("square.getArea(): " + square.getArea());
            System.out.println("square.getApotema(): " + square.getApotema());
        }
        // a square as a real square
        {
            PoligonoRegolare square = new Quadrato();
            System.out.println("square = new Quadrato(): " + square);
            System.out.println("square.getVertici():");
            for (Punto p : square.getVertici()) {
                System.out.println(p);
            }
            System.out.println("square.getArea(): " + square.getArea());
            System.out.println("square.getApotema(): " + square.getApotema());
        }
        // a square as a scalable square
        {
            QuadratoScalabile square = new QuadratoScalabile();
            System.out.println("square = new QuadratoScalabile(): " + square);
            System.out.println("square.getVertici():");
            for (Punto p : square.getVertici()) {
                System.out.println(p);
            }
            System.out.println("square.getArea(): " + square.getArea());
            System.out.println("square.getApotema(): " + square.getApotema());
            System.out.println("Scaling square: square.scala(5);");
            square.scala(5);
            System.out.println("Scaled square: " + square);
            System.out.println("square.getVertici():");
            for (Punto p : square.getVertici()) {
                System.out.println(p);
            }
            System.out.println("square.getArea(): " + square.getArea());
            System.out.println("square.getApotema(): " + square.getApotema());
            System.out.println("WTF, why it doesn't work???");
        }
    }
}
