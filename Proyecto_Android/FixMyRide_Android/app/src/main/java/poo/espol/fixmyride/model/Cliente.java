package poo.espol.fixmyride.model;

public class Cliente extends Persona {
    // Atributos
    private String direccion;
    private TipoCliente tipoCliente; // "Personal" o "Empresarial"
    private Empresa empresa;

    // Constructor
    public Cliente(String id, String nombre, String telefono, String direccion, TipoCliente tipoCliente) {
        super(id, nombre, telefono);
        this.direccion = direccion;
        this.tipoCliente = tipoCliente;
    }

    // Getters y Setters
    public String getDireccion() { return direccion; }
    public Empresa getEmpresa(){ return empresa; }
    public TipoCliente getTipoCliente() { return tipoCliente; }
    public void setTipoCliente(TipoCliente tipoCliente) { this.tipoCliente = tipoCliente; }
    public void setEmpresa(Empresa empresa) { this.empresa = empresa; }
    @Override
    public String toString() { return (super.toString() + ", " + direccion + ", " + tipoCliente +(empresa!=null?(", "+empresa.getNombre()):"")); }
}