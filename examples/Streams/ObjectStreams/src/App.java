import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public class App {

    public static boolean DEBUG = true;

    public static void debug(Object what) {
        if (DEBUG)
            System.out.println(what.toString());
    }

    public static void main(String[] args) {
        // get planets array
        Planet[] planets = getPlanets();
        showPlanets(planets);
        debug("Simple write/read test #1: w/r each element");
        writePlanets("planets.dat", planets);
        Planet[] readPlanets = readPlanets("planets.dat");
        showPlanets(readPlanets);
        debug("Simple write/read test #2: w/r the whole array");
        writeArrayPlanets("planetsArray.dat", planets);
        Planet[] readArrayPlanets = readArrayPlanets("planetsArray.dat");
        showPlanets(readArrayPlanets);
        debug("Write/read test #3: with (de)compression and (de)ciphering");
        writeCCPlanets("planetsCC.dat", planets);
        Planet[] readDDPlanets = readDDPlanets("planetsCC.dat");
        showPlanets(readDDPlanets);
        // se arrivo qui le eccezioni sono state gestite
        debug("Applicazione terminata regolarmente");
    }

    private static void writePlanets(String filename, Planet[] planets) {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filename))) {
            // number of planets, then planets
            os.writeInt(planets.length);
            for (Planet planet : planets) {
                os.writeObject(planet);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Planet[] readPlanets(String filename) {
        Planet[] result = null;
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(filename))) {
            int numPlanets = is.readInt();
            result = new Planet[numPlanets];
            for (int i = 0; i < result.length; i++) {
                result[i] = (Planet) is.readObject();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static void writeArrayPlanets(String filename, Planet[] planets) {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filename))) {
            os.writeObject(planets);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Planet[] readArrayPlanets(String filename) {
        Planet[] result = null;
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(filename))) {
            result = (Planet[]) is.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static void writeCCPlanets(String filename, Planet[] planets) {
        try (ObjectOutputStream os = new ObjectOutputStream( // encoding
                new DeflaterOutputStream( // compressing
                        new Rot13OutputStream( // ciphering
                                new FileOutputStream(filename))))) {
            os.writeObject(planets);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Planet[] readDDPlanets(String filename) {
        Planet[] result = null;
        try (ObjectInputStream is = new ObjectInputStream( // decoding
                new InflaterInputStream(// decompressing
                        new Rot13InputStream( // deciphering
                                new FileInputStream(filename))))) {
            result = (Planet[]) is.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static Planet[] getPlanets() {
        return new Planet[] { new Planet("Mars", 6.4171E23, 206700000, false, 2),
                new Planet("Jupiter", 1.8982E27, 740520000, false, 79),
                new Planet("Mercury", 3.3011E23, 46001200, false, 0),
                new Planet("Venus", 4.8675E24, 107477000, false, 0), new Planet("Earth", 5.9726E24, 147098074, true, 1),
                new Planet("Saturn", 5.6834E26, 1352550000, false, Integer.MAX_VALUE),
                new Planet("Uranus", 8.6810E25, 2.742E9, false, 27),
                new Planet("Neptune", 1.02413E26, 4.5E9, false, 14) };
    }

    private static void showPlanets(Planet[] planets) {
        System.out.println(planets.length + " planets:");
        for (Planet planet : planets) {
            System.out.println(planet);
        }
    }
}
