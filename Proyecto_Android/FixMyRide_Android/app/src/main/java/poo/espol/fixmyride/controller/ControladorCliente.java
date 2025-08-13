package poo.espol.fixmyride.controller;

// Importaciones
import java.util.ArrayList;

import poo.espol.fixmyride.model.*;

// Clase ControladorCliente
public class ControladorCliente extends ControladorPersona {
    // Atributos
    private ControladorEmpresa controladorEmpresa;

    // Constructor    
    public ControladorCliente(ControladorEmpresa controladorEmpresa) {
        super();
        this.controladorEmpresa = controladorEmpresa;
    }
    
    // Métodos para agregar un cliente
    public String agregarClientePersonal(String id, String nombre, String telefono, String direccion, TipoCliente tipoCliente) {
        Persona clienteExistente = ControladorPersona.buscarPersonaPorId(id, getLista());
        if (clienteExistente == null) {
            Cliente cliente = new Cliente(id, nombre, telefono, direccion, tipoCliente);
            agregarPersona(cliente);
            return "Cliente agregado exitosamente";
        }
        return "El cliente ya existe";
    }
    public String agregarClienteEmpresarial(String id, String nombre, String telefono, String direccion, TipoCliente tipoCliente, String codigoEmpresa){
        Persona clienteExistente = ControladorPersona.buscarPersonaPorId(id, getLista());
        if (clienteExistente == null) {
            Cliente cliente = new Cliente(id, nombre, telefono, direccion, tipoCliente);
            Empresa empresa = ControladorEmpresa.obtenerEmpresaPorCodigo(codigoEmpresa, controladorEmpresa.getLista());
            cliente.setEmpresa(empresa);
            agregarPersona(cliente);
            return "Cliente agregado exitosamente";
        }
        return "El cliente ya existe";
    }

    // Método estático para buscar un cliente por ID en una lista
    public static Cliente buscarClientePorId(String id, ArrayList<Cliente> lista) {
        for (Cliente cliente : lista) { if (cliente.getId().equals(id)) return cliente; }
        return null;
    }
}