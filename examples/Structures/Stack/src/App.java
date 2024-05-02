public class App {
    public static void main(String[] args) throws Exception {
        test(new ArrayStack<>());
        test(new SafeArrayStack<>());
        test(new NodeStack<>());
        test(new SafeNodeStack<>());
    }

    public static void test(Stack<Integer> stack) {
        System.out.println(stack);
        for (int i = 0; i < 10; i++) {
            try {
                stack.push(i);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        System.out.println(stack);
        for (int i = 0; i < 11; i++) {
            try {
                System.out.println(stack.pop());
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        System.out.println(stack);
        threadTest(stack);
    }

    public static void threadTest(Stack<Integer> stack) {
        final int STACK_SIZE = 10_000_000;
        final int THREADS = 3;
        System.out.println("Test thread safety of " + stack + ": size = " + stack.size());
        for (int i = 0; i < STACK_SIZE; i++) {
            stack.push(i);
        }
        StackThread[] t = new StackThread[THREADS];
        for (int i = 0; i < t.length; i++) {
            t[i] = new StackThread(stack);
            t[i].start();
        }
        long total = 0;
        for (int i = 0; i < t.length; i++) {
            try {
                t[i].join();
            } catch (InterruptedException ex) {
            }
            total += t[i].getExtracted();
        }
        System.out.println(THREADS + " threads extracted " + total + " items.");
        if (total != STACK_SIZE)
            System.err.println("ERROR!!! " + STACK_SIZE + " != " + total);
        else
            System.out.println("CORRECT!");
        System.out.println();
    }
}
