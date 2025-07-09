package espol.fixmyride.controlador;

// Importaciones
import espol.fixmyride.modelo.*;

// Clase ControladorCliente
public class ControladorCliente extends ControladorPersona {
    // Constructor    
    public ControladorCliente() { super();}
    
    // Métodos para agregar un cliente
    public String agregarClientePersonal(String id, String nombre, String telefono, String direccion, TipoCliente tipoCliente) {
        Persona clienteExistente = ControladorPersona.buscarPersonaPorId(id, getLista());
        if (clienteExistente == null) {
            Cliente cliente = new Cliente(id, nombre, telefono, direccion, tipoCliente);
            agregarPersona(cliente);
            return "Cliente agregado exitosamente";
        }
        return "El cliente ya existe";
    }
    public String agregarClienteEmpresarial(String id, String nombre, String telefono, String direccion, TipoCliente tipoCliente, String codigoEmp){
        Persona clienteExistente = ControladorPersona.buscarPersonaPorId(id, getLista());
        if (clienteExistente == null) {
            Cliente cliente = new Cliente(id, nombre, telefono, direccion, tipoCliente, codigoEmp);
            agregarPersona(cliente);
            return "Cliente agregado exitosamente";
        }
        return "El cliente ya existe";
    }
}