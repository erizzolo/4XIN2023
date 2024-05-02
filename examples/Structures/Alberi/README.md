### Alberi

Diverse implementazioni di alberi, basate sull'interfaccia ``Albero``

## Albero binario

Implementazione *ricorsiva* di un **albero binario**, che è così definito:

* l'albero binario EMPTY (**unico** albero binario privo di elementi), oppure
* la composizione di:
    * un elemento,
    * l'albero binario sottoalbero di sinistra,
    * l'albero binario sottoalbero di destra.

Ad esempio, l'albero binario contenente il solo elemento 'A' è la composizione di:

* un elemento: 'A',
* l'albero binario di sinistra: EMPTY,
* l'albero binario di destra: EMPTY.

L'interfaccia iniziale sarà diversa da quella tipica delle Collection...

Sono date due implementazioni:
* ``AlberoBinario`` implementazione di base
* ``AlberoBinarioEfficiente`` con alcune migliorie per efficienza

E' implementata l'interfaccia ``Collection<E>``, tranne (forse) le operazioni opzionali, tramite le classi *Adapter* ``ABCollection`` e ``AdapterCollection``.

## Albero generico

Implementazione di un **albero generico**, in cui ogni nodo ricorda il primo nodo figlio *firstChild* ed il prossimo nodo fratello *nextSibling*:

Per esercizio, potete creare la classe *Adapter* all'interfaccia ``Collection``


## Albero binario di ricerca

Implementazione *ricorsiva* di un **albero binario di ricerca**, che è così definito:

* l'albero binario EMPTY (**unico** albero binario privo di elementi), oppure
* la composizione di:
    * un elemento **e**,
    * l'albero binario di ricerca sottoalbero di sinistra, contenente solo elementi < **e**,
    * l'albero binario sottoalbero di destra, contenente solo elementi > **e**.

>Spesso si impone che non vi siano duplicati né valori null.

Ovviamente, è richiesto un operatore di confronto, ovvero che ``E extends Comparable<E>``.

Il vantaggio che si ottiene è la maggiore efficienza della ricerca che diventa dicotomica quindi di complessità *logarimtica*, naturalmente se l'albero si mantiene *bilanciato*.

Sono date tre implementazioni:
* ``ABRicerca`` implementazione di base
* ``AlberoAVL`` auto-bilanciante (AVL)[https://en.wikipedia.org/wiki/AVL_tree] senza duplicati
* ``AlberoAVLDuplicates`` auto-bilanciante (AVL)[https://en.wikipedia.org/wiki/AVL_tree] con duplicati

Infine è fornita la classe ``AVLSet`` che implementa l'interfaccia ``SortedSet`` (tranne per alcuni metodi) tramite un albero AVL.

La classe App di avvio fornisce alcuni test delle varie implementazioni ed un confronto tra l'implementazione di un Set del package java.util e quella qui fornita (ahimé perdente...).