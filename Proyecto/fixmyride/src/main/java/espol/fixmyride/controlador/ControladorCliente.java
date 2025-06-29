package espol.fixmyride.controlador;

// Importaciones
import espol.fixmyride.modelo.*;

// Clase ControladorCliente
public class ControladorCliente extends ControladorPersona {
    // Constructor    
    public ControladorCliente() { super();}
    
    // Método para agregar un cliente
    public String agregarCliente(String id, String nombre, String telefono, String direccion, TipoCliente tipoCliente) {
        Persona clienteExistente = ControladorPersona.buscarPersonaPorId(id, getLista());
        if (clienteExistente != null) return "El cliente ya existe";
        Cliente cliente = new Cliente(id, nombre, telefono, direccion, tipoCliente);
        agregarPersona(cliente);
        return "Cliente agregado exitosamente";
    }
}