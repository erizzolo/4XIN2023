package ricerca;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.SortedSet;

public class AVLNodeSet<E extends Comparable<E>> implements SortedSet<E> {

    private static class Node implements Iterable<Object> {

        private static final Node EMPTY = new Node() {

            @Override
            public Node add(Object e) {
                return new Node(e);
            }

            @Override
            public int height() {
                return 0;
            }

            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Object> iterator() {
                return new Iterator<Object>() {

                    @Override
                    public boolean hasNext() {
                        return false;
                    }

                    @Override
                    public Object next() {
                        throw new NoSuchElementException("EMPTY tree");
                    }

                };
            }

            @Override
            public Node remove(Object o) {
                return this;
            }

            @Override
            public String toString() {
                return "[]";
            }

        };

        private static final int LEFT_HEAVY = -1;
        private static final int RIGHT_HEAVY = +1;
        private static final int BALANCED = 0;

        private Object obj;
        private Node left;
        private Node right;
        private int balance;
        private int height;

        public Node(Object o) {
            this(o, EMPTY, EMPTY, BALANCED, 1);
        }

        public Node() {
        }

        @SuppressWarnings({ "unchecked", "rawtypes" })
        public Node add(Object o) {
            int compare = ((Comparable) o).compareTo(obj);
            if (compare == 0) {
                return this;
            } else if (compare < 0) {
                left = left.add(o);
            } else {
                right = right.add(o);
            }
            return selfBalance();
        }

        /**
         * Perform a right rotation of the tree with this root
         * 
         * <pre>
         *      N                 L
         *    /    \            /   \
         *   L      R         LL     N
         *  / \    / \              /  \
         * LL  LR RL RR           LR    R
         *                             /  \
         *                            RL   RR
         * </pre>
         * 
         * @return the root of the rotated tree
         */
        private Node rightRotate() {
            Node newRoot = left;
            left = newRoot.right;
            newRoot.right = update();
            return newRoot.update();
        }

        /**
         * Perform a left rotation of the tree with this root
         * 
         * <pre>
         *      N                 R
         *    /    \            /   \
         *   L      R          N     RR
         *  / \    / \        / \
         * LL  LR RL RR      L    RL
         *                  /  \
         *                 LL  LR
         * </pre>
         * 
         * @return the root of the rotated tree
         */
        private Node leftRotate() {
            Node newRoot = right;
            right = newRoot.left;
            newRoot.left = update();
            return newRoot.update();
        }

        private Node selfBalance() {
            if (balance() < LEFT_HEAVY) {
                if (left.balance() > 0) {
                    left = left.leftRotate();
                }
                return rightRotate();
            }
            if (balance() > RIGHT_HEAVY) {
                if (right.balance() < 0) {
                    right = right.rightRotate();
                }
                return leftRotate();
            }
            return update();
        }

        private Node update() {
            height = 1 + Math.max(right.height(), left.height());
            return this;
        }

        public int height() {
            return height;
        }

        public int size() {
            return left.size() + 1 + right.size();
        }

        private int balance() {
            return right.height() - left.height();
        }

        private Node getCopy() {
            return new Node(obj, left.getCopy(), right.getCopy(), balance, height);
        }

        @SuppressWarnings({ "unchecked", "rawtypes" })
        public boolean contains(Object o) {
            int compare = ((Comparable) o).compareTo(obj);
            return compare == 0 ? true : compare < 0 ? left.contains(o) : right.contains(o);
        }

        private Node nextGreaterParent() {
            Node parent = this;
            Node current = right;
            while (current.left != EMPTY) {
                parent = current;
                current = parent.left;
            }
            obj = current.obj;
            right = right.remove(obj);
            return selfBalance();
        }

        @SuppressWarnings({ "unchecked", "rawtypes" })
        public Node remove(Object o) {
            int compare = ((Comparable) o).compareTo(obj);
            if (compare == 0) {
                return left == EMPTY ? right : (right == EMPTY ? left : nextGreaterParent());
            } else if (compare < 0) { // search left
                left = left.remove(o);
            } else { // search right
                right = right.remove(o);
            }
            return selfBalance();
        }

        private Node(Object obj, Node left, Node right, int balance, int height) {
            this.obj = obj;
            this.left = left;
            this.right = right;
            this.balance = balance;
            this.height = height;
        }

        @Override
        public Iterator<Object> iterator() {
            return new Iterator<Object>() {

                private Node current = Node.this;
                private boolean processed = Node.this == EMPTY;
                private Iterator<Object> left;
                private Iterator<Object> right;

                private Iterator<Object> left() {
                    return left != null ? left : (left = current.left.iterator());
                }

                private Iterator<Object> right() {
                    return right != null ? right : (right = current.right.iterator());
                }

                @Override
                public boolean hasNext() {
                    return current == EMPTY ? false : left().hasNext() || !processed || right().hasNext();
                }

                @Override
                public Object next() {
                    if (left().hasNext())
                        return left().next();
                    if (!processed) {
                        processed = true;
                        return current.obj;
                    }
                    if (right().hasNext())
                        return right().next();
                    throw new NoSuchElementException();
                }
            };
        }

        public String toString() {
            return "[" + left.toShortString("", ", ") + obj + right.toShortString(", ", "") + "]";
        }

        private String toShortString(String l, String r) {
            return this == EMPTY ? ""
                    : l + "[" + left.toShortString("", ", ") + obj + right.toShortString(", ", "") + "]" + r;
        }

    }

    private Node root;

    public AVLNodeSet() {
        root = Node.EMPTY;
    }

    public AVLNodeSet(E elemento) {
        root = new Node(elemento);
    }

    private AVLNodeSet(Node root) {
        this.root = root;
    }

    public AVLNodeSet<E> getCopy() {
        return new AVLNodeSet<>(root.getCopy());
    }

    @Override
    public int size() {
        return root.size();
    }

    public int height() {
        return root.height();
    }

    @Override
    public boolean isEmpty() {
        return root == Node.EMPTY;
    }

    @Override
    public boolean contains(Object o) {
        return root.contains(o);
    }

    @Override
    public boolean add(E e) {
        root = root.add(e);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        root = root.remove(o);
        return true;
    }

    @Override
    public String toString() {
        return root.toString();
    }

    public String details() {
        return root == Node.EMPTY ? "[EMPTY]" : "N" + size() + ", L" + height() + " " + this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Iterator<E> iterator() {
        return (Iterator<E>) root.iterator();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E e : c) {
            add(e);
        }
        return true;
    }

    @Override
    public void clear() {
        root = Node.EMPTY;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object object : c) {
            if (!contains(object))
                return false;
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object object : c) {
            remove(object);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
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
        for (Object object : root) {
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
        return iterator().next();
    }

    @Override
    public SortedSet<E> headSet(E toElement) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E last() {
        E result = null;
        for (E e : this) {
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
