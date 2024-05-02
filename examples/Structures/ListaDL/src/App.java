import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class App {
    public static void main(String[] args) throws Exception {
        test(new ArrayDLList<Integer>());
        test(new NodeList<Integer>());
    }

    public static void test(List<Integer> list) {
        System.out.println(list);
        for (int i = 0; i < 10; i++) {
            try {
                list.add(i);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        System.out.println(list);
        for (int i = 0; i < 11; i++) {
            try {
                System.out.println(i + ": " + list.get(i));
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        System.out.println(list);
        for (Integer integer : list) {
            System.out.print(integer + " ");
        }
        System.out.println();
        ListIterator<Integer> it = list.listIterator();
        System.out.println("Test of list iterator ??!!");
        while (it.hasNext())
            it.next();
        while (it.hasPrevious())
            System.out.print(it.previous() + " ");
        System.out.println(it.next());

    }
}
