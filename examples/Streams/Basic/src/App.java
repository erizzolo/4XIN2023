import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.Duration;
import java.time.LocalDateTime;

public class App {

    public static boolean DEBUG = true;
    private static LocalDateTime start;
    private static Duration elapsed;

    public static void debug(Object what) {
        if (DEBUG)
            System.out.println(what.toString());
    }

    public static void main(String[] args) {
        // file copy with streams
        String infile = "README.md";
        if (args.length > 0) {
            infile = args[0];
        }

        start = LocalDateTime.now();
        tryWithBlock(infile);
        elapsed = Duration.between(start, LocalDateTime.now());
        System.out.println("tryWithBlock completed in " + elapsed);

        start = LocalDateTime.now();
        tryWithOutBlock(infile);
        elapsed = Duration.between(start, LocalDateTime.now());
        System.out.println("tryWithOutBlock completed in " + elapsed);

        start = LocalDateTime.now();
        tryWithSingle(infile);
        elapsed = Duration.between(start, LocalDateTime.now());
        System.out.println("tryWithSingle completed in " + elapsed);

        start = LocalDateTime.now();
        tryWithOutSingle(infile);
        elapsed = Duration.between(start, LocalDateTime.now());
        System.out.println("tryWithOutSingle completed in " + elapsed);

        // se arrivo qui le eccezioni sono state gestite
        debug("Applicazione terminata regolarmente");
    }

    private static void tryWithBlock(String infile) {
        // try with resources
        try (InputStream is = new FileInputStream(infile);
                OutputStream os = new FileOutputStream("tryWithBlock", false)) {
            // utilizzo dello stream
            debug("Ottenuto stream " + is + ": " + is.available() + " bytes disponibili");
            block(is, os);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void tryWithOutBlock(String infile) {
        // try without resources
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(infile);
            os = new FileOutputStream("tryWithOutBlock", false);
            // utilizzo dello stream
            debug("Ottenuto stream " + is + ": " + is.available() + " bytes disponibili");
            block(is, os);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (Exception e) {
            }
            try {
                os.close();
            } catch (Exception e) {
            }
        }
    }

    private static void tryWithSingle(String infile) {
        // try with resources
        try (InputStream is = new FileInputStream(infile);
                OutputStream os = new FileOutputStream("tryWithSingle", false)) {
            // utilizzo dello stream
            debug("Ottenuto stream " + is + ": " + is.available() + " bytes disponibili");
            single(is, os);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void tryWithOutSingle(String infile) {
        // try without resources
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(infile);
            os = new FileOutputStream("tryWithOutSingle", false);
            // utilizzo dello stream
            debug("Ottenuto stream " + is + ": " + is.available() + " bytes disponibili");
            single(is, os);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (Exception e) {
            }
            try {
                os.close();
            } catch (Exception e) {
            }
        }
    }

    private static void block(InputStream is, OutputStream os) throws IOException {
        int bytesRead = 0; // bytes letti
        byte[] buffer = new byte[128]; // [4096] = 4KB
        int readCalls = 1; // chiamate a read(...)
        int inseriti = is.read(buffer); // prima lettura
        while (inseriti != -1) { // -1 means end of stream
            bytesRead += inseriti;
            os.write(buffer, 0, inseriti);
            inseriti = is.read(buffer);
            readCalls++;
        }
        debug(readCalls + " chiamate a read(...), letti " + bytesRead + " B: disponibili " + is.available() + " B");
    }

    private static void single(InputStream is, OutputStream os) throws IOException {
        int bytesRead = 0; // bytes letti
        int readCalls = 1; // chiamate a read(...)
        int letto = is.read(); // prima lettura
        while (letto != -1) { // -1 means end of stream
            bytesRead++;
            os.write(letto);
            letto = is.read();
            readCalls++;
        }
        debug(readCalls + " chiamate a read(...), letti " + bytesRead + " B: disponibili " + is.available() + " B");
    }

}
