package espol.fixmyride.vista;

// Importaciones
import espol.fixmyride.controlador.*;
import espol.fixmyride.modelo.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

// Clase VistaOrdenServicio
public class VistaOrdenServicio {
    // Atributo controlador
    private ControladorOrdenServicio controlador;
    private ControladorServicio controladorServicio;
    private ControladorCliente controladorCliente;
    private ControladorTecnico controladorTecnico;

    // Constructor
    public VistaOrdenServicio(ControladorOrdenServicio controlador, ControladorServicio controladorServicio, ControladorCliente controladorCliente, ControladorTecnico controladorTecnico) {
        this.controlador = controlador;
        this.controladorServicio = controladorServicio;
        this.controladorCliente = controladorCliente;
        this.controladorTecnico = controladorTecnico;
    }

    // Método para mostrar el submenu de órdenes
    public void generarOrden(Scanner scanner) {
        // Mostrar órdenes actuales
        Vista.caja("ORDENES DE SERVICIOS");
        ArrayList<OrdenServicio>listaOrdenes = controlador.getLista();
        for (OrdenServicio orden: listaOrdenes) { System.out.println(orden); }

        // Menu de generar órdenes
        Vista.caja("GENERAR ORDEN DE SERVICIOS");
        String idCliente = Vista.verificarPersonaPorID(scanner, controladorCliente.getLista(), "cliente");
        String idTecnico = Vista.verificarPersonaPorID(scanner, controladorTecnico.getLista(), "técnico");
        String fechaString = Vista.solicitarFecha(scanner);
        
        // Validación del tipo de vehículo
        TipoVehiculo tipoVehiculo = Vista.obtenerTipoVehiculo(scanner);
        String placaVehiculo = Vista.obtenerString(scanner,"Placa del vehículo: ");
        
        // Agregar orden de servicios
        OrdenServicio orden = controlador.agregarOrdenServicio(idCliente, idTecnico,LocalDate.parse(fechaString), tipoVehiculo, placaVehiculo);
        
        // Se mantiene pidiendo códigos de servicios y cantidad hasta que escriba -1
        controlador.solicitarOrdenes(scanner, controladorServicio, orden);
    }
}