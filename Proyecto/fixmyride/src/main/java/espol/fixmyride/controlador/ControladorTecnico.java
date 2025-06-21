package espol.fixmyride.controlador;
import espol.fixmyride.modelo.*;
import java.util.ArrayList;

public class ControladorTecnico extends ControladorPersona {
    // Constructor
    public ControladorTecnico() { this.setLista(new ArrayList<>());}
    // Método para agregar un técnico
    public String agregarTecnico(String id, String nombre, String telefono, String especialidad) {
        Persona tecnicoExistente = ControladorPersona.buscarPersonaPorId(id, getLista());
        if (tecnicoExistente == null) {
            Tecnico tecnico = new Tecnico(id, nombre, telefono, especialidad);
            agregarPersona(tecnico);
            return "Técnico agregado exitosamente";
        }
        return "El técnico ya existe";
    }
}