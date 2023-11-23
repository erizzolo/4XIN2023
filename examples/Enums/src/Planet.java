public enum Planet {

    // first things first: objects of the "class"
    MARS("Mars", 6.4171E23, 206700000, false, 2), JUPITER("Jupiter", 1.8982E27, 740520000, false, 79),
    MERCURY("Mercury", 3.3011E23, 46001200, false, 0), VENUS("Venus", 4.8675E24, 107477000, false, 0),
    EARTH("Earth", 5.9726E24, 147098074, true, 1), SATURN("Saturn", 5.6834E26, 1352550000, false, Integer.MAX_VALUE),
    URANUS("Uranus", 8.6810E25, 2.742E9, false, 27), NEPTUNE("Neptune", 1.02413E26, 4.5E9, false, 14);

    // attributes
    public final String name;
    public final double mass;
    public final double distanceFromSun;
    public final boolean inhabited;
    public final int numSatellites;

    // constructor
    private Planet(String name, double mass, double distanceFromSun, boolean inhabited, int numSatellites) {
        this.name = name;
        this.mass = mass;
        this.distanceFromSun = distanceFromSun;
        this.inhabited = inhabited;
        this.numSatellites = numSatellites;
    }

    // detailed "toString"
    public String toLongString() {
        return name + "[" + mass + " kg, " + distanceFromSun + " km, " + (inhabited ? "" : "not ") + "inhabited, "
                + numSatellites + " satellites(s)]";
    }

    // a simple method (wrong, by the way)
    public double getDistanceFrom(Planet other) {
        return Math.abs(distanceFromSun - other.distanceFromSun);
    }

    // another method
    public Planet getClosestPlanet() {
        Planet result = null;
        for (Planet p : values()) {
            if (p != this) {
                if (result == null || getDistanceFrom(p) < getDistanceFrom(result)) {
                    result = p;
                }
            }
        }
        return result;
    }

}
