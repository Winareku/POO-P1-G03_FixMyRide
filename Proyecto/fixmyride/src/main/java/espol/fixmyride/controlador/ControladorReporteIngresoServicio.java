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
}
