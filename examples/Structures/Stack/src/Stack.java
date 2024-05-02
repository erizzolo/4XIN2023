public interface Stack<E> {

    /**
     * @return actual number of items in the stack
     */
    int size();

    /**
     * @return maximum number of items in the stack
     */
    int capacity();

    /**
     * Adds an item on the top of the stack
     */
    void push(E e);

    /**
     * @return the item on the top of the stack, removing it
     */
    E pop();

}
