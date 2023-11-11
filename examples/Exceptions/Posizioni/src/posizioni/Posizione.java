package posizioni;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Scanner;

public class Posizione {

    private final double latitudine;
    private final double longitudine;
    private final String indirizzo;

    public double getLatitudine() {
        return latitudine;
    }

    public double getLongitudine() {
        return longitudine;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public Posizione(double latitudine, double longitudine, String indirizzo) {
        this.latitudine = check(latitudine, "latitudine");
        this.longitudine = check(longitudine, "longitudine");
        this.indirizzo = Objects.requireNonNull(indirizzo, "indirizzo non specificato");
    }

    private static double check(double value, String name) {
        if (Double.isFinite(value)) {
            return value;
        }
        throw new IllegalArgumentException(name + " non valido");
    }

    @Override
    public String toString() {
        return indirizzo + " (" + latitudine + ", " + longitudine + ")";
    }

    public String toString(String formato) {
        String result = "";
        switch (formato) {
            case "CSV":
                result += getLatitudine() + "," + getLongitudine() + "," + getIndirizzo();
                break;
            case "JSON":
                result += "{ \"latitudine\": " + getLatitudine();
                result += ", \"longitudine\": " + getLongitudine();
                result += ", \"indirizzo\": \"" + getIndirizzo() + "\" }";
                break;
            case "XML":
                result += "<posizione>";
                result += "<latitudine>" + getLatitudine() + "</latitudine>";
                result += "<longitudine>" + getLongitudine() + "</longitudine>";
                result += "<indirizzo>" + getIndirizzo() + "</indirizzo>";
                result += "</posizione>";
                break;
            default:
                result = toString();
        }
        return result;
    }

    public static void writeToFile(Collection<Posizione> posizioni, String filename) {
        BufferedWriter output = null;
        try {
            // output = Files.newBufferedWriter(Paths.get(filename),
            // StandardOpenOption.APPEND, StandardOpenOption.CREATE);
            output = Files.newBufferedWriter(Paths.get(filename));
            writeCollection(posizioni, "CSV", output);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // chiusura file
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void writeToNetwork(Collection<Posizione> posizioni, String hostname, String formato) {
        Socket socket = null;
        try {
            InetAddress host = InetAddress.getByName(hostname);
            socket = new Socket(host, 80);
            OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
            BufferedWriter output = new BufferedWriter(osw);
            writeCollection(posizioni, formato, output);
            System.out.println("Written to " + socket);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // chiusura file
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void writeToFile(Collection<Posizione> posizioni, String filename, String formato) {
        BufferedWriter output = null;
        try {
            // output = Files.newBufferedWriter(Paths.get(filename),
            // StandardOpenOption.APPEND, StandardOpenOption.CREATE);
            output = Files.newBufferedWriter(Paths.get(filename));
            writeCollection(posizioni, formato, output);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // chiusura file
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void writeCollection(Collection<Posizione> posizioni, String formato, BufferedWriter output)
            throws IOException {
        // operazioni su file
        output.write("# formato: " + formato);
        output.newLine();
        output.write("# " + posizioni.size() + " posizioni");
        output.newLine();
        int written = 0;
        for (Posizione posizione : posizioni) {
            output.write(posizione.toString(formato));
            output.newLine();
            written++;
        }
        output.write("# " + written + " posizioni");
        output.newLine();
    }

    public static Collection<Posizione> readFromFile(String filename) {
        Collection<Posizione> result = new ArrayList<>();
        Scanner input = null;
        try {
            input = new Scanner(new File(filename));
            // operazioni sul file
            // IOException --> catch
            while (input.hasNextLine()) {
                String line = input.nextLine();
                System.out.println(":" + line + ":");
                if (line.startsWith("#")) {
                    // commento
                } else {
                    String[] parti = line.split(",", 3);
                    if (parti.length == 3) {
                        try {
                            double latitudine = Double.parseDouble(parti[0]);
                            double longitudine = Double.parseDouble(parti[1]);
                            String indirizzo = parti[2];
                            Posizione posizione = new Posizione(latitudine, longitudine, indirizzo);
                            result.add(posizione);
                            System.out.println("Aggiunto " + posizione);
                        } catch (RuntimeException ex) {
                            System.err.println(ex);
                            ex.printStackTrace();
                        }
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            // System.err.println(ex);
        } finally {
            // chiusura del file
            if (input != null) {
                input.close();
            }
        }
        return result;
    }

    public static Collection<Posizione> tryReadFromFile(String filename) throws FileNotFoundException {
        Collection<Posizione> result = new ArrayList<>();
        Scanner input = new Scanner(new File(filename));
        // operazioni sul file
        // IOException --> catch
        while (input.hasNextLine()) {
            String line = input.nextLine();
            System.out.println(":" + line + ":");
            if (line.startsWith("#")) {
                // commento
            } else {
                String[] parti = line.split(",", 3);
                if (parti.length == 3) {
                    try {
                        double latitudine = Double.parseDouble(parti[0]);
                        double longitudine = Double.parseDouble(parti[1]);
                        String indirizzo = parti[2];
                        Posizione posizione = new Posizione(latitudine, longitudine, indirizzo);
                        result.add(posizione);
                        System.out.println("Aggiunto " + posizione);
                    } catch (RuntimeException e) {
                    }
                }
            }
        }
        // chiusura del file
        input.close();
        return result;
    }

}
