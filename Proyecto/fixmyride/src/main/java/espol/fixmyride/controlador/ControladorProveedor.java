package espol.fixmyride.controlador;

// Importaciones
import espol.fixmyride.modelo.*;

// Clase ControladorProveedor
public class ControladorProveedor extends ControladorPersona {
    // Constructor
    public ControladorProveedor() { super();}
    
    // Método para agregar un proveedor
    public String agregarProveedor(String id, String nombre, String telefono, String descripcion) {
        Persona proveedorExistente = ControladorPersona.buscarPersonaPorId(id, getLista());
        if (proveedorExistente != null) return "El proveedor ya existe";
        Proveedor proveedor = new Proveedor(id, nombre, telefono, descripcion);
        agregarPersona(proveedor);
        return "Proveedor agregado exitosamente";
    }
}