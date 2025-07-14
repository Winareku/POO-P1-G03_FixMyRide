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
    private ControladorOrdenServicio controladorOrdenServicio;

    // Constructor
    public VistaReporteIngresoServicio(ControladorReporteIngresoServicio controlador, ControladorOrdenServicio controladorOrdenServicio) {
        this.controlador = controlador;
        this.controladorOrdenServicio = controladorOrdenServicio;
    }

    // Método principal
    public void metodo(Scanner scanner) {
        // Listas en los Controladores
        Vista.caja("REPORTE DE INGRESOS POR SERVICIOS");
        ArrayList<OrdenServicio> listaOrdenServicios = controladorOrdenServicio.getLista();

        // Solicitudes con validación
        int anio = Vista.obtenerInt(scanner,"Escriba el año que desea consultar: ");
        int mes = Vista.convertirMesNumero(scanner);

        Vista.cajaIzquierda(Vista.stringTabla(" Servicio", "Total"));
        ReporteIngresoServicio reporte = controlador.crearReporteIngresoServicio(anio, mes, listaOrdenServicios);
        boolean hayCoincidencias = reporte.getHayCoincidencias();
        for (DetalleServicio servicio:reporte.getlistaServicios()){
            System.out.println("  "+Vista.stringTabla(servicio.getServicio().getNombre(), "$"+servicio.getTotal()));
        }
        if(!hayCoincidencias) System.out.println("No se encontraron coincidencias.");
    }
}