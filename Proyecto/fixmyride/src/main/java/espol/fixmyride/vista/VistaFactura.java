package espol.fixmyride.vista;
import espol.fixmyride.controlador.*;
import espol.fixmyride.modelo.*;
import java.util.ArrayList;
import java.util.Scanner;

public class VistaFactura {
    // Atributo controlador
    private ControladorFactura controlador;
    // Constructor
    public VistaFactura(ControladorFactura controlador) { this.controlador = controlador; }
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