package espol.fixmyride.modelo;
import java.util.ArrayList;

public class ReporteAtencionTecnico {
    // Atributos
    ArrayList<String> listaNombreTecnicos;
    ArrayList<Double> listaTotales;
    boolean hayCoincidencias;
    // Constructor
    public ReporteAtencionTecnico(ArrayList<String> listaNombreTecnicos, ArrayList<Double> listaTotales, boolean hayCoincidencias) {
        this.listaNombreTecnicos = listaNombreTecnicos;
        this.listaTotales = listaTotales;
        this.hayCoincidencias = hayCoincidencias;
    }
    // Getters y Setters
    public ArrayList<String> getListaNombreTecnicos() { return listaNombreTecnicos; }
    public ArrayList<Double> getListaTotales() { return listaTotales; }
    public boolean getHayCoincidencias() { return hayCoincidencias; }
}
