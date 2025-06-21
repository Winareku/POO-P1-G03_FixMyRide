package espol.fixmyride.modelo;

public class Cliente extends Persona {
    
    // Atributos
    private String direccion;
    private TipoCliente tipoCliente; // "Personal" o "Empresarial"

    // Constructor
    public Cliente(String id, String nombre, String telefono, String direccion, TipoCliente tipoCliente) {
        super(id, nombre, telefono);
        this.direccion = direccion;
        this.tipoCliente = tipoCliente;
    }

    // Getters y Setters
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public TipoCliente getTipoCliente() { return tipoCliente; }
    public void setTipoCliente(TipoCliente tipoCliente) { this.tipoCliente = tipoCliente; }

    // Override toString para mostrar la información del cliente
    @Override
    public String toString() { return (super.toString() + ", " + direccion + ", " + tipoCliente); }
}