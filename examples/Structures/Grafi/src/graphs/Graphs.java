package graphs;

// import java.util.regex.Matcher;
// import java.util.regex.Pattern;

public class Graphs {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String g = "{C=[{C->D (2.0)}], E=[{E->B (1.0)}, {E->C (3.0)}, {E->D (6.0)}], D=[{D->A (3.0)}, {D->C (7.0)}], B=[{B->C (6.0)}, {B->E (2.0)}], A=[{A->B (3.0)}, {A->E (5.0)}]}";
        Grafo fromString = Grafo.fromString(g);
        test(fromString);
        // Vertice a = new Vertice("A", null);
        // Vertice b = new Vertice("B", null);
        // Vertice c = new Vertice("C", null);
        // Vertice d = new Vertice("D", null);
        // Vertice e = new Vertice("E", null);
        // Grafo grafo = new Grafo();
        // grafo.addVertice(a);
        // grafo.addVertice(b);
        // grafo.addVertice(c);
        // grafo.addVertice(d);
        // grafo.addVertice(e);
        // Arco ab = new Arco(a, b, 3);
        // Arco ae = new Arco(a, e, 5);
        // Arco bc = new Arco(b, c, 6);
        // Arco be = new Arco(b, e, 2);
        // Arco cd = new Arco(c, d, 2);
        // Arco da = new Arco(d, a, 3);
        // Arco dc = new Arco(d, c, 7);
        // Arco eb = new Arco(e, b, 1);
        // Arco ed = new Arco(e, d, 6);
        // Arco ec = new Arco(e, c, 3);
        // grafo.addArco(ab);
        // grafo.addArco(ae);
        // grafo.addArco(bc);
        // grafo.addArco(be);
        // grafo.addArco(cd);
        // grafo.addArco(da);
        // grafo.addArco(dc);
        // grafo.addArco(eb);
        // grafo.addArco(ed);
        // grafo.addArco(ec);
        // System.out.println(grafo);
        // Dijkstra infoA = grafo.dijkstra(a);
        // System.out.println(infoA);
        // Dijkstra infoAPQ = grafo.dijkstraPQ(a);
        // System.out.println(infoAPQ);
        // System.out.println("");
        // Dijkstra infoE = grafo.dijkstra(e);
        // System.out.println(infoE);
        // Dijkstra infoEPQ = grafo.dijkstraPQ(e);
        // System.out.println(infoEPQ);
        test(Grafo.getRandom(10, 60));
    }

    private static void test(Grafo grafo) {
        System.out.println(grafo);
        for (Vertice vertice : grafo.getVertici()) {
            Grafo.Dijkstra info = grafo.dijkstra(vertice);
            System.out.println(info);
        }
        System.out.println("");
        for (Vertice vertice : grafo.getVertici()) {
            Grafo.Dijkstra info = grafo.dijkstraPQ(vertice);
            System.out.println(info);
        }

    }

}
