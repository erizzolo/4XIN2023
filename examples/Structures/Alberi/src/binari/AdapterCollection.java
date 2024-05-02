package binari;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class AdapterCollection<E> implements Collection<E> {

    private AlberoBinario<E> tree;

    @SuppressWarnings("unchecked")
    public AdapterCollection() {
        tree = AlberoBinario.EMPTY;
    }

    @SuppressWarnings("unchecked")
    public AdapterCollection(Collection<? extends E> c) {
        tree = AlberoBinario.EMPTY;
        addAll(c);
    }

    @Override
    public boolean add(E e) {
        int oldSize = tree.size();
        tree = tree.add(e);
        return tree.size() != oldSize;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int oldSize = tree.size();
        for (E e : c) {
            tree = tree.add(e);
        }
        return tree.size() != oldSize;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        tree = AlberoBinario.EMPTY;
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
        return tree.size() == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return tree.iterator();
    }

    @Override
    public boolean remove(Object o) {
        int oldSize = tree.size();
        tree = tree.remove(o);
        return tree.size() != oldSize;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int oldSize = tree.size();
        for (Object o : c) {
            tree = tree.remove(o);
        }
        return tree.size() != oldSize;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int oldSize = tree.size();
        for (Object o : tree) {
            if (!c.contains(o)) {
                tree = tree.remove(o);
            }
        }
        return tree.size() != oldSize;
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
        for (Object o : tree) {
            a[index++] = (T) o;
        }
        return a;
    }

    public static void main(String[] args) {
        Collection<Integer> coll = new AdapterCollection<>();
        for (int i = 0; i < 10; i++) {
            coll.add(i * 2 + 5);
        }
        for (Integer integer : coll) {
            System.out.println(integer);
        }
        for (int i = 0; i < 4; i++) {
            coll.remove(i * 2 + 5);
        }
        System.out.println("");
        
        for (Integer integer : coll) {
            System.out.println(integer);
        }
    }
}
