package espol.fixmyride.controlador;

// Importaciones
import espol.fixmyride.modelo.*;
import java.util.ArrayList;

public class ControladorTecnico extends ControladorPersona {
    // Constructor
    public ControladorTecnico() { super();}
    
    // Método para agregar un técnico
    public String agregarTecnico(String id, String nombre, String telefono, String especialidad) {
        Persona tecnicoExistente = ControladorPersona.buscarPersonaPorId(id, getLista());
        if (tecnicoExistente != null) return "El técnico ya existe";
        Tecnico tecnico = new Tecnico(id, nombre, telefono, especialidad);
        agregarPersona(tecnico);
        return "Técnico agregado exitosamente";
    }

    // Método para eliminar un técnico
    public String eliminarTecnico(Tecnico tecnico, ArrayList<Persona> lista){
    	lista.remove(tecnico);
        return "Técnico agregado exitosamente";
    }
}
