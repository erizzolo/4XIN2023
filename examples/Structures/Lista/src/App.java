import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("EMPTY = " + Lista.EMPTY);
        Lista<Integer> l = new Lista<Integer>(10, Lista.EMPTY, Lista.EMPTY);
        System.out.println("l = " + l);
        Lista<Integer> l2 = new Lista<Integer>(20, l, Lista.EMPTY);
        System.out.println("l2 = " + l2);
        System.out.println("l = " + l);
        for (int i = 0; i < 5; i++) {
            l.add(i);
        }
        Collection<Integer> c = new ArrayList<>();
        c.add(6);
        c.add(7);
        c.add(8);
        l.addAll(c);
        System.out.println(l);
        // l = new Lista<Integer>(42, l, Lista.EMPTY);
        // l = new Lista<Integer>(66, Lista.EMPTY, l);
        try {
            for (int i = 0; i < 11; i++) {
                System.out.println(l + (l.contains(i) ? "" : " non") + " contiene " + i);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("ciclo for each :");
        for (Integer integer : l) {
            System.out.println(integer);
        }
        System.out.println("l.size() = " + l.size());
        System.out.println(l.toArray());
        System.out.println(Arrays.toString(l.toArray()));
        System.out.println(l.toArray(new Integer[0]));
        System.out.println(Arrays.toString(l.toArray(new Integer[0])));

    }
}
