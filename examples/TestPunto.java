/*
    test application for class Punto
*/
public class TestPunto {
    public static void main(String[] args) {
        // creazione di un oggetto di classe Punto (inutilizzabile in seguito)
        new Punto(); // primo oggetto creato
        // creazione ed assegnazione del riferimento a variabile locale di tipo Punto
        Punto p = new Punto(); // secondo oggetto creato
        System.out.println("Punto " + p + " (" + p.getX() + ", " + p.getY() + ")");
        p.ribaltaAsseX();
        System.out.println("Ribaltamento del punto");
        System.out.println("Punto " + p + " (" + p.getX() + ", " + p.getY() + ")");
        // creazione ed assegnazione del riferimento a variabile locale di tipo Object
        Object o = new Punto(); // terzo oggetto creato
        System.out.println("Oggetto? " + o + " (non tutti gli oggetti hanno getX() e getY())");
        // Naturalmente dovrei verificare tutti i costruttori, metodi, ecc.
    }
}