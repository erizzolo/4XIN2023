# About inheritance
L'ereditarietà (inheritance) è una caratteristica del software OO (Object Oriented) che favorisce la modularità ed il riutilizzo del codice.
## What it is
L'ereditarietà è un meccanismo che consente di derivare nuove classi a partire da classi esistenti, mantenendo ed eventualmente estendendo le caratteristiche e le operazioni esistenti.

In sostanza:

Una classe *derivata* (*sottoclasse*, *subclass*, *child class*)

**estende**

una classe *base* (*sopraclasse*, ***super**class*, *parent class*) non *final*

**ereditandone**

attributi e metodi (*NON* costruttori).

>Gli attributi ed i metodi ereditati non sono necessariamente utilizzabili nel codice della sottoclasse ( ad es. se dichiarati private o package).

>Se non diversamente specificato, in Java tutte le classi derivano *implicitamente* dalla classe Object.

La sottoclasse può:

* aggiungere attributi (static e non)
* aggiungere metodi (static e non)
* ridefinire metodi (non static) (override) ereditati ma:

  * solo quelli non dichiarati *final*
  * senza cambiare il tipo di ritorno
  * senza ridurre l'accessibilità
  * senza aggiungere clausole *throws* (può però eliminarle o "ridurre" le eccezioni)
>Per i metodi static è possibile *"ridefinirli"*, anche specializzando il tipo di ritorno, ma di fatto si tratta di **nuovi** metodi.

Esempio delle classi Punto e PuntoMobile:

* ereditati (ma non accessibili) attributi: *x*, *y* e *static RANDOM*
* ereditati attributi: *static DEBUG* e *static ORIGIN*
* aggiunti attributi: *dx*, *dy*, *travelledDistance* e *static maxTravelledDistance*
* ereditato (ma non accessibile) metodo: *check*
* ereditati metodi: *static getMedio*, *getDistanceFrom*, *static getRandom* e *static test*
* ereditati e ridefiniti metodi: *getX*, *getY*, *toString* e *getSymmetric* (now public instead of private)
* aggiunto metodo *move*, *getter vari* (static e non)
* aggiunti metodi *static getRandom* e *static test* con diverso tipo di ritorno, *PuntoMobile* anziché *Punto* (non proprio un *override*)

>In Java, **per le classi**, è supportata solo l'ereditarietà *singola*, ovvero la *parent class* è una sola: di conseguenza, la gerarchia di ereditarietà è un albero.

### List of examples
| example              | topic                                                                            |
| -------------------- | -------------------------------------------------------------------------------- |
| [Basic](Basic)       | Esempio Punto / PuntoMobile                                                      |
| [Geometry](Geometry) | Esempio con classi *abstract* e *interfaces*                                     |
| [LSP](LSP)           | Esempio di buono e cattivo uso dell'ereditarietà (Liskov Substitution Principle) |
