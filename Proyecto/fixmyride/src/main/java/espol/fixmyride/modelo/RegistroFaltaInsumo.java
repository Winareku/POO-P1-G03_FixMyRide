package espol.fixmyride.modelo;

public class RegistroFaltaInsumo {
    private String descripcion;
    private String idProveedor;
    private LocalDate fechaRegistro;

    // Constructor
    public void FaltaInsumo(String descripcion, String idProveedor){
        this.descripcion = descripcion;
        this.idProveedor = idProveedor;
    }

    // Getters y Setters

    public String getDescripcion(){ return descripcion; }
    public void setDescripcion(String descripcion){ this.descripcion = descripcion; }
    public String getIdProveedor(){ return idProveedor; }
    public void setIdProveedor(String idProveedor){ this.idProveedor = idProveedor; }
    public LocalDate getFechaRegistro(){ return fechaRegistro; }
    public void setFechaRegistro(LocalDate fechaRegistro){ this.fechaRegistro = fechaRegistro; }

    @Override
    public String toString(){ return "Descripcion: "+descripcion+" ID Proveedor: "+idProveedor+ "Fecha de registro: "+fechaRegistro; }

}

