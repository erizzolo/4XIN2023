/*
    First example of a basic class.
    NOT an application: the test application is in TestPunto.java
*/
/**
 * La classe punto rappresenta un punto nello spazio bidimensionale.
 */
public class Punto {
    // Attributi: proprietà caratteristiche degli oggetti
    // - distinguono un oggetto da un altro
    // - possono essere costanti o variabili
    // - di norma sono invisibili all'esterno (private)
    private double x;
    private double y;

    // Costruttori: codice che "crea" (istanzia) un oggetto e lo inizializza
    // - non c'è tipo di ritorno
    // - il nome è quello della classe
    // - si distinguono per la lista dei parametri
    // - di norma sono visibili all'esterno (public)
    public Punto() {
        // costruttore "di default"
        x = y = 1.0; // inizializzo lo stato
        // meglio riutilizzare il costruttore completo:
        // this(1.0, 1.0);
    }

    public Punto(double x, double y) {
        // costruttore "completo"
        this.x = x; // attributo x di questo oggetto = parametro x
        this.y = y;
    }

    // Metodi: modalità di interazione tra l'oggetto e l'esterno
    // 1: osservatori (getter)
    // - restituiscono informazioni sull'oggetto (senza modificarne lo stato!)
    // - di norma senza parametri
    // - tipicamente: <tipo informazione> <nome>() { ... }
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getDistanceFromOrigin() {
        return Math.sqrt(x * x + y * y);
    }

    // 2: modificatori
    // - modificano lo stato dell'oggetto (senza restituire nulla quindi void)
    // - possono avere parametri
    // - tipicamente: void <nome>(<eventuali parametri>) { ... }
    public void trasla(double dx, double dy) {
        x += dx;
        y += dy;
    }

    public void ribaltaAsseX() {
        y = -y;
    }

    // 3: misti
    // - da evitare come il COVID ....

}