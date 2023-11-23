/**
 *
 * @author rizzolo.emanuele
 */
public enum Direzione {
    TOP(-1, 0), RIGHT(0, +1), LEFT(0, -1), BOTTOM(+1, 0);
    private final int deltaRiga;
    private final int deltaColonna;

    private Direzione(int deltaRiga, int deltaColonna) {
        this.deltaRiga = deltaRiga;
        this.deltaColonna = deltaColonna;
    }

    public int getDeltaRiga() {
        return deltaRiga;
    }

    public int getDeltaColonna() {
        return deltaColonna;
    }
    
    
}
