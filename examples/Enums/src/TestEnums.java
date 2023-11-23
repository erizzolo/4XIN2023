import java.time.DayOfWeek;
import java.time.Month;

public class TestEnums {
    public static void main(String[] args) throws Exception {
        System.out.println("Using enums: java.time.DayOfWeek");
        for (DayOfWeek day : DayOfWeek.values()) {
            System.out.println(day.ordinal() + " " + day + " " + day.name());
        }
        DayOfWeek sunday = DayOfWeek.SUNDAY;
        System.out.println(sunday + " comes before " + sunday.plus(1) + " and after " + sunday.minus(1));
        System.out.println("Using enums: java.time.Month");
        System.out.println(Month.NOVEMBER.length(true) + " days have " + Month.NOVEMBER + ",");
        System.out.println(Month.APRIL + ", " + Month.JUNE + " and " + Month.SEPTEMBER + ".");
        System.out.println(Month.FEBRUARY + " has " + Month.FEBRUARY.minLength() + " alone,");
        System.out.println("and all the rest have " + Month.DECEMBER.length(false) + "!");
        System.out.println("Defining and using enums: Planet");
        Planet[] planets = Planet.values();
        System.out.print("There are " + planets.length + " planets in our system ");
        System.out.println("(we are living on planet " + Planet.valueOf("EARTH") + ").");
        System.out.print("The closest planet to ours is " + Planet.EARTH.getClosestPlanet());
        System.out.println(", which is " + Planet.EARTH.getDistanceFrom(Planet.VENUS) + " km away.");
        for (int p = 0; p < planets.length; ++p) {
            System.out.println(planets[p] + ": " + planets[p].toLongString());
        }
        System.out.println("Defining and using enums: Seme");
        System.out.println("Here are the suits in:");
        for (String language : new String[] { "english", "italian", "russian" }) {
            System.out.print(language);
            char separator = ':';
            for (Seme s : Seme.values()) {
                System.out.print(separator + " " + s.symbol + " = " + s.name(language.substring(0, 2)));
                separator = ',';
            }
            System.out.println("");
        }

    }
}
