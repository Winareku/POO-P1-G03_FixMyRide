package espol.fixmyride.modelo;

public class Factura {
    // Atributos
    String nombre;
    String cedula;

    // Constructor
    public Factura(String nombre, String cedula) {
        this.nombre = nombre;
        this.cedula = cedula;
    }
    
    // Getters y Setters

    @Override
    public String toString(){ return (""); }
}