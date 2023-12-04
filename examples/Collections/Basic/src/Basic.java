public class Basic {
    public static void main(String[] args) {
        try {
            puntowithoutequals.Collezioni.main(args);
        } catch (Exception e) {
            System.out.println("");
        }
        try {
            puntowithequals.Collezioni.main(args);
        } catch (Exception e) {
            System.out.println("");
        }
        try {
            puntowithequalsandhashcode.Collezioni.main(args);
        } catch (Exception e) {
            System.out.println("");
        }
        puntocomparable.Collezioni.main(args);
    }

}
