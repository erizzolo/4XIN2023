package screen;

/**
 * Sample test application
 *
 * @author emanuele
 */
public class ScreenTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Errore: new Tappable();
        Tappable t;
        // Errore: new TouchScreen(10, 30, 100, 300);
        t = null;
        System.out.println("t = " + t);
        // deferencing null pointer !!! t.tap(PhysicalPosition.TOP_LEFT_CORNER);
        test(t);
        t = new RealTouchScreen(10, 30, 100, 300);
        // String is not Touchable t = new String("3784");
        test(t);
        SuperTouchScreen superTouchScreen = new SuperTouchScreen(20, 60, 200, 600);
        test(superTouchScreen);
        superTouchScreen.setOrientation(Orientable.Orientation.LANDSCAPE);
        test(superTouchScreen);
    }

    /**
     * Test a Tappable something
     *
     * @param t the Tappable to be tested
     */
    public static void test(Tappable t) {
        if (t != null) {
            System.out.println("Testing " + t);
            t.tap(new PhysicalPosition(1, 5), 2);
            t.doubleTap(PhysicalPosition.TOP_LEFT_CORNER);
            t.tap(40, 100, 1);
        } else {
            System.out.println("t is null");
        }
    }

}
