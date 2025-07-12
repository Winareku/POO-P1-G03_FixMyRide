package espol.fixmyride.vista;

// Importaciones
import espol.fixmyride.controlador.*;
import espol.fixmyride.modelo.*;

import java.util.ArrayList;
import java.util.Scanner;

public class VistaFactura {
    // Atributo controlador
    private ControladorFactura controlador;
    private ControladorOrdenServicio controladorOrdenServicio;
    private ControladorEmpresa controladorEmpresa;
    
    // Constructor
    public VistaFactura(ControladorFactura controlador, ControladorOrdenServicio controladorOrdenServicio, ControladorEmpresa controladorEmpresa) {
        this.controlador = controlador;
        this.controladorOrdenServicio = controladorOrdenServicio;
        this.controladorEmpresa = controladorEmpresa;
    }
    // Método principal
    public void generarFactura(Scanner scanner) {
        // Listas en los Controladores
        Vista.caja("Facturas");
        ArrayList<OrdenServicio> listaOrdenServicios = controladorOrdenServicio.getLista();
        Empresa empresa;

        // Solicitudes
        String codigoEmpresa = Vista.verificarEmpresaPorCodigo(scanner, controladorEmpresa.getLista());
        empresa = ControladorEmpresa.obtenerEmpresaPorCodigo(codigoEmpresa, controladorEmpresa.getLista());
        int anio = Vista.obtenerInt(scanner, "Escriba el año que desea consultar: ");
        int mes = Vista.convertirMesNumero(scanner);
        Factura factura = controlador.crearFactura(codigoEmpresa, anio, mes, empresa, listaOrdenServicios);
        Vista.separador();
        System.out.println("Empresa: "+empresa.getNombre()+"\nPeriodo de Facturación: "+factura.getPeriodo()+"\nDetalle de servicios: ");
        Vista.cajaIzquierda(Vista.stringTabla(" Placa","Fecha","Tipo","Servicio","Cantidad","Total"));
        int totalEmpresa = 0;
        for (OrdenServicio orden:factura.getListaOrdenServicio()){
            String placa = orden.getPlacaVehiculo();
            String fecha = String.valueOf(orden.getFechaOrden());
            String tipoVehiculo = Vista.stringTipoVehiculo(orden.getTipoVehiculo());
            for (DetalleServicio servicio:orden.getListaDetalleServicio()){
                String nombreServicio = servicio.getServicio().getNombre();
                String cantidad = ""+servicio.getCantidad();
                String total = ""+servicio.getTotal();
                System.out.println("  "+Vista.stringTabla(placa, fecha, tipoVehiculo, nombreServicio, ""+cantidad, "$"+total));;
                totalEmpresa += servicio.getTotal();
            }
        }
        if(factura.getHayCoincidencias()) System.out.println("\nTotal a pagar: $"+totalEmpresa);
        if(!factura.getHayCoincidencias()) System.out.println("No se encontraron coincidencias.");
    }    
}