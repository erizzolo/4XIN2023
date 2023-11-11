package accessibility;

/**
 *
 * @author emanuele
 */
public class SamePackage {

    public SamePackage() {
        test();
    }

    public static void test() {
        System.out.println("");
        System.out.println("Test from the another class in the same package (any class in THE package derived from any class)");
        System.out.println("private constructors, methods, attributes are not available !!!");
        System.out.println("Calling constructors...");
        // Classe cFromSameClass = new Classe("<THE package>", "<any class>", "<any class>");
        Classe cFromSamePackage = new Classe("<THE package>");
        Classe cFromDerivedClass = new Classe("<any package>", "<THE class>");
        Classe cFromAnywhere = new Classe();
        System.out.println("Calling methods...");
        // System.out.println(cFromAnywhere.mPriv());
        System.out.println(cFromAnywhere.mPack());
        System.out.println(cFromAnywhere.mProt());
        System.out.println(cFromAnywhere.mPub());
        System.out.println("Accessing attributes...");
        // System.out.println(cFromAnywhere.aPriv);
        System.out.println(cFromAnywhere.aPack);
        System.out.println(cFromAnywhere.aProt);
        System.out.println(cFromAnywhere.aPub);
    }

}
