package espol.fixmyride.controlador;
import espol.fixmyride.modelo.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ControladorOrdenServicio {
    // Atributos
    public static int contadorOrden =0;
    // Lista en memoria para almacenar las órdenes
    public static ArrayList<OrdenServicio> listaOrdenes;
    // Método para obtener la lista de órdenes
    public ArrayList<OrdenServicio> getLista() { return listaOrdenes; }
    // Constructor
    public ControladorOrdenServicio() { listaOrdenes = new ArrayList<>(); }
    // Método para agregar una orden
    public OrdenServicio agregarOrdenServicio(String idCliente, LocalDate fechaServicio, TipoVehiculo tipoVehiculo, String placaVehiculo) {
        OrdenServicio orden = new OrdenServicio(idCliente, fechaServicio, tipoVehiculo, placaVehiculo);
        orden.setNumeroOrden(++contadorOrden);
        listaOrdenes.add(orden);
        return orden;
    }
    // Método para añadir DetalleServicio a una orden
    public void agregarDetalleServicio(OrdenServicio orden, DetalleServicio detalle) {
        orden.getListaDetalleServicio().add(detalle);
        System.out.println("Servicio agregado a la orden #" + orden.getNumeroOrden());
    }
}