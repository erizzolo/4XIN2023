## Lista

Implementazione *ricorsiva* di una lista, che è così definita:

* la lista EMPTY (**unica** lista priva di elementi), oppure
* la composizione di:
    * un elemento,
    * la lista dei precedenti,
    * la lista dei successivi.

Ad esempio, la lista contenente il solo elemento 'A' è la composizione di:

* un elemento: 'A',
* la lista dei precedenti: EMPTY,
* la lista dei successivi: EMPTY.

Si noti che nel caso generale si può vedere la lista in più modi.

Ad esempio, la lista con elementi '1' e '2' (nell'ordine) si può vedere così:

Lista L1:
* un elemento: '1',
* la lista dei precedenti: EMPTY,
* la lista dei successivi: L2, composta da:
    * un elemento: '2',
    * la lista dei precedenti: L1,
    * la lista dei successivi: EMPTY.

oppure così:

Lista L2:
* un elemento: '2',
* la lista dei precedenti: L1, composta da:
    * un elemento: '1',
    * la lista dei precedenti: EMPTY,
    * la lista dei successivi: L2.
* la lista dei successivi: EMPTY.

ed in generale in molti modi diversi.

Sarebbe opportuno darne una rappresentazione "canonica" univoca.

E' implementata l'interfaccia ``Collection<E>``, tranne le operazioni opzionali.

L'implementazione non è molto efficiente, specie nel caso in cui si voglia implementare anche l'interfaccia ``List``.

> **Inoltre** ha un tremendo **BUG** !!!
> 
> **Ricchi premi e cotillons per chi lo scopre...**