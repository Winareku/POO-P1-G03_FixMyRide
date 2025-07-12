package espol.fixmyride.modelo;

import java.util.ArrayList;

public class ReporteIngresoServicio {
    // Atributos
    ArrayList<DetalleServicio> listaServicios;
    boolean hayCoincidencias;

    // Constructor
    public ReporteIngresoServicio(ArrayList<DetalleServicio> listaServicios, boolean hayCoincidencias){
        this.listaServicios=listaServicios;
        this.hayCoincidencias = hayCoincidencias;
    }

    // Getters y Setters
    public ArrayList<DetalleServicio> getlistaServicios(){ return listaServicios; }
    public boolean getHayCoincidencias(){ return hayCoincidencias; }

    @Override
    public String toString(){ return (""); }
}