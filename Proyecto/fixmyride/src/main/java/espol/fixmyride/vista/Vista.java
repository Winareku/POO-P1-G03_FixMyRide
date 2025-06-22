package espol.fixmyride.vista;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import espol.fixmyride.controlador.ControladorPersona;
import espol.fixmyride.modelo.Persona;
import espol.fixmyride.modelo.TipoCliente;
import espol.fixmyride.modelo.TipoVehiculo;

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
    public static double obtenerDouble(Scanner scanner) {
        double valor = scanner.nextDouble();
        scanner.nextLine(); // Limpiar buffer
        return valor;
    }
    public static double obtenerDouble(Scanner scanner, String mensaje) {
        System.out.print(mensaje);
        double valor = scanner.nextDouble();
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
        texto = texto.toUpperCase();
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
    public static boolean esFechaValida(String texto) {
        try {
            LocalDate.parse(texto);
            return true;
        } catch (Exception e) { return false; }
    }
    public static String solicitarFecha(Scanner scanner) {
        String fechaString = null;
        do {
            fechaString = Vista.obtenerString(scanner,"Fecha del servicio (YYYY-MM-DD): ");
            if (!Vista.esFechaValida(fechaString)) Vista.opcionNoValida();
        } while (!Vista.esFechaValida(fechaString));
        return fechaString;
    }
    public static String verificarPersonaPorID(Scanner scanner, ArrayList<Persona> listaPersona, String tipoPersona) {
        String idPersona;
        Persona persona;
        while (true) {
            idPersona = Vista.obtenerString(scanner,"Ingrese el ID del " + tipoPersona + ": ");
            persona = ControladorPersona.buscarPersonaPorId(idPersona, listaPersona);
            if (persona!=null) return idPersona;
            Vista.opcionNoValida();
        }
    }
    public static TipoCliente obtenerTipoCliente(Scanner scanner){
        TipoCliente tipoCliente;
        while (true) {
            System.out.println("Seleccione el tipo de cliente");
            System.out.println("  1. Personal");
            System.out.println("  2. Empresarial");
            int entrada = Vista.obtenerInt(scanner,"Ingrese su opción: ");
            switch (entrada) {
                case 1:
                    tipoCliente = TipoCliente.PERSONAL;
                    break;
                case 2:
                    tipoCliente = TipoCliente.EMPRESARIAL;
                    break;
                default:
                    Vista.opcionNoValida();
                    continue;
            }
            break;
        }
        return tipoCliente;
    }
    public static TipoVehiculo obtenerTipoVehiculo(Scanner scanner){
        TipoVehiculo tipoVehiculo;
        while (true) {
            System.out.println("Seleccione el tipo de vehículo:");
            System.out.println("  1. Automóvil");
            System.out.println("  2. Motocicleta");
            System.out.println("  3. Bus");
            System.out.print("Ingrese su opción: ");
            int entrada = Vista.obtenerInt(scanner);
            switch (entrada) {
                case 1:
                    tipoVehiculo = TipoVehiculo.AUTOMOVIL;
                    break;
                case 2:
                    tipoVehiculo = TipoVehiculo.MOTOCICLETA;
                    break;
                case 3:
                    tipoVehiculo = TipoVehiculo.BUS;
                    break;
                default:
                    Vista.opcionNoValida();
                    continue;
            }
            break;
        }
        return tipoVehiculo;
    }
}