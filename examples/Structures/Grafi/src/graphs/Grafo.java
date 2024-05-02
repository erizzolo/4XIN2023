package graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grafo {

    private final Map<Vertice, Set<Arco>> adiacenze;

    public Grafo() {
        adiacenze = new HashMap<>();
    }

    public void addVertice(Vertice v) {
        Objects.requireNonNull(v, "null vertice not allowed");
        adiacenze.putIfAbsent(v, new HashSet<>());
    }

    public void addArco(Arco a) {
        Objects.requireNonNull(a, "null arco not allowed");
        addVertice(a.getSource());
        addVertice(a.getDestination());
        Set<Arco> uscenti = adiacenze.get(a.getSource());
        uscenti.add(a);
    }

    public Set<Vertice> getVertici() {
        return new HashSet<>(adiacenze.keySet());
    }

    public Set<Arco> getArchi() {
        Set<Arco> result = new HashSet<>();
        for (Set<Arco> archi : adiacenze.values()) {
            result.addAll(archi);
        }
        return result;
    }

    public Map<Vertice, Set<Arco>> getAdiacenze() {
        return new HashMap<>(adiacenze);
    }

    public Dijkstra dijkstraPQ(Vertice source) {
        // distanze per i vertici definitivi
        Map<Vertice, Double> distanze = new HashMap<>();
        // coda dei vertici da processare in ordine di distanza
        PriorityQueue<DistanceInfo> queue = new PriorityQueue<>();
        // predecessori per i vertici definitivi
        Map<Vertice, Vertice> predecessori = new HashMap<>();
        // primo vertice da elaborare: source
        queue.add(new DistanceInfo(0, source, null));
        do {
            DistanceInfo info = queue.remove();
            Vertice base = info.getVertice();
            // se non già definitivo
            if (!distanze.keySet().contains(base)) {
                distanze.put(base, info.getDistanza());
                predecessori.put(base, info.getPrecedente());
                double distanza = info.getDistanza();
                Set<Arco> uscenti = adiacenze.get(base);
                for (Arco arco : uscenti) {
                    // se non già definitivo
                    if (!distanze.keySet().contains(arco.getDestination())) {
                        queue.add(new DistanceInfo(distanza + arco.getWeight(), arco.getDestination(), base));
                    }
                }
            }
        } while (queue.size() > 0);
        Set<Vertice> unreachables = new HashSet<>(adiacenze.keySet());
        unreachables.removeAll(distanze.keySet());
        return new Dijkstra(source, distanze, predecessori, unreachables);
    }

    public Dijkstra dijkstra(Vertice source) {
        Map<Vertice, Double> distanze = new HashMap<>();
        for (Vertice v : adiacenze.keySet()) {
            distanze.put(v, v == source ? 0 : Double.POSITIVE_INFINITY);
        }
        Map<Vertice, Vertice> predecessori = new HashMap<>();
        for (Vertice v : adiacenze.keySet()) {
            predecessori.put(v, null);
        }
        Set<Vertice> daVisitare = new HashSet<>(adiacenze.keySet());
        while (daVisitare.size() > 0) {
            double minDistanza = Double.POSITIVE_INFINITY;
            Vertice base = null;
            for (Vertice vertice : daVisitare) {
                Double distanza = distanze.get(vertice);
                if (minDistanza > distanza || base == null) {
                    minDistanza = distanza;
                    base = vertice;
                }
            }
            daVisitare.remove(base);
            Set<Arco> uscenti = adiacenze.get(base);
            if (uscenti == null) {
                System.out.println("null uscenti for " + base);
            }
            for (Arco arco : uscenti) {
                double newDistanza = minDistanza + arco.getWeight();
                double distanza = distanze.get(arco.getDestination());
                if (newDistanza < distanza) {
                    distanze.put(arco.getDestination(), newDistanza);
                    predecessori.put(arco.getDestination(), base);
                }
            }
        }
        Set<Vertice> unreachables = new HashSet<>(adiacenze.keySet());
        unreachables.removeAll(distanze.keySet());
        return new Dijkstra(source, distanze, predecessori, unreachables);
    }

    public static String pathString(Vertice source, Vertice destination, Map<Vertice, Vertice> predecessori) {
        if (destination == source) {
            return source.toString();
        } else {
            String result = pathString(source, predecessori.get(destination), predecessori);
            return result + " -> " + destination.toString();
        }
    }

    public String toString() {
        return adiacenze.toString();
    }

    public static Grafo fromString(String g) {
        Grafo result = new Grafo();
        // g = "{C=[{C->D (2.0)}], E=[{E->B (1.0)}, {E->C (3.0)}, {E->D (6.0)}],
        // D=[{D->A (3.0)}, {D->C (7.0)}], B=[{B->C (6.0)}, {B->E (2.0)}], A=[{A->B
        // (3.0)}, {A->E (5.0)}]}";
        // ------------ (nome vertice)=[(archi)]
        String regex = "([A-Z])=.([^]]*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(g);
        Map<String, Vertice> byName = new HashMap<>();
        while (matcher.find()) {
            String name = matcher.group(1); // vertex name
            Vertice vertice = byName.computeIfAbsent(name, k -> new Vertice(name, null));
            result.addVertice(vertice);
            String[] arcs = matcher.group(2).split(", ");
            // --------------- (src )->(dest ) (weight)
            String arcRegex = "([A-Z])->([A-Z]) \\(([^)]*)";
            Pattern arcPattern = Pattern.compile(arcRegex);
            for (String arc : arcs) {
                Matcher arcMatcher = arcPattern.matcher(arc);
                while (arcMatcher.find()) {
                    String srcName = arcMatcher.group(1); // vertex name
                    Vertice source = byName.computeIfAbsent(srcName, k -> new Vertice(srcName, null));
                    String dstName = arcMatcher.group(2); // vertex name
                    Vertice destination = byName.computeIfAbsent(dstName, k -> new Vertice(dstName, null));
                    double weight = Double.parseDouble(arcMatcher.group(3));
                    result.addArco(new Arco(source, destination, weight));
                }
            }
        }
        return result;
    }

    public static Grafo getRandom(int numVertici, int numArchi) {
        final Random rnd = new Random();
        final String names = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        if (numVertici > names.length())
            throw new IllegalArgumentException("too many vertices " + numVertici);
        List<Vertice> vertici = new ArrayList<>(numVertici);
        for (int v = 0; v < numVertici; v++) {
            vertici.add(new Vertice(names.substring(v, v + 1), null));
        }
        List<Arco> archi = new ArrayList<>(numVertici * (numVertici - 1));
        for (int s = 0; s < numVertici; s++) {
            for (int d = 0; d < numVertici; d++) {
                if (d != s)
                    archi.add(new Arco(vertici.get(s), vertici.get(d), rnd.nextInt(20) + 1));
            }
        }
        Collections.shuffle(archi);
        Grafo result = new Grafo();
        for (Vertice vertice : vertici) {
            result.addVertice(vertice);
        }
        for (int a = 0; a < numArchi; a++) {
            result.addArco(archi.get(a));
        }
        return result;
    }

    public static class Dijkstra {

        private final Vertice source;
        private final Map<Vertice, Double> distanze;
        private final Map<Vertice, Vertice> predecessori;
        private final Set<Vertice> unreachables;

        public Dijkstra(Vertice source, Map<Vertice, Double> distanze, Map<Vertice, Vertice> predecessori,
                Set<Vertice> unreachables) {
            this.source = source;
            this.distanze = new HashMap<>(distanze);
            this.predecessori = new HashMap<>(predecessori);
            this.unreachables = new HashSet<>(unreachables);
            this.distanze.remove(source);
            this.predecessori.remove(source);
        }

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder("Dijkstra result for source ");
            result.append(source);
            for (Vertice vertice : distanze.keySet()) {
                result.append(System.lineSeparator());
                result.append(vertice);
                result.append(": distance ").append(distanze.get(vertice));
                result.append(", path ").append(pathString(source, vertice, predecessori));
            }
            if (unreachables.size() > 0) {
                result.append(System.lineSeparator());
                result.append("Unreachables: ").append(unreachables);
            }
            return result.toString();
        }

    }

    private static class DistanceInfo implements Comparable<DistanceInfo> {

        private final double distanza;
        private final Vertice vertice;
        private final Vertice precedente;

        public DistanceInfo(double distanza, Vertice vertice, Vertice precedente) {
            this.distanza = distanza;
            this.vertice = vertice;
            this.precedente = precedente;
        }

        public double getDistanza() {
            return distanza;
        }

        public Vertice getVertice() {
            return vertice;
        }

        public Vertice getPrecedente() {
            return precedente;
        }

        @Override
        public int compareTo(DistanceInfo other) {
            int result = Double.compare(distanza, other.distanza);
            return result != 0 ? result : vertice.hashCode() - other.vertice.hashCode();
        }

    }

}
