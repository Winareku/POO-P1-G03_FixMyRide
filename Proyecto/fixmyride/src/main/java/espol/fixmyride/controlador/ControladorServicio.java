package espol.fixmyride.controlador;
import espol.fixmyride.modelo.*;
import java.util.ArrayList;

public class ControladorServicio {

    // Lista en memoria para almacenar los servicios
    private ArrayList<Servicio> listaServicios;

    // Constructor
    public ControladorServicio() { this.listaServicios = new ArrayList<>(); }

    // Método para agregar un servicio
    public String agregarServicio(String nombre, double precio) {
        Servicio servicio = new Servicio(nombre, precio);
        listaServicios.add(servicio);
        return "Servicio agregado exitosamente";
    }

    // Método para editar un servicio
    public String editarServicio(String codigo, double precio) {
        for (Servicio servicio : listaServicios) {
            if (servicio.getCodigo().equals(codigo)) {
                servicio.editarPrecio(precio);
                return "Servicio editado exitosamente";
            }
        }
        return "Servicio no encontrado";
    }

    // Método para obtener un servicio por código
    public Servicio obtenerServicioPorCodigo(String codigo) {
        for (Servicio servicio : listaServicios) { if (servicio.getCodigo().equals(codigo)) { return servicio; } }
        return null;
    }

    // Método para obtener la lista de servicios
    public  ArrayList<Servicio>  getListaServicios() { return listaServicios; }
}