public class StackThread extends Thread {

    private Stack<Integer> stack;
    private long extracted;

    public StackThread(Stack<Integer> stack) {
        this.stack = stack;
    }

    public long getExtracted() {
        return extracted;
    }

    @Override
    public void run() {
        extracted = 0;
        // The effect is stack MONOPOLIZATION (just one thread running...)
        // synchronized (stack) {
        while (stack.size() > 0) {
            try {
                stack.pop();
                extracted++;
            } catch (UnsupportedOperationException e) {
            }
        }
        // }
        System.out.println(this + " extracted " + extracted);
    }

}
