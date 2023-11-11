public class QuadratoScalabile extends Quadrato implements Scalabile {

    private double scaleFactor = 1.0;

    @Override
    public void scala(double factor) {
        scaleFactor *= factor;
    }

    @Override
    public double getLato() {
        return super.getLato() * scaleFactor;
    }

    @Override
    public String toString() {
        return super.toString().replace('[', ',') + " scaleFactor=" + scaleFactor + "]";
    }
    
}
