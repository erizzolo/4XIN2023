package generici;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Random;

import alberi.Albero;

/**
 * Un albero generico, possibilmente con più figli che sono essi stessi alberi
 * generici.
 * 
 * Viene mantenuto per ogni nodo il riferimento al primo figlio (eventualmente
 * EMPTY) ed al successivo fratello (eventualmente EMPTY).
 * 
 * I metodi sono differenziati per evitare che la radice abbia fratelli.
 *
 * Si suppone che i metodi pubblici siano invocati sulla radice.
 */
public class AlberoGenerico<E> implements Albero<E> {

    @SuppressWarnings("rawtypes")
    public static final AlberoGenerico EMPTY = new AlberoGenerico<>();

    private E elemento;
    private AlberoGenerico<E> firstChild;
    private AlberoGenerico<E> nextSibling;

    private AlberoGenerico() {
        firstChild = nextSibling = this;
    }

    @SuppressWarnings("unchecked")
    public AlberoGenerico(E elemento) {
        this(elemento, EMPTY, EMPTY);
    }

    private AlberoGenerico(E elemento, AlberoGenerico<E> firstChild, AlberoGenerico<E> nextSibling) {
        this.elemento = elemento;
        this.firstChild = Objects.requireNonNull(firstChild, "firstChild null");
        this.nextSibling = Objects.requireNonNull(nextSibling, "nextSibling null");
    }

    @SuppressWarnings("unchecked")
    @Override
    public AlberoGenerico<E> getCopy() {
        return this == EMPTY ? EMPTY : new AlberoGenerico<E>(elemento, firstChild.getCopy(), nextSibling.getCopy());
    }

    @Override
    public int size() {
        return this == EMPTY ? 0 : 1 + firstChild.siblingSize();
    }

    private int siblingSize() {
        return this == EMPTY ? 0 : 1 + firstChild.siblingSize() + nextSibling.siblingSize();
    }

    @Override
    public int height() {
        return this == EMPTY ? 0 : 1 + firstChild.siblingHeight();
    }

    private int siblingHeight() {
        return this == EMPTY ? 0 : Math.max(1 + firstChild.siblingHeight(), nextSibling.siblingHeight());
    }

    @Override
    public boolean isEmpty() {
        return this == EMPTY;
    }

    @Override
    public E getRootElement() {
        if (this == EMPTY)
            throw new NoSuchElementException("EMPTY tree");
        return elemento;
    }

    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Albero<E>[] getChildren() {
        if (this == EMPTY)
            throw new NoSuchElementException("EMPTY tree");
        // count children
        int children = 0;
        AlberoGenerico<E> current = firstChild;
        while (current != EMPTY) {
            children++;
            current = current.nextSibling;
        }
        Albero[] result = new Albero[children];
        current = firstChild;
        int child = 0;
        while (current != EMPTY) {
            result[child++] = current;
            current = current.nextSibling;
        }
        return result;
    }

    @Override
    public boolean contains(Object o) {
        return this == EMPTY ? false : nullEquals(o, elemento) || firstChild.siblingContains(o);
    }

    private boolean siblingContains(Object o) {
        return this == EMPTY ? false
                : nullEquals(o, elemento) || firstChild.siblingContains(o) || nextSibling.siblingContains(o);
    }

    @Override
    public AlberoGenerico<E> add(E e) {
        if (this == EMPTY) {
            return new AlberoGenerico<E>(e);
        }
        firstChild = firstChild.siblingAdd(e);
        return this;
    }

