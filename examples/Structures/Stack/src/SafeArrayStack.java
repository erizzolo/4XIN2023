import java.util.Arrays;

public class SafeArrayStack<E> implements Stack<E> {

    private E[] data; // the actual data
    private int size; // actual size of the stack (number of elements)

    public SafeArrayStack() {
        this(16);
    }

    @SuppressWarnings("unchecked")
    public SafeArrayStack(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0; // just to be clear
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int capacity() {
        return data.length;
    }

    @Override
    public synchronized void push(E e) {
        if (size() >= capacity()) { // already full
            data = Arrays.copyOf(data, 2 * capacity());
        }
        data[size()] = e; // insert into bottom empty position
        size++; // one element more
    }

    @Override
    public synchronized E pop() {
        if (size() == 0) // empty
            throw new EmptyStackException("empty stack");
        size--; // one element less
        E result = data[size()]; // extract from top non-empty position
        data[size()] = null; // let the gc do its job
        return result;
    }

    @Override
    public String toString() {
        return "SafeArrayStack{ " + size() + "/" + capacity() + ", bottom :" + Arrays.toString(Arrays.copyOf(data, size()))
                + " : top}";
    }

    public static void main(String[] args) {
        App.test(new SafeArrayStack<>(5));
    }

}
