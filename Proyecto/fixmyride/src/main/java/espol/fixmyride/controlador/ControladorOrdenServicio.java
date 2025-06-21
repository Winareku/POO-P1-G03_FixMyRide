package controlador;
import modelo.OrdenServicio;
import java.time.LocalDate;

public class ControladorOrdenServicio {

    // Atributos
    public  static int contadorOrden =0;

    // Lista en memoria para almacenar las órdenes
    private static java.util.ArrayList<OrdenServicio> listaOrdenes;

    // Constructor
    public ControladorOrdenServicio() { listaOrdenes = new java.util.ArrayList<>(); }

    // Método para agregar una orden de servicio
    public String agregarOrdenServicio(String idCliente, String idTecnico, LocalDate fechaServicio, modelo.TipoVehiculo tipoVehiculo, String placaVehiculo) {
        OrdenServicio orden = new OrdenServicio(idCliente, fechaServicio, tipoVehiculo, placaVehiculo);
        orden.setNumeroOrden(++contadorOrden);
        listaOrdenes.add(orden);
        return "Orden de servicio agregada exitosamente";
    }

    // Método para obtener la lista de órdenes
    public java.util.ArrayList<OrdenServicio> getListaOrdenes() { return listaOrdenes; }
}
