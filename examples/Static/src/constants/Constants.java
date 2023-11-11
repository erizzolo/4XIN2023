package constants;

/**
 * Classe che usa:
 * <ul>
 * <li>attributi statici (final) per varie costanti
 * <li>metodi static per funzioni di utilità (vedi la classe java.lang.Math)
 * </ul>
 * In questo modo la costante è memorizzata una volta sola.<br>
 * Essendo costante (final) può essere lasciata public.
 */
public class Constants {

    public static final double SPEED_OF_LIGHT = 299792458.0; // m/s

    public static final double GOLDEN_RATIO = (1 + Math.sqrt(5)) / 2;

    /**
     * Compute the error function erf(x)
     * https://en.wikipedia.org/wiki/Error_function#Numerical_approximations
     * 
     * 
     * where p = 0.47047, a1 = 0.3480242, a2 = −0.0958798, a3 = 0.7478556
     * 
     * @param x
     * @return erf(x)
     */
    public static final double erf(double x) {
        if (x < 0)
            return -erf(-x); // odd function
        double t = 1 / (1 + 0.47047 * x);
        return 1 - ((0.7478556 * t) - 0.0958798 * t + 0.3480242) * t * Math.exp(-x * x);
    }

}
