package controlador;
import modelo.DetalleServicio;
import java.util.ArrayList;

public class ControladorDetalleServicio {
    private static ArrayList<DetalleServicio> listaDetalleServicio;
    
    public ControladorDetalleServicio() { listaDetalleServicio = new java.util.ArrayList<>(); }
    
    //Metodo para agregar detalle de un servicio
    public void agregarDetalleServicio(DetalleServicio ds){
        this.getListaDetalle().add(ds);
    }
    
    //Metodo para calcular el total
     public double calcularSubtotal(DetalleServicio ds) {
        double total_sum= ds.getPrecio() * ds.getCantidad();
        ds.setTotal(total_sum);
        return total_sum;
     }

    // Método para obtener la lista de detalles
    public  ArrayList<DetalleServicio>  getListaDetalle() { return listaDetalleServicio; }
}

