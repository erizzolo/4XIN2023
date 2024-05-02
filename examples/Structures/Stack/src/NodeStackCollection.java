import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class NodeStackCollection<E> implements Stack<E>, Collection<E> {

    private static class Node<E> {

        E item; // the element in this Node
        Node<E> next; // next Node according to structure logic

        Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }

    }

    private Node<E> top; // top of the stack
    private int size; // size for efficiency

    public NodeStackCollection() {
        top = null; // just to be explicit (aka eccesso di chiarezza)
        size = 0; // just to be explicit (aka eccesso di chiarezza)
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int capacity() {
        return Integer.MAX_VALUE;
    }

    @Override
    public void push(E e) {
        top = new Node<>(e, top); // new top Node
        size++; // don't forget...
    }

    @Override
    public E pop() {
        if (size() == 0) // empty
            throw new EmptyStackException("empty stack");
        size--; // one element less
        E result = top.item; // extract from top non-empty Node
        top = top.next; // update top Node (+ let the gc do its job)
        return result;
    }

    @Override
    public String toString() {
        String result = "NodeStackCollection{ " + size() + "/" + capacity() + ", top :";
        for (E item : this) {
            result += " " + item;
        }
        result += " : top}";
        return result;
    }

    public static void main(String[] args) {
        App.test(new NodeStackCollection<Integer>());
    }

    @Override
    public boolean add(E e) {
        try { // just in case of OOM
            push(e);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E e : c)
            if (!add(e))
                return false;
        return true;
    }

    @Override
    public void clear() {
        top = null; // restart
        size = 0; // restart
    }

    @Override
    public boolean contains(Object o) {
        // basic iteration !!! reused to implement iterator...
        Node<E> nodo = top; // initialization
        while (nodo != null) { // is there a next element?
            E item = nodo.item; // element to be processed
            nodo = nodo.next; // proceed to next element if any
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
    public boolean isEmpty() {
        return size() == 0; // code reuse
    }

    private static class StackIterator<E> implements Iterator<E> {

        private Node<E> nodo; // current Node

        private StackIterator(Node<E> nodo) {
            this.nodo = nodo;
        }

        @Override
        public boolean hasNext() {
            return nodo != null;
        }

        @Override
        public E next() {
            E item = nodo.item; // element to be processed
            nodo = nodo.next; // proceed to next
            return item;
        }

    }

    @Override
    public Iterator<E> iterator() {
        return new StackIterator<>(top);
    }

    @Override
    public boolean remove(Object o) {
        // optional operation
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object object : c)
            if (!remove(object))
                return false;
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        // optional operation
        throw new UnsupportedOperationException("Unimplemented method 'retainAll'");
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
