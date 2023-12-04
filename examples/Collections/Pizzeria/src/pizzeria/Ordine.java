package pizzeria;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

/**
 * Ordine del cliente della pizzeria
 *
 * Contiene Pizze, ammette duplicati, non ammette valori null
 *
 * @author emanuele
 */
public class Ordine {

    private final Collection<PizzaOrdinata> pizze;

    public Ordine() {
        pizze = new ArrayList<>();
    }

    /**
     * Crea un ordine casuale dal menu
     *
     * @param from il menu da cui scegliere
     * @param numClienti il numero di clienti
     * @return un ordine casuale
     */
    public static Ordine getRandom(Menu from, int numClienti) {
        Random random = new Random();
        Ordine result = new Ordine();
        Set<PizzaDescrizione> scelte = from.getPizze();
        for (int cliente = 0; cliente < numClienti; cliente++) {
            for (PizzaDescrizione pizzaDescrizione : scelte) {
                if (random.nextInt(scelte.size()) == cliente) {
                    result.addPizza(pizzaDescrizione, from);
                }
            }
        }
        return result;
    }

    /**
     * Aggiunge una pizza all'ordine da un menu
     *
     * @param pizza
     * @param from
     * @return true se la pizza è stata ordinata, false altrimenti
     */
    public boolean addPizza(PizzaDescrizione pizza, Menu from) {
        Objects.requireNonNull(from, "Impossibile ordinare da un menu null");
        return pizze.add(new PizzaOrdinata(pizza, from.getPrezzo(pizza)));
    }

    /**
     * Elimina una pizza dal menu
     *
     * @param pizza
     * @return true se la pizza è stata eliminata, false altrimenti
     */
    public boolean removePizza(PizzaDescrizione pizza) {
        PizzaOrdinata daEliminare = null;
        for (PizzaOrdinata pizzaOrdinata : pizze) {
            if (pizzaOrdinata.getDescrizione().equals(pizza)) {
                daEliminare = pizzaOrdinata;
            }
        }
        return pizze.remove(daEliminare);
    }

    /**
     * Restituisce l'elenco delle pizze in ordine
     *
     * @return l'elenco delle pizze in ordine
     */
    public Collection<PizzaOrdinata> getPizze() {
        return new ArrayList<>(pizze);
    }

    /**
     * Restituisce il prezzo totale dell'ordine
     *
     * @return il prezzo totale dell'ordine
     */
    public double getPrezzoTotale() {
        double result = 0;
        for (PizzaOrdinata pizza : pizze) {
            result += pizza.getPrezzo();
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Ordine\n");
        pizze.forEach((pizza) -> {
            result.append(String.format("%-60s € %6.2f\n", pizza.getDescrizione().toString(), pizza.getPrezzo()));
        });
        result.append(String.format("%-60s € %6.2f", " Totale", getPrezzoTotale()));
        return result.toString();
    }

}
