package accessibility;

/**
 *
 * @author emanuele
 */
public class Accessibility {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Classi per testare i modificatori di accessibilità per metodi, attributi e costruttori");
        System.out.println("");
        System.out.println("Nel codice si sono usati nomi del tipo:");
        System.out.println("\t<tipo><modificatore>");
        System.out.println("dove <tipo> è m = metodo, a = attributo");
        System.out.println("e <modificatore> è priv = privato, pack = package, prot = protected, pub = public");
        System.out.println("Per i costruttori, essendo il nome <<imposto>>, si sono usati:");
        System.out.println("\tprivate NomeClasse(String pack, String base, String classe)");
        System.out.println("\t/* package */ NomeClasse(String pack)");
        System.out.println("\tprotected NomeClasse(String pack, String base)");
        System.out.println("\tpublic NomeClasse()");
        System.out.println("");
        System.out.println("Guardare il codice!!!!");
        System.out.println("");
        System.out.println("Modificatore Classe  Package Sottoclasse Mondo ");
        System.out.println("private      Y       N       N           N");
        System.out.println("'package'    Y       Y       N           N");
        System.out.println("protected    Y       Y       Y           N");
        System.out.println("public       Y       Y       Y           Y");
        System.out.println("");
        Classe.test();
        SamePackage.test();
        other.OtherPackage.test();
        other.DerivedClass.test();

    }

}
