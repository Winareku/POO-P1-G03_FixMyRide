package espol.fixmyride.modelo;

public class Cliente extends Persona {
    // Atributos
    private String direccion;
    private TipoCliente tipoCliente; // "Personal" o "Empresarial"
    private String codigoEmpresa;

    // Constructor
    public Cliente(String id, String nombre, String telefono, String direccion, TipoCliente tipoCliente) {
        super(id, nombre, telefono);
        this.direccion = direccion;
        this.tipoCliente = tipoCliente;
    }
    public Cliente(String id, String nombre, String telefono, String direccion, TipoCliente tipoCliente, String codigoEmpresa) {
        super(id, nombre, telefono);
        this.direccion = direccion;
        this.tipoCliente = tipoCliente;
        this.codigoEmpresa= codigoEmpresa;}

    // Getters y Setters
    public String getDireccion() { return direccion; }
    public String getCodigoEmpresa(){
        if(this.tipoCliente == TipoCliente.EMPRESARIAL){
            return codigoEmpresa;
        }return null;
    }
    public TipoCliente getTipoCliente() { return tipoCliente; }
    public void setTipoCliente(TipoCliente tipoCliente) { this.tipoCliente = tipoCliente; }
    @Override
    public String toString() { return (super.toString() + ", " + direccion + ", " + tipoCliente); }
}