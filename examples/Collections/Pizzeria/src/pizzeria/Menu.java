package pizzeria;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Menu della pizzeria: elenco delle pizze disponibili e relativi prezzi
 *
 * Non sono ammesse pizze duplicate / valori null
 *
 * @author emanuele
 */
public class Menu {

    /**
     * La mappa che associa il prezzo ad ogni pizza
     */
    private final Map<PizzaDescrizione, Double> menu;

    /**
     * Costruttore di un menu vuoto
     */
    public Menu() {
        menu = new HashMap<>();
    }

    /**
     * Costruttore di copia
     *
     * @param originale
     */
    public Menu(Menu originale) {
        menu = new HashMap<>(originale.menu);
    }

    /**
     * Aggiunge una pizza al menu con relativo prezzo
     *
     * @param pizza
     * @param prezzo
     */
    public void addPizza(PizzaDescrizione pizza, double prezzo) {
        Objects.requireNonNull(pizza, "Impossibile aggiungere una pizza null");
        if (prezzo <= 0) {
            throw new IllegalArgumentException("Impossibile avere un prezzo non positivo");
        }
        menu.put(pizza, prezzo);
    }

    /**
     * Elimina una pizza dal menu
     *
     * @param pizza
     */
    public void removePizza(PizzaDescrizione pizza) {
        menu.keySet().remove(pizza);
    }

    /**
     * Restituisce l'elenco delle pizze in menu come Set
     *
     * @return l'elenco delle pizze in menu
     */
    public Set<PizzaDescrizione> getPizze() {
        return new HashSet<>(menu.keySet());
    }

    /**
     * Restituisce il prezzo della pizze specificate nel menu
     *
     * @param pizza
     * @return il prezzo della pizze specificate, oppure NaN se non è nel menu
     */
    public double getPrezzo(PizzaDescrizione pizza) {
        Double prezzo = menu.get(pizza);
        return prezzo == null ? Double.NaN : prezzo;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Menu\n");
        for (Map.Entry<PizzaDescrizione, Double> entry : menu.entrySet()) {
            result.append(String.format("%-60s € %6.2f\n", entry.getKey(), entry.getValue()));
        }
        return result.toString();
    }

}
