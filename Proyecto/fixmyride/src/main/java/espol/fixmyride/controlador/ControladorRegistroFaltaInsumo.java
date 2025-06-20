package espol.fixmyride.controlador;
import espol.fixmyride.modelo.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ControladorRegistroFaltaInsumo {
    // Lista en memoria para almacenar los registros
    public static ArrayList<RegistroFaltaInsumo> listaRFI;
    // Constructor
    public ControladorRegistroFaltaInsumo() { listaRFI = new ArrayList<>(); }
    // Método para obtener la lista de órdenes
    public ArrayList<RegistroFaltaInsumo> getListaRFI() { return listaRFI; }
}