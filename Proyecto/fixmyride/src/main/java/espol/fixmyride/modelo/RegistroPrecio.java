package espol.fixmyride.modelo;
import java.time.LocalDate;

public class RegistroPrecio {

    // Atributos
    private Servicio servicio;
    private double precio;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    // Constructor
    public RegistroPrecio(Servicio servicio, double precio) {
        this.servicio = servicio;
        this.precio = precio;
        this.fechaInicio = servicio.getFechaInicio();
        this.fechaFin = servicio.getFechaFin();
    }

    // Getters y Setters
    public Servicio getServicio() { return servicio; }
    public void setServicio(Servicio servicio) { this.servicio = servicio; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }

    @Override
    public String toString() { return "Servicio: " + servicio.getNombre() + ", Precio: " + precio + ", Fecha Inicio: " + fechaInicio + ", Fecha Fin: " + fechaFin; }
}