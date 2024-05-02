package ricerca;

import java.util.Iterator;
import java.util.NoSuchElementException;
import alberi.Albero;

public class AlberoAVLDuplicates<E extends Comparable<E>> implements Albero<E> {

    // the EMPTY tree
    @SuppressWarnings("rawtypes")
    public static final AlberoAVLDuplicates EMPTY = new AlberoAVLDuplicates() {

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
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public AlberoAVLDuplicates remove(Object o) {
            return this;
        }

        @Override
        public AlberoAVLDuplicates getCopy() {
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
    private AlberoAVLDuplicates<E> left;
    private AlberoAVLDuplicates<E> right;
    private int size; // for efficiency reason
    private int height; // for efficiency reason
    private int count; // handle duplicates

    // just for EMPTY
    private AlberoAVLDuplicates() {
    }

    @SuppressWarnings("unchecked")
    public AlberoAVLDuplicates(E elemento) {
        this(elemento, EMPTY, EMPTY, 1, 1, 1);
    }

    private AlberoAVLDuplicates(E elemento, AlberoAVLDuplicates<E> left, AlberoAVLDuplicates<E> right, int size,
            int height, int count) {
        this.elemento = elemento;
        this.left = left;
        this.right = right;
        this.size = size;
        this.height = height;
        this.count = count;
    }

    private void update() {
        height = 1 + Math.max(left.height(), right.height());
        size = left.size() + count + right.size();
    }

    public int balance() {
        return right.height() - left.height();
    }

    private AlberoAVLDuplicates<E> selfBalance() {
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
    private AlberoAVLDuplicates<E> selfBalance(E inserted) {
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
    private AlberoAVLDuplicates<E> rightRotate() {
        AlberoAVLDuplicates<E> newRoot = left;
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

    private AlberoAVLDuplicates<E> leftRotate() {
        AlberoAVLDuplicates<E> newRoot = right;
        right = newRoot.left;
        newRoot.left = this;
        update();
        newRoot.update();
        return newRoot;
    }

    @SuppressWarnings("unchecked")
    @Override
    public AlberoAVLDuplicates<E> getCopy() {
        return this == EMPTY ? EMPTY
                : new AlberoAVLDuplicates<E>(elemento, left.getCopy(), right.getCopy(), size, height, count);
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
        return this == EMPTY;
    }

    @Override
    public E getRootElement() {
        if (this == EMPTY)
            throw new NoSuchElementException("EMPTY tree");
        return elemento;
    }

    @Override
    public String rootString() {
        return elemento + (count > 1 ? ("*" + count) : "");
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
    public AlberoAVLDuplicates<E> add(E e) {
        // debug("Adding " + e + " to " + this);
        if (this == EMPTY) {
            return new AlberoAVLDuplicates<E>(e, EMPTY, EMPTY, 1, 1, 1);
        }
        int compare = e.compareTo(elemento);
        if (compare == 0) {
            ++count;
            ++size;
            return this;
        } else if (compare < 0) {
            left = left.add(e);
        } else {
            right = right.add(e);
        }
        return selfBalance();
    }

    private AlberoAVLDuplicates<E> nextGreaterParent() {
        AlberoAVLDuplicates<E> parent = this;
        AlberoAVLDuplicates<E> current = right;
        while (current.left != EMPTY) {
            parent = current;
            current = parent.left;
        }
        elemento = current.elemento;
        count = current.count;
        current.count = 1;
        right = right.remove(elemento);
        return this.selfBalance();
    }

    @Override
    public AlberoAVLDuplicates<E> remove(Object o) {
        @SuppressWarnings("unchecked")
        Comparable<? super E> oc = (Comparable<? super E>) o;
        int compare = oc.compareTo(elemento);
        if (compare == 0) {
            if (count > 1) {
                --count;
                --size;
                return this;
            }
            // this node disappears
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
        return this == EMPTY ? "[]"
                : "[" + left.toShortString("", ", ") + elemento + (count > 1 ? ("*" + count) : "")
                        + right.toShortString(", ", "") + "]";
    }

    private String toShortString(String l, String r) {
        return this == EMPTY ? ""
                : l + "[" + left.toShortString("", ", ") + elemento + (count > 1 ? ("*" + count) : "")
                        + right.toShortString(", ", "") + "]" + r;
    }

    public String details() {
        return this == EMPTY ? "[EMPTY]" : "N" + size() + ", L" + height() + " " + this;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private AlberoAVLDuplicates<E> current = AlberoAVLDuplicates.this;
            private int remaining = AlberoAVLDuplicates.this.count;
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
                return current == EMPTY ? false : left().hasNext() || (remaining > 0) || right().hasNext();
            }

            @Override
            public E next() {
                if (hasNext()) {
                    if (left().hasNext())
                        return left().next();
                    if (remaining > 0) {
                        --remaining;
                        return current.elemento;
                    }
                    return right().next();
                }
                throw new NoSuchElementException();
            }
        };
    }

}
