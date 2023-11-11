import java.io.InputStream;
import java.io.OutputStream;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;

public class App {

    static final String command = "dieharder -a -g 200 -m 0.1";
    // static final String command = "dieharder -g 200 -m 0.1 -d ";
    // static final String command = "dieharder -g 200 -d ";

    public static void main(String[] args) throws Exception {
        test(new Random());
        test(new MathRandom());
    }

    private static void test(Random random) throws Exception {
        System.out.println("Test of " + random.getClass() + " " + speed(random));
        // for (int i = 0; i < 5; i++) {
        for (int i = 0; i < 1; i++) {
            // Process test = Runtime.getRuntime().exec(command + i);
            Process test = Runtime.getRuntime().exec(command);
            OutputStream os = test.getOutputStream();
            InputStream is = test.getInputStream();
            while (test.isAlive()) {
                int next = random.nextInt(1 << 16);
                for (int b = 0; b < 2; b++) {
                    try {
                        os.write(next & 0xFF);
                    } catch (Exception e) {
                    }
                    next >>= 8;
                }
                while (is.available() > 0) {
                    System.out.write(is.read());
                }
            }
        }
    }

    private static Duration speed(Random random) {
        final int NUMBERS = 1000000;
        LocalDateTime started = LocalDateTime.now();
        long total = 0;
        for (int i = 0; i < NUMBERS; i++) {
            total += random.nextInt(1 << 16);
        }
        if(total < 0) {
            return Duration.ZERO;
        }
        return Duration.between(started, LocalDateTime.now());
    }
}
