package espol.fixmyride.vista;

// Importaciones
import espol.fixmyride.controlador.*;
import espol.fixmyride.modelo.*;
import java.util.Scanner;

// Clase VistaCliente
public class VistaCliente extends VistaPersona {
    // Atributos
    private ControladorCliente controladorCliente = (ControladorCliente) getControlador();

    // Constructor
    public VistaCliente(ControladorPersona controlador) { super(controlador); }
    
    // Método para agregar un nuevo cliente
    public void agregarPersona(Scanner scanner) {
        Vista.caja("INGRESAR DATOS");
        String id = Vista.obtenerString(scanner, "Cédula o RUC del cliente: ");
        String nombre = Vista.obtenerString(scanner, "Nombre del cliente: ");
        String telefono = Vista.obtenerString(scanner, "Teléfono del cliente: ");
        String direccion = Vista.obtenerString(scanner, "Dirección del cliente: ");
        // Validación del tipo de cliente
        TipoCliente tipoCliente = Vista.obtenerTipoCliente(scanner);
        if (tipoCliente==TipoCliente.EMPRESARIAL){
            String cod= Vista.obtenerString(scanner, "Ingrese código de la empresa: ");
            System.out.println(controladorCliente.agregarClienteEmpresarial(id, nombre, telefono, direccion, tipoCliente, cod));
        }
        System.out.println(controladorCliente.agregarClientePersonal(id, nombre, telefono, direccion, tipoCliente));
    }
}