package espol.fixmyride.vista;
import espol.fixmyride.controlador.*;
import espol.fixmyride.modelo.*;
import java.util.Scanner;

public class VistaCliente extends VistaPersona {
    // Constructor
    public VistaCliente(ControladorPersona controlador) { super(controlador); }
    // Método para agregar un nuevo cliente
    public void agregarPersona(Scanner scanner) {
        MensajeUsuario.caja("INGRESAR DATOS");
        System.out.print("Cédula o RUC del cliente: ");
        String id = scanner.nextLine();
        System.out.print("Nombre del cliente: ");
        String nombre = scanner.nextLine();
        System.out.print("Teléfono del cliente: ");
        String telefono = scanner.nextLine();
        System.out.print("Dirección del cliente: ");
        String direccion = scanner.nextLine();
        // Validación del tipo de cliente
        TipoCliente tipoCliente = null;
        while (true) {
            System.out.println("Seleccione el tipo de cliente");
            System.out.println("  1. Personal");
            System.out.println("  2. Empresarial");
            System.out.print("Ingrese su opción: ");
            int entrada = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            switch (entrada) {
                case 1:
                    tipoCliente = TipoCliente.PERSONAL;
                    break;
                case 2:
                    tipoCliente = TipoCliente.EMPRESARIAL;
                    break;
                default:
                    continue;
            }
            break;
        }
        ControladorCliente controladorCliente = (ControladorCliente) getControlador();
        System.out.println(controladorCliente.agregarCliente(id, nombre, telefono, direccion, tipoCliente));
    }
}