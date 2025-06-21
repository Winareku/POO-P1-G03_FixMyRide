package espol.fixmyride.vista;
import espol.fixmyride.controlador.*;
import java.util.Scanner;

public class VistaTecnico extends VistaPersona {
    // Constructor
    public VistaTecnico(ControladorPersona controlador) { super(controlador); }
    // Método para agregar un nuevo técnico
    public void agregarTecnico(Scanner scanner) {
        MensajeUsuario.caja("INGRESAR DATOS");
        System.out.print("Cédula o RUC del técnico: ");
        String id = scanner.nextLine();
        System.out.print("Nombre del técnico: ");
        String nombre = scanner.nextLine();
        System.out.print("Teléfono del técnico: ");
        String telefono = scanner.nextLine();
        System.out.print("Especialidad del técnico: ");
        String especialidad = scanner.nextLine();
        ControladorTecnico controladorTecnico = (ControladorTecnico) getControlador();
        System.out.println(controladorTecnico.agregarTecnico(id, nombre, telefono, especialidad));
    }
}