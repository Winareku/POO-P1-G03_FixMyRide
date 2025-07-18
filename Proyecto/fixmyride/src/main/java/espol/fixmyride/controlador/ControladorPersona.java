package espol.fixmyride.controlador;

// Importaciones
import espol.fixmyride.modelo.*;
import java.util.ArrayList;

// Clase ControladorPersona
public class ControladorPersona {
    // Atributo para almacenar la lista de personas
    private ArrayList<Persona> lista;

    // Constructor
    public ControladorPersona() { this.lista = new ArrayList<>();}
    
    // Getters y Setters
    public ArrayList<Persona> getLista() { return lista; }

    // Método estático para buscar una persona por ID en una lista
    public static Persona buscarPersonaPorId(String id, ArrayList<Persona> lista) {
        for (Persona persona : lista) { if (persona.getId().equals(id)) return persona; }
        return null;
    }

    // Método para añadir una persona a la lista
    public void agregarPersona(Persona persona) { lista.add(persona); }
}
