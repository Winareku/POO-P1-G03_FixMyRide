package espol.fixmyride.controlador;
import java.util.ArrayList;
import espol.fixmyride.modelo.Servicio;


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
    
    //Metodo para buscar servicio por código
    public Servicio buscarServicioPorCodigo(String codigo) {
        for (Servicio s : listaServicios) {
            if (s.getCodigo().equals(codigo)) {
                return s;
            }
        }
        return null;
    }

    // Método para obtener la lista de servicios
    public  ArrayList<Servicio>  getListaServicios() { return listaServicios; }
}