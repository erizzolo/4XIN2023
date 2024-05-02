import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Lista<E> implements Collection<E> {

    public static final Lista EMPTY = new Lista<>();

    private E elemento;
    private Lista<E> precedenti;
    private Lista<E> successivi;

    private Lista() {
        precedenti = successivi = this;
    }

    public Lista(E elemento, Lista<E> precedenti, Lista<E> successivi) {
        this.elemento = elemento;
        this.precedenti = Objects.requireNonNull(precedenti, "precedenti null");
        this.successivi = Objects.requireNonNull(successivi, "successivi null");
        // update adjacent lists
        if (precedenti != EMPTY) {
            while (precedenti.successivi != EMPTY) {
                precedenti = precedenti.successivi;
            }
            precedenti.successivi = this;
        }
        if (successivi != EMPTY) {
            while (successivi.precedenti != EMPTY) {
                successivi = successivi.precedenti;
            }
            successivi.precedenti = this;
        }
    }

    @Override
    public int size() {
        return this == EMPTY ? 0 : precedenti.leftSize() + 1 + successivi.rightSize();
    }

    private int leftSize() {
        return this == EMPTY ? 0 : precedenti.leftSize() + 1;
    }

    private int rightSize() {
        return this == EMPTY ? 0 : 1 + successivi.rightSize();
    }

    @Override
    public boolean isEmpty() {
        return this == EMPTY;
    }

    @Override
    public boolean contains(Object o) {
        return this == EMPTY ? false : precedenti.leftContains(o) || o.equals(elemento) || successivi.rightContains(o);
    }

    private boolean leftContains(Object o) {
        return this == EMPTY ? false : precedenti.leftContains(o) || o.equals(elemento);
    }

    private boolean rightContains(Object o) {
        return this == EMPTY ? false : o.equals(elemento) || successivi.rightContains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private Lista<E> current = Lista.this;

            @Override
            public boolean hasNext() {
                return current != EMPTY;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    E result = current.elemento;
                    current = current.successivi;
                    return result;
                }
                throw new NoSuchElementException();
            }

        };
    }

    @Override
    public Object[] toArray() {
        return toArray(new Object[0]);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        a = Arrays.copyOf(a, size());
        int index = 0;
        for (Object object : this) {
            a[index++] = (T) object;
        }
        return a;
    }

    @Override
    public boolean add(E e) {
        if (this == EMPTY) {
            throw new UnsupportedOperationException("Cannot modify Lista.EMPTY");
        }
        Lista<E> current = this;
        while (current.successivi != EMPTY) {
            current = current.successivi;
        }
        // now I'm sure current.successivi == EMPTY
        current.successivi = new Lista<E>(e, current, EMPTY);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object object : c) {
            if (!contains(object)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (this == EMPTY) {
            throw new UnsupportedOperationException("Cannot modify Lista.EMPTY");
        }
        Lista<E> current = this;
        while (current.successivi != EMPTY) {
            current = current.successivi;
        }
        // now I'm sure current.successivi == EMPTY
        for (E e : c) {
            current = current.successivi = new Lista<E>(e, current, EMPTY);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return this == EMPTY ? "|_|" : precedenti.leftToString() + "_" + elemento + "_" + successivi.rightToString();
    }

    private String leftToString() {
        return this == EMPTY ? "|" : precedenti.leftToString() + "_" + elemento;
    }

    private String rightToString() {
        return this == EMPTY ? "|" : elemento + "_" + successivi.rightToString();
    }

}
