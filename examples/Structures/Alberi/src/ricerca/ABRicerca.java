package ricerca;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Random;

import alberi.Albero;

public class ABRicerca<E extends Comparable<E>> implements Albero<E> {

    @SuppressWarnings("rawtypes")
    public static final ABRicerca EMPTY = new ABRicerca<>();

    private E elemento;
    private ABRicerca<E> left;
    private ABRicerca<E> right;
    private int size; // for efficiency reason
    private int height; // for efficiency reason

    private ABRicerca() {
        left = right = this;
    }

    @SuppressWarnings("unchecked")
    public ABRicerca(E elemento) {
        this(elemento, EMPTY, EMPTY);
    }

    private ABRicerca(E elemento, ABRicerca<E> left, ABRicerca<E> right) {
        this.elemento = Objects.requireNonNull(elemento, "nulls not allowed here");
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
    public ABRicerca<E> getCopy() {
        return this == EMPTY ? EMPTY : new ABRicerca<E>(elemento, left.getCopy(), right.getCopy());
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
        if (this == EMPTY) {
            return false;
        }
        @SuppressWarnings("unchecked")
        Comparable<? super E> oc = (Comparable<? super E>) o;
        int compare = oc.compareTo(elemento);
        return compare == 0 ? true : compare < 0 ? left.contains(o) : right.contains(o);
    }

    @Override
    public ABRicerca<E> add(E e) {
        if (this == EMPTY) {
            return new ABRicerca<E>(e);
        } else if (e.compareTo(elemento) <= 0) { // left wins ties
            left = left.add(e);
        } else {
            right = right.add(e);
        }
        update();
        return this;
    }

    private ABRicerca<E> attach(ABRicerca<E> e) {
        assert (e != null);
        if (this == EMPTY) {
            return e;
        } else if (e != EMPTY) {
            if (e.elemento.compareTo(elemento) <= 0) { // left wins ties
                left = left.attach(e);
            } else {
                right = right.attach(e);
            }
            update();
        }
        return this;
    }

    @Override
    public ABRicerca<E> remove(Object o) {
        if (this == EMPTY) { // nothing to do
        } else {
            @SuppressWarnings("unchecked")
            Comparable<? super E> oc = (Comparable<? super E>) o;
            int compare = oc.compareTo(elemento);
            if (compare == 0) { // this node disappears
                if (left.height() <= right.height()) { // attach smaller to bigger
                    return right.attach(left);
                } else {
                    return left.attach(right);
                }
            } else if (compare < 0) { // search left
                left = left.remove(o);
            } else { // search right
                right = right.remove(o);
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

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private ABRicerca<E> current = ABRicerca.this;
            private boolean processed = ABRicerca.this == EMPTY;
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
                    // if (right().hasNext())
                    return right().next();
                }
                throw new NoSuchElementException();
            }
        };
    }

    public static void main(String[] args) {
        Random rnd = new Random();
        ABRicerca<Integer> abr = new ABRicerca<Integer>(20);
        System.out.println(abr.details());
        for (int i = 0; i < 20; i++) {
            // int next = rnd.nextInt(40);
            int next = i;
            abr = abr.add(next);
            System.out.println("Added " + next + ": " + abr.details());
        }
        for (int i = 0; i < 20; i++) {
            int next = rnd.nextInt(40);
            System.out.println(next + (abr.contains(next) ? " YES :-)" : " NO :-("));
        }
        System.out.println(abr.toTreeString());
        for (int i = 0; i < 20; i++) {
            int next = rnd.nextInt(40);
            abr = abr.remove(next);
            System.out.println("Removed " + next + ": " + abr.details());
        }
        System.out.println(abr.toTreeString());
        for (Integer integer : abr) {
            System.out.print(integer + " ");
        }
        System.out.println("");
    }
}
