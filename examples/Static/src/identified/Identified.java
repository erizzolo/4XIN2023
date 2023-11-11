package identified;

/**
 * Classe che usa un attributo statico per assegnare un id progressivo agli oggetti creati
 * 
 */
public class Identified {
    
    /**
     * Last id assigned to an object of this class (or subclasses)
     */
    private static int lastId = 0;

    /**
     * Object identifier
     */
    private int id;

    /**
     * Costruttore "normale" che assegna una nuova id all'oggetto
     */
    public Identified() {
        // here is the magic
        // incrementa ultimo id e lo acquisisce per sé
        id = ++lastId;
    }

    /**
     * Costruttore di copia che crea una copia esatta dell'oggetto originale
     */
    public Identified(Identified original) {
        id = original.id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Identified [id=" + id + "]";
    }

}
