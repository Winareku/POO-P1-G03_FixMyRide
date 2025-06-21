package espol.fixmyride;
import espol.fixmyride.controlador.*;
import espol.fixmyride.modelo.*;
import espol.fixmyride.vista.*;
import java.util.Scanner;

public class AppMain {
    public static void main(String[] args) {
        // Atributos
        Scanner scanner = new Scanner(System.in);
        int opcion;
        // Inicializar controladores
        ControladorCliente controladorCliente = new ControladorCliente();
        ControladorProveedor controladorProveedor = new ControladorProveedor();
        ControladorTecnico controladorTecnico = new ControladorTecnico();
        ControladorServicio controladorServicio = new ControladorServicio();
        ControladorOrdenServicio controladorOrdenServicio = new ControladorOrdenServicio();
        // Inicializar vistas
        VistaMenuPrincipal vistaMenuPrincipal = new VistaMenuPrincipal();
        VistaCliente vistaCliente = new VistaCliente(controladorCliente);
        VistaProveedor vistaProveedor = new VistaProveedor(controladorProveedor);
        VistaTecnico vistaTecnico = new VistaTecnico(controladorTecnico);
        VistaServicio vistaServicio = new VistaServicio(controladorServicio);
        VistaOrdenServicio vistaOrdenServicio = new VistaOrdenServicio(controladorOrdenServicio, controladorServicio, controladorCliente);
        // Inicializar la aplicación
        inicializarApp(controladorCliente, controladorProveedor, controladorTecnico, controladorServicio);
        do {
            // Llamada a un método que devuelve la opción seleccionada por el usuario
            opcion = vistaMenuPrincipal.mostrarMenuPrincipal();
            switch (opcion) {
                case 1:
                    vistaMenuPrincipal.manejarSubmenuPersonas(vistaCliente, scanner, "clientes");
                    break;
                case 2:
                    vistaMenuPrincipal.manejarSubmenuPersonas(vistaProveedor, scanner, "proveedores");
                    break;
                case 3:
                    vistaMenuPrincipal.manejarSubmenuPersonas(vistaTecnico, scanner, "técnicos");
                    break;
                case 4:
                    vistaMenuPrincipal.manejarSubmenuServicios(vistaServicio, scanner);
                    break;
                case 5:
                    vistaMenuPrincipal.manejarOrdenes(vistaOrdenServicio, scanner);
                    break;
                case 10:
                    System.out.println("Saliendo de la aplicación...");
                    break;
                default:
                    Vista.opcionNoValida();
            }
        } while (opcion != 10);
        scanner.close();
    }
    // Inicialización de objetos
    private static void inicializarApp( ControladorCliente controladorCliente, ControladorProveedor controladorProveedor, ControladorTecnico controladorTecnico, ControladorServicio controladorServicio) {
        // Crear técnicos
        controladorTecnico.agregarTecnico("0912345678", "Marco Herrera", "0991122334", "Mecánica automotriz general");
        controladorTecnico.agregarTecnico("0923456789", "Paula Lema", "0987654321", "Sistemas eléctricos de vehículos");
        // Crear clientes
        controladorCliente.agregarCliente("0911111111", "Andrés Ruiz", "0999988776", "Av. 10 de Agosto y Rumichaca", TipoCliente.EMPRESARIAL);
        controladorCliente.agregarCliente("0922222222", "Sofía Benítez", "0988877665", "Calle Guayas 123", TipoCliente.PERSONAL);
        controladorCliente.agregarCliente("0933333333", "Esteban Vargas", "0977766554", "Av. América N35-110", TipoCliente.EMPRESARIAL);
        controladorCliente.agregarCliente("0944444444", "Carla Mendoza", "0966655443", "Vía Daule Km 12", TipoCliente.PERSONAL);
        // Crear proveedores
        controladorProveedor.agregarProveedor("0955555555", "Ricardo Paredes", "0955544332", "Distribuidor de repuestos automotrices");
        controladorProveedor.agregarProveedor("0966666666", "Diana Palacios", "0944433221", "Proveedor de herramientas para talleres mecánicos");
        // Crear servicios
        controladorServicio.agregarServicio("Cambio de aceite", 30.0);
        controladorServicio.agregarServicio("Revisión de frenos", 50.0);
        controladorServicio.agregarServicio("Alineación y balanceo", 40.0);
        controladorServicio.agregarServicio("Cambio de neumáticos", 100.0);
    }
}