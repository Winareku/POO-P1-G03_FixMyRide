package espol.fixmyride.modelo;
import java.time.LocalDate;
public class OrdenServicio {
    
    //Atributos
    private int numeroOrden;
    private String idCliente;
    private LocalDate fechaServicio;
    private TipoVehiculo tipoVehiculo;
    private String placaVehiculo;
    private double totalOrden;
    
    // Constructor
    public OrdenServicio(String idCliente,LocalDate fechaServicio,TipoVehiculo tipoVehiculo,String placa){
        this.idCliente = idCliente;
        this.fechaServicio = fechaServicio;
        this.tipoVehiculo = tipoVehiculo;
        this.placaVehiculo = placa;
    }
    
    // Getters y Setters
    public int getNumeroOrden() { return numeroOrden; }
    public void setNumeroOrden(int numeroOrden) { this.numeroOrden = numeroOrden; }
    public String getIdCliente() { return idCliente; }
    public void setIdCliente(String idCliente) { this.idCliente = idCliente; }
    //Nota: id tecnico eliminadp
    public LocalDate getFechaServicio() { return fechaServicio; }
    public void setFechaServicio(LocalDate fechaServicio) { this.fechaServicio = fechaServicio; }
    public TipoVehiculo getTipoVehiculo() { return tipoVehiculo; }
    public void setTipoVehiculo(TipoVehiculo tipoVehiculo) { this.tipoVehiculo = tipoVehiculo; }
    public String getPlacaVehiculo() { return placaVehiculo; }
    public void setPlacaVehiculo(String placaVehiculo) { this.placaVehiculo = placaVehiculo; }
    public double getTotalOrden() { return totalOrden;}
    public void setTotalOrden(double totalOrden) { this.totalOrden = totalOrden;}

    // Override toString para mostrar la información de la orden de servicio
    @Override
    public String toString() {
        return (numeroOrden + ", " + idCliente + ", "  + fechaServicio + ", " + tipoVehiculo + ", " + placaVehiculo + ", " + totalOrden);
    }
}
