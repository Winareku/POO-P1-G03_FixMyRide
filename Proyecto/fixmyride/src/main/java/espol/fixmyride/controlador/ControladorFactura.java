package espol.fixmyride.controlador;

// Importaciones
import espol.fixmyride.modelo.*;
import java.time.LocalDate;
import java.util.ArrayList;

// Clase ControladorFactura
public class ControladorFactura {
    // Atributos

    // Lista en memoria para almacenar
    public static ArrayList<Factura> lista;
    
    // Método para obtener la lista
    public ArrayList<Factura> getLista() { return lista; }
    
    // Constructor
    public ControladorFactura() { lista = new ArrayList<>(); }
    
    // Métodos
}