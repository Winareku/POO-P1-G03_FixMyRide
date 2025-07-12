package espol.fixmyride.controlador;

// Importaciones
import espol.fixmyride.modelo.*;
import java.time.LocalDate;
import java.util.ArrayList;

// Clase ControladorReporteIngresoServicio
public class ControladorReporteIngresoServicio {
    // Atributos
    
    // Lista en memoria para almacenar
    public static ArrayList<ReporteIngresoServicio> lista;
    
    // Método para obtener la lista
    public ArrayList<ReporteIngresoServicio> getLista() { return lista; }

    // Constructor
    public ControladorReporteIngresoServicio() { lista = new ArrayList<>(); }
    
    // Métodos
    public ReporteIngresoServicio crearReporteIngresoServicio(int anio, int mes, ArrayList<OrdenServicio> listaOrdenServicios){
        ArrayList<DetalleServicio> listaServicio = new ArrayList<>();
        boolean hayCoincidencias = false;
        for (OrdenServicio orden:listaOrdenServicios){
            LocalDate fechaOrden = orden.getFechaOrden();
            boolean correspondePeriodo = fechaOrden.getYear()==anio && fechaOrden.getMonthValue()==mes;
            if (correspondePeriodo){
                for (DetalleServicio servicio:orden.getListaDetalleServicio()){
                    listaServicio.add(servicio);
                    hayCoincidencias = true;
                }
            }
        }
        return new ReporteIngresoServicio(listaServicio, hayCoincidencias);
    }
}
