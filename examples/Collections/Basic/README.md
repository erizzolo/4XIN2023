# Esempio di base

L'esempio contiene diversi package in cui il metodo di test è identico, ma la classe Punto (sempre immutabile) è definita in diversi modi:
| package                    | @Override        | implements |
| -------------------------- | ---------------- | ---------- |
| puntowithoutequals         | nulla            | nulla      |
| puntowithequals            | equals           | nulla      |
| puntowithequalsandhashcode | equals, hashCode | nulla      |
| puntocomparable            | equals, hashCode | Comparable |

Lo scopo è di mostrare come le varie implementazioni delle Collections necessitino, per funzionare secondo quanti ci si aspetta, della corretta definizione di alcuni metodi.
