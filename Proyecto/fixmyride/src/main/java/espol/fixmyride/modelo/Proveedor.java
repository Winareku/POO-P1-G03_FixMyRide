package espol.fixmyride.modelo;

public class Proveedor extends Persona {
    
    // Atributos
    private String descripcion;


    // Constructor
    public Proveedor(String id, String nombre, String telefono, String descripcion) {
        super(id, nombre, telefono);
        this.descripcion = descripcion;
    }
    

    // Getters y Setters
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    // Override toString para mostrar la información del proveedor
    @Override
    public String toString() { return (super.toString() + ", " + descripcion); }

}