package posizioni;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

/**
 *
 * @author erizzolo
 */
public class Posizioni {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Posizione posizione = new Posizione(1.0, 2.5, "Via Roma 14");
        System.out.println(posizione);
        Collection<Posizione> posizioni = Posizione.readFromFile("src/posizioni/posizioni.txt");
        for (Posizione p : posizioni) {
            System.out.println(p);
        }

        Posizione.writeToFile(posizioni, "src/posizioni/salvataggio.txt");

        Posizione.writeToFile(posizioni, "src/posizioni/salvataggio.json", "JSON");

        Posizione.writeToFile(posizioni, "src/posizioni/salvataggio.xml", "XML");
        Posizione.writeToNetwork(posizioni, "www.google.it", "XML");

        try {
            Collection<Posizione> tryReadFromFile = Posizione.tryReadFromFile("src/posizioni/posizioni.txt");
            for (Posizione posizione1 : tryReadFromFile) {
                System.out.println(posizione1);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            try {
                new File("pippo").createNewFile();
            } catch (IOException ex1) {
            }
        }

    }

}
