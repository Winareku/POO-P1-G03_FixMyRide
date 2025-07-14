package espol.fixmyride.controlador;

// Importaciones
import espol.fixmyride.modelo.*;
import espol.fixmyride.vista.Vista;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

// Clase ControladorOrdenServicio
public class ControladorOrdenServicio {
    // Atributos
    public static int contadorOrden = 0;

    // Lista en memoria para almacenar las órdenes
    public ArrayList<OrdenServicio> listaOrdenes;
    
    // Método para obtener la lista de órdenes
    public ArrayList<OrdenServicio> getLista() { return listaOrdenes; }
    
    // Constructor
    public ControladorOrdenServicio() { listaOrdenes = new ArrayList<>(); }
    
    // Método para agregar una orden
    public OrdenServicio agregarOrdenServicio(String idCliente, String idTecnico, LocalDate fechaServicio, TipoVehiculo tipoVehiculo, String placaVehiculo) {
        OrdenServicio orden = new OrdenServicio(idCliente, idTecnico, fechaServicio, tipoVehiculo, placaVehiculo);
        orden.setNumeroOrden(++contadorOrden);
        listaOrdenes.add(orden);
        return orden;
    }

    // Método para añadir DetalleServicio a una orden
    public void agregarDetalleServicio(OrdenServicio orden, DetalleServicio detalle) {
        orden.getListaDetalleServicio().add(detalle);
        System.out.println("Servicio agregado a la orden #" + orden.getNumeroOrden());
    }
    
    // Método para solicitar servicios
    public void solicitarOrdenes(Scanner scanner, ControladorServicio controladorServicio, OrdenServicio orden) {
        String codigoServicio;
        while(true) {
            // Solicitar y validar el código del servicio
            codigoServicio = Vista.obtenerString(scanner,"Ingrese el código del servicio: ");
            if (codigoServicio.equals("-1")){break;}
            Servicio servicio = controladorServicio.obtenerServicioPorCodigo(codigoServicio);
            if (servicio == null){Vista.opcionNoValida();continue;}
            // Solicitar la cantidad del servicio y crear detalles
            int cantidadServicio = Vista.obtenerInt(scanner,"Ingrese la cantidad del servicio: ");
            DetalleServicio detalleServicio = new DetalleServicio(servicio, cantidadServicio);
            agregarDetalleServicio(orden, detalleServicio);
            orden.setTotalOrden(orden.getTotalOrden()+detalleServicio.getTotal());
            System.out.println("Orden registrada. Total a pagar: $"+orden.getTotalOrden());
        }
    }

    // Método para actualizar el total de la orden
    public static void actualizarTotal(OrdenServicio orden){
        for (DetalleServicio detalleServicio:orden.getListaDetalleServicio()){ orden.setTotalOrden(orden.getTotalOrden()+detalleServicio.getTotal()); }
    }
}