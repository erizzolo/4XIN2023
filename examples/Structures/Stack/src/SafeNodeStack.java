public class SafeNodeStack<E> implements Stack<E> {

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

    public SafeNodeStack() {
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
    public synchronized void push(E e) {
        top = new Node<>(e, top); // new top Node
        size++; // don't forget...
    }

    @Override
    public synchronized E pop() {
        if (size() == 0) // empty
            throw new EmptyStackException("empty stack");
        size--; // one element less
        E result = top.item; // extract from top non-empty Node
        top = top.next; // update top Node (+ let the gc do its job)
        return result;
    }

    @Override
    public String toString() {
        return "SafeNodeStack{ " + size() + "/" + capacity() + ", bottom : " + "???" + " : top}";
    }

    public static void main(String[] args) {
        App.test(new SafeNodeStack<Integer>());
    }

}
