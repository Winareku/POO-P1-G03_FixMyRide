package espol.fixmyride.controlador;
import espol.fixmyride.modelo.DetalleServicio;
import java.util.ArrayList;

public class ControladorDetalleServicio {
    // Lista en memoria para almacenar
    private static ArrayList<DetalleServicio> listaDetalleServicio;
    // Constructor
    public ControladorDetalleServicio() { listaDetalleServicio = new java.util.ArrayList<>(); }
    //Metodo para agregar detalle de un servicio
    public void agregarDetalleServicio(DetalleServicio detalleServicio){ this.getListaDetalle().add(detalleServicio); }
    // Método para obtener la lista de detalles
    public  ArrayList<DetalleServicio>  getListaDetalle() { return listaDetalleServicio; }
}