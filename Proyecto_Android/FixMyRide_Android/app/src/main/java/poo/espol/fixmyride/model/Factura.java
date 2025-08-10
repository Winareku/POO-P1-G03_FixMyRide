package poo.espol.fixmyride.model;

import java.util.ArrayList;

public class Factura {
    // Atributos
    private String periodo;
    private Empresa empresa;
    private ArrayList<OrdenServicio> listaOrdenServicio;
    boolean hayCoincidencias = false;

    // Constructor
    public Factura(String periodo, Empresa empresa, ArrayList<OrdenServicio> listaOrdenServicio, boolean hayCoincidencias) {
        this.periodo = periodo;
        this.empresa = empresa;
        this.listaOrdenServicio = listaOrdenServicio;
        this.hayCoincidencias = hayCoincidencias;
    }
    
    // Getters y Setters
    public String getPeriodo() {return periodo;}
    public Empresa getEmpresa() {return empresa;}
    public ArrayList<OrdenServicio> getListaOrdenServicio() {return listaOrdenServicio;}
    public boolean getHayCoincidencias() {return hayCoincidencias;}

    @Override
    public String toString(){ return (""); }
}