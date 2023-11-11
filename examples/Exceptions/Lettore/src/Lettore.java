import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Lettore {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: Lettore <files...>");
        } else {
            for (int i = 0; i < args.length; ++i) {
                try {
                    printFile(args[i]);
                } catch (FileNotFoundException e) {
                    System.out.print("Cannot print file " + args[i]);
                    File f = new File(args[i]);
                    if (f.exists()) {
                        System.out.println(": probabile permessi insufficienti.");
                    } else {
                        System.out.println(": il file non esiste");
                    }
                }
            }
        }
    }

    private static void printFile(String name) throws FileNotFoundException {
        Scanner input = new Scanner(new File(name));
        while (input.hasNextLine()) {
            String line = input.nextLine();
            System.out.println(line);
        }
        input.close();
    }
}