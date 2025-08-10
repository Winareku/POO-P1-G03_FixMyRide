package poo.espol.fixmyride.extra;

// Importaciones
import android.os.Build;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import poo.espol.fixmyride.model.*;
import poo.espol.fixmyride.controller.*;

// Clase Tools
public class Tools {
    // Atributos
    private static final int ANCHO_CAJA = 110;

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
        return scanner.nextLine();
    }

    public static String obtenerString(Scanner scanner, String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
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

    public static void cajaIzquierda(String texto) {
        final int anchoInterno = ANCHO_CAJA - 2;
        texto = texto.toUpperCase();
        int espaciosDerecha = anchoInterno - texto.length();
        String lineaSuperior = "╔" + "═".repeat(anchoInterno) + "╗";
        String lineaContenido = "║" + texto + " ".repeat(espaciosDerecha) + "║";
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
        int espaciosDerecha = anchoInterno - texto.length();
        String lineaContenido = "║" + texto + " ".repeat(espaciosDerecha) + "║";
        System.out.println(lineaContenido);
    }

    // Métodos para validaciones y solicitudes
    public static boolean fechaInvalida(String texto) {
        try {
            LocalDate.parse(texto);
            return false;
        } catch (Exception e) { return true; }
    }

    public static String solicitarFecha(Scanner scanner) {
        String fechaString = null;
        do {
            fechaString = obtenerString(scanner,"Fecha del servicio (YYYY-MM-DD): ");
            if (fechaInvalida(fechaString)) opcionNoValida();
        } while (fechaInvalida(fechaString));
        return fechaString;
    }

    public static boolean esMesValido(int mes) {
        return mes >= 1 && mes <= 12;
    }

    public static String verificarPersonaPorID(Scanner scanner, ArrayList<Persona> listaPersona, String tipoPersona) {
        String idPersona;
        Persona persona;
        while (true) {
            idPersona = obtenerString(scanner,"Ingrese el ID del " + tipoPersona + ": ");
            persona = ControladorPersona.buscarPersonaPorId(idPersona, listaPersona);
            if (persona!=null) return idPersona;
            opcionNoValida();
        }
    }

    public static String verificarEmpresaPorCodigo(Scanner scanner, ArrayList<Empresa> listaEmpresa) {
        String codigoEmpresa;
        Empresa empresa;
        while (true) {
            codigoEmpresa = obtenerString(scanner,"Escriba el código de la Empresa que desea consultar: ");
            empresa = ControladorEmpresa.obtenerEmpresaPorCodigo(codigoEmpresa, listaEmpresa);
            if (empresa!=null) return codigoEmpresa;
            opcionNoValida();
        }
    }

    public static TipoCliente obtenerTipoCliente(Scanner scanner){
        TipoCliente tipoCliente;
        while (true) {
            System.out.println("Seleccione el tipo de cliente");
            System.out.println("  1. Personal");
            System.out.println("  2. Empresarial");
            int entrada = obtenerInt(scanner,"Ingrese su opción: ");
            switch (entrada) {
                case 1:
                    tipoCliente = TipoCliente.PERSONAL;
                    break;
                case 2:
                    tipoCliente = TipoCliente.EMPRESARIAL;
                    break;
                default:
                    opcionNoValida();
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
            int entrada = obtenerInt(scanner);
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
                    opcionNoValida();
                    continue;
            }
            break;
        }
        return tipoVehiculo;
    }

    public static TipoCliente obtenerTipoCliente(String entrada){
        TipoCliente tipoCliente = null;
        switch (entrada) {
            case "Personal":
                tipoCliente = TipoCliente.PERSONAL;
                break;
            case "Empresarial":
                tipoCliente = TipoCliente.EMPRESARIAL;
                break;
            default:
                opcionNoValida();
        }
        return tipoCliente;
    }

    public static String rellenar(String texto, int ancho) {
        while (texto.length() < ancho) { texto += " "; }
        return texto;
    }
    public static String stringTabla(String s1, String s2){
        String linea =  rellenar(s1, 30) + rellenar(s2, 20);
        return linea;
    }
    public static String stringTabla(String s1, String s2, String s3, String s4, String s5, String s6){
        String linea =  rellenar(s1, 15) +
                rellenar(s2, 15) +
                rellenar(s3, 15) +
                rellenar(s4, 30) +
                rellenar(s5, 15) +
                rellenar(s6, 10);
        return linea;
    }

    public static int convertirMesNumero(Scanner scanner){
        while (true) {
            System.out.print("Escriba el mes que desea consultar: ");
            String nombreMes = scanner.nextLine().toLowerCase();
            switch (nombreMes) {
                case "enero": return 1;
                case "febrero": return 2;
                case "marzo": return 3;
                case "abril": return 4;
                case "mayo": return 5;
                case "junio": return 6;
                case "julio": return 7;
                case "agosto": return 8;
                case "septiembre": return 9;
                case "octubre": return 10;
                case "noviembre": return 11;
                case "diciembre": return 12;
                default: opcionNoValida();
            }
        }
    }

    public static String convertirNumeroMes(int numeroMes){
        switch(numeroMes){
            case 1: return "Enero";
            case 2: return "Febrero";
            case 3: return "Marzo";
            case 4: return "Abril";
            case 5: return "Mayo";
            case 6: return "Junio";
            case 7: return "Julio";
            case 8: return "Agosto";
            case 9: return "Septiembre";
            case 10: return "Octubre";
            case 11: return "Noviembre";
            case 12: return "Diciembre";
            default: return null;
        }
    }
}
