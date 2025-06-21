package espol.fixmyride.vista;
import java.util.Scanner;

public class VistaMenuPrincipal {

    // Atributos
    private Scanner scanner;

    // Constructor
    public VistaMenuPrincipal() { this.scanner = new Scanner(System.in); }

    // Método para obtener un entero del usuario
    public int obtenerInt(String mensaje) {
        System.out.print(mensaje);
        int valor = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        return valor;
    }

    // Método para mostrar el menú principal
    public int mostrarMenuPrincipal() {
        MensajeUsuario.separador();
        MensajeUsuario.caja("TECNICENTRO RP S.A.");
        MensajeUsuario.abrirCaja("OPCIONES");
        MensajeUsuario.lateralCaja("1. Administrar clientes");
        MensajeUsuario.lateralCaja("2. Administrar proveedores");
        MensajeUsuario.lateralCaja("3. Administrar técnicos");
        MensajeUsuario.lateralCaja("4. Administrar servicios");
        MensajeUsuario.lateralCaja("5. Generar orden de servicios");
        MensajeUsuario.lateralCaja("6. Registrar falta de insumos");
        MensajeUsuario.lateralCaja("7. Generar facturas a empresas");
        MensajeUsuario.lateralCaja("8. Reporte de ingresos por servicios");
        MensajeUsuario.lateralCaja("9. Reporte de atenciones por técnico");
        MensajeUsuario.lateralCaja("10. Salir");
        MensajeUsuario.cerrarCaja();
        System.out.print("Seleccione una opción: ");
        return obtenerInt("");
    }

    public void manejarSubmenuPersonas(VistaPersona vista, Scanner scanner, String clase) {
        int opcion = 0;
        do {
            VistaPersona.mostrarSubmenu(vista.getControlador().getLista(), clase);
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            
            switch (opcion) {
                case 1:
                    vista.agregarPersona(scanner);
                    break;
                case 2:
                    MensajeUsuario.regresandoMenuPrincipal();
                    break;
                default:
                    MensajeUsuario.opcionNoValida();
            }
        } while (opcion != 2);
    }

    public void manejarSubmenuServicios(VistaServicio vista, Scanner scanner) {
        int opcion = 0;
        do {
            vista.mostrarSubmenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            
            switch (opcion) {
                case 1:
                    vista.agregarServicio(scanner);
                    break;
                case 2:
                    vista.editarServicio(scanner);
                    break;
                case 3:
                    MensajeUsuario.regresandoMenuPrincipal();
                    break;
                default:
                    MensajeUsuario.opcionNoValida();
            }
        } while (opcion != 3);
    }

    public void manejarOrdenes(VistaOrdenServicio vista, Scanner scanner) {
        vista.generarOrden(scanner);
    }
}