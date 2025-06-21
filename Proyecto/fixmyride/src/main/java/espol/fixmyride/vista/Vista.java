package espol.fixmyride.vista;
import java.util.Scanner;

public class Vista {
    // Atributos
    private static final int ANCHO_CAJA = 45;
    // Métodos para obtener Int o String
    public static int obtenerInt(Scanner scanner) {
        int valor = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        return valor;
    }
    public static int obtenerInt(Scanner scanner, String mensaje) {
        System.out.print(mensaje);
        int valor = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        return valor;
    }
    public static String obtenerString(Scanner scanner) {
        String entrada = scanner.nextLine();
        return entrada;
    }
    public static String obtenerString(Scanner scanner, String mensaje) {
        System.out.print(mensaje);
        String entrada = scanner.nextLine();
        return entrada;
    }
    // Métodos para mostrar mensajes
    public static void regresandoMenuPrincipal() { System.out.println("Regresando al menú principal..."); }
    public static void opcionNoValida() { System.out.println("Opción no válida. Por favor, intente nuevamente."); }
    // Métodos para encerrar en cajas (solo es estético)
    public static void separador() { System.out.println("=".repeat(ANCHO_CAJA)+"\n"); }
    public static void caja(String texto){
        final int anchoInterno = ANCHO_CAJA - 2;
        int espaciosTotales = anchoInterno - texto.length();
        int espaciosIzquierda = espaciosTotales / 2;
        int espaciosDerecha = espaciosTotales - espaciosIzquierda;
        String lineaSuperior = "╔" + "═".repeat(anchoInterno) + "╗";
        String lineaContenido = "║" + " ".repeat(espaciosIzquierda) + texto + " ".repeat(espaciosDerecha) + "║";
        String lineaInferior = "╚" + "═".repeat(anchoInterno) + "╝";
        System.out.println();
        System.out.println(lineaSuperior);
        System.out.println(lineaContenido);
        System.out.println(lineaInferior);
    }
    public static void abrirCaja(String texto){
        final int anchoInterno = ANCHO_CAJA - 2;
        int espaciosTotales = anchoInterno - texto.length();
        int espaciosIzquierda = espaciosTotales / 2;
        int espaciosDerecha = espaciosTotales - espaciosIzquierda;
        String lineaSuperior = "╔" + "═".repeat(anchoInterno) + "╗";
        String lineaContenido = "║" + " ".repeat(espaciosIzquierda) + texto + " ".repeat(espaciosDerecha) + "║";
        System.out.println(lineaSuperior);
        System.out.println(lineaContenido);
    }
    public static void cerrarCaja() {
        final int anchoInterno = ANCHO_CAJA - 2;
        String lineaInferior = "╚" + "═".repeat(anchoInterno) + "╝";
        System.out.println(lineaInferior);
    }
    public static void lateralCaja(String texto) {
        final int anchoInterno = ANCHO_CAJA - 2;
        int espaciosTotales = anchoInterno - texto.length();
        int espaciosDerecha = espaciosTotales;
        String lineaContenido = "║" + texto + " ".repeat(espaciosDerecha) + "║";
        System.out.println(lineaContenido);
    }
}