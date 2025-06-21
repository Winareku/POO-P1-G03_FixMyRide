package vista;
import controlador.ControladorOrdenServicio;
import controlador.ControladorServicio;
import controlador.ControladorDetalleServicio;
import modelo.DetalleServicio;
import modelo.Servicio;
import modelo.OrdenServicio;
import modelo.TipoVehiculo;
import java.util.Scanner;
import java.time.LocalDate;

public class VistaOrdenServicio {
    
    // Atributo controlador
    private ControladorOrdenServicio controlador;

    // Constructor
    public VistaOrdenServicio(controlador.ControladorOrdenServicio controlador) { this.controlador = controlador; }

    // Método para mostrar el submenu de órdenes
    public void generarOrden(Scanner scanner) {
        MensajeUsuario.caja("GENERAR ÓRDEN DE SERVICIOS");
        System.out.println("Ingrese el ID del cliente: ");
        String idCliente = scanner.nextLine();
        System.out.println("Fecha del servicio (YYYY-MM-DD): ");
        String fechaString = scanner.nextLine();
        System.out.println("Tipo de vehículo (MOTO, CARRO, CAMION): ");
        String tipoVehiculoString = scanner.nextLine();
        System.out.println("Placa del vehículo: ");
        String placaVehiculo = scanner.nextLine();
        
        //Creacion de la orden y añadirla
        OrdenServicio o_s = new OrdenServicio(idCliente, LocalDate.parse(fechaString),TipoVehiculo.valueOf(tipoVehiculoString.toUpperCase()), placaVehiculo);
        ControladorOrdenServicio contro_os= new ControladorOrdenServicio();
        contro_os.getListaOrdenes().add(o_s);
        
        //Pedir el codigo del servicio
        while (true) {
            System.out.print("Código del servicio (-1 para finalizar): ");
            String codigo = scanner.nextLine();
            if (codigo.equals("-1")) break;
            
            //Ingreso del codigo de servicio
            ControladorServicio s = new ControladorServicio();
            Servicio servicio_encontrado = s.buscarServicioPorCodigo(codigo);
            if (servicio_encontrado==null){
                System.out.println("Servicio no encontrado.");
                continue;   
            }
            System.out.print("Cantidad: ");
            int cantidad = scanner.nextInt(); scanner.nextLine();
            
            //Creación de un detalle
            ControladorDetalleServicio control_ds= new ControladorDetalleServicio();
            DetalleServicio detalle = new DetalleServicio(servicio_encontrado, cantidad, servicio_encontrado.getPrecio());
            control_ds.agregarDetalleServicio(detalle);
            
            //Calculo del subtotal y sumarlo con el total orden
            double subtotal= control_ds.calcularSubtotal(detalle);
            o_s.setTotalOrden(o_s.getTotalOrden()+subtotal);
           }
        //Presentar el total a pagar de la orden
        System.out.println("Orden registrada. Total a pagar: $" + o_s.getTotalOrden());
        
        System.out.println(controlador.agregarOrdenServicio(idCliente, "", LocalDate.parse(fechaString), TipoVehiculo.valueOf(tipoVehiculoString.toUpperCase()), placaVehiculo));
    }
}
