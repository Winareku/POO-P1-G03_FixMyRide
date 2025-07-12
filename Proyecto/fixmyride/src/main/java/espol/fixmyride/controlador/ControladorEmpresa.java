package espol.fixmyride.controlador;

// Importaciones
import espol.fixmyride.modelo.*;
import java.util.ArrayList;

public class ControladorEmpresa {
    // Lista en memoria para almacenar
    public static ArrayList<Empresa> lista;

    // Método para obtener la lista
    public ArrayList<Empresa> getLista() { return lista; }
    
    // Constructor
    public ControladorEmpresa() { lista = new ArrayList<>(); }
    
    // Métodos
    public void agregarEmpresa(String nombre,String codigo) { lista.add(new Empresa(nombre, codigo)); }
    public static Empresa obtenerEmpresaPorCodigo(String codigo, ArrayList<Empresa> lista){
        for (Empresa empresa : lista) { if (empresa.getCodigo().equals(codigo)) return empresa; }
        return null;
    }
}
