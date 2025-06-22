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
        ArrayList<ReporteAtencionTecnico> lista = controlador.getLista();
        ArrayList<Persona> listaTecnicos = controladorTecnico.getLista();
        ArrayList<OrdenServicio> listaOrdenServicios = controladorOrdenServicio.getLista();
        // Solicitudes
        String fechaString = Vista.solicitarFecha(scanner);
        /*
        for (OrdenServicio orden: listaOrdenServicios) {

        }
        */
        // Solicitar
        
        // Completar
        
    }
}