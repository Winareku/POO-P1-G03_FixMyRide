package espol.fixmyride.vista;

// Importaciones
import espol.fixmyride.controlador.*;
import espol.fixmyride.modelo.*;
import java.util.ArrayList;
import java.util.Scanner;

// Clase VistaRegistroFaltaInsumo
public class VistaRegistroFaltaInsumo {
    // Atributo controlador
    private ControladorRegistroFaltaInsumo controlador;
    private ControladorProveedor controladorProveedor;

    // Constructor
    public VistaRegistroFaltaInsumo(ControladorRegistroFaltaInsumo controlador, ControladorProveedor controladorProveedor) {
        this.controlador = controlador;
        this.controladorProveedor = controladorProveedor;
    }

    // Método para empezar registro
    public void registrarFaltaInsumo(Scanner scanner) {
        // Mostrar registros
        Vista.caja("REGISTROS DE FALTA DE INSUMOS");
        ArrayList<RegistroFaltaInsumo> listaRFI = controlador.getLista();
        ArrayList<Persona> listaProveedor = controladorProveedor.getLista();
        for (RegistroFaltaInsumo RFI: listaRFI) { System.out.println(RFI); }
        
        // Menu de registrar
        Vista.caja("GENERAR REGISTRO DE FALTA DE INSUMOS");
        String desc= Vista.obtenerString(scanner, "Ingrese la descripción: ");
        String idProveedor = Vista.verificarPersonaPorID(scanner, listaProveedor, "proveedor");
        RegistroFaltaInsumo registro = new RegistroFaltaInsumo(desc,idProveedor);
        listaRFI.add(registro);
        System.out.println("Se registró la falta exitosamente");
    }
}