import java.io.Serializable;

public class Planet implements Serializable {

    private final String name; // planet name (max 10 chars)
    private final double mass; // mass in kg
    private final double distance; // distance from Sun in km
    private final boolean inhabited; // inhabited (usually false)
    private final int numSatellites; // number of satellites

    public Planet(String name, double mass, double distance, boolean inhabited, int numSatellites) {
        this.name = name;
        this.mass = mass;
        this.distance = distance;
        this.inhabited = inhabited;
        this.numSatellites = numSatellites;
    }

    public String getName() {
        return name;
    }

    public double getMass() {
        return mass;
    }

    public double getDistance() {
        return distance;
    }

    public boolean isInhabited() {
        return inhabited;
    }

    public int getNumSatellites() {
        return numSatellites;
    }

    @Override
    public String toString() {
        return "Planet [distance=" + distance + ", inhabited=" + inhabited + ", mass=" + mass + ", name=" + name
                + ", numSatellites=" + numSatellites + "]";
    }

}
