## Note sui metodi *equals* e *hashCode*

Per capire il funzionamento delle strutture dati basate su **hash**. e quindi perché sia necessario implementare in modo congruente i metodi *equals* e *hashCode*, si consideri l'esempio di una scuola (IIS Levi-Ponti).

Tale scuola, terminato il periodo di auge del secolo *ventesimoprimo* (ovvero XXI), si era ormai ridotta ad un *contenitore* di studenti: infatti, non si svolgevano **mai** lezioni di alcun tipo; al più si organizzavano attività per intrattenere i presenti, ma non sul serio...

L'unica cosa che preoccupava il personale (*non docente*, ovviamente) era di poter svolgere rapidamente i seguenti compiti:

* assegnare un posto agli studenti che chiedevano un *inserimento* a scuola;
* recuperare dal loro posto e far uscire gli studenti di cui i genitori ~~(o le forze dell'ordine, nel peggiore dei casi)~~ chiedevano la *rimozione*;
* trovare un certo studente per dire a chiunque fosse interessato alla *ricerca* se egli era presente a scuola o no.
  
Il personale *docente*, stufo di non fare nulla, aveva cominciato a lambiccarsi il cervello per trovare soluzioni ottimali. Ecco le varie proposte emerse, in ordine *quasi* cronologico.

### La proposta del prof. **Vettore**
Detto prof. aveva suggerito di numerare gli *N* posti disponibili da 0 (sic!) a N-1 e di assegnare a ciascuno studente il posto libero con numero minimo possibile senza mai lasciare posti vuoti tra gli studenti.

I responsabili dell'*inserimento* erano contentissimi: bastava infatti tener conto di quanti studenti fossero presenti a scuola per sapere quale posto assegnare ai nuovi arrivati.

Purtroppo i responsabili della *rimozione* e della *ricerca* trovavano estenuante il loro compito che li costringeva ad un continuo peregrinare lungo (quasi) tutti i posti ~~disponibili~~ occupati.

Inoltre lo spostamento degli studenti da un posto all'altro per evitare di lasciare posti vuoti era talmente rumoroso che la scuola venne citata per disturbo alla quiete pubblica. Quando si provò a far spostare gli studenti uno alla volta il rumore divenne ragionevole ma spesso i tempi necessari alla *rimozione* erano così lunghi che all'ingresso si formavano code lunghissime per l'*inserimento* e la *ricerca*.

### La proposta della prof.ssa **Lista**
La prof.ssa Lista (poi soprannominata ListaCollegata) suggerì allora di collegare ciascuno studente presente a chi lo aveva preceduto ed a chi lo aveva seguito nell'*inserimento*. Sosteneva che in tal modo la *rimozione* non avrebbe più provocato rumore od attese poiché sarebbe stato sufficiente modificare i collegamenti dello studente rimosso, di chi lo precedeva e di chi lo seguiva (al più, tre studenti).

Nonostante la proposta avesse ottenuto molti pareri favorevoli (sebbene alcuni detrattori sostenessero che la *ricerca* sarebbe stata ugualmente lunga come in precedenza), essa
non venne approvata per timore di accuse di *dittatura*: non era infatti ben chiaro quale fosse lo strumento di collegamento da adottare (alcuni avevano pensato addirittura ad un'app!!!).

### La proposta del sig. **Hash**
Mentre la scuola si trovava nel caos, aggravato dalla 42-esima ondata del famigerato Covid-19, in sostituzione di un responsabile dell'*inserimento* temporaneamente in quarantena fu assunto con contratto *interinale* un tale *Hash*, forse sbarcato a Lampedusa il giorno prima ed in fuga dal conflitto del Nagorno-Karabakh (ma le fonti sono discordanti).

Egli propose un nuovo sistema che avrebbe garantito minori tempi di attesa e maggior soddisfazione del personale.

Propose di suddividere i posti disponibili in aree (per comodità e per facilitare la comprensione del suo metodo, o forse per scarsa dimestichezza con la lingua locale, usava il termine improprio di *aule*) e di adottare un criterio semplice per assegnare gli studenti ad una qualunque di esse, ad esempio gli studenti potevano essere assegnati ad un'area in base ad una lettera del loro nome (o cognome).

In questo modo la *ricerca* e la *rimozione* di uno studente potevano essere limitate all'area cui esso risultava associato, riducendo così significativamente le lunghe attese necessarie, ed anche l'*inserimento* non risentiva di gravi penalizzazioni dato che l'associazione studente-area era semplice da determinare.

Dopo un breve periodo di sperimentazione si notò che in alcuni casi le ~~aule~~ aree potevano risultare sovraffollate, ma si risolse il problema destinando gli studenti in eccesso ad alcune ~~aule~~ aree di riserva altrimenti inutilizzate ~~(i bagni)~~.

Fu così che la proposta del sig. **Hash** venne adottata all'unanimità e tale fu l'entusiasmo che si propose di nominare **professore emerito omnidocente** il sig. **Hash** e modificare la denominazione della scuola in **HASH Primo Levi**.


### Ma la storia continua...

Purtroppo poco dopo il **Comitato Scolastico Galattico** ordinò di predisporre ogni ora l'elenco alfabetico degli studenti presenti, per informare via web i genitori della situazione dei loro figli e l'elaborazione di tale elenco alfabetico risultava richiedere ben più dei 60 minuti a disposizione. Il ~~sig.~~ prof. **Hash** si era nel frattempo reso irreperibile: alcuni sostenevano fosse ritornato nel Nagorno, altri dicevano nel Karabakh ma tutti concordavano sul fatto che si fosse dedicato alla sua passione: gli *alberi*!!!

#### Note tecniche per *equals*

Il metodo *equals* deve rappresentare una *relazione* di **equivalenza**.

Quindi deve soddisfare le seguenti proprietà:
* **riflessivo**: per qualunque *x* non ``null``, ``x.equals(x)`` deve restituire ``true``
* **simmetrico**: per qualunque *x* e *y* non ``null``, ``x.equals(y)`` deve restituire lo stesso valore di ``y.equals(x)``
* **transitivo**: per qualunque *x*, *y* e *z* non ``null``, se ``x.equals(y)`` restituisce ``true`` e ``y.equals(z)`` restituisce ``true`` allora ``x.equals(z)`` deve restituire ``true``
* **consistente**: per qualunque *x* e *y* non ``null``, ripetute chiamate di ``x.equals(y)`` devono restituire sempre lo stesso valore se le informazioni usate per determinarlo non sono cambiate
* per qualunque *x* non ``null``, ``x.equals(null)`` deve restituire ``false``.

L'implementazione del metodo nella classe *Object*:
```Java
public boolean equals(Object o) { return this == o; }
```
è ovviamente corretta ma raramente è quello che vogliamo; ad esempio, per la classe String, usando l'implementazione di Object, il seguente codice restituirebbe ``false``:
```Java
new String("stringa").equals(new String("stringa"));
```
contrariamente a quello che ci si aspetta dal contenuto delle *due* stringhe.

#### Note tecniche per *hashCode*

Il metodo *hashCode* deve essere:

* **consistente**, per qualunque *x* non ``null``, ripetute chiamate di ``x.hashCode()`` devono restituire sempre lo stesso valore se le informazioni usate per calcolarlo non sono cambiate;
* **coerente**: per qualunque *x* e *y* non ``null``, se ``x.equals(y)`` restituisce ``true``, ``x.hashCode()`` e ``y.hashCode()`` devono restituire lo stesso valore.

>Da evitare: un possibile, funzionante ma **inefficiente** hashCode:
```Java
@Override public int hashCode() { return 0x42; }
```

Una possibilità abbastanza comoda, a parte quella di far generare il codice all'IDE, è quella di usare il metodo *Objects.hash*; supponendo che il nostro *equals* consideri i tre attributi x, y e z:
```Java
@Override public int hashCode() { return Objects.hash(x, y, z); }
```
