package graphs;
import java.util.Objects;

public class Arco {

    private final Vertice source;
    private final Vertice destination;
    private final double weight;

    public Arco(Vertice source, Vertice destination, double weight) {
        this.source = Objects.requireNonNull(source, "null source not allowed");
        this.destination = Objects.requireNonNull(destination, "null destination not allowed");
        this.weight = weight;
    }

    public Vertice getSource() {
        return source;
    }

    public Vertice getDestination() {
        return destination;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "{" + source + "->" + destination + " (" + weight + ")}";
    }

}
