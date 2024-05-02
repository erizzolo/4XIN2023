## Stack

### **ArrayStack*
Semplici implementazioni di uno **stack** dinamico basato su un array.

La prima non è sincronizzata e presenta problemi quando lo stesso stack è usato concorrentemente da più threads.

La seconda è sincronizzata e lo stesso stack può essere usato concorrentemente da più threads senza problemi.

### **NodeStack*
Semplici implementazioni di uno **stack** dinamico basato su *Node*(s).

La prima non è sincronizzata e presenta problemi quando lo stesso stack è usato concorrentemente da più threads.

La seconda (dovrebbe essere) sincronizzata e lo stesso stack può essere usato concorrentemente da più threads senza problemi.
