package poo.espol.fixmyride;

public class Proveedor {
    public String identificacion;
    public String nombre;
    public String telefono;
    public String descripcion;

    public Proveedor(String identificacion, String nombre, String telefono, String descripcion) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.telefono = telefono;
        this.descripcion = descripcion;
    }
}