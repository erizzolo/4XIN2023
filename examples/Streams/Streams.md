# Streams

Gli *stream* sono **sequenze di dati elementari** opportunamente disponibili tramite un dispositivo hardware o software.

I dati elementari possono essere elaborati, *filtrati* o altro ancora.

Dal punto di vista dell'applicazione, i dati possono essere letti (dati di input) o scritti (dati di output), permettendo così la distinzione tra *input stream* e *output stream*.

Il tipo di dati elementari di più basso livello è il **byte**.

Di questi dati elementari si occupano le classi ``InputStream`` e ``OutputStream``.

>Sono entrambi classi astratte con diverse sottoclassi specifiche.

>Gli *streams* sono delle risorse che l'applicazione deve *rilasciare* dopo l'utilizzo: ciò **deve** essere fatto richiamando il metodo ``close()``.

Per il loro utilizzo è in genere necessario utilizzare il costrutto *try catch finally* poichè nelle operazioni di creazione (apertura), utilizzo (lettua/scrittura) e rilascio (chiusura) possono verificarsi situazioni anomale (exception).

Un esempio con ``InputStream`` è:
```Java
InputStream is = null;
try {
    is = new FileInputStream(infile);
    // utilizzo dello stream ...
} catch (Exception e) {
} finally {
    try { is.close();
    } catch (Exception e) {}
}
```
Un esempio con ``OutputStream`` è:
```Java
try (OutputStream os = new FileOutputStream("tryWithSingle")) {
    // utilizzo dello stream
} catch (Exception e) {}
```
dove si sono usati costrutti sintatticamente diversi e non sono state intercettate Exception specifiche: vedi [Exceptions](../Exceptions/README.md).

Le operazioni di base sono, rispettivamente ``public int read()`` per gli ``InputStream`` e ``public void write(int b)`` per gli ``OutputStream``, che consentono di leggere/scrivere un singolo **byte** di dati.

I parametri/valori di ritorno sono ``int`` per poter distinguere il dato -1 dalla condizione di fine stream.

Sono presenti metodi che consentono operazioni su blocchi (array) di byte o su porzioni di essi. In genere queste sono da preferirsi per motivi di efficienza,

L'elaborazione tipica consiste in una lettura completa dello stream, che può essere realizzata in vari modi, ad esempio per singoli dati:
```Java
        int dato = is.read(); // prima lettura
        while (dato != -1) { // -1 means end of stream
            // elabora dato ...
            // lettura dato successivo
            dato = is.read();
        }
```
oppure a blocchi
```Java
        byte[] buffer = new byte[128]; // [4096] = 4KB
        int inseriti = is.read(buffer); // prima lettura
        while (inseriti != -1) { // -1 means end of stream
            // elabora dati in buffer[0..inseriti[ ...
            // lettura blocco successivo
            inseriti = is.read(buffer);
        }
```
ed analogamente per il caso di output.

## Sottoclassi principali

Vi sono sottoclassi legate a particolari dispositivi e sottoclassi con funzionalità aggiuntive.

>Nel seguito si indicherà con *(IO)* uno tra Input | Output.

Ad esempio, per il primo caso:
* ``File(IO)Stream`` per I/O da file
* ``ByteArray(IO)Stream`` per I/O da array di bytes
* ...

e per il secondo caso:
* ``Filter(IO)Stream`` per I/O filtrato
* ``Data(IO)Stream`` per I/O di dati primitivi
* ``Object(IO)Stream`` per I/O di Objects
* ...

ed ancora casi specifici delle sottoclassi, in particolare di ``Filter(IO)Stream`` quali:
* ``Checked(IO)Stream`` per I/O con verifica tramite checksum
* ``CipherIO)Stream`` per I/O (de)cifrato
* ``Inflater(IO)Stream`` per I/O (de)compresso
* ``Deflater(IO)Stream`` per I/O (de)compresso
* ...

## Utilizzo in altre classi

Vi sono altre classi che ottengono dati da *(IO)Streams* e li trattano in modo particolare, fornendo metodi più *evoluti* e specifici,

Ad esempio, le classi *Reader* e *Writer* per trattare i dati come caratteri.

Non va dimenticata l'estensione del concetto di stream a dati generici tramite l'interfaccia ``Stream<T>`` che da Java8 è stata integrata con le Collection.
