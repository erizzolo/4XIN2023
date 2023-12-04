## Note sulle interfacce *Comparable* e *Comparator*

Sono le interfacce che consentono di confrontare e quindi ordinare gli oggetti di una classe.

### Interfaccia *Comparable<T>*

La più semplice delle due, contiene solo il metodo ``int compareTo(T o)`` che restituisce un valore negativo, nullo o positivo a seconda che ``this`` preceda (<), corrisponda (==) o segua (>) ``o`` nell'ordinamento **naturale** degli oggetti della classe che la implementa.

Ad esempio:
```Java
public class Quadrato implements Comparable<Quadrato> {
    private final double lato;
    public Quadrato(double lato) { this.lato = lato; }
    public double getLato() { return lato; }
    @Override
    public int compareTo(Quadrato o) { return Double.compare(lato, o.lato); }
}
```
essendo ovvio l'ordinamento dei quadrati in base al lato (area, perimetro,...).

>Notare come è in genere conveniente sfruttare metodi di confronto forniti da altre classi, che gestiscono correttamente i casi particolari.

>L'implementazione **dovrebbe** essere coerente con quella di *equals* nel senso che ``o1.compareTo(o2) == 0`` se e solo se ``o1.equals(o2)``.

### Interfaccia *Comparator<T>*

Qualora la classe non abbia un ordinamento **naturale** ovvio, o quando per altre ragioni desiderassimo usare un criterio di ordinamento diverso, possiamo usare oggetti che implementano l'interfaccia *Comparator*, la quale contiene molti metodi *static* e *default* che non dobbiamo/possiamo ridefinire e due soli metodi astratti:
* ``int compare(T o1, T o2)``: *Compares its two arguments for order.*
* ``boolean equals(Object obj)``: *Indicates whether some other object is "equal to" this comparator.*
  
dei quali ci interessa soltanto il primo.

Il metodo ``int compare(T o1, T o2)`` deve restituire un valore negativo, nullo o positivo a seconda che ``o1`` preceda (<), corrisponda (==) o segua (>) ``o2`` nell'ordinamento che vogliamo utilizzare.

Ad esempio, l'ordinamento **naturale** della classe *Quadrato*:
```Java
public class ComparatoreNaturaleQuadrato implements Comparator<Quadrato> {
    @Override
    public int compare(Quadrato o1, Quadrato o2) {
        return Double.compare(o1.getLato(), o2.getLato()); }
}
```
oppure quello opposto:
```Java
public class ComparatoreOppostoNaturaleQuadrato implements Comparator<Quadrato> {
    @Override
    public int compare(Quadrato o1, Quadrato o2) {
        return -Double.compare(o1.getLato(), o2.getLato()); }
}
```
sebbene sia conveniente usare metodi *static* o *default* di *Comparator*, ad esempio:
```Java
new ComparatoreNaturaleQuadrato().reversed();
```
oppure ancora:
```Java
Comparator<Quadrato> natural = Comparator.comparing(Quadrato::getLato);
```

>Notare come è in genere conveniente sfruttare metodi di confronto forniti da altre classi, che gestiscono correttamente i casi particolari.

