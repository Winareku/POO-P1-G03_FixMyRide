package espol.fixmyride.controlador;
import espol.fixmyride.modelo.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ControladorReporteAtencionTecnico {
    // Atributos
    // Lista en memoria para almacenar
    public static ArrayList<ReporteAtencionTecnico> lista;
    // Método para obtener la lista
    public ArrayList<ReporteAtencionTecnico> getLista() { return lista; }
    // Constructor
    public ControladorReporteAtencionTecnico() { lista = new ArrayList<>(); }
    // Métodos
}