package espol.fixmyride.vista;
import espol.fixmyride.controlador.*;
import espol.fixmyride.modelo.*;
import java.util.Scanner;
import java.util.ArrayList;

public class VistaPersona {

    // Atributo controlador
    private ControladorPersona controlador;

    // Constructor
    public VistaPersona(ControladorPersona controlador) { this.controlador = controlador; }

    // Getters y Setters
    public ControladorPersona getControlador() { return controlador; }
    public void setControlador(ControladorPersona controlador) { this.controlador = controlador; }

    // Método para mostrar el submenu de personas
    public static void mostrarSubmenu(ArrayList<Persona> listaPersonas, String clase) {
        MensajeUsuario.caja(clase.toUpperCase());
        for (Persona persona : listaPersonas) {
            System.out.println(persona);
        }
        MensajeUsuario.abrirCaja("ADMINISTRAR " + clase.toUpperCase());
        MensajeUsuario.lateralCaja("1. Agregar " + clase);
        MensajeUsuario.lateralCaja("2. Regresar al menú principal");
        MensajeUsuario.cerrarCaja();
        System.out.print("Seleccione una opción: ");
    }

    public void agregarPersona(Scanner scanner) {
        // Implementado en las subclases
    }
}