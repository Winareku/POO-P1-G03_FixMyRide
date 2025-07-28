package espol.fixmyride.vista;

// Importaciones
import espol.fixmyride.controlador.*;
import espol.fixmyride.modelo.*;
import java.util.ArrayList;
import java.util.Scanner;

// Clase VistaTecnico
public class VistaTecnico extends VistaPersona {
    // Atributos
    private ControladorTecnico controladorTecnico = (ControladorTecnico) getControlador();

    // Constructor
    public VistaTecnico(ControladorPersona controlador) { super(controlador); }

    // Método para mostrar el submenu de técnicos
    public static void mostrarSubmenu(ArrayList<Persona> listaPersonas, String clase) {
        Vista.caja(clase.toUpperCase());
        for (Persona persona : listaPersonas) { System.out.println(persona); }
        Vista.abrirCaja("ADMINISTRAR " + clase.toUpperCase());
        Vista.lateralCaja("1. Agregar " + clase);
        Vista.lateralCaja("2. Eliminar " + clase);
        Vista.lateralCaja("3. Regresar al menú principal");
        Vista.cerrarCaja();
        System.out.print("Seleccione una opción: ");
    }
    
    // Método para agregar un nuevo técnico
    public void agregarPersona(Scanner scanner) {
        Vista.caja("INGRESAR DATOS");
        String id = Vista.obtenerString(scanner, "Cédula o RUC del técnico: ");
        String nombre = Vista.obtenerString(scanner, "Nombre del técnico: ");
        String telefono = Vista.obtenerString(scanner, "Teléfono del técnico: ");
        String especialidad = Vista.obtenerString(scanner, "Especialidad del técnico: ");
        System.out.println(controladorTecnico.agregarTecnico(id, nombre, telefono, especialidad));
    }

    // Método para eliminar un técnico
    public void eliminarPersona(Scanner scanner) {
    	Vista.caja("Eliminar Técnico");
		while(true) {
    		String id = Vista.obtenerString(scanner, "Cédula o RUC del técnico: ");
    		ArrayList<Persona> lista = controladorTecnico.getLista();
			Tecnico tecnico = (Tecnico) ControladorTecnico.buscarPersonaPorId(id, lista);
    		if(!(tecnico==null)) {
    			System.out.println(controladorTecnico.eliminarTecnico(tecnico, lista));
    			return;
    		}
            Vista.opcionNoValida();
    	}
    }
}
