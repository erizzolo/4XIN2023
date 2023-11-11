package singleton;

import java.util.Date;

/**
 * Classe che consente all'applicazione di condividere l'unico oggetto creato.
 * 
 * Ad esempio una connessione ad un server ...
 */
public class Singleton {

    /**
     * Reference to the unique Singleton: none so far
     */
    private static Singleton theSingleton = null;

    private String server;
    private Date created;

    /**
     * Costruttore privato, non usabile in altre classi
     */
    private Singleton() {
        // inizializza l'oggetto
        // ad esempio, stabilisce la connessione
        server = "my.server.universe:42"; // port 42!!!
        created = new Date();
    }

    /**
     * "Costruttore" statico pubblico per ottenere il riferimento all'unico oggetto
     * creato
     */
    public static Singleton get() {
        if (theSingleton == null) // not yet created
            theSingleton = new Singleton();
        return theSingleton;
    }

    @Override
    public String toString() {
        return "Singleton [connected to " + server + " since " + created + "]";
    }

}
