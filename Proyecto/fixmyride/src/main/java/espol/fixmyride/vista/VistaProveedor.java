package espol.fixmyride.vista;
import espol.fixmyride.controlador.*;
import java.util.Scanner;

public class VistaProveedor extends VistaPersona {
    // Constructor
    public VistaProveedor(ControladorPersona controlador) { super(controlador); }
    // Método para agregar un nuevo proveedor
    public void agregarProveedor(Scanner scanner) {
        MensajeUsuario.caja("INGRESAR DATOS");
        System.out.print("Cédula o RUC del proveedor: ");
        String id = scanner.nextLine();
        System.out.print("Nombre del proveedor: ");
        String nombre = scanner.nextLine();
        System.out.print("Teléfono del proveedor: ");
        String telefono = scanner.nextLine();
        System.out.print("Descripción del proveedor: ");
        String descripcion = scanner.nextLine();
        ControladorProveedor controladorProveedor = (ControladorProveedor) getControlador();
        System.out.println(controladorProveedor.agregarProveedor(id, nombre, telefono, descripcion));
    }
}