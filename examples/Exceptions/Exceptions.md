# Exceptions

La classe *Throwable* e quelle derivanti da essa servono a *segnalare* e/o *gestire* situazioni "anomale" che possono verificarsi nell'esecuzione del codice, usando particolari costrutti del linguaggio Java.

## Rappresentazione di anomalie
Come per qualunque altro oggetto, quelli che rappresentano le anomalie si creano usando la keyword *new* ed invocando gli opportuni costruttori.

Di norma tutte le sottoclassi forniscono gli stessi costruttori di *Throwable*:
| Costruttore                                    | Constructs a new throwable with ...                 |
| ---------------------------------------------- | --------------------------------------------------- |
| ``Throwable()``                                | ... null as its detail message.                     |
| ``Throwable(String message)``                  | ... the specified detail message.                   |
| ``Throwable(String message, Throwable cause)`` | ... the specified detail message and cause.         |
| ``Throwable(Throwable cause)``                 | ... the specified cause and a detail message of ... |

Ad esempio:
```Java
Throwable npe = new NullPointerException();
Throwable iae = new IllegalArgumentException("received a null array", npe);
```

## Segnalazione di anomalie

La segnalazione di un'anomalia avviene sfruttando la keyword **throw** (NON ~~throws~~) seguita dall'oggetto che la rappresenta (di norma costruito "al volo").

L'istruzione **throw** interrompe l'esecuzione del metodo/costruttore corrente e restituisce il controllo al chiamante, il quale può gestire l'anomalia segnalata o segnalarla a sua volta, e così via risalendo lo stack delle chiamate fino al metodo *main* di avvio: se nessuno gestisce l'anomalia la JVM termina l'applicazione e visualizza l'anomalia e la traccia dello stack su ``System.err``.

Ad esempio:
```Java
public Horse(String name) {
    if(name == null) {
        throw new NullPointerException("I've been through the desert on a horse with no name ...");
    }
    this.name = name;   // non eseguita se name == null !!!
}
```

## Specifica delle anomalie
I metodi/costruttori che possono segnalare anomalie al chiamante possono/devono specificarlo nella dichiarazione usando la keyword **throws** (NON ~~throw~~) seguita dall'elenco dei (tipi di) anomalie che possono segnalare.

Questo consente al compilatore di effettuare opportuni controlli ed al programmatore di sapere con quali anomalie potrebbe ritrovarsi utilizzando quel codice.

Ad esempio:
```Java
public static Horse readFromFile(String filename) throws NullPointerException, FileNotFoundException {
    if(filename == null) {
        throw new NullPointerException("I've been through the desert on a horse with no name ...");
    }
    Scanner infile = new Scanner(new File(filename));   // non eseguita se filename == null !!!
    return new Horse(infile.nextLine());    // non eseguita se il file non esiste !!!
}
```

## Gestione di anomalie
La gestione delle anomalie sfrutta il costrutto *try, catch, finally* e consente di "rimediare" alle anomalie prendendo opportuni provvedimenti ed evitando in genere che si propaghino ulteriormente.

Il costrutto è formato da un blocco "try", zero o più blocchi "catch" (di norma almeno uno) ed opzionalmente di un blocco "finally".

Ad esempio:
```Java
try {
    Horse horse = Horse.readFromFile(args[0]);
    horse.rideThrough("desert");
    horse.comeBackHome();
} catch(ArrayIndexOutOfBoundsException aiobe) {
    System.out.println("missing filename");
} catch(NullPointerException npe) {
    System.out.println("missing a REAL filename");
} catch(FileNotFoundException fnfe) {
    System.out.println("File " + args[0] + " not found!!!");
} finally {
    System.out.println("Safely at home, anyway");
}
```

L'esecuzione inizia dal blocco *try*:
* se vi sono anomalie l'esecuzione del blocco *try* è interrotta e
  * viene ripresa con il blocco della **prima** clausola *catch* con tipo dichiarato compatibile con la classe dell'anomalia segnalata, interrompendo così la propagazione dell'anomalia

l'esecuzione prosegue (**comunque**) con il blocco *finally*.

Se vi è stata un'anomalia e nessuna clausola *catch* corrispondeva, l'anomalia si propaga al chiamante **dopo** l'esecuzione del blocco *finally*.

## Tipi di anomalie
La gerarchia (incompleta) di *Throwable* è riportata nella figura seguente:
![](exceptions-throwable.gif)

