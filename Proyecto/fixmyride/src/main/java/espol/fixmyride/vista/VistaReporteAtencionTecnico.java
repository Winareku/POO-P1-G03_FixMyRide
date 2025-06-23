package espol.fixmyride.vista;
import espol.fixmyride.controlador.*;
import espol.fixmyride.modelo.*;

import java.time.LocalDate;
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
        ArrayList<ReporteAtencionTecnico> listaReporteAtencionTecnico = controlador.getLista();
        ArrayList<Persona> listaTecnicos = controladorTecnico.getLista();
        ArrayList<OrdenServicio> listaOrdenServicios = controladorOrdenServicio.getLista();
        // Lista de Nombres y Totales por Técnico
        ArrayList<String> listaNombreTecnicos = new ArrayList<>();
        ArrayList<Double> listaTotales = new ArrayList<>();
        // Solicitudes
        boolean hayCoincidencias = false;
        int anio = Vista.obtenerInt(scanner,"Escriba el año que desea consultar: ");
        int mes = Vista.obtenerInt(scanner,"Escriba el mes que desea consultar: ");
        Vista.caja("Técnico        Total    ");
        for (OrdenServicio orden:listaOrdenServicios) {
            LocalDate fechaOrden = orden.getFechaOrden();
            String idTecnico = orden.getIdTecnico();
            Persona tecnico = ControladorPersona.buscarPersonaPorId(idTecnico, listaTecnicos);
            if (fechaOrden.getYear()==anio && fechaOrden.getMonthValue()==mes) {
                String nombreTecnico = tecnico.getNombre();
                double total = orden.getTotalOrden();
                int indiceComun;
                boolean nombreEnLista = false;
                for (String nombre:listaNombreTecnicos) { if (nombre.equals(nombreTecnico)) nombreEnLista = true; }
                if (!nombreEnLista) listaNombreTecnicos.add(nombreTecnico);
                if (listaNombreTecnicos.indexOf(nombreTecnico)<listaTotales.size()) {
                    indiceComun = listaNombreTecnicos.indexOf(nombreTecnico);
                    listaTotales.set(indiceComun, listaTotales.get(indiceComun) + total);
                } else { listaTotales.add(total); }
                hayCoincidencias = true;
            }
        }
        for (int i=0;i<listaNombreTecnicos.size();i++) {
            System.out.println(listaNombreTecnicos.get(i)+"\t\t"+listaTotales.get(i));
        }
        if(!hayCoincidencias) System.out.println("No se encontraron coincidencias.");
    }
}