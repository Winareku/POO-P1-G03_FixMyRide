package espol.fixmyride.controlador;
import espol.fixmyride.modelo.*;
import java.time.LocalDate;

public class ControladorOrdenServicio {

    // Atributos
    public  static int contadorOrden =0;

    // Lista en memoria para almacenar las órdenes
    public static java.util.ArrayList<OrdenServicio> listaOrdenes;

    // Constructor
    public ControladorOrdenServicio() { listaOrdenes = new java.util.ArrayList<>(); }

    // Método para agregar una orden
    public int agregarOrdenServicio(String idCliente, String idTecnico, LocalDate fechaServicio, TipoVehiculo tipoVehiculo, String placaVehiculo) {
        OrdenServicio orden = new OrdenServicio(idCliente, idTecnico, fechaServicio, tipoVehiculo, placaVehiculo);
        orden.setNumeroOrden(++contadorOrden);
        listaOrdenes.add(orden);
        return contadorOrden;
    }

    // Método para añadir DetalleServicio a una orden
    public String agregarDetalleServicio(int numeroOrden, DetalleServicio detalle) {
        for (OrdenServicio orden : listaOrdenes) {
            if (orden.getNumeroOrden() == numeroOrden) {
                orden.getListaDetalleServicio().add(detalle);
                System.out.println("Servicio agregado a la orden #" + numeroOrden);
                return String.valueOf(numeroOrden);
            }
        }
        System.out.println("Orden #" + numeroOrden + " no encontrada.");
        return null;
    }

    // Método para obtener la lista de órdenes
    public java.util.ArrayList<OrdenServicio> getListaOrdenes() { return listaOrdenes; }
}