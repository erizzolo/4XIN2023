import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Purpose: basic list implementation with a doubly linked "hashed" list
 * Author: Emanuele Rizzolo
 * Class: 3XIN
 * Date: 2020/03/04
 */
public class ArrayDLList<E> implements List<E> {

    private static final int INITIAL_CAPACITY = 16;
    private static final int NONE = -1; // index of nothing

    private E[] data; // the actual data
    private int[] next; // next indexes array (will be used for free list also)
    private int[] prev; // previous indexes array
    private int front; // index of first item
    private int back; // index of last item
    private int free; // index of first free slot in data array

    private int size; // size for efficiency

    public ArrayDLList() {
        this(INITIAL_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayDLList(int capacity) {
        data = (E[]) new Object[capacity];
        next = new int[capacity];
        prev = new int[capacity];
        for (int i = 0; i < capacity; ++i)
            next[i] = i + 1;
        next[next.length - 1] = NONE;
        front = back = NONE;
        size = 0; // just to be clear
    }

    @Override
    public boolean add(E e) {
        ensureCapacity();
        int inserted = free; // get the first free slot
        free = next[free];
        data[inserted] = e; // insert data
        next[inserted] = NONE; // no next
        prev[inserted] = back; // link to previous
        if (back == NONE) { // update old back links (if any) and list
            back = front = inserted; // new front and new back
        } else {
            back = next[back] = inserted; // new next and new back
        }
        size++; // one element more
        return true;
    }

    private void ensureCapacity() {
        if (free == NONE) {
        }
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E e : c)
            if (!add(e))
                return false;
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("Unimplemented method 'addAll'");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Unimplemented method 'clear'");
    }

    @Override
    public boolean contains(Object o) {
        // basic iteration !!! reused to implement iterator...
        int current = front; // initialization
        while (current != NONE) { // is there a next element?
            E item = data[current]; // element to be processed
            current = next[current]; // proceed to next element if any
            // process element ...
            if (o == null ? item == null : o.equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object object : c)
            if (!contains(object))
                return false;
        return true;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException("index " + index + " is out of range");
        int current = front;
        while (index-- > 0)
            current = next[current]; // proceed to next element if any
        return data[current];
    }

    @Override
    public int indexOf(Object o) {
        int current = front; // initialization
        int index = 0;
        while (current != NONE) { // is there a next element?
            E item = data[current]; // element to be processed
            current = next[current]; // proceed to next element if any
            // process element ...
            if (o == null ? item == null : o.equals(item)) {
                return index;
            }
            index++;
        }
        return NONE;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private class FrontIterator implements Iterator<E> {

        private int current;

        public FrontIterator(int current) {
            this.current = current;
        }

        @Override
        public boolean hasNext() {
            return current != NONE;
        }

        @Override
        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();
            E item = data[current];
            current = next[current];
            return item;
        }

    }

    @Override
    public Iterator<E> iterator() {
        return new FrontIterator(front);
    }

    @Override
    public int lastIndexOf(Object o) {
        int current = back; // initialization
        int index = size() - 1;
        while (current != NONE) { // is there a next element?
            E item = data[current]; // element to be processed
            current = prev[current]; // proceed to next element if any
            // process element ...
            if (o == null ? item == null : o.equals(item)) {
                return index;
            }
            index--;
        }
        return NONE;
    }

    private class ListaIterator implements ListIterator<E> {

        private int before;
        private int after;
        private int index;

        public ListaIterator(int before, int after) {
            this.before = before;
            this.after = after;
            index = 0;
        }

        @Override
        public void add(E e) {
            // optional operation
            throw new UnsupportedOperationException("Unimplemented method 'add'");
        }

        @Override
        public boolean hasNext() {
            return after != NONE;
        }

        @Override
        public boolean hasPrevious() {
            return before != NONE;
        }

        @Override
        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();
            E item = data[after];
            before = after;
            after = next[after];
            index++;
            return item;
        }

        @Override
        public int nextIndex() {
            return index;
        }

        @Override
        public E previous() {
            if (!hasPrevious())
                throw new NoSuchElementException();
            E item = data[before];
            after = before;
            before = prev[before];
            index--;
            return item;
        }

        @Override
        public int previousIndex() {
            return index - 1;
        }

        @Override
        public void remove() {
            // optional operation
            throw new UnsupportedOperationException("Unimplemented method 'remove'");
        }

        @Override
        public void set(E e) {
            // optional operation
            throw new UnsupportedOperationException("Unimplemented method 'set'");
        }

    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListaIterator(NONE, front);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException("index " + index + " is out of range");
        ListaIterator it = new ListaIterator(NONE, front);
        while (index-- > 0)
            it.next();
        return it;
    }

    @Override
    public boolean remove(Object o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public E remove(int index) {
        // optional operation
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        // optional operation
        throw new UnsupportedOperationException("Unimplemented method 'removeAll'");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        // optional operation
        throw new UnsupportedOperationException("Unimplemented method 'retainAll'");
    }

    @Override
    public E set(int arg0, E arg1) {
        // optional operation
        throw new UnsupportedOperationException("Unimplemented method 'set'");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<E> subList(int arg0, int arg1) {
        throw new UnsupportedOperationException("Unimplemented method 'subList'");
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size()];
        int index = 0;
        for (Object object : this)
            result[index++] = object;
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(T[] a) {
        // the specs look quite complex...
        T[] result = Arrays.copyOf(a, Math.min(size(), a.length));
        int index = 0;
        for (Object object : this)
            result[index++] = (T) object;
        return result;
    }

}
