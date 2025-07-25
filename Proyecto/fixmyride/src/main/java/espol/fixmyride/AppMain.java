package espol.fixmyride;

// Importaciones
import espol.fixmyride.controlador.*;
import espol.fixmyride.modelo.*;
import espol.fixmyride.vista.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

// Clase principal
public class AppMain {
    // Método principal
    public static void main(String[] args) {
        // Atributos
        Scanner scanner = new Scanner(System.in);
        int opcion;

        // Inicializar controladores
        ControladorEmpresa controladorEmpresa = new ControladorEmpresa();
        ControladorCliente controladorCliente = new ControladorCliente(controladorEmpresa);
        ControladorProveedor controladorProveedor = new ControladorProveedor();
        ControladorTecnico controladorTecnico = new ControladorTecnico();
        ControladorServicio controladorServicio = new ControladorServicio();
        ControladorOrdenServicio controladorOrdenServicio = new ControladorOrdenServicio();
        ControladorRegistroFaltaInsumo controladorRFI = new ControladorRegistroFaltaInsumo();
        ControladorFactura controladorFactura = new ControladorFactura(controladorCliente);
        ControladorReporteIngresoServicio controladorReporteIngresoServicio = new ControladorReporteIngresoServicio();
        ControladorReporteAtencionTecnico controladorReporteAtencionTecnico = new ControladorReporteAtencionTecnico();

        // Inicializar vistas
        VistaMenuPrincipal vistaMenuPrincipal = new VistaMenuPrincipal(scanner);
        VistaCliente vistaCliente = new VistaCliente(controladorCliente);
        VistaProveedor vistaProveedor = new VistaProveedor(controladorProveedor);
        VistaTecnico vistaTecnico = new VistaTecnico(controladorTecnico);
        VistaServicio vistaServicio = new VistaServicio(controladorServicio);
        VistaOrdenServicio vistaOrdenServicio = new VistaOrdenServicio(controladorOrdenServicio, controladorServicio, controladorCliente, controladorTecnico);
        VistaRegistroFaltaInsumo vistaRegistroFaltaInsumo = new VistaRegistroFaltaInsumo(controladorRFI, controladorProveedor);
        VistaFactura vistaFactura = new VistaFactura(controladorFactura, controladorOrdenServicio, controladorEmpresa);
        VistaReporteIngresoServicio vistaReporteIngresoServicio = new VistaReporteIngresoServicio(controladorReporteIngresoServicio, controladorOrdenServicio);
        VistaReporteAtencionTecnico vistaReporteAtencionTecnico = new VistaReporteAtencionTecnico(controladorReporteAtencionTecnico, controladorTecnico, controladorOrdenServicio);
        
        // Inicializar la aplicación (Se crean los objetos por defecto)
        inicializarApp(controladorCliente, controladorProveedor, controladorTecnico, controladorServicio, controladorOrdenServicio, controladorEmpresa);

        // Ciclo del menú principal
        do {
            // Llamada a un método que devuelve la opción seleccionada por el usuario
            opcion = vistaMenuPrincipal.mostrarMenuPrincipal();

            // Switch para manejar la opción seleccionada
            switch (opcion) {
                case 1: vistaMenuPrincipal.manejarSubmenuPersonas(vistaCliente, scanner, "clientes"); break;
                case 2: vistaMenuPrincipal.manejarSubmenuPersonas(vistaProveedor, scanner, "proveedores"); break;
                case 3: vistaMenuPrincipal.manejarSubmenuTecnico(vistaTecnico, scanner, "técnicos"); break;
                case 4: vistaMenuPrincipal.manejarSubmenuServicios(vistaServicio, scanner); break;
                case 5: vistaMenuPrincipal.manejarOrdenes(vistaOrdenServicio, scanner); break;
                case 6: vistaMenuPrincipal.manejarRFI(vistaRegistroFaltaInsumo, scanner); break;
                case 7: vistaMenuPrincipal.manejarFactura(vistaFactura, scanner); break;
                case 8: vistaMenuPrincipal.manejarRIS(vistaReporteIngresoServicio, scanner); break;
                case 9: vistaMenuPrincipal.manejarRAT(vistaReporteAtencionTecnico, scanner); break;
                case 10: System.out.println("Saliendo de la aplicación....."); break;
                default: Vista.opcionNoValida();
            }
        } while (opcion != 10);
        scanner.close();
    }

