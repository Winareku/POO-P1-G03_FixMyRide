package espol.fixmyride.modelo;

public class DetalleServicio {
    // Atributos
    private Servicio servicio;
    private int cantidad;
    private double precioUnitario;
    private double total;

    // Constructor
    public DetalleServicio(Servicio servicio, int cantidad) {
        this.servicio = servicio;
        this.cantidad = cantidad;
        this.precioUnitario = servicio.getPrecio();
        this.total = cantidad * precioUnitario;
    }
    
    // Getters y Setters
    public Servicio getServicio() { return servicio; }
    public int getCantidad() { return cantidad; }
    public double getPrecioUnitario() { return precioUnitario; }
    public double getTotal() { return total; }
    public void setServicio(Servicio servicio) { this.servicio = servicio; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public void setPrecioUnitario(double precioUnitario) { this.precioUnitario = precioUnitario; }
    public void setTotal(double total) { this.total = total; }
}