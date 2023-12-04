## La classe *Collections*

Contiene metodi di utilità ed implementazioni efficienti di algoritmi sulle *Collections*.

Ad esempio:

ricerche dicotomiche su List:
* ``static <T> int binarySearch(List<? extends Comparable<? super T>> list, T key)``
* ``static <T> int binarySearch(List<? extends T> list, T key, Comparator<? super T> c)``

ricerche elementi minimo e massimo su Collection:
* ``static <T extends Object & Comparable<? super T>> T max(Collection<? extends T> coll)``
* ``static <T> T max(Collection<? extends T> coll, Comparator<? super T> comp)``
* ``static <T extends Object & Comparable<? super T>> T min(Collection<? extends T> coll)``
* ``static <T> T min(Collection<? extends T> coll, Comparator<? super T> comp)``

rovesciamenti e mescolamenti (su Lis9):
* ``public static void reverse(List<?> list)``
* ``public static void shuffle(List<?> list)``

ed altro ancora...
