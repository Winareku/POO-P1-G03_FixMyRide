package espol.fixmyride.controlador;

// Importaciones
import espol.fixmyride.modelo.*;
import espol.fixmyride.vista.Vista;

import java.time.LocalDate;
import java.util.ArrayList;

// Clase ControladorFactura
public class ControladorFactura {
    // Atributos
    private ControladorCliente controladorCliente;

    // Constructor
    public ControladorFactura (ControladorCliente controladorCliente){ this.controladorCliente = controladorCliente; }

    // Métodos
    public Factura crearFactura(String codigoEmpresa, int anio, int mes, Empresa empresa, ArrayList<OrdenServicio> listaOrdenServicios){
        String periodo = Vista.convertirNumeroMes(mes)+", "+anio;
        boolean hayCoincidencias = false;
        for (OrdenServicio orden:listaOrdenServicios){
            // Atributos
            ArrayList<Persona> listaClientes = controladorCliente.getLista();
            Cliente cliente = (Cliente) ControladorPersona.buscarPersonaPorId(orden.getIdCliente(), listaClientes);
            LocalDate fechaOrden = orden.getFechaOrden();
            boolean correspondePeriodo = fechaOrden.getYear()==anio && fechaOrden.getMonthValue()==mes;
            // Verificaciones
            if (cliente.getEmpresa() == empresa && correspondePeriodo ){
                empresa.getListaOrdenServicio().add(orden);
                hayCoincidencias = true;
            }
        }
        Factura factura = new Factura(periodo, empresa, empresa.getListaOrdenServicio(),hayCoincidencias);
        empresa.setListaOrdenServicio(new ArrayList<>()); // Limpiar la lista de órdenes de servicio de la empresa después de crear la factura
        return factura;
    }
}