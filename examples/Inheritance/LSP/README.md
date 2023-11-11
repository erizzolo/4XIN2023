## LSP: the Liskov Substitution Principle
> Quoted from [wikipedia](https://en.wikipedia.org/wiki/Liskov_substitution_principle).
>
> -- <cite>... in a computer program, if S is a subtype of T, then objects of type T may be replaced with objects of type S (i.e. an object of type T may be substituted with any object of a subtype S) without altering any of the desirable properties of the program (correctness, task performed, etc.).

Esempio: se *Square* è una sottoclasse di *Rectangle* gli oggetti di tipo *Rectangle* possono essere sostituiti da oggeti di tipo *Square* senza alterare le proprietà del programma.

## Farlo "sbagliato"

Definisco la classe **Rectangle**, con attributi *base* e *height* e relativi *getters* e *setters*.
A questo punto ne derivo la classe **Square**, ridefinendo i *setters* in modo che mantengano la proprità di un quadrato, cioè di avere *base* = *height*.

Questo viola il principio, perché in realtà quella che ho definito come classe **Rectangle** è in realtà la classe **RectangleWithIndependentlyResizableSides**, e naturalmente un quadrato non rientra in questa definizione.

## Farlo "Giusto"
1. Definisco la classe **Rectangle**, con attributi *base* e *height* e relativi *getters* (NO *setters*).
A questo punto ne derivo la classe **Square**, e la cosa funziona perché gli oggetti sono immutabili e quindi, se sono nati quadrati, restano quadrati.
2. Se voglio poter modificare gli oggetti *Rectangle*, posso introdure un'operazione *scale(double factor)* che mantiene le proporzioni originali. Quindi derivo una classe **RectangleScalable** con un metodo *scale(double factor)* che ridimensiona il rettangolo, mantenendone le proporzioni.
A questo punto ne derivo la classe **SquareScalable**, e la cosa funziona perché i quadrati ridimensionati restano quadrati.
3. Se voglio poter modificare le proporzioni originali degli oggetti *Rectangle*, posso introdure un'operazione *resize(double baseFactor, double heightFactor)* che non necessariamente mantiene le proporzioni originali. Quindi derivo da *RectangleScalable* una classe **RectangleResizable** con un metodo *resize(double baseFactor, double heightFactor)* che ridimensiona il rettangolo, senza necessariamente mantenerne le proporzioni. A questo punto non posso più derivare la classe **SquareResizable**, perché i quadrati ridimensionati in tal modo non sarebbero più dei quadrati!!!

## Morale
Ricordarsi che l'ereditarietà rappresenta delle gerarchie **is-A**, che devono valere **SEMPRE** e **SENZA CONDIZIONI**.

Uno *Square* is-A *Rectangle*, ma

Uno *Square* is-NOT-ALWAYS-A *RectangleWithIndependentlyResizableSides*
