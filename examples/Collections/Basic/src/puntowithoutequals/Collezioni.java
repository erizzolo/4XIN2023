package puntowithoutequals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Stack;
import java.util.TreeSet;

/**
 *
 * @author erizzolo
 */
public class Collezioni {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Per le classi che non ridefiniscono il metodo equals,");
        System.out.println("le Collection NON FUNZIONANO come ci si aspetta !!!");

        // Tipo più generico possibile<elemento più specifico possibile>
        Collection<Punto> lista = new ArrayList<>();
        test(lista);
        Collection<Punto> pila = new Stack<>();
        test(pila);
        Collection<Punto> coda = new ArrayDeque<>();
        test(coda);
        Collection<Punto> insiemeHash = new HashSet<>();
        test(insiemeHash);
        Collection<Punto> insiemeAlbero = new TreeSet<>();
        test(insiemeAlbero);
    }

    public static void test(Collection<Punto> c) {
        System.out.println("\nTesting " + c.getClass().getName());
        showCollection(c);
        addPunti(c, 3);
        showCollection(c);
        testPunti(c, 3);
        System.out.println("Clearing collection: ");
        c.clear();
        showCollection(c);
    }

    public static void addPunti(Collection<Punto> c, int howMany) {
        System.out.print("Adding " + howMany + ":");
        for (int i = 0; i < howMany; i++) {
            Punto p = new Punto(i, i);
            System.out.print(" " + p);
            c.add(p);
        }
        System.out.println("");

    }

    public static void testPunti(Collection<Punto> c, int howMany) {
        System.out.print("Checking " + howMany + ":");
        for (int i = 0; i < howMany; i++) {
            Punto p = new Punto(i, i);
            System.out.print(" " + p);
            System.out.print(c.contains(p) ? ": PRESENTE." : ": ASSENTE.");
        }
        System.out.println("");

    }

    private static void showCollection(Collection<Punto> c) {
        System.out.print("Showing " + c.getClass().getSimpleName());
        System.out.println(" of size " + c.size() + ": " + c);
    }

}
