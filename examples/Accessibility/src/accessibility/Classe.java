package accessibility;

/**
 *
 * @author emanuele
 */
public class Classe {

    // attributi di diversa accessibilità
    private String aPriv = "private attribute";                 // minima: solo in questa classe
    /* package */ String aPack = "/* package */ attribute";     // private + altre classi nello stesso package
    protected String aProt = "protected attribute";             // package + classi derivate in package diversi (con alcuni limiti)
    public String aPub = "public attribute";                    // massima: nessuna limitazione

    // costruttori di diversa accessibilità
    private Classe(String pack, String base, String classe) {   // minima: solo in questa classe
        System.out.println("-> private Classe(...)");
        System.out.println("In class " + classe + ", package " + pack + ", derived from " + base + ", cioè la classe di appartenenza!");
    }

    /* package */ Classe(String pack) {                         // private + altre classi nello stesso package
        System.out.println("-> /* package */ Classe(...)");
        System.out.println("In package " + pack + ", cioè una classe dello stesso package!");
    }

    protected Classe(String pack, String base) {                // package + classi derivate in package diversi (con alcuni limiti)
        System.out.println("-> protected Classe(...)");
        System.out.println("In a class of package " + pack + ", derived from " + base + ", cioè una sottoclasse!");
    }

    public Classe() {                                           // massima: nessuna limitazione
        System.out.println("-> public Classe()");
        System.out.println("In a class of some package derived from some class, cioè ovunque nel mondo!");
    }

    // metodi di diversa accessibilità
    private String mPriv() {          // minima: solo in questa classe
        return "private method";
    }

    /* package */ String mPack() {    // private + altre classi nello stesso package
        return "/* package */ method";
    }

    protected String mProt() {        // package + classi derivate in package diversi (con alcuni limiti)
        return "protected method";
    }

    public String mPub() {            // massima: nessuna limitazione
        return "public method";
    }

    public static void test() {
        System.out.println("");
        System.out.println("Test from the same class (THE class in THE package derived from THE superclass)");
        System.out.println("Calling constructors...");
        Classe cFromSameClass = new Classe("<THE package>", "<THE superclass>", "<THE class>");
        Classe cFromSamePackage = new Classe("<THE package>");
        Classe cFromDerivedClass = new Classe("<any package>", "<THE class>");
        Classe cFromAnywhere = new Classe();
        System.out.println("Calling methods...");
        System.out.println(cFromAnywhere.mPriv());
        System.out.println(cFromAnywhere.mPack());
        System.out.println(cFromAnywhere.mProt());
        System.out.println(cFromAnywhere.mPub());
        System.out.println("Accessing attributes...");
        System.out.println(cFromAnywhere.aPriv);
        System.out.println(cFromAnywhere.aPack);
        System.out.println(cFromAnywhere.aProt);
        System.out.println(cFromAnywhere.aPub);
    }

}
