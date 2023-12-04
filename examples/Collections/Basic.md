## Esempio di base e considerazioni generali sulle Collections di Java 8

Il cosiddetto [Java Collection Framework](https://docs.oracle.com/javase/8/docs/technotes/guides/collections/overview.html) è un insieme di tipi riferimento (interfacce, classi, ...) che fornisce soluzione ai problemi che hanno a che fare con la gestione di *gruppi* di *elementi*.

Sono dei tipi *generici*, che vanno adattati al tipo specifico di elementi che dovranno contenere e le implementazioni sono dinamiche, cioè la capacità si adatta agli elementi inseriti senza necessità di intervento esplicito.

L'interfaccia base della gerarchia è *Iterable*, che consente di effettuare delle iterazioni tramite il ciclo *foreach*.

*Iterable* <- *Collection*

Essa è estesa dall'interfaccia *Collection* da cui derivano tutte le altre: per qualunque *Collection* *c* potremo quindi scrivere:
```Java
for(Element e: c) { // operazioni su e }
```
essendo *Element* il tipo della *Collection*.

L'interfaccia *Collection* è molto generale, non presumendo nulla sulle caratteristiche degli elementi, e non ha implementazioni dirette.

Offre operazioni di inserimento, rimozione, ricerca su elementi singoli o multipli, che restituiscono un valore *boolean* **true** se l'operazione ha avuto successo, **false** altrimenti. Sono inoltre presenti ```int size()```, ```void clear()```, ```boolean isEmpty()``` con ovvio significato.

 oltre a comuni funzioni

>Nel seguito indicheremo con **E** (maiuscolo) il tipo di elementi gestito dalla Collection e con **e** (minuscolo) un generico elemento di essa.
Si ipotizza una dichiarazione del tipo: ```Collection<E> c = ...```

| operazione  | singolo elemento                 | multipli elementi                               |
| ----------- | -------------------------------- | ----------------------------------------------- |
| inserimento | ```boolean add(E e)```           | ```boolean addAll(Collection<? extends E> c)``` |
| rimozione   | ```boolean remove(Object o)```   | ```boolean removeAll(Collection<?> c)```        |
| ricerca     | ```boolean contains(Object o)``` | ```boolean containsAll(Collection<?> c)```      |

>Si noti che le operazioni di ricerca e rimozione sono dichiarate in modo più generico, non essendo critiche ai fini del tipo di elementi presenti nella Collection.

>Si tenga presente che le Collection si basano per le operazioni sul metodo **equals** **dell'oggetto cercato o da rimuovere**, tranne nel caso di valori null. Quindi è fondamentale che gli oggetti usati (preferibilmente di tipo E!!!) forniscano un metodo **equals** congruente all'uso previsto. Ad esempio, una possibile implementazione del metodo di ricerca potrebbe essere:
```Java
boolean contains(Object o) {
    for(E e: this) {
        if(o == null ? e == null : o.equals(e)) {
            return true;
        }
    }
    return false;
}
```
*Iterable* <- *Collection* <- *List*

L'interfaccia *List*, che estende *Collection*, rappresenta una sequenza in cui gli elementi hanno una posizione (variabile da 0 a size() - 1), similmente a quanto avviene negli array. **Ammette duplicati e valori null**.

Oltre alle operazioni ereditate, offre operazioni di accesso, inserimento, rimozione, ricerca basate su indice.

| operazione   | singolo elemento              | (multipli?) elementi                                     |
| ------------ | ----------------------------- | -------------------------------------------------------- |
| inserimento  | ``void add(int index, E e)``  | ``boolean addAll(int index, Collection<? extends E> c)`` |
| rimozione    | ``E remove(int index)``       | N/A                                                      |
| ricerca      | ``int indexOf(Object o)``     |                                                          |
| ricerca      | ``int lastIndexOf(Object o)`` |                                                          |
| accesso      | ``E get(int index)``          |                                                          |
| sostituzione | ``E set(int index, E e)``     |                                                          |

Sono fornite due implementazioni:
* **ArrayList**, basata su array ridimensionabile, con buone prestazioni per l'accesso indicizzato e minori per gli inserimenti (qualora sia necessario una riallocazione)
* **LinkedList**, basata su doppi collegamenti tra gli elementi, che di fatto implementa anche le operazioni dell'interfaccia *Deque* (ovvero una *double ended queue* che comprende le funzionalità di *Stack* e *Queue*)

*Iterable* <- *Collection* <- *Set*

L'interfaccia *Set*, che estende *Collection*, rappresenta un insieme di elementi distinti. **Non ammette duplicati ed eventualmente un solo elemento null**.

Sono fornite due implementazioni:
* **HashSet**, basata su una hash table (sfrutta il metodo ``int hashCode()`` che dev'essere congruente con il metodo ``equals(Object o)``: vedi [dettagli](equals_hashCode.md))
* **TreeSet**, basata su una mappa ad albero (sfrutta l'ordinamento degli elementi che deve essere fornito, implementando l'interfaccia ``Comparable`` o tramite un ``Comparator``: vedi [dettagli](comparing.md))

più quella specifica per le classi enum: **EnumSet**.

### Note sull'utilizzo

Per scegliere una Collection, si deve stabilire:
* se l'ordine è importante
* se la posizione è importante
* se sono ammessi duplicati
* se sono ammessi valori null
* quali operazioni sono effettuate e con quale frequenza
* tipi degli elementi possibili

Stabilito ciò, si scelga:
* l'interfaccia più generale possibile che offre tutte le operazioni che intendiamo utilizzare
* l'implementazione più efficiente e che soddisfa il maggior numero di requisiti
* il tipo più specifico possibile degli elementi da gestire

Ad esempio, siano:
* *List* l'interfaccia scelta
* *LinkedList* l'implementazione scelta
* *Poligono* il tipo scelto

Dichiarare (ed inizializzare) l'attributo/variabile locale/parametro come segue:
```Java
List<Poligono> l = new LinkedList<>();
```

In questo modo, saremo sufficientemente garantiti che:
* nella Collection non ci saranno oggetti non gestibili
* tutte le operazioni necessarie saranno disponibili
* la scelta di una diversa implementazione sarà semplice e comporterà un'unica modifica
