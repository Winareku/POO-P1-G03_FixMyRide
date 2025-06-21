package espol.fixmyride.vista;
import espol.fixmyride.controlador.*;
import java.util.Scanner;

public class VistaProveedor extends VistaPersona {
    // Constructor
    public VistaProveedor(ControladorPersona controlador) { super(controlador); }
    // Método para agregar un nuevo proveedor
    public void agregarProveedor(Scanner scanner) {
        Vista.caja("INGRESAR DATOS");
        String id = Vista.obtenerString(scanner, "Cédula o RUC del proveedor: ");
        String nombre = Vista.obtenerString(scanner, "Nombre del proveedor: ");
        String telefono = Vista.obtenerString(scanner, "Teléfono del proveedor: ");
        String descripcion = Vista.obtenerString(scanner, "Descripción del proveedor: ");
        ControladorProveedor controladorProveedor = (ControladorProveedor) getControlador();
        System.out.println(controladorProveedor.agregarProveedor(id, nombre, telefono, descripcion));
    }
}