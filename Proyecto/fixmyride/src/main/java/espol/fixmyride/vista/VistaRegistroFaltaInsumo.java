package espol.fixmyride.vista;
import espol.fixmyride.controlador.*;
import espol.fixmyride.modelo.*;
import java.util.ArrayList;
import java.util.Scanner;

public class VistaRegistroFaltaInsumo {
    // Atributo controlador
    private ControladorRegistroFaltaInsumo controlador;
    // Constructor
    public VistaRegistroFaltaInsumo(ControladorRegistroFaltaInsumo controlador) { this.controlador = controlador; }
    // Método para empezar registro
    public void registrarFaltaInsumo(Scanner scanner) {
        // Mostrar registros
        Vista.caja("REGISTROS DE FALTA DE INSUMOS");
        ArrayList<RegistroFaltaInsumo> listaRFI = controlador.getListaRFI();
        for (RegistroFaltaInsumo RFI: listaRFI) { System.out.println(RFI); }
        // Menu de registrar
        Vista.caja("GENERAR REGISTRO DE FALTA DE INSUMOS");
        // Completar
    }
}