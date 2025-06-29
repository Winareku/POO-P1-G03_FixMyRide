package espol.fixmyride.vista;

// Importaciones
import espol.fixmyride.controlador.*;
import espol.fixmyride.modelo.*;
import java.util.ArrayList;
import java.util.Scanner;

// Clase VistaReporteIngresoServicio
public class VistaReporteIngresoServicio {
    // Atributo controlador
    private ControladorReporteIngresoServicio controlador;
    // Constructor
    public VistaReporteIngresoServicio(ControladorReporteIngresoServicio controlador) { this.controlador = controlador; }
    // Método principal
    public void metodo(Scanner scanner) {
        // Mostrar lista
        Vista.caja("REGISTROS DE FALTA DE INSUMOS");
        // ArrayList<ReporteIngresoServicio> lista = controlador.getLista();
        // for (ReporteIngresoServicio reporte: lista) { System.out.println(reporte); }
        // Solicitar
        Vista.caja("GENERAR REGISTRO DE FALTA DE INSUMOS");
        // Completar
    }
}