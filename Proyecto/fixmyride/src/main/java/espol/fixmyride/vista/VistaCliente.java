package espol.fixmyride.vista;
import espol.fixmyride.controlador.*;
import espol.fixmyride.modelo.*;
import java.util.Scanner;

public class VistaCliente extends VistaPersona {
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
        TipoCliente tipoCliente = null;
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
        ControladorCliente controladorCliente = (ControladorCliente) getControlador();
        System.out.println(controladorCliente.agregarCliente(id, nombre, telefono, direccion, tipoCliente));
    }
}