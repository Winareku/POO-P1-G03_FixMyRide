package espol.fixmyride.modelo;

public class Persona {
    // Atributos
    private String id;
    private String nombre;
    private String telefono;

    // Constructor
    public Persona(String id, String nombre, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
    }
    
    // Getters y Setters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getTelefono() { return telefono; }
    public void setId(String id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    @Override
    public String toString() { return (id + ", " + nombre + ", " + telefono); }
}