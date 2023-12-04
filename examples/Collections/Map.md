## L'interfaccia **Map<K, V>**

L'interfaccia Map costituisce un'estensione del concetto di array.

Un array *associa* ad un indice intero dei valori di un certo tipo, ad esempio l'array:
```Java
String[] words = {"Ugo", "Alberto", "Ugo"};
```
associa:
* all'indice 0: il valore (String) "Ugo"
* all'indice 1: il valore (String) "Alberto"
* all'indice 2: il valore (String) "Ugo"

>Si noti che i possibili valori dell'indice costituiscono un *insieme*, mentre i valori associati possono essere duplicati.

Sostanzialmente, esso rappresenta la tabella:
| indice | valore    |
| ------ | --------- |
| 0      | "Ugo"     |
| 1      | "Alberto" |
| 2      | "Ugo"     |

Una **Map**, detta anche *array associativo*, *associa* ad un elemento detto *key* (chiave) di un dato insieme, un elemento detto *value* (valore) di un (altro) insieme. Nel caso in cui la *key* sia un intero, l'array precedente potrebbe essere considerato equivalente alla mappa ``Map<int, String>`` seguente:

| chiave | valore    |
| ------ | --------- |
| 0      | "Ugo"     |
| 1      | "Alberto" |
| 2      | "Ugo"     |

Ma la *Map* consente di usare come *chiave* (*indice*) un qualunque elemento, ad esempio una ``Map<Cognome, Nome>`` potrebbe essere:
| chiave    | valore    |
| --------- | --------- |
| "Rossi"   | "Ugo"     |
| "Bianchi" | "Alberto" |
| "Verdi"   | "Ugo"     |

In sostanza, è simile ad avere un array con insieme degli indici = insieme delle chiavi.

La sintassi potrebbe essere (ma in Java non lo è, mentre in C++, php ed altri linguaggi sì):

``mappa["Rossi"]`` : restituisce "Ugo"

In Java, per creare la mappa della tabella precedente, si potrebbe scrivere:
```Java
Map<String, String> persone = new HashMap<>();
persone.put("Rossi", "Ugo");
persone.put("Bianchi", "Alberto");
persone.put("Verdi", "Ugo");
```
dopodichè il codice ``persone.get("Bianchi")`` restituirebbe "Alberto".

Naturalmente l'interfaccia dispone di molte operazioni per inserire, eliminare o modificare *associazioni*.

Sono fornite due implementazioni principali:
* **HashMap**, basata su una hash table (sfrutta il metodo ``int hashCode()`` che dev'essere congruente con il metodo ``equals(Object o)``: vedi [dettagli](equals_hashCode.md))
* **TreeMap**, basata su un albero (sfrutta l'ordinamento degli elementi che deve essere fornito, implementando l'interfaccia ``Comparable`` o tramite un ``Comparator``: vedi [dettagli](comparing.md))
