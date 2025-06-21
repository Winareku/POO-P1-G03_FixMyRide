package espol.fixmyride.vista;
import espol.fixmyride.controlador.*;
import espol.fixmyride.modelo.*;
import java.time.LocalDate;
import java.util.Scanner;

public class VistaOrdenServicio {
    // Atributo controlador
    private ControladorOrdenServicio controlador;
    private ControladorServicio controladorServicio;
    // Constructor
    public VistaOrdenServicio(ControladorOrdenServicio controlador, ControladorServicio controladorServicio) {
        this.controlador = controlador;
        this.controladorServicio = controladorServicio;
    }
    // Método para mostrar el submenu de órdenes
    public void generarOrden(Scanner scanner) {
        MensajeUsuario.caja("GENERAR ÓRDEN DE SERVICIOS");
        System.out.println("Ingrese el ID del cliente: ");
        String idCliente = scanner.nextLine();
        System.out.println("Fecha del servicio (YYYY-MM-DD): ");
        String fechaString = scanner.nextLine();
        // Validación del tipo de vehículo
        TipoVehiculo tipoVehiculo;
        while (true) {
            System.out.println("Seleccione el tipo de vehículo:");
            System.out.println("  1. Automóvil");
            System.out.println("  2. Motocicleta");
            System.out.println("  3. Bus");
            System.out.print("Ingrese su opción: ");
            int entrada = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
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
                    continue;
            }
            break;
        }
        System.out.println("Placa del vehículo: ");
        String placaVehiculo = scanner.nextLine();
        // Agregar orden de servicios
        int numeroOrden = controlador.agregarOrdenServicio(idCliente, "", LocalDate.parse(fechaString), tipoVehiculo, placaVehiculo);
        // Se mantiene pidiendo códigos de servicios y cantidad hasta que escriba -1
        String codigoServicio;
        do {
            System.out.println("Ingrese el código del servicio (-1 para terminar): ");
            codigoServicio = scanner.nextLine();
            if (!codigoServicio.equals("-1")) {
                System.out.println("Ingrese la cantidad del servicio: ");
                int cantidadServicio = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                Servicio servicio = controladorServicio.obtenerServicioPorCodigo(codigoServicio);
                DetalleServicio detalleServicio = new DetalleServicio(servicio, cantidadServicio);
                controlador.agregarDetalleServicio(numeroOrden, detalleServicio);
            }
        } while (!codigoServicio.equals("-1"));
        System.out.println();
    }
}