    // Inicialización de objetos
    private static void inicializarApp( ControladorCliente controladorCliente, ControladorProveedor controladorProveedor, ControladorTecnico controladorTecnico, ControladorServicio controladorServicio, ControladorOrdenServicio controladorOrdenServicio, ControladorEmpresa controladorEmpresa) {
    // Creación de Empresas
    controladorEmpresa.agregarEmpresa("Master Motors","00001");
    controladorEmpresa.agregarEmpresa("Ruta Segura","00002");

    // Crear [2] técnicos
    controladorTecnico.agregarTecnico("0901010101", "Marco Herrera", "0991122334", "Mecánica automotriz general");
    controladorTecnico.agregarTecnico("0902020202", "Paula Lema", "0987654321", "Sistemas eléctricos de vehículos");

    // Crear [4] clientes
    controladorCliente.agregarClienteEmpresarial("0911111111", "Andrés Ruiz", "0999988776", "Av. 10 de Agosto y Rumichaca", TipoCliente.EMPRESARIAL,"00001");
    controladorCliente.agregarClientePersonal("0922222222", "Sofía Benítez", "0988877665", "Calle Guayas 123", TipoCliente.PERSONAL);
    controladorCliente.agregarClienteEmpresarial("0933333333", "Esteban Vargas", "0977766554", "Av. América N35-110", TipoCliente.EMPRESARIAL,"00002");
    controladorCliente.agregarClientePersonal("0944444444", "Carla Mendoza", "0966655443", "Vía Daule Km 12", TipoCliente.PERSONAL);

    // Crear [2] proveedores
    controladorProveedor.agregarProveedor("0927272727", "Ricardo Paredes", "0955544332", "Distribuidor de repuestos automotrices");
    controladorProveedor.agregarProveedor("0928282828", "Diana Palacios", "0944433221", "Proveedor de herramientas para talleres mecánicos");

    // Crear [6] servicios
    controladorServicio.agregarServicio("Cambio de aceite",32.5);
    controladorServicio.agregarServicio("Revisión de frenos",48.0);
    controladorServicio.agregarServicio("Alineación y balanceo",42.0);
    controladorServicio.agregarServicio("Reparación de motor",250.0);
    controladorServicio.agregarServicio("Diagnóstico electrónico",60.0);
    controladorServicio.agregarServicio("Lavado y detallado",25.0);

    // Crear [4] órdenes de servicios
    OrdenServicio orden1 = controladorOrdenServicio.agregarOrdenServicio("0911111111", "0901010101", LocalDate.of(2025, 4, 15), TipoVehiculo.AUTOMOVIL,"PBC-2345");
    ArrayList<DetalleServicio> listaServicio_Orden1 = new ArrayList<DetalleServicio>();
    listaServicio_Orden1.add(new DetalleServicio(controladorServicio.getListaServicios().get(0), 2));
    listaServicio_Orden1.add(new DetalleServicio(controladorServicio.getListaServicios().get(1), 2));
    orden1.setListaDetalleServicio(listaServicio_Orden1);
    ControladorOrdenServicio.actualizarTotal(orden1);

    OrdenServicio orden2 = controladorOrdenServicio.agregarOrdenServicio("0911111111", "0902020202", LocalDate.of(2025, 4, 18), TipoVehiculo.MOTOCICLETA,"QCD-3456");
    orden2.setListaDetalleServicio(new ArrayList<DetalleServicio>());
    ArrayList<DetalleServicio> listaServicio_Orden2 = new ArrayList<DetalleServicio>();
    listaServicio_Orden2.add(new DetalleServicio(controladorServicio.getListaServicios().get(2), 4));
    listaServicio_Orden2.add(new DetalleServicio(controladorServicio.getListaServicios().get(3), 5));
    orden2.setListaDetalleServicio(listaServicio_Orden2);
    ControladorOrdenServicio.actualizarTotal(orden2);

    OrdenServicio orden3 = controladorOrdenServicio.agregarOrdenServicio("0933333333", "0901010101", LocalDate.of(2025, 5,  2), TipoVehiculo.AUTOMOVIL,"RDE-4567");
    orden3.setListaDetalleServicio(new ArrayList<DetalleServicio>());
    ArrayList<DetalleServicio> listaServicio_Orden3 = new ArrayList<DetalleServicio>();
    listaServicio_Orden3.add(new DetalleServicio(controladorServicio.getListaServicios().get(4), 2));
    listaServicio_Orden3.add(new DetalleServicio(controladorServicio.getListaServicios().get(5), 4));
    orden3.setListaDetalleServicio(listaServicio_Orden3);
    ControladorOrdenServicio.actualizarTotal(orden3);

    OrdenServicio orden4 = controladorOrdenServicio.agregarOrdenServicio("0944444444", "0902020202", LocalDate.of(2025, 5, 10), TipoVehiculo.BUS,"SFE-5678");
    orden4.setListaDetalleServicio(new ArrayList<DetalleServicio>());
    ArrayList<DetalleServicio> listaServicio_Orden4 = new ArrayList<DetalleServicio>();
    listaServicio_Orden4.add(new DetalleServicio(controladorServicio.getListaServicios().get(1), 2));
    listaServicio_Orden4.add(new DetalleServicio(controladorServicio.getListaServicios().get(2), 3));
    orden4.setListaDetalleServicio(listaServicio_Orden4); 
    ControladorOrdenServicio.actualizarTotal(orden4);
    }
}