import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.Scanner;

public class App {

    private static final String shell = getShell();

    public static void main(String[] args) throws Exception {
        System.out.println("System + Runtime demo!!");
        Properties props = System.getProperties();
        props.setProperty("javafx.animation.framerate", "1");
        passwordDemo();
        sysinfo();
        processDemo();
    }

    private static void passwordDemo() {
        Console console;
        if ((console = System.console()) != null) {
            char[] password = console.readPassword("%s", "Enter your password:");
            System.out.println("Your password: " + new String(password));
            for (int i = 0; i < password.length; i++) {
                password[i] = ' ';
            }
        }
    }

    private static String getShell() {
        String osname = System.getProperty("os.name");
        if (osname.startsWith("Windows"))
            return "cmd.exe /c ";
        // assume linux
        return "sh -c ";
    }

    private static void processDemo() {
        String cmd;
        Scanner kbd = new Scanner(System.in);
        Runtime runtime = Runtime.getRuntime();
        System.out.println("Enter 'exit' to leave the shell");
        do {
            System.out.print("> ");
            cmd = kbd.nextLine();
            try {
                Process process = runtime.exec(shell + cmd);
                InputStream err = process.getErrorStream();
                InputStream in = process.getInputStream();
                // OutputStream out = process.getOutputStream();
                while (process.isAlive()) {
                    while (in.available() > 0) {
                        System.out.write(in.read());
                    }
                    while (err.available() > 0) {
                        System.out.write(err.read());
                    }
                }
                System.out.println("Executed " + cmd);
                procInfo(process);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (!cmd.equals("exit"));
        kbd.close();
    }

    private static void procInfo(Process process) {
        System.out.println(process);
        // System.out.println("pid: " + process.pid());
        System.out.println("info: " + process.info());
    }

    private static void sysinfo() {
        Runtime runtime = Runtime.getRuntime();
        System.out.println(runtime);
        System.out.println("availableProcessors(): " + runtime.availableProcessors());
        System.out.println("Memory: free " + runtime.freeMemory() + ", max " + runtime.maxMemory() + ", total "
                + runtime.totalMemory() + ".");
        System.out.println("System properties");
        Properties properties = System.getProperties();
        for (String name : properties.stringPropertyNames()) {
            System.out.println(name + ": " + properties.getProperty(name));
        }
    }
}
