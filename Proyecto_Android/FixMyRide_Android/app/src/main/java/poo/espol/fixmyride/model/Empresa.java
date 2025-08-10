package poo.espol.fixmyride.model;

import java.util.ArrayList;

public class Empresa {
    // Atributos
    private String nombre;
    private String codigo;
    private ArrayList<OrdenServicio> listaOrdenServicio;
    // Constructor
    public Empresa(String nombre,String codigo){
        this.nombre=nombre;
        this.codigo=codigo;
        this.listaOrdenServicio = new ArrayList<>();
    }
    // Getters
    public String getNombre(){return nombre;}
    public String getCodigo(){return codigo;}
    public ArrayList<OrdenServicio> getListaOrdenServicio() { return listaOrdenServicio; }
    // Setters
    public void setListaOrdenServicio(ArrayList<OrdenServicio> listaOrdenServicio) { this.listaOrdenServicio = listaOrdenServicio; }
}