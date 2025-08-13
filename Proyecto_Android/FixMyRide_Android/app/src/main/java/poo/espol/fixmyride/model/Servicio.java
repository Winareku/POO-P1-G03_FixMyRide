package poo.espol.fixmyride.model;
import java.time.LocalDate;

public class Servicio {
    private String codigo;
    private String nombre;
    private double precio;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    //private ArrayList<RegistroPrecio> historialPrecios;
    private static int conteo = 1;

    public Servicio(String nombre, double precio) {
        this.codigo = generarCodigo(conteo);
        this.nombre = nombre;
        this.precio = precio;
        this.fechaInicio = LocalDate.now();
        this.fechaFin = this.fechaInicio.plusMonths(1);
        //this.historialPrecios = new ArrayList<>();
        //historialPrecios.add(new RegistroPrecio(this, precio));
        conteo++;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }
    //public ArrayList<RegistroPrecio> getHistorialPrecios() { return historialPrecios; }

    public static String generarCodigo(int conteo) { return String.format("%06d",conteo); }

    public void editarPrecio(double nuevoPrecio) {
        setPrecio(nuevoPrecio);;
        //historialPrecios.add(new RegistroPrecio(this, nuevoPrecio));
    }

    @Override
    public String toString() { return getNombre();}
}
