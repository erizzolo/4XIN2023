package alberi;

public interface Albero<E> extends Iterable<E> {

    boolean isEmpty();

    int size();

    int height();

    E getRootElement();

    Albero<E>[] getChildren();

    boolean contains(Object o);

    Albero<E> add(E e);

    Albero<E> remove(Object o);

    Albero<E> getCopy();

    default String rootString() {
        return getRootElement().toString();
    }

    char CHILD = '.';
    String EMPTY = "";
    char PARENT = '\'';
    char BOTH = '|';
    char BRANCH = '-';

    default String toTreeString() {
        StringBuilder result = new StringBuilder();
        if (isEmpty()) {
            result.append(EMPTY);
        } else {
            String root = rootString();
            Albero<E>[] children = getChildren();
            String[][] parts = new String[children.length][];
            int length = 0, nonEmpty = 0;
            for (int child = 0; child < children.length; child++) {
                parts[child] = children[child].toTreeString().split("\n");
                int partial = parts[child][0].length();
                if (partial > 0) {
                    length += partial;
                    ++nonEmpty;
                }
            }
            if (nonEmpty > 0) {
                int totSpaces = Math.max(children.length - 1, root.length() - length);
                length += totSpaces;
                String[] spaces = new String[children.length];
                for (int part = spaces.length - 1; part > 0;) {
                    int s = totSpaces / part;
                    spaces[--part] = charPadding(s, ' ');
                    totSpaces -= s;
                }
                spaces[spaces.length - 1] = charPadding(totSpaces, ' ');
                StringBuilder branches = new StringBuilder();
                int rows = 0;
                for (int child = 0; child < children.length; child++) {
                    int partial = parts[child][0].length();
                    if (partial > 0) {
                        int center = symbolCenter(parts[child][0]);
                        branches.append(charPadding(center, ' '));
                        branches.append(CHILD);
                        branches.append(charPadding(partial - center - 1, ' '));
                    }
                    branches.append(spaces[child]);
                    if (rows < parts[child].length)
                        rows = parts[child].length;
                }
                int left = (branches.length() - root.length()) / 2;
                int right = branches.length() - left - root.length();
                result.append(charPadding(left, ' ') + root + charPadding(right, ' '));
                int first = branches.indexOf("" + CHILD);
                int last = branches.lastIndexOf("" + CHILD);
                int center = left + root.length() / 2;
                if (first > center)
                    first = center;
                else if (last < center)
                    last = center;
                String replace = branches.substring(first, last + 1).replace(' ', BRANCH);
                branches.replace(first, last + 1, replace);
                branches.setCharAt(center, branches.charAt(center) == CHILD ? BOTH : PARENT);
                result.append("\n" + branches.toString());
                for (int row = 0; row < rows; row++) {
                    StringBuilder level = new StringBuilder();
                    for (int child = 0; child < children.length; child++) {
                        if (row < parts[child].length)
                            level.append(parts[child][row]);
                        else
                            level.append(charPadding(parts[child][0].length(), ' '));
                        level.append(spaces[child]);
                    }
                    result.append("\n").append(level.toString());
                }
            } else {
                result.append(root);
            }
        }
        return result.toString();
    }

    private static String charPadding(int length, char fill) {
        return new String(new char[length]).replace('\0', fill);
    }

    private static int symbolCenter(String line) {
        return (line.length() + line.stripTrailing().length() - line.stripLeading().length()) / 2;
    }
}
