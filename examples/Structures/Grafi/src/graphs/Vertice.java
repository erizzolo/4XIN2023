package graphs;
import java.util.Objects;

public class Vertice {

    private final String name;
    private final Object info;

    public Vertice(String name, Object info) {
        this.name = Objects.requireNonNull(name, "null name not allowed");
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public Object getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return name;
    }

}
