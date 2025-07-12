package espol.fixmyride.vista;

// Importaciones
import espol.fixmyride.controlador.*;
import espol.fixmyride.modelo.*;
import java.util.ArrayList;
import java.util.Scanner;

// Clase VistaReporteAtencionTecnico
public class VistaReporteAtencionTecnico {
    // Atributo controlador
    private ControladorReporteAtencionTecnico controlador;
    private ControladorTecnico controladorTecnico;
    private ControladorOrdenServicio controladorOrdenServicio;

    // Constructor
    public VistaReporteAtencionTecnico(ControladorReporteAtencionTecnico controlador, ControladorTecnico controladorTecnico, ControladorOrdenServicio controladorOrdenServicio) {
        this.controlador = controlador;
        this.controladorTecnico = controladorTecnico;
        this.controladorOrdenServicio = controladorOrdenServicio;
    }

    // Método principal
    public void generarReporte(Scanner scanner) {
        // Listas en los Controladores
        Vista.caja("Reporte de atenciones por técnico");
        ArrayList<Persona> listaTecnicos = controladorTecnico.getLista();
        ArrayList<OrdenServicio> listaOrdenServicios = controladorOrdenServicio.getLista();

        // Solicitudes con validación
        int anio = Vista.obtenerInt(scanner,"Escriba el año que desea consultar: ");
        int mes = Vista.convertirMesNumero(scanner);
        
        Vista.cajaIzquierda(Vista.stringTabla(" Técnico", "Total"));
        ReporteAtencionTecnico reporte = controlador.crearReporteAtencionTecnico(anio, mes, listaTecnicos, listaOrdenServicios);
        
        // Lista de Nombres y Totales por Técnico
        ArrayList<String> listaNombreTecnicos = reporte.getListaNombreTecnico();
        ArrayList<Double> listaTotales = reporte.getListaTotales();
        boolean hayCoincidencias = reporte.getHayCoincidencias();
        
        // Iteración de ambas listas para imprimir los resultados
        for (int i=0;i<listaNombreTecnicos.size();i++) { System.out.println("  "+Vista.stringTabla(listaNombreTecnicos.get(i), "$"+listaTotales.get(i))); }
        if(!hayCoincidencias) System.out.println("No se encontraron coincidencias.");
    }
}
