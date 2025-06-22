package espol.fixmyride.vista;
import espol.fixmyride.controlador.*;
import espol.fixmyride.modelo.*;
import java.util.ArrayList;
import java.util.Scanner;

public class VistaReporteAtencionTecnico {
    // Atributo controlador
    private ControladorReporteAtencionTecnico controlador;
    // Constructor
    public VistaReporteAtencionTecnico(ControladorReporteAtencionTecnico controlador) { this.controlador = controlador; }
    // Método principal
    public void metodo(Scanner scanner) {
        // Mostrar lista
        Vista.caja("");
        ArrayList<ReporteAtencionTecnico> lista = controlador.getLista();
        for (ReporteAtencionTecnico objeto: lista) { System.out.println(objeto); }
        // Solicitar
        Vista.caja("");
        // Completar
        
    }
}