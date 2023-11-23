import java.awt.Color;
// import javafx.scene.paint.Color;

public enum Seme {

    CUORI('♥', Color.RED), QUADRI('♦', Color.RED), FIORI('♣', Color.BLACK), PICCHE('♠', Color.BLACK);

    public final char symbol;
    public final Color colour;

    private Seme(char symbol, Color colour) {
        this.symbol = symbol;
        this.colour = colour;
    }

    public String name(String lang) {
        switch (lang) {
            case "it":
                return itNames()[ordinal()];
            case "en":
                return enNames()[ordinal()];
            case "ru":
                return ruNames()[ordinal()];
            default:
                return null;
        }
    }

    private static String[] itNames() {
        return new String[] { "Cuori", "Quadri", "Fiori", "Picche" };
    }

    private static String[] enNames() {
        return new String[] { "Hearts", "Diamonds", "Clubs", "Spades" };
    }

    private static String[] ruNames() {
        return new String[] { "черви", "буби", "крести", "пики" };
    }
}
