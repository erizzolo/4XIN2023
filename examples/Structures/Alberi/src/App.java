import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import alberi.Albero;
import binari.AlberoBinario;
import binari.AlberoBinarioEfficiente;
import generici.AlberoGenerico;
import ricerca.ABRicerca;
import ricerca.AVLNodeSet;
import ricerca.AVLNodes;
import ricerca.AVLSet;
import ricerca.AlberoAVL;
import ricerca.AlberoAVLDuplicates;

public class App {

    private static final long SEED = new Random().nextLong();

    private static final void debug(Object what) {
        System.out.println(what.toString());
    }

    // @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        testString(new AlberoBinario<String>("test"));
        testString(new AlberoBinarioEfficiente<String>("test"));
        testString(new AlberoGenerico<String>("test"));
        testString(new ABRicerca<String>("test"));
        testString(new AlberoAVLDuplicates<String>("test"));
        testString(new AlberoAVL<String>("test"));
        testString(new AVLNodes<String>("test"));
        testSet(new TreeSet<Integer>());
        testSet(new AVLSet<Integer>());
        testSet(new AVLNodeSet<Integer>());
    }

    public static void testString(Albero<String> albero) {
        final String[] words = "Quel ramo del lago di Como che volge a mezzogiorno tra due catene ininterrotte di monti"
                .split(" ");
        System.out.println("Test of " + albero.getClass() + ": " + albero);
        LocalDateTime start = LocalDateTime.now();
        boolean test = albero.contains("test");
        if (test) {
            albero = albero.remove("test");
            test = !albero.contains("test");
        }
        debug("Checking and removing word 'test': test " + (test ? "successful" : "FAILED"));
        debug("Inserting " + words.length + " words");
        String inserted = "";
        for (String word : words) {
            inserted += word + " ";
            albero = albero.add(word);
        }
        debug("Inserted: " + inserted);
        debug(albero);
        debug(albero.toTreeString());
        debug("size: " + albero.size() + ", height: " + albero.height());
        debug("Removing short words (< 4 letters)");
        String removed = "";
        for (String word : words) {
            if (word.length() < 4) {
                removed += word + " ";
                albero = albero.remove(word);
            }
        }
        debug("Removed: " + removed);
        debug(albero);
        debug(albero.toTreeString());
        debug("size: " + albero.size() + ", height: " + albero.height());
        debug("Checking iterator");
        int count = 0;
        String sentence = "";
        for (String word : albero) {
            ++count;
            sentence += word + " ";
        }
        debug(sentence);
        debug("count: " + count + ", size: " + albero.size());
        Duration duration = Duration.between(start, LocalDateTime.now());
        System.out.println("Test completed in " + duration);
        System.out.println("");
    }

    public static void testSpeed(Albero<Integer> ab) {
        // final int INSERTIONS = 50_000;
        // final int REMOVALS = 50_000;
        final int INSERTIONS = 50;
        final int REMOVALS = 50;
        System.out.println("Speed test of " + ab.getClass() + " " + ab);
        LocalDateTime start = LocalDateTime.now();
        Random random = new Random(SEED); // same sequence
        System.out.println("Inserting " + INSERTIONS + " random elements");
        for (int i = 0; i < INSERTIONS; i++) {
            // ab = ab.add(random.nextInt(INSERTIONS / 10));
            int next = random.nextInt(INSERTIONS);
            debug("Inserting " + next);
            ab = ab.add(next);
            debug(ab);
        }
        System.out.println("size: " + ab.size());
        System.out.println("height: " + ab.height());
        // System.out.println(ab);
        Duration partial = Duration.between(start, LocalDateTime.now());
        System.out.println(partial);
        System.out.println("(Trying to) remove " + INSERTIONS + " random elements");
        for (int i = 0; i < REMOVALS; i++) {
            int next = random.nextInt(INSERTIONS);
            debug("Removing " + next);
            ab = ab.remove(next);
            debug(ab);
        }
        Duration duration = Duration.between(start, LocalDateTime.now());
        System.out.println(duration);
        System.out.println("size: " + ab.size());
        System.out.println("height: " + ab.height());
        System.out.println(ab);
        System.out.println(ab.toTreeString());
        System.out.println("Checking iterator");
        int count = 0;
        for (Integer integer : ab) {
            ++count;
            System.out.print(integer + " ");
        }
        System.out.println("");
        System.out.println("count: " + count + ", size: " + ab.size());

    }

    public static void testSet(Set<Integer> set) {
        final int INSERTIONS = 5_000_000;
        final int REMOVALS = 5_000_000;
        // final int INSERTIONS = 50;
        // final int REMOVALS = 50;
        System.out.println("Speed test of " + set.getClass() + " " + set);
        Random random = new Random(SEED); // same sequence
        LocalDateTime start = LocalDateTime.now();
        System.out.println("Inserting " + INSERTIONS + " random elements");
        for (int i = 0; i < INSERTIONS; i++) {
            // ab = ab.add(random.nextInt(INSERTIONS / 10));
            int next = random.nextInt(INSERTIONS);
            set.add(next);
        }
        System.out.println("size: " + set.size());
        Duration partial = Duration.between(start, LocalDateTime.now());
        System.out.println(partial);
        System.out.println("(Trying to) remove " + INSERTIONS + " random elements");
        for (int i = 0; i < REMOVALS; i++) {
            int next = random.nextInt(INSERTIONS);
            set.remove(next);
        }
        Duration duration = Duration.between(start, LocalDateTime.now());
        System.out.println("Test completed in " + duration);
        System.out.println("size: " + set.size());

    }

}
