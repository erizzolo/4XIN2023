## Interfaces

In Java, le *interfaces* sono particolari tipi riferimento simili alle classi ma di cui non è possibile istanziare oggetti.

Esse descrivono un'interfaccia, cioè un insieme di operazioni (metodi), con cui è possibile interagire con gli oggetti delle classi che offrono (implementano) tale interfaccia.

Nelle *interface* è possibile avere soltanto (Java **8**):

* costanti (ovvero attributi *public static final*)
* metodi *abstract* (ovvero privi di implementazione)
* metodi con implementazione di default.

Una *interface* può *estendere* una o più altre *interface(s)* e le classi possono *implementare* una o più *interface(s)*: in questo senso Java fornisce l'ereditarietà *multipla*.

>Quando una classe (non *abstract*) *implements* una *interface* è **obbligata** a definire, ovvero implementare, i metodi *abstract* della *interface*. ***In caso contrario, non si saprebbe cosa comportano determinate operazioni sugli oggetti concreti della classe!***

## Abstract classes
Le classi *abstract* sono classi di cui non è possibile istanziare oggetti e che quindi possono dichiarare metodi *abstract* privi di implementazione.

Sono simili alle *interfaces* ma possono contenere:
* attributi (*static* e non)
* costruttori
* metodi *abstract* (ovvero privi di implementazione)
* metodi (*static* e non) con implementazione

>Quando una classe dichiara o eredita (almeno) un metodo *abstract*, **deve** essere dichiarata essa stessa *abstract*. ***In caso contrario, non si saprebbe cosa comportano determinate operazioni sugli oggetti concreti della classe!***

## Note comuni ad *interfaces* e *abstract classes*

>In entrambi i casi non è possibile istanziare oggetti.

>In entrambi i casi, è possibile dichiarare variabili e attributi di quel tipo (che potranno contenere riferimenti ad oggetti di sottoclassi che implementano la *interface* od estendono la *classe abstract*).

>Non è possibile ereditare da più di una classe (*abstract* o non) mentre è possibile implementare più di una *interface*.

### Morale
La scelta tra *abstract class* e *interface* può dipendere da tanti criteri...

In diversi casi, si parte da una *interface* che definisce le possibili operazioni, ad es. per un touch screen:
```Java
public interface Touchable {...}
```
Ma le operazioni possibili sono tante, e quindi derivare una classe
```Java
public class TouchScreen implements Touchable {...}
```
può risultare scomodo perché si devono implementare *tutte* le possibili *gestures*..., quindi può risultare conveniente introdurre una *abstract* class che "implementa" i metodi in maniera "banale", ad esempio senza fare nulla:
```Java
public abstract class FakeTouchable implements Touchable {
    @Override
    public void tap(...) {}
    @Override
    public void doubleTap(...) {}
    ...
}
```
In questo modo, sarà più semplice scrivere una classe "concreta" che ridefinisce solo le *gestures* significative, ad esempio:
```Java
public class BasicTouchable extends FakeTouchable {
    @Override
    public void tap(...) {
        System.out.println("Tapped");
    }
    // Nothing else supported, sorry -:(
}
```
Naturalmente ciò è possibile *solo* se la la classe derivata non deve estendere un'altra classe, ad esempio se *BasicTouchable* non deve estendere *Display*: in questo caso non resta che scegliere se:
* estendere *Display* ed implementare *Touchable*
* estendere *FakeTouchable* ed implementare l'interfaccia offerta da *Display*.

La cosa diventa più facile se l'interfaccia offre metodi *default*.

## Modifica delle *interfaces*
Quando si modifica una *interface* può essere necessario:
* modificare **tutte** le classi che la implementano; questo accade quando:
  * si modifica la dichiarazione di un metodo *abstract* già esistente
  * si aggiunge la dichiarazione di un metodo *abstract*
* modificare **alcune** classi che la implementano; questo accade quando:
  * si modifica la dichiarazione di un metodo *default* già esistente che **alcune** classi hanno ridefinito

Le modifiche alla dichiarazione di un metodo possono, in qualche caso, essere sostituite dall'aggiunta di un ulteriore metodo; in questo caso:
* se il metodo aggiunto è *abstract*, si ricade nel caso precedente ed è necessario modificare **tutte** le classi che la implementano
* se il metodo aggiunto è *default*, non è necessaria alcuna modifica perché **nessuna** classe lo utilizzava!

In generale è opportuno scegliere la modalità che minimizza le modifiche necessarie.

## [List of examples](Esempi/README.md)

