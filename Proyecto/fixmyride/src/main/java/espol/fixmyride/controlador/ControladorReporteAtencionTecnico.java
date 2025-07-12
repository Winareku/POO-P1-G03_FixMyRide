package espol.fixmyride.controlador;    

// Importaciones
import espol.fixmyride.modelo.*;
import java.time.LocalDate;
import java.util.ArrayList;

// Clase ControladorReporteAtencionTecnico
public class ControladorReporteAtencionTecnico {
    // Métodos
    public ReporteAtencionTecnico crearReporteAtencionTecnico(int anio, int mes, ArrayList<Persona> listaTecnicos, ArrayList<OrdenServicio> listaOrdenServicios) {
        ArrayList<String> listaNombreTecnicos = new ArrayList<>();
        ArrayList<Double> listaTotales = new ArrayList<>();
        boolean hayCoincidencias = false;
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
        return new ReporteAtencionTecnico(listaNombreTecnicos, listaTotales, hayCoincidencias);
    }
}