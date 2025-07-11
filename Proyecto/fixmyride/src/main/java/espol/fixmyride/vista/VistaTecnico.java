package espol.fixmyride.vista;

// Importaciones
import espol.fixmyride.controlador.*;
import java.util.Scanner;

// Clase VistaTecnico
public class VistaTecnico extends VistaPersona {
    // Atributos
    ControladorTecnico controladorTecnico = (ControladorTecnico) getControlador();

    // Constructor
    public VistaTecnico(ControladorPersona controlador) { super(controlador); }
    
    // Método para agregar un nuevo técnico
    public void agregarPersona(Scanner scanner) {
        Vista.caja("INGRESAR DATOS");
        String id = Vista.obtenerString(scanner, "Cédula o RUC del técnico: ");
        String nombre = Vista.obtenerString(scanner, "Nombre del técnico: ");
        String telefono = Vista.obtenerString(scanner, "Teléfono del técnico: ");
        String especialidad = Vista.obtenerString(scanner, "Especialidad del técnico: ");
        System.out.println(controladorTecnico.agregarTecnico(id, nombre, telefono, especialidad));
    }
}