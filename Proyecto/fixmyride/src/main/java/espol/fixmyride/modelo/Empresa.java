package espol.fixmyride.modelo;

import java.util.ArrayList;

public class Empresa {
    // Atributos
    private String nombre;
    private String codigo;
    ArrayList<OrdenServicio> listaOrdenServicio;
    // Constructor
    public Empresa(String nombre,String codigo){
        this.nombre=nombre;
        this.codigo=codigo;
    }
    // Getters
    public String getNombre(){return nombre;}
    public String getCodigo(){return codigo;}
}