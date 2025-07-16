package espol.fixmyride.vista;

// Importaciones
import java.util.Scanner;

// Clase VistaMenuPrincipal
public class VistaMenuPrincipal {
    // Atributos
    private Scanner scanner;

    // Constructor
    public VistaMenuPrincipal(Scanner scanner) { this.scanner = scanner; }

    // Método para mostrar el menú principal
    public int mostrarMenuPrincipal() {
        Vista.separador();
        Vista.caja("TECNICENTRO RP S.A.");
        Vista.abrirCaja("OPCIONES");
        Vista.lateralCaja("  1. Administrar clientes");
        Vista.lateralCaja("  2. Administrar proveedores");
        Vista.lateralCaja("  3. Administrar técnicos");
        Vista.lateralCaja("  4. Administrar servicios");
        Vista.lateralCaja("  5. Generar orden de servicios");
        Vista.lateralCaja("  6. Registrar falta de insumos");
        Vista.lateralCaja("  7. Generar facturas a empresas");
        Vista.lateralCaja("  8. Reporte de ingresos por servicios");
        Vista.lateralCaja("  9. Reporte de atenciones por técnico");
        Vista.lateralCaja(" 10. Salir");
        Vista.cerrarCaja();
        return Vista.obtenerInt(scanner,"Seleccione una opción: ");
    }

    // Métodos para manejar opciones con submenus de selección
    public void manejarSubmenuPersonas(VistaPersona vista, Scanner scanner, String clase) {
        int opcion = 0;
        do {
            VistaPersona.mostrarSubmenu(vista.getControlador().getLista(), clase);
            opcion = Vista.obtenerInt(scanner);
            switch (opcion) {
                case 1:
                    vista.agregarPersona(scanner);
                    break;
                case 2:
                    Vista.regresandoMenuPrincipal();
                    break;
                default:
                    Vista.opcionNoValida();
            }
        } while (opcion != 2);
    }

    public void manejarSubmenuServicios(VistaServicio vista, Scanner scanner) {
        int opcion = 0;
        do {
            vista.mostrarSubmenu();
            opcion = Vista.obtenerInt(scanner);
            switch (opcion) {
                case 1:
                    vista.agregarServicio(scanner);
                    break;
                case 2:
                    vista.editarServicio(scanner);
                    break;
                case 3:
                    Vista.regresandoMenuPrincipal();
                    break;
                default:
                    Vista.opcionNoValida();
            }
        } while (opcion != 3);
    }
    
    // Métodos para manejar opciones sin submenus de selección
    public void manejarOrdenes(VistaOrdenServicio vista, Scanner scanner) { vista.generarOrden(scanner); }
    public void manejarRFI(VistaRegistroFaltaInsumo vista, Scanner scanner) { vista.registrarFaltaInsumo(scanner); }
    public void manejarFactura(VistaFactura vista, Scanner scanner) {vista.generarFactura(scanner); }
    public void manejarRIS(VistaReporteIngresoServicio vista, Scanner scanner) {vista.metodo(scanner); }
    public void manejarRAT(VistaReporteAtencionTecnico vista, Scanner scanner) { vista.generarReporte(scanner); }
}
