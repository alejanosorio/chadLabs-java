package chadLabs.utils;
import java.util.Scanner;
public class InputUtils {
    private static final Scanner SC = new Scanner(System.in);

    public static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String line = SC.nextLine();
            try {
                return Integer.parseInt(line.trim());
            } catch (Exception e) {
                System.out.println("Entrada inválida. Ingrese un número válido.");
            }
        }
    }

    public static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return SC.nextLine().trim();
    }

    public static boolean leerBoolean(String mensaje) {
        while (true) {
            System.out.print(mensaje + " (s/n): ");
            String l = SC.nextLine().trim().toLowerCase();
            if (l.equals("s") || l.equals("si") || l.equals("y") || l.equals("yes")) return true;
            if (l.equals("n") || l.equals("no")) return false;
            System.out.println("Respuesta inválida. Ingrese 's' o 'n'.");
        }
    }
}
