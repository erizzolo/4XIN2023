package other;

import accessibility.Classe;

/**
 *
 * @author emanuele
 */
public class DerivedClass extends Classe {

    public DerivedClass() {
        System.out.println("");
        System.out.println("BUT from a constructor or in general from initializing code, they are!!!");
        System.out.println("A protected member or constructor of an object may be accessed from outside the package in which it is declared only by code that is responsible for the implementation of that object.");
        System.out.println("https://docs.oracle.com/javase/specs/jls/se7/html/jls-6.html#jls-6.6.2");
        new DerivedClass(true);
    }

    private DerivedClass(boolean access) {
        super("<any package>", "<THE class>");
        System.out.println(mProt());
        System.out.println(aProt);
    }

    public static void test() {
        System.out.println("");
        System.out.println("Test from the a subclass in another package (any class in any package derived from THE class)");
        System.out.println("private, /* package */, protected constructors, methods, attributes are not available in general!!!");
        System.out.println("Calling constructors...");
        // Classe cFromSameClass = new Classe("<any package>", "<any class>", "<THE class>");
        // Classe cFromSamePackage = new Classe("<any package>");
        // Classe cFromDerivedClass = new Classe("<any package>", "<THE class>");
        Classe cFromAnywhere = new Classe();
        System.out.println("Calling methods...");
        // System.out.println(cFromAnywhere.mPriv());
        // System.out.println(cFromAnywhere.mPack());
        // System.out.println(cFromAnywhere.mProt());
        System.out.println(cFromAnywhere.mPub());
        System.out.println("Accessing attributes...");
        // System.out.println(cFromAnywhere.aPriv);
        // System.out.println(cFromAnywhere.aPack);
        // System.out.println(cFromAnywhere.aProt);
        System.out.println(cFromAnywhere.aPub);
        new DerivedClass();
    }
}
