package espol.fixmyride.vista;

// Importaciones
import espol.fixmyride.controlador.*;
import espol.fixmyride.modelo.*;
import java.util.Scanner;

// Clase VistaServicio
public class VistaServicio {
    // Atributo controlador
    private ControladorServicio controlador;

    // Constructor
    public VistaServicio(ControladorServicio controlador) { this.controlador = controlador; }

    // Método para mostrar el submenu de servicios
    public void mostrarSubmenu() {
        Vista.caja("SERVICIOS");
        for (Servicio servicio : controlador.getListaServicios()) { System.out.println(servicio); }
        Vista.abrirCaja("ADMINISTRAR SERVICIOS");
        Vista.lateralCaja("1. Agregar servicio");
        Vista.lateralCaja("2. Editar servicios");
        Vista.lateralCaja("3. Regresar al menú principal");
        Vista.cerrarCaja();
        System.out.print("Seleccione una opción: ");
    }

    // Método para agregar un nuevo servicio
    public void agregarServicio(Scanner scanner) {
        Vista.caja("INGRESAR DATOS DEL SERVICIO");
        String nombre = Vista.obtenerString(scanner,"Ingrese el nombre del servicio: ");
        double precio = Vista.obtenerDouble(scanner,"Ingrese el precio del servicio: ");
        System.out.println(controlador.agregarServicio(nombre, precio));
    }
    
    // Método para editar un servicio existente
    public void editarServicio(Scanner scanner) {
        Vista.caja("EDITAR SERVICIO");
        String codigo = Vista.obtenerString(scanner, "Ingrese el código del servicio: ");
        double precio = Vista.obtenerDouble(scanner,"Ingrese el nuevo precio del servicio: ");
        System.out.println(controlador.editarServicio(codigo, precio));
    }
}