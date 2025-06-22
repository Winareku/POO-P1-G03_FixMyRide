package espol.fixmyride.vista;
import espol.fixmyride.controlador.*;
import espol.fixmyride.modelo.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class VistaOrdenServicio {
    // Atributo controlador
    private ControladorOrdenServicio controlador;
    private ControladorServicio controladorServicio;
    private ControladorCliente controladorCliente;
    // Constructor
    public VistaOrdenServicio(ControladorOrdenServicio controlador, ControladorServicio controladorServicio, ControladorCliente controladorCliente) {
        this.controlador = controlador;
        this.controladorServicio = controladorServicio;
        this.controladorCliente = controladorCliente;
    }
    // Método para mostrar el submenu de órdenes
    public void generarOrden(Scanner scanner) {
        // Mostrar órdenes actuales
        Vista.caja("ORDENES DE SERVICIOS");
        ArrayList<OrdenServicio>listaOrdenes = controlador.getLista();
        for (OrdenServicio orden: listaOrdenes) { System.out.println(orden); }
        // Menu de generar órdenes
        Vista.caja("GENERAR ORDEN DE SERVICIOS");
        String idCliente = Vista.verificarPersonaPorID(scanner, controladorCliente.getLista(), "cliente");
        String fechaString = Vista.solicitarFecha(scanner);
        // Validación del tipo de vehículo
        TipoVehiculo tipoVehiculo;
        while (true) {
            System.out.println("Seleccione el tipo de vehículo:");
            System.out.println("  1. Automóvil");
            System.out.println("  2. Motocicleta");
            System.out.println("  3. Bus");
            System.out.print("Ingrese su opción: ");
            int entrada = Vista.obtenerInt(scanner);
            switch (entrada) {
                case 1:
                    tipoVehiculo = TipoVehiculo.AUTOMOVIL;
                    break;
                case 2:
                    tipoVehiculo = TipoVehiculo.MOTOCICLETA;
                    break;
                case 3:
                    tipoVehiculo = TipoVehiculo.BUS;
                    break;
                default:
                    Vista.opcionNoValida();
                    continue;
            }
            break;
        }
        String placaVehiculo = Vista.obtenerString(scanner,"Placa del vehículo: ");
        // Agregar orden de servicios
        OrdenServicio orden = controlador.agregarOrdenServicio(idCliente, LocalDate.parse(fechaString), tipoVehiculo, placaVehiculo);
        // Se mantiene pidiendo códigos de servicios y cantidad hasta que escriba -1
        String codigoServicio;
        do {
            codigoServicio = Vista.obtenerString(scanner,"Ingrese el código del servicio: ");
            if (codigoServicio.equals("-1")){break;}
            Servicio servicio = controladorServicio.obtenerServicioPorCodigo(codigoServicio);
            if (servicio == null){Vista.opcionNoValida();continue;}
            int cantidadServicio = Vista.obtenerInt(scanner,"Ingrese la cantidad del servicio: ");
            DetalleServicio detalleServicio = new DetalleServicio(servicio, cantidadServicio);
            controlador.agregarDetalleServicio(orden, detalleServicio);
            orden.setTotalOrden(orden.getTotalOrden()+detalleServicio.getTotal());
            System.out.println("Orden registrada. Total a pagar: $"+orden.getTotalOrden());
        } while (!codigoServicio.equals("-1"));
    }
}