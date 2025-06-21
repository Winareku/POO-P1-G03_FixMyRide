package espol.fixmyride.vista;
import espol.fixmyride.controlador.*;
import espol.fixmyride.modelo.*;
import java.util.Scanner;

public class VistaServicio {
    // Atributo controlador
    private ControladorServicio controlador;
    // Constructor
    public VistaServicio(ControladorServicio controlador) { this.controlador = controlador; }
    // Método para mostrar el submenu de servicios
    public void mostrarSubmenu() {
        MensajeUsuario.caja("SERVICIOS");
        for (Servicio servicio : controlador.getListaServicios()) {
            System.out.println(servicio);
        }
        MensajeUsuario.abrirCaja("ADMINISTRAR SERVICIOS");
        MensajeUsuario.lateralCaja("1. Agregar servicio");
        MensajeUsuario.lateralCaja("2. Editar servicios");
        MensajeUsuario.lateralCaja("3. Regresar al menú principal");
        MensajeUsuario.cerrarCaja();
        System.out.print("Seleccione una opción: ");
    }
    // Método para agregar un nuevo servicio
    public void agregarServicio(Scanner scanner) {
        MensajeUsuario.caja("INGRESAR DATOS DEL SERVICIO");
        System.out.println("Ingrese el nombre del servicio: ");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese el precio del servicio: ");
        double precio = scanner.nextDouble();
        scanner.nextLine(); // Limpiar buffer
        System.out.println(controlador.agregarServicio(nombre, precio));
    }
    // Método para editar un servicio existente
    public void editarServicio(Scanner scanner) {
        MensajeUsuario.caja("EDITAR SERVICIO");
        System.out.println("Ingrese el código del servicio: ");
        String codigo = scanner.nextLine();
        System.out.println("Ingrese el nuevo precio del servicio: ");
        double precio = scanner.nextDouble();
        scanner.nextLine(); // Limpiar buffer
        System.out.println(controlador.editarServicio(codigo, precio));
    }
}