package pizzeria;

/**
 *
 * @author emanuele
 */
public class Pizzeria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.addPizza(new PizzaDescrizione(TipoPizza.ROMANA, TagliaPizza.PICCOLA), 4.5);
        menu.addPizza(new PizzaDescrizione(TipoPizza.MARGHERITA, TagliaPizza.PICCOLA), 5.5);
        System.out.println(menu);
        Menu special = new Menu(menu);
        special.addPizza(new PizzaDescrizione(TipoPizza.ROMANA, TagliaPizza.PICCOLA), 4.0);
        special.addPizza(new PizzaDescrizione(TipoPizza.ROMANA, TagliaPizza.NORMALE), 5.0);
        System.out.println(special);
        for (PizzaDescrizione pizzaDescrizione : special.getPizze()) {
            System.out.println(pizzaDescrizione + " " + special.getPrezzo(pizzaDescrizione));
        }
        System.out.println(special.getPrezzo(new PizzaDescrizione(TipoPizza.QUATTRO_STAGIONI, TagliaPizza.GRANDE)));
        System.out.println("Simulazione ordine");
        Ordine random = Ordine.getRandom(special, 5);
        System.out.println(random);
    }

}