    private AlberoGenerico<E> siblingAdd(E e) {
        if (this == EMPTY) {
            return new AlberoGenerico<E>(e);
        } else if (firstChild.size() <= nextSibling.siblingSize()) { // child wins ties
            firstChild = firstChild.siblingAdd(e);
        } else {
            nextSibling = nextSibling.siblingAdd(e);
        }
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public AlberoGenerico<E> remove(Object o) {
        if (this == EMPTY) { // nothing to do
        } else if (nullEquals(o, elemento)) { // root node disappears
            if (firstChild != EMPTY) {
                AlberoGenerico<E> removed = firstChild.nextSibling;
                firstChild.nextSibling = EMPTY;
                firstChild.firstChild = firstChild.firstChild.attach(removed);
            }
            return firstChild;
        } else
            firstChild = firstChild.siblingRemove(o);
        return this;
    }

    private AlberoGenerico<E> siblingRemove(Object o) {
        if (this == EMPTY) { // nothing to do
        } else if (nullEquals(o, elemento)) { // this node disappears
            if (firstChild.size() <= nextSibling.size()) { // attach smaller to bigger
                return nextSibling.attach(firstChild);
            } else {
                return firstChild.attach(nextSibling);
            }
        } else {
            if (firstChild.siblingContains(o)) { // remove from children if possible
                firstChild = firstChild.siblingRemove(o);
            } else { // else from siblings
                nextSibling = nextSibling.siblingRemove(o);
            }
        }
        return this;
    }

    private AlberoGenerico<E> attach(AlberoGenerico<E> e) {
        Objects.requireNonNull(e, "sottoalbero null");
        if (this == EMPTY) {
            return e;
        } else if (firstChild.size() <= nextSibling.size()) { // left wins ties
            firstChild = firstChild.attach(e);
        } else {
            nextSibling = nextSibling.attach(e);
        }
        return this;
    }

    @Override
    public String toString() {
        return this == EMPTY ? "[]"
                : "[" + firstChild.toShortString("", ", ") + elemento + nextSibling.toShortString(", ", "") + "]";
    }

    private String toShortString(String l, String r) {
        return this == EMPTY ? ""
                : l + "[" + firstChild.toShortString("", ", ") + elemento + nextSibling.toShortString(", ", "") + "]"
                        + r;
    }

    public String details() {
        return this == EMPTY ? "[EMPTY]" : "N" + size() + ", L" + height() + " " + this;
    }

    // null aware comparison
    private static boolean nullEquals(Object o, Object e) {
        return o == null ? e == null : o.equals(e);
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private AlberoGenerico<E> current = AlberoGenerico.this;
            private boolean processed = AlberoGenerico.this == EMPTY;
            private Iterator<E> child;

            private Iterator<E> child() {
                return child != null ? child : (child = current.firstChild.siblingIterator());
            }

            @Override
            public boolean hasNext() {
                return current == EMPTY ? false : child().hasNext() || !processed;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    if (child().hasNext())
                        return child().next();
                    if (!processed) {
                        processed = true;
                        return current.elemento;
                    }
                }
                throw new NoSuchElementException();
            }
        };
    }

    public Iterator<E> siblingIterator() {
        return new Iterator<E>() {

            private AlberoGenerico<E> current = AlberoGenerico.this;
            private boolean processed = AlberoGenerico.this == EMPTY;
            private Iterator<E> child;
            private Iterator<E> sibling;

            private Iterator<E> child() {
                return child != null ? child : (child = current.firstChild.siblingIterator());
            }

            private Iterator<E> sibling() {
                return sibling != null ? sibling : (sibling = current.nextSibling.siblingIterator());
            }

            @Override
            public boolean hasNext() {
                return current == EMPTY ? false : child().hasNext() || !processed || sibling().hasNext();
            }

            @Override
            public E next() {
                if (hasNext()) {
                    if (child().hasNext())
                        return child().next();
                    if (!processed) {
                        processed = true;
                        return current.elemento;
                    }
                    return sibling().next();
                }
                throw new NoSuchElementException();
            }
        };
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        AlberoGenerico<String> abs = new AlberoGenerico<String>("A");
        abs = abs.add("B").add("C");

        System.out.println(abs);
        System.out.println("size = " + abs.size());
        System.out.println(abs + " contains C " + abs.contains("C"));
        System.out.println(abs + " contains Z " + abs.contains("Z"));
        abs = abs.add("Y");
        System.out.println(abs);

        AlberoGenerico<String> xyz = AlberoGenerico.EMPTY.add("X").add("Y").add("Z");
        System.out.println(xyz.getClass());
        System.out.println(abs.getClass());
        System.out.println(xyz.details());
        // abs = abs.attach(xyz); risky business
        System.out.println(abs.details());
        abs = abs.remove("C");
        abs = abs.remove("Y");
        System.out.println(abs.details());
        System.out.println(abs.getCopy().details());

        System.out.println("Iterating foreach: " + AlberoGenerico.EMPTY);
        for (Object o : AlberoGenerico.EMPTY) {
            System.out.print(o + ", ");
        }
        System.out.println("");

        System.out.println("Iterating foreach: " + abs);
        for (String s : abs) {
            System.out.print(s + ", ");
        }
        System.out.println("");
        testSpeed(new AlberoGenerico<Integer>(0));
    }

    private static final long SEED = new Random().nextLong();

    private static final void debug(Object what) {
        // System.out.println(what.toString());
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

}
