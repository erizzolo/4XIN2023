package ricerca;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.SortedSet;

public class AVLSet<E extends Comparable<E>> implements SortedSet<E> {

    private AlberoAVL<E> tree;

    @SuppressWarnings("unchecked")
    public AVLSet() {
        tree = AlberoAVL.EMPTY;
    }

    @SuppressWarnings("unchecked")
    public AVLSet(Collection<? extends E> c) {
        tree = AlberoAVL.EMPTY;
        addAll(c);
    }

    @Override
    public boolean add(E e) {
        int size = tree.size();
        tree = tree.add(e);
        return tree.size() != size;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int size = tree.size();
        for (E e : c) {
            tree = tree.add(e);
        }
        return tree.size() != size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        tree = AlberoAVL.EMPTY;
    }

    @Override
    public boolean contains(Object o) {
        return tree.contains(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!tree.contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    @Override
    public Iterator<E> iterator() {
        return tree.iterator();
    }

    @Override
    public boolean remove(Object o) {
        int size = tree.size();
        tree = tree.remove(o);
        return tree.size() != size;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int size = tree.size();
        for (Object o : c) {
            tree = tree.remove(o);
        }
        return tree.size() != size;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int size = tree.size();
        for (Object o : tree) {
            if (!c.contains(o)) {
                tree = tree.remove(o);
            }
        }
        return tree.size() != size;
    }

    @Override
    public int size() {
        return tree.size();
    }

    @Override
    public Object[] toArray() {
        return toArray(new Object[0]);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        a = Arrays.copyOf(a, size());
        int index = 0;
        for (Object object : tree) {
            a[index++] = (T) object;
        }
        return a;
    }

    @Override
    public Comparator<? super E> comparator() {
        return null;
    }

    @Override
    public E first() {
        return tree.iterator().next();
    }

    @Override
    public SortedSet<E> headSet(E toElement) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E last() {
        E result = null;
        for (E e : tree) {
            result = e;
        }
        if (result == null)
            throw new NoSuchElementException();
        return result;
    }

    @Override
    public SortedSet<E> subSet(E fromElement, E toElement) {
        throw new UnsupportedOperationException();
    }

    @Override
    public SortedSet<E> tailSet(E fromElement) {
        throw new UnsupportedOperationException();
    }

}
