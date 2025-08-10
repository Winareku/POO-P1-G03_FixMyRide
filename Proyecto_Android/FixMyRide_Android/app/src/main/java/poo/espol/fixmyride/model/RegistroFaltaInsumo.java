package poo.espol.fixmyride.model;

public class RegistroFaltaInsumo {
    // Atributos
    private String descripcion;
    private String idProveedor;

    // Constructor
    public RegistroFaltaInsumo(String descripcion, String idProveedor){
        this.descripcion = descripcion;
        this.idProveedor = idProveedor;
    }
    
    // Getters y Setters
    public String getDescripcion(){ return descripcion; }
    public void setDescripcion(String descripcion){ this.descripcion = descripcion; }
    public String getIdProveedor(){ return idProveedor; }
    public void setIdProveedor(String idProveedor){ this.idProveedor = idProveedor; }
    @Override
    public String toString(){ return (descripcion + ", " + idProveedor); }
}