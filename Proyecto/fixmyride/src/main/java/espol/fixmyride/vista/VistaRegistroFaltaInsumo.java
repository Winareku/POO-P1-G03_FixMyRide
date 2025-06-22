package espol.fixmyride.vista;
import espol.fixmyride.controlador.*;
import espol.fixmyride.modelo.*;
import java.util.ArrayList;
import java.util.Scanner;

public class VistaRegistroFaltaInsumo {
    Scanner sc= new Scanner(System.in);
    // Atributo controlador
    private ControladorRegistroFaltaInsumo controlador;
    // Constructor
    public VistaRegistroFaltaInsumo(ControladorRegistroFaltaInsumo controlador) { this.controlador = controlador; }
    // Método para empezar registro
    public void registrarFaltaInsumo(Scanner scanner) {
        // Mostrar registros
        Vista.caja("REGISTROS DE FALTA DE INSUMOS");
        ArrayList<RegistroFaltaInsumo> listaRFI = controlador.getListaRFI();
        Vista.separador();
        System.out.println("A continuación se muestra un lista de los insumos faltantes:"+"\n");
        for (RegistroFaltaInsumo RFI: listaRFI) { System.out.println(RFI); }
        // Menu de registrar
        Vista.separador();
        String R= Vista.obtenerString(sc, "¿Desea generar un nuevo registro de falta de insumo?(Si, No)");
        if(R.toLowerCase().equals("si")){
            Vista.caja("GENERAR REGISTRO DE FALTA DE INSUMOS");
            String desc= Vista.obtenerString(sc, "Ingrese la descripción de la falta: ");
            String id_pro= Vista.obtenerString(sc,"Ingrese el id del proveedor a contactar: ");
            System.out.println("Se registró la falta exitosamente"+"\n");
        }
        
        
        
        // Completar
    }
}