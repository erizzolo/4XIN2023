import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class NodeList<E> implements List<E> {

    private static class Node<E> {

        Node<E> previous; // previous Node according to structure logic
        E item; // the element in this Node
        Node<E> next; // next Node according to structure logic

        public Node(NodeList.Node<E> previous, E item, NodeList.Node<E> next) {
            this.previous = previous;
            this.item = item;
            this.next = next;
        }

    }

    private Node<E> front; // front of the list
    private Node<E> back; // back of the list
    private int size; // size for efficiency

    public NodeList() {
        front = back = null; // just to be explicit (aka eccesso di chiarezza)
        size = 0; // just to be explicit (aka eccesso di chiarezza)
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        String result = "Nodelist{ " + size() + ", front :";
        for (E item : this)
            result += " " + item;
        result += " : back}";
        return result;
    }

    public static void main(String[] args) {
        App.test(new NodeList<Integer>());
    }

    @Override
    public boolean add(E e) {
        Node<E> inserted = new Node<>(back, e, null); // new Node
        if (back == null) // if empty
            front = back = inserted; // the one and only Node
        else
            back = back.next = inserted; // update back link and back
        ++size;
        return true;
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
        front = back = null; // restart
        size = 0; // restart
    }

    @Override
    public boolean contains(Object o) {
        return o == null ? containsNull() : containsNotNull(o);
    }

    private boolean containsNull() {
        for (Node<E> node = front; node != null; node = node.next)
            if (node.item == null)
                return true;
        return false;
    }

    private boolean containsNotNull(Object o) {
        // basic iteration !!! reused to implement iterator...
        Node<E> nodo = front; // initialization (iterator constructor)
        while (nodo != null) { // is t @Override
            E item = nodo.item; // element to be processed (next() result)
            nodo = nodo.next; // proceed to next element if any (next() step forward)
            // process element ...
            if (o.equals(item))
                return true;
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

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            // from contains() logic ...
            Node<E> node = front;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public E next() {
                E item = node.item; // element to be processed
                node = node.next; // proceed to next
                return item;
            }

        };
    }

    @Override
    public boolean remove(Object o) {
        return o == null ? removeNull() : removeNotNull(o);
    }

    private boolean removeNotNull(Object o) {
        for (Node<E> node = front; node != null; node = node.next)
            if (o.equals(node.item))
                return removeNode(node) != null;
        return false;
    }

    private boolean removeNull() {
        Node<E> node = front;
        while (node != null) {
            if (node.item == null)
                return removeNode(node) == null;
            node = node.next;
        }
        return false;
    }

    private E removeNode(Node<E> node) {
        // assert node != null
        E result = node.item;
        final Node<E> next = node.next;
        final Node<E> previous = node.previous;
        if (previous == null) { // node == front
            front = next;
        } else {
            previous.next = next;
            node.previous = null; // ?!!! gc
        }
        if (next == null) { // node == back
            back = previous;
        } else {
            next.previous = previous;
            node.next = null; // ?!!! gc
        }
        node.item = null; // ?!!! gc
        --size;
        return result;
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

    @Override
    public void add(int index, E element) {
        if (index == size())
            add(element); // to the end
        else {
            NodeList.Node<E> node = getNode(checkPosition(index));
            final Node<E> inserted = new Node<>(node.previous, element, node);
            node.previous = inserted;
            if (node.previous == null) { // front node
                front = inserted;
            } else { // not front node
                node.next = inserted;
            }
            ++size;
        }
    }

    private int checkPosition(int position) {
        if (position < 0 || position > size())
            throw new IndexOutOfBoundsException("position " + position + " is out of range");
        return position;
    }

    private Node<E> getNode(int index) {
        if (index < size() / 2)
            return getForward(index);
        return getBackward(size() - 1 - index);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        for (E e : c) {
            if (!add(e))
                return false;
        }
        return true;
    }

    @Override
    public E get(int index) {
        return getNode(checkedIndex(index)).item;
    }

    private int checkedIndex(int index) {
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException("index " + index + " is out of range");
        return index;
    }

    private Node<E> getForward(int index) {
        Node<E> node = front;
        while (index-- > 0)
            node = node.next;
        return node;
    }

    private Node<E> getBackward(int index) {
        Node<E> node = back;
        while (index-- > 0)
            node = node.previous;
        return node;
    }

    @Override
    public int indexOf(Object o) {
        return o == null ? indexOfNull() : indexOfNotNull(o);
    }

    private int indexOfNull() {
        Node<E> node = front;
        for (int index = 0; node != null; ++index, node = node.next)
            if (node.item == null)
                return index;
        return -1;
    }

    private int indexOfNotNull(Object o) {
        Node<E> node = front;
        for (int index = 0; node != null; ++index, node = node.next)
            if (o.equals(node.item))
                return index;
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        Node<E> node = back;
        int index = size() - 1;
        while (node != null) {
            E item = node.item;
            node = node.previous;
            if (o == null ? item == null : o.equals(item))
                return index;
            --index;
        }
        return -1;
    }

    private class ListaIterator implements ListIterator<E> {

        private Node<E> before;
        private int index; // for efficiency
        private Node<E> after;

        public ListaIterator(NodeList.Node<E> before, NodeList.Node<E> after) {
            this.before = before;
            index = 0;
            this.after = after;
        }

        @Override
        public void add(E e) {
            // optional operation
            throw new UnsupportedOperationException("Unimplemented method 'add'");
        }

        @Override
        public boolean hasNext() {
            return after != null;
        }

        @Override
        public boolean hasPrevious() {
            return before != null;
        }

        @Override
        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();
            E item = after.item;
            before = after;
            after = after.next;
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
            E item = before.item;
            after = before;
            before = before.previous;
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
        return new ListaIterator(null, front);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("Unimplemented method 'listIterator'");
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public E set(int index, E e) {
        throw new UnsupportedOperationException("Unimplemented method 'set'");
    }

    @Override
    public List<E> subList(int from, int to) {
        throw new UnsupportedOperationException("Unimplemented method 'subList'");
    }

}
