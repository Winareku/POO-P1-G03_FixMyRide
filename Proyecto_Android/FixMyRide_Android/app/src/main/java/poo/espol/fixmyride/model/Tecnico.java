package poo.espol.fixmyride.model;

public class Tecnico extends Persona {
    // Atributos
    private String especialidad;

    // Constructor
    public Tecnico(String id, String nombre, String telefono, String especialidad) {
        super(id, nombre, telefono);
        this.especialidad = especialidad;
    }
    
    // Getters y Setters
    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    @Override
    public String toString() { return getNombre(); }
    // public String toString() { return (super.toString() + ", " + especialidad); }
}