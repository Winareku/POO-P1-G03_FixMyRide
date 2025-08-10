package poo.espol.fixmyride.model;

public class Tecnico {
    private String identificacion;
    private String nombre;
    private String telefono;
    private String especialidad;

    public Tecnico(String identificacion, String nombre, String telefono, String especialidad) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.telefono = telefono;
        this.especialidad = especialidad;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEspecialidad() {
        return especialidad;
    }
}