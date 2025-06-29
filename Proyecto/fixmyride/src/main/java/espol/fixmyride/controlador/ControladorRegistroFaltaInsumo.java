package espol.fixmyride.controlador;

// Importaciones
import espol.fixmyride.modelo.*;
import java.util.ArrayList;

// Clase ControladorRegistroFaltaInsumo
public class ControladorRegistroFaltaInsumo {
    // Lista en memoria para almacenar los registros
    public static ArrayList<RegistroFaltaInsumo> listaRFI;

    // Constructor
    public ControladorRegistroFaltaInsumo() { listaRFI = new ArrayList<>(); }
    
    // Método para obtener la lista de órdenes
    public ArrayList<RegistroFaltaInsumo> getListaRFI() { return listaRFI; }
}