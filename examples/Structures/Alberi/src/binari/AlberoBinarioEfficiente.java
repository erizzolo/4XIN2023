package binari;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Random;

import alberi.Albero;

public class AlberoBinarioEfficiente<E> implements Albero<E> {

    @SuppressWarnings("rawtypes")
    public static final AlberoBinarioEfficiente EMPTY = new AlberoBinarioEfficiente<>();

    private E elemento;
    private AlberoBinarioEfficiente<E> left;
    private AlberoBinarioEfficiente<E> right;
    private int size; // for efficiency reason
    private int height; // for efficiency reason

    private AlberoBinarioEfficiente() {
        left = right = this;
    }

    @SuppressWarnings("unchecked")
    public AlberoBinarioEfficiente(E elemento) {
        this(elemento, EMPTY, EMPTY);
    }

    private AlberoBinarioEfficiente(E elemento, AlberoBinarioEfficiente<E> left, AlberoBinarioEfficiente<E> right) {
        this.elemento = elemento;
        this.left = Objects.requireNonNull(left, "sottoalbero sinistro null");
        this.right = Objects.requireNonNull(right, "sottoalbero destro null");
        update();
    }

    private void update() {
        size = left.size() + 1 + right.size();
        height = 1 + Math.max(left.height(), right.height());
    }

    @SuppressWarnings("unchecked")
    @Override
    public AlberoBinarioEfficiente<E> getCopy() {
        return this == EMPTY ? EMPTY : new AlberoBinarioEfficiente<E>(elemento, left.getCopy(), right.getCopy());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height;
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
        Albero[] result = { left, right };
        return result;
    }

    @Override
    public boolean contains(Object o) {
        return this == EMPTY ? false : left.contains(o) || nullEquals(o, elemento) || right.contains(o);
    }

    @Override
    public AlberoBinarioEfficiente<E> add(E e) {
        if (this == EMPTY) {
            return new AlberoBinarioEfficiente<E>(e);
        } else if (left.size() <= right.size()) { // left wins ties
            left = left.add(e);
        } else {
            right = right.add(e);
        }
        update();
        return this;
    }

    public AlberoBinarioEfficiente<E> attach(AlberoBinarioEfficiente<E> e) {
        assert (e != null);
        if (this == EMPTY) {
            return e;
        } else if (left.size() <= right.size()) { // left wins ties
            left = left.attach(e);
        } else {
            right = right.attach(e);
        }
        update();
        return this;
    }

    public AlberoBinarioEfficiente<E> remove(Object o) {
        if (this == EMPTY) { // nothing to do
        } else if (nullEquals(o, elemento)) { // this node disappears
            if (left.size() <= right.size()) { // attach smaller to bigger
                return right.attach(left);
            } else {
                return left.attach(right);
            }
        } else {
            int ls = left.size();
            left = left.remove(o); // try to remove from left
            if (left.size() == ls) { // unsuccessful
                right = right.remove(o);// try to remove from right
            }
            update();
        }
        return this;
    }

    @Override
    public String toString() {
        return this == EMPTY ? "[]"
                : "[" + left.toShortString("", ", ") + elemento + right.toShortString(", ", "") + "]";
    }

    private String toShortString(String l, String r) {
        return this == EMPTY ? ""
                : l + "[" + left.toShortString("", ", ") + elemento + right.toShortString(", ", "") + "]" + r;
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

            private AlberoBinarioEfficiente<E> current = AlberoBinarioEfficiente.this;
            private boolean processed = AlberoBinarioEfficiente.this == EMPTY;
            private Iterator<E> left;
            private Iterator<E> right;

            private Iterator<E> left() {
                return left != null ? left : (left = current.left.iterator());
            }

            private Iterator<E> right() {
                return right != null ? right : (right = current.right.iterator());
            }

            @Override
            public boolean hasNext() {
                return current == EMPTY ? false : left().hasNext() || !processed || right().hasNext();
            }

            @Override
            public E next() {
                if (hasNext()) {
                    if (left().hasNext())
                        return left().next();
                    if (!processed) {
                        processed = true;
                        return current.elemento;
                    }
                    return right().next();
                }
                throw new NoSuchElementException();
            }
        };
    }

    private static final long SEED = new Random().nextLong();

    private static final void debug(Object what) {
        // System.out.println(what.toString());
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        String[] split = "".split("\n");
        System.out.println(split);
        for (String string : split) {
            System.out.println("-" + string + "-");
        }

        String a = null, b = null;
        System.out.println(" " + (a == b));

        // AlberoBinario<String> absb = new AlberoBinario<String>("B",
        // AlberoBinario.EMPTY, AlberoBinario.EMPTY);
        // AlberoBinario<String> absc = new AlberoBinario<String>("C",
        // AlberoBinario.EMPTY, AlberoBinario.EMPTY);

        // AlberoBinario<String> abs = new AlberoBinario<String>("A", absb, absc);
        // AlberoBinario<String> abs = new AlberoBinario<String>("A");
        AlberoBinario<String> abs = AlberoBinario.EMPTY;
        AlberoBinario<String> add = abs.add("B").add("C").add("A");
        if (add == abs) {
            System.out.println("add e abs sono lo stesso albero");
        }

        System.out.println(abs);
        System.out.println(add);
        abs = add;
        System.out.println("size = " + abs.size());
        System.out.println(abs + " contains C " + abs.contains("C"));
        System.out.println(abs + " contains Z " + abs.contains("Z"));
        abs.add("Y");
        System.out.println(abs);

        AlberoBinario<String> xyz = AlberoBinario.EMPTY.add("X").add("Y").add("Z");
        System.out.println(xyz.getClass());
        System.out.println(abs.getClass());
        System.out.println(xyz.details());
        // abs = abs.attach(xyz); risky business
        System.out.println(abs.details());
        abs = abs.remove("C");
        abs = abs.remove("Y");
        System.out.println(abs.details());
        System.out.println(abs.getCopy().details());
        // AlberoBinario<String> what = new AlberoBinario<String>("What", xyz, xyz);
        // System.out.println(what.details());
        // xyz.remove("Z");
        // System.out.println(what.details());

        System.out.println("Iterating foreach: " + AlberoBinario.EMPTY);
        for (Object o : AlberoBinario.EMPTY) {
            System.out.print(o + ", ");
        }
        System.out.println("");

        System.out.println("Iterating foreach: " + abs);
        for (String s : abs) {
            System.out.print(s + ", ");
        }
        System.out.println("");
        // testSpeed(new BalancedABR<Integer>(0));
        // testSpeed(new ABRBalanced<Integer>(0));
        testSpeed(new AlberoBinario<Integer>(0));
        // testSpeed(new AlberoBinarioEfficiente<Integer>(0));
        // testSpeed(new ABRicerca<Integer>(0));
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
