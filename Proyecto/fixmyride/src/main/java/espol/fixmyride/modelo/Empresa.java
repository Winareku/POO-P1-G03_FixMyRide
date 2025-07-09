package espol.fixmyride.modelo;

public class Empresa {
    // Atributos
    private String nombre;
    private String codigo;
    // Constructor
    public Empresa(String nombre,String codigo){
        this.nombre=nombre;
        this.codigo=codigo;
    }
    // Getters
    public String getNombre(){return nombre;}
    public String getCodigo(){return codigo;}
}