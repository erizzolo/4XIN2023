## Enums

In Java, le *enum* sono particolari classi di cui sono possibili solo un numero finito di oggetti definiti nella enum stessa.

Per convenzione, i nomi degli oggetti sono in MAIUSCOLO ( in sostanza è come se fossero dei *public static final EnumType ENUM_OBJECT*).

Come in tutte le classi è possibile avere attributi, costruttori, metodi (static e non).

Sono ereditati metodi come:
* int ordinal(): restituisce un int corrispondente all'ordine di dichiarazione (0...)
* String toString(): restituisce il nome dell'oggetto (come dichiarato)

Sono ereditati metodi *static* come:
* values(): restituisce un array con tutti gli oggetti dell'enum
* valueOf(String): restituisce l'oggetto con il nome dato

>Purtroppo, l'estensione delle enum non è in sostanza fattibile.

### List of source files
| file                            | topic                         |
| ------------------------------- | ----------------------------- |
| [TestEnums](src/TestEnums.java) | Test class                    |
| [Planet](src/Planet.java)       | Planet enum                   |
| [Seme](src/Seme.java)           | Seme enum                     |
| [Direzione](src/Direzione.java) | Direzione enum, da completare |

