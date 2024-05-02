package binari;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ABCollection<E> implements Collection<E> {

    private AB<E> tree;

    @SuppressWarnings("unchecked")
    public ABCollection() {
        tree = AB.EMPTY;
    }

    @SuppressWarnings("unchecked")
    public ABCollection(Collection<? extends E> c) {
        tree = AB.EMPTY;
        addAll(c);
    }

    @Override
    public boolean add(E e) {
        int size = tree.size();
        tree = tree.add(e);
        return tree.size() != size;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int size = tree.size();
        for (E e : c) {
            tree = tree.add(e);
        }
        return tree.size() != size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        tree = AB.EMPTY;
    }

    @Override
    public boolean contains(Object o) {
        return tree.contains(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!tree.contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        return tree == AB.EMPTY;
    }

    @Override
    public Iterator<E> iterator() {
        return tree.iterator();
    }

    @Override
    public boolean remove(Object o) {
        int size = tree.size();
        tree = tree.remove(o);
        return tree.size() != size;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int size = tree.size();
        for (Object o : c) {
            tree = tree.remove(o);
        }
        return tree.size() != size;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int size = tree.size();
        for (Object o : tree) {
            if (!c.contains(o)) {
                tree = tree.remove(o);
            }
        }
        return tree.size() != size;
    }

    @Override
    public int size() {
        return tree.size();
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
        for (Object object : tree) {
            a[index++] = (T) object;
        }
        return a;
    }

    private static class AB<E> implements Iterable<E> {

        @SuppressWarnings("rawtypes")
        public static final AB EMPTY = new AB<>();

        private E elemento;
        private AB<E> left;
        private AB<E> right;

        private AB() {
            left = right = this;
        }

        public AB(E elemento, AB<E> left, AB<E> right) {
            this.elemento = elemento;
            this.left = Objects.requireNonNull(left, "sottoalbero sinistro null");
            this.right = Objects.requireNonNull(right, "sottoalbero destro null");
        }

        @SuppressWarnings({ "unchecked", "unused" })
        public AB<E> getCopy() {
            return this == EMPTY ? EMPTY : new AB<E>(elemento, left.getCopy(), right.getCopy());
        }

        public int size() {
            return this == EMPTY ? 0 : left.size() + 1 + right.size();
        }

        @SuppressWarnings("unused")
        public int height() {
            return this == EMPTY ? 0 : 1 + Math.max(left.height(), right.height());
        }

        public boolean contains(Object o) {
            return this == EMPTY ? false : left.contains(o) || nullEquals(o, elemento) || right.contains(o);
        }

        @SuppressWarnings("unchecked")
        public AB<E> add(E e) {
            if (this == EMPTY) {
                // @SuppressWarnings("unchecked")
                return new AB<E>(e, EMPTY, EMPTY);
            }
            if (left.size() <= right.size()) { // left wins ties
                left = left.add(e);
            } else {
                right = right.add(e);
            }
            return this;
        }

        public AB<E> attach(AB<E> e) {
            Objects.requireNonNull(e, "sottoalbero null");
            if (this == EMPTY) {
                return e;
            }
            if (left.size() <= right.size()) { // left wins ties
                left = left.attach(e);
            } else {
                right = right.attach(e);
            }
            return this;
        }

        public AB<E> remove(Object o) {
            if (this == EMPTY) { // nothing to do
                return this;
            } else if (nullEquals(o, elemento)) { // this node disappears
                if (left.size() <= right.size()) { // left wins ties
                    return right.attach(left);
                } else {
                    return left.attach(right);
                }
            } else { // search left first, then right if not found
                int ls = left.size();
                left = left.remove(o);
                if (left.size() == ls) {
                    right = right.remove(o);
                }
                return this;
            }
        }

        @Override
        public String toString() {
            return this == EMPTY ? "[]" : "[" + elemento + ", " + left + ", " + right + "]";
        }

        private static boolean nullEquals(Object o, Object e) {
            return o == null ? e == null : o.equals(e);
        }

        @Override
        public Iterator<E> iterator() {
            return new Iterator<E>() {

                private AB<E> current = AB.this;
                private boolean processed = AB.this == EMPTY;
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
                    return current == EMPTY ? false : left().hasNext() || !processed || right().hasNext();
                }

                @Override
                public E next() {
                    if (hasNext()) {
                        if (left().hasNext())
                            return left().next();
                        if (!processed) {
                            processed = true;
                            return current.elemento;
                        }
                        return right().next();
                    }
                    throw new NoSuchElementException();
                }
            };
        }

    }

    public static void main(String[] args) {
        ABCollection<Integer> abc = new ABCollection<>();
        for (int i = 0; i < 10; i++) {
            abc.add(i * 2);
        }
        ABCollection<Integer> abc2 = new ABCollection<>();
        for (int i = 0; i < 10; i++) {
            abc2.add(i * 3);
        }
        for (Integer integer : abc) {
            System.out.println(integer);
        }
        System.out.println("retainall");
        abc.retainAll(abc2);
        for (Integer integer : abc) {
            System.out.println(integer);
        }
    }

}