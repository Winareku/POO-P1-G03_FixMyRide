package espol.fixmyride.modelo;
import java.time.LocalDate;
import java.util.ArrayList;

public class OrdenServicio {
    //Atributos
    private int numeroOrden;
    private String idCliente;
    private String idTecnico;
    private LocalDate fechaOrden;
    private TipoVehiculo tipoVehiculo;
    private String placaVehiculo;
    private double totalOrden;
    private ArrayList<DetalleServicio> listaDetalleServicio;

    // Constructor
    public OrdenServicio(String idCliente, String idTecnico,LocalDate fechaOrden,TipoVehiculo tipoVehiculo,String placa){
        this.idCliente = idCliente;
        this.idTecnico = idTecnico;
        this.fechaOrden = fechaOrden;
        this.tipoVehiculo = tipoVehiculo;
        this.placaVehiculo = placa;
        this.listaDetalleServicio = new ArrayList<>();
    }
    
    // Getters y Setters
    public int getNumeroOrden() { return numeroOrden; }
    public void setNumeroOrden(int numeroOrden) { this.numeroOrden = numeroOrden; }
    public String getIdCliente() { return idCliente; }
    public void setIdCliente(String idCliente) { this.idCliente = idCliente; }
    public String getIdTecnico() { return idTecnico; }
    public void setIdTecnico(String idTecnico) { this.idTecnico = idTecnico; }
    public LocalDate getFechaOrden() { return fechaOrden; }
    public void setFechaOrden(LocalDate fechaServicio) { this.fechaOrden = fechaServicio; }
    public TipoVehiculo getTipoVehiculo() { return tipoVehiculo; }
    public void setTipoVehiculo(TipoVehiculo tipoVehiculo) { this.tipoVehiculo = tipoVehiculo; }
    public String getPlacaVehiculo() { return placaVehiculo; }
    public void setPlacaVehiculo(String placaVehiculo) { this.placaVehiculo = placaVehiculo; }
    public double getTotalOrden() { return totalOrden;}
    public void setTotalOrden(double totalOrden) { this.totalOrden = totalOrden;}
    public ArrayList<DetalleServicio> getListaDetalleServicio() { return listaDetalleServicio; }
    public void setListaDetalleServicio(ArrayList<DetalleServicio> listaDetalleServicio) { this.listaDetalleServicio = listaDetalleServicio; }
    @Override
    public String toString() {
        return (numeroOrden + ", " + idCliente + ", " + idTecnico + ", " + fechaOrden + ", " + tipoVehiculo + ", " + placaVehiculo + ", " + totalOrden);
    }
}