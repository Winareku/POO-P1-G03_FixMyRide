package poo.espol.fixmyride.controller;

// Importaciones
import poo.espol.fixmyride.model.*;
import java.util.ArrayList;

// Clase ControladorDetalleServicio
public class ControladorDetalleServicio {
    // Lista en memoria para almacenar
    private static ArrayList<DetalleServicio> lista;
    
    // Constructor
    public ControladorDetalleServicio() { lista = new java.util.ArrayList<>(); }
    
    //Metodo para agregar detalle de un servicio
    public void agregarDetalleServicio(DetalleServicio detalleServicio){ this.getLista().add(detalleServicio); }
    
    // Método para obtener la lista de detalles
    public  ArrayList<DetalleServicio>  getLista() { return lista; }
}