package espol.fixmyride.controlador;
import espol.fixmyride.modelo.*;
import java.util.ArrayList;

public class ControladorCliente extends ControladorPersona {

    // Constructor    
    public ControladorCliente() { this.setLista(new ArrayList<>());}
    
    // Método para agregar un cliente
    public String agregarCliente(String id, String nombre, String telefono, String direccion, TipoCliente tipoCliente) {
        Persona clienteExistente = ControladorPersona.buscarPersonaPorId(id, getLista());
        if (clienteExistente == null) {
            Cliente cliente = new Cliente(id, nombre, telefono, direccion, tipoCliente);
            agregarPersona(cliente);
            return "Cliente agregado exitosamente";
        }
        return "El cliente ya existe";
    }
}