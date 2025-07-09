package espol.fixmyride.vista;

// Importaciones
import espol.fixmyride.controlador.*;
import espol.fixmyride.modelo.*;
import java.util.ArrayList;
import java.util.Scanner;

public class VistaFactura {
    // Atributo controlador
    private ControladorFactura controlador;
    private ControladorOrdenServicio controladorOrdenServicio;
    
    // Constructor
    public VistaFactura(ControladorFactura controlador, ControladorOrdenServicio controladorOrdenServicio) {
        this.controlador = controlador;
        this.controladorOrdenServicio = controladorOrdenServicio;
    }
    // Método principal
    public void metodo(Scanner scanner) {
        // Mostrar lista
        Vista.caja("Facturas");
        //ArrayList<Factura> lista = controlador.getLista();
            //for (Factura factura: lista) { System.out.println(factura); }
            // Solicitar
        Vista.caja("Generar factura");
        // Completar
    }    
}