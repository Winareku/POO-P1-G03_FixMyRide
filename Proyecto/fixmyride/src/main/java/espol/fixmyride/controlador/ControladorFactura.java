package espol.fixmyride.controlador;

// Importaciones
import espol.fixmyride.modelo.*;
import espol.fixmyride.vista.Vista;

import java.time.LocalDate;
import java.util.ArrayList;

// Clase ControladorFactura
public class ControladorFactura {
    // Atributos
    ControladorCliente controladorCliente;

    // Constructor
    public ControladorFactura (ControladorCliente controladorCliente){
        this.controladorCliente = controladorCliente;
    }

    // Métodos
    public Factura crearFactura(String codigoEmpresa, int anio, int mes, Empresa empresa, ArrayList<OrdenServicio> listaOrdenServicios){
        String periodo = Vista.convertirNumeroMes(mes) + anio;
        ArrayList<OrdenServicio> listaOrdenServicioPorEmpresa = new ArrayList<>();
        boolean hayCoincidencias = false;
        for (OrdenServicio orden:listaOrdenServicios){
            // Atributos
            ArrayList<Persona> listaClientes = controladorCliente.getLista();
            Cliente cliente = (Cliente) ControladorPersona.buscarPersonaPorId(orden.getIdCliente(), listaClientes);
            LocalDate fechaOrden = orden.getFechaOrden();
            boolean correspondePeriodo = fechaOrden.getYear()==anio && fechaOrden.getMonthValue()==mes;
            // Verificaciones
            if (cliente.getEmpresa() == empresa && correspondePeriodo ){
                listaOrdenServicioPorEmpresa.add(orden);
                hayCoincidencias = true;
            }
        }
        return new Factura(periodo, empresa, listaOrdenServicioPorEmpresa,hayCoincidencias);
    }
}