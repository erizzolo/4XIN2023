import constants.Constants;
import identified.Identified;
import singleton.Singleton;

public class StaticTest {
    public static void main(String[] args) throws Exception {
        System.out.println("The speed of light is " + Constants.SPEED_OF_LIGHT + " m/s");
        System.out.println("The golden ratio is " + Constants.GOLDEN_RATIO);
        System.out.println("erf(1.5) " + Constants.erf(1.5));
        Identified original = new Identified();
        System.out.println(original);
        for (int i = 0; i < 5; i++) {
            System.out.println(new Identified());
        }
        Identified copied = new Identified(original);
        System.out.println(copied);
        Singleton singleton = Singleton.get();
        System.out.println(singleton);
        Thread.sleep(2000); // wait a couple of seconds
        Singleton anotherSingleton = Singleton.get();
        System.out.println(anotherSingleton);
        if (singleton == anotherSingleton) {
            System.out.println("Got the same singleton again...");
        }
    }
}
