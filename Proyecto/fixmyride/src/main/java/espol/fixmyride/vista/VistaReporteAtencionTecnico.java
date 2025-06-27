package espol.fixmyride.vista;
import espol.fixmyride.controlador.*;
import espol.fixmyride.modelo.*;
import java.util.ArrayList;
import java.util.Scanner;

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
        // Listas de Controladores
        Vista.caja("Reporte de atenciones por técnico");
        ArrayList<Persona> listaTecnicos = controladorTecnico.getLista();
        ArrayList<OrdenServicio> listaOrdenServicios = controladorOrdenServicio.getLista();
        // Solicitudes
        int anio = Vista.obtenerInt(scanner,"Escriba el año que desea consultar: ");
        int mes = Vista.obtenerInt(scanner,"Escriba el mes que desea consultar: ");
        Vista.caja("Técnico        Total    ");
        ReporteAtencionTecnico reporte = controlador.crearListaTotalesPorTecnico(anio, mes, listaTecnicos, listaOrdenServicios);
        // Lista de Nombres y Totales por Técnico
        ArrayList<String> listaNombreTecnicos = reporte.getListaNombreTecnicos();
        ArrayList<Double> listaTotales = reporte.getListaTotales();
        boolean hayCoincidencias = reporte.getHayCoincidencias();
        // Iteración de ambas listas para imprimir los resultados
        for (int i=0;i<listaNombreTecnicos.size();i++) { System.out.println(listaNombreTecnicos.get(i)+"\t\t"+listaTotales.get(i)); }
        if(!hayCoincidencias) System.out.println("No se encontraron coincidencias.");
    }
}