package ricerca;

import java.util.Iterator;
import java.util.NoSuchElementException;
import alberi.Albero;

public class AlberoAVL<E extends Comparable<E>> implements Albero<E> {

    // the EMPTY tree
    @SuppressWarnings("rawtypes")
    public static final AlberoAVL EMPTY = new AlberoAVL() {

        @Override
        public int balance() {
            return 0;
        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public int height() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return true;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public AlberoAVL remove(Object o) {
            return this;
        }

        @Override
        public AlberoAVL getCopy() {
            return this;
        }

        @Override
        public Iterator iterator() {
            return new Iterator<>() {

                @Override
                public boolean hasNext() {
                    return false;
                }

                @Override
                public Object next() {
                    throw new NoSuchElementException();
                }

            };
        }

        @Override
        public String toString() {
            return "[]";
        }

    };

    private E elemento;
    private AlberoAVL<E> left;
    private AlberoAVL<E> right;
    private int size; // for efficiency reason
    private int height; // for efficiency reason

    // just for EMPTY
    private AlberoAVL() {
    }

    @SuppressWarnings("unchecked")
    public AlberoAVL(E elemento) {
        this(elemento, EMPTY, EMPTY, 1, 1);
    }

    private AlberoAVL(E elemento, AlberoAVL<E> left, AlberoAVL<E> right, int size, int height) {
        this.elemento = elemento;
        this.left = left;
        this.right = right;
        this.size = size;
        this.height = height;
    }

    private void update() {
        height = 1 + Math.max(left.height(), right.height());
        size = left.size() + 1 + right.size();
    }

    public int balance() {
        return right.height() - left.height();
    }

    private AlberoAVL<E> selfBalance() {
        if (balance() < -1) {
            if (left.balance() > 0) {
                left = left.leftRotate();
            }
            return rightRotate();
        }
        if (balance() > 1) {
            if (right.balance() < 0) {
                right = right.rightRotate();
            }
            return leftRotate();
        }
        update();
        return this;
    }

    @SuppressWarnings("unused")
    private AlberoAVL<E> selfBalance(E inserted) {
        if (balance() < -1) {
            if (inserted.compareTo(left.elemento) > 0) {
                left = left.leftRotate();
            }
            return rightRotate();
        }
        if (balance() > 1) {
            if (inserted.compareTo(right.elemento) < 0) {
                right = right.rightRotate();
            }
            return leftRotate();
        }
        update();
        return this;
    }

    /**
     * Perform a right rotation of the tree
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
    private AlberoAVL<E> rightRotate() {
        AlberoAVL<E> newRoot = left;
        left = newRoot.right;
        newRoot.right = this;
        update();
        newRoot.update();
        return newRoot;
    }

    /**
     * Perform a left rotation of the tree
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

    private AlberoAVL<E> leftRotate() {
        AlberoAVL<E> newRoot = right;
        right = newRoot.left;
        newRoot.left = this;
        update();
        newRoot.update();
        return newRoot;
    }

    @SuppressWarnings("unchecked")
    @Override
    public AlberoAVL<E> getCopy() {
        assert (this != EMPTY);
        return this == EMPTY ? EMPTY : new AlberoAVL<E>(elemento, left.getCopy(), right.getCopy(), size, height);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public E getRootElement() {
        if (this == EMPTY)
            throw new NoSuchElementException("EMPTY tree");
        return elemento;
    }

    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Albero<E>[] getChildren() {
        if (this == EMPTY)
            throw new NoSuchElementException("EMPTY tree");
        Albero[] result = { left, right };
        return result;
    }

    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        Comparable<? super E> oc = (Comparable<? super E>) o;
        int compare = oc.compareTo(elemento);
        return compare == 0 ? true : compare < 0 ? left.contains(o) : right.contains(o);
    }

    @Override
    @SuppressWarnings("unchecked")
    public AlberoAVL<E> add(E e) {
        if (this == EMPTY) {
            return new AlberoAVL<E>(e, EMPTY, EMPTY, 1, 1);
        }
        int compare = e.compareTo(elemento);
        if (compare == 0) {
            return this;
        } else if (compare < 0) {
            left = left.add(e);
        } else {
            right = right.add(e);
        }
        return selfBalance();
    }

    private AlberoAVL<E> nextGreaterParent() {
        AlberoAVL<E> parent = this;
        AlberoAVL<E> current = right;
        while (current.left != EMPTY) {
            parent = current;
            current = parent.left;
        }
        elemento = current.elemento;
        right = right.remove(elemento);
        return this.selfBalance();
    }

    @Override
    public AlberoAVL<E> remove(Object o) {
        @SuppressWarnings("unchecked")
        Comparable<? super E> oc = (Comparable<? super E>) o;
        int compare = oc.compareTo(elemento);
        if (compare == 0) {
            return left == EMPTY ? right : (right == EMPTY ? left : nextGreaterParent());
        } else if (compare < 0) { // search left
            left = left.remove(o);
        } else { // search right
            right = right.remove(o);
        }
        return selfBalance();
    }

    @Override
    public String toString() {
        return "[" + left.toShortString("", ", ") + elemento + right.toShortString(", ", "") + "]";
    }

    private String toShortString(String l, String r) {
        return this == EMPTY ? ""
                : l + "[" + left.toShortString("", ", ") + elemento + right.toShortString(", ", "") + "]" + r;
    }

    public String details() {
        return this == EMPTY ? "[EMPTY]" : "N" + size() + ", L" + height() + " " + this;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private AlberoAVL<E> current = AlberoAVL.this;
            private boolean processed = AlberoAVL.this == EMPTY;
            private Iterator<E> left;
            private Iterator<E> right;

            private Iterator<E> left() {
                return left != null ? left : (left = current.left.iterator());
            }

            private Iterator<E> right() {
                return right != null ? right : (right = current.right.iterator());
            }

            @Override
            public boolean hasNext() {
                return left().hasNext() || !processed || right().hasNext();
            }

            @Override
            public E next() {
                if (left().hasNext())
                    return left().next();
                if (!processed) {
                    processed = true;
                    return current.elemento;
                }
                if (right.hasNext())
                    return right().next();
                throw new NoSuchElementException();
            }
        };
    }

}
