import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test {
    public static void main(String[] args) {
        Punto.test();   // Basic tests of Punto
        PuntoMobile.test();   // Basic tests of PuntoMobile
        showClass(Punto.class);
        showClass(PuntoMobile.class);
    }

    private static void showClass(Class c) {
        System.out.println("Showing class " + c.getName());
        System.out.println("Constructors:");
        for (Constructor constructor : c.getDeclaredConstructors()) {
            System.out.println(constructor);
        }
        System.out.println("Public fields (includes inherited):");
        for (Field field : c.getFields()) {
            System.out.println(field);
        }
        System.out.println("Declared fields: (excludes inherited)");
        for (Field field : c.getDeclaredFields()) {
            System.out.println(field);
        }
        System.out.println("Public Methods (includes inherited):");
        for (Method method : c.getMethods()) {
            System.out.println(method);
        }
        System.out.println("Declared Methods (excludes inherited):");
        for (Method method : c.getDeclaredMethods()) {
            System.out.println(method);
        }
    }
}