(Source: [https://docs.oracle.com/javase/tutorial/figures/essential/exceptions-throwable.gif](https://docs.oracle.com/javase/tutorial/figures/essential/exceptions-throwable.gif))

Tutte le classi derivanti da *Throwable* si suddividono in tre categorie:
1. errors (*Error* e relative sottoclassi)
2. unchecked exceptions (*RuntimeException* e relative sottoclassi)
3. **checked exceptions** (tutte le altre)

### Errors
Si tratta di anomalie gravi, in genere esterne all'applicazione, quali errori hardware o della JVM. La maggior parte delle applicazione non deve preoccuparsi di gestire queste anomalie.

Esempio:
* ``InternalError``: Thrown to indicate some unexpected internal error has occurred in the Java Virtual Machine.

### Unchecked exceptions
Si tratta di anomalie probabilmente dovute a bug del codice oppure ad un uso errato dei metodi. Anziché gestirle, sarebbe meglio correggere il codice per evitarle.

Esempio:
* ``ArithmeticException``: ... an exceptional arithmetic condition ... an integer "divide by zero"...
* ``ClassCastException``: ... cast invalido a Comparable ...
* ``NumberFormatException``: ... nextInt() con "a-.67?" ...
* ``IllegalArgumentException``: ... generica per parametri non validi ...
* ``IndexOutOfBoundsException``: ... indice errato per String o array ...
* ``NullPointerException``: ... uso improprio di riferimento null ...

### Checked exceptions
Si tratta di anomalie che un'applicazione non può prevedere e che dovrebbe gestire in modo da consentire all'utente di proseguire il lavoro. In effetti, Java ci obbliga a fare qualcosa quando abbiamo a che fare con queste anomalie.

Esempio:
* ``java.util.zip.DataFormatException``: ... archivio corrotto ?! ...
* ``java.io.FileNotFoundException``: ... (tentativo di) accesso a file non esistente ...
* ``java.net.UnknownHostException``: ... (tentativo di) accesso a host non esistente ...

#### **Catch or Specify** requirement
Quando il nostro codice può far propagare una *checked exceptions* (direttamente con *throw* o indirettamente richiamando altro codice) siamo obbligati a prendere uno dei due seguenti provvedimenti:
* **Catch**: usare il costrutto try/catch per evitare la propagazione
* **Specify**: dichiarare l'anomalia tramite la keyword *throws*

Quest'ultima soluzione ovviamente non va usata indiscriminatamente: un'applicazione con
```Java
public static void main(String[] args) throws IOException { ... }
```
è una **pessima** applicazione!!!

**Specify** va usata **solo** nel caso in cui chi genera l'anomalia non è in grado di stabilire il modo migliore di gestirla, e quindi lascia che a prendere la decisione sul come farlo sia qualcun altro.

Ad esempio, il costruttore della classe Scanner:

``Scanner(File source)``: Constructs a new Scanner that produces values scanned from the specified file.

non può sapere cosa fare se il file che gli diciamo di aprire non esiste, e quindi, non potendo gestire in modo ottimale l'anomalia, ce la lascia gestire come ci pare e piace: **Specify**.

Ma quando noi usiamo tale costruttore, ~~possiamo~~ DOBBIAMO decidere come risolvere la faccenda e quindi useremo un try/catch opportuno: **Catch**.

## I nostri nuovi tipi di anomalie
Come per qualunque classe non final, anche *Exception* o le sue sottoclassi possono essere utilizzate per derivare sottoclassi più specifiche. L'utilizzo di *Exception* specifiche della nostra applicazione può essere molto utile per rendere il codice più chiaro o la gestione delle anomalie più semplice.

## Approfondimenti
Da fare

### Catene di anomalie
Uso sensato del parametro *cause* ... ad esempio:
```Java
@Override
public void getta(Riempibile r, Chiave k) {
    if (!smaltito) {
        try {
            r.inserisci(this, k);
            smaltito = true;
        } catch (ContenitorePienoException
                | MaterialeNonCompatibileException
                | ChiaveNonCorrettaException ex) {
            throw new RifiutoNonSmaltitoException("Rifiuto non smaltito.", ex);
        } 
    }
}
```
In questo caso le eccezioni checked della clausola catch vengono "trasformate" in una eccezione unchecked con causa.

### try-with-resource
Il costrutto *try-with-resource* consente di semplificare la gestione delle eccezioni quando il blocco try acquisisce delle risorse (ad esempio file, connessioni di rete, ecc.) che sono *Closeable*, anzi *AutoCloseable*.

Considerate il seguente metodo:
```Java
static void printNumbers(String name) throws FileNotFoundException {
    Scanner input = new Scanner(new File(name));
    while (input.hasNextLine()) {
        System.out.println(Double.parseDouble(input.nextLine()));
    }
    input.close();
}
```
e pensate di avere una linea contenente "abcde": il *parseDouble* genera una *NumberFormatException* che interrompe l'esecuzione e lascia il file aperto!

Per ovviare a ciò dovrei scrivere:
```Java
static void printNumbers(String name) throws FileNotFoundException {
    Scanner input = null;
    try {
        input = new Scanner(new File(name));
        while (input.hasNextLine()) {
            System.out.println(Double.parseDouble(input.nextLine()));
        }
    } finally {
        if (input != null) {
            input.close();
        }
    }
}
```
Il costrutto *try-with-resource* semplifica la scrittura e garantisce che il metodo *close* sia richiamato comunque:
```Java
static void printNumbers(String name) throws FileNotFoundException {
    try (Scanner input = new Scanner(new File(name))) {
        while (input.hasNextLine()) {
            String line = input.nextLine();
            System.out.println(Double.parseDouble(line));
        }
    }
}
```
Naturalmente anch'esso può prevedere clausole *catch* e blocco *finally*.