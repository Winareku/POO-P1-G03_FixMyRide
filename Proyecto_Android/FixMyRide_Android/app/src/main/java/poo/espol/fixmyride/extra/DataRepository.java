package poo.espol.fixmyride.extra;
import java.time.LocalDate;
import java.util.ArrayList;
import poo.espol.fixmyride.model.*;

public class DataRepository {

    // Variables
    private static boolean datosCargados = false;
    private static ArrayList<Cliente> listaCliente = new ArrayList<>();
    private static ArrayList<Tecnico> listaTecnico = new ArrayList<>();
    private static ArrayList<Proveedor> listaProveedor = new ArrayList<>();
    private static ArrayList<Servicio> listaServicio = new ArrayList<>();
    private static ArrayList<OrdenServicio> listaOrdenServicio = new ArrayList<>();

    // Getters
    public static ArrayList<Cliente> getClientes() {return listaCliente;}
    public static ArrayList<Tecnico> getTecnicos() {return listaTecnico;}
    public static ArrayList<Proveedor> getProveedores() {return listaProveedor;}
    public static ArrayList<Servicio> getServicios() {return listaServicio;}
    public static ArrayList<OrdenServicio> getOrdenServicios() {return listaOrdenServicio;}

    // Métodos para Agregar
    public static void addCliente(Cliente cliente) {listaCliente.add(cliente);}
    public static void addTecnico(Tecnico tecnico) {listaTecnico.add(tecnico);}
    public static void addProveedor(Proveedor proveedor) {listaProveedor.add(proveedor);}
    public static void addServicio(Servicio servicio) {listaServicio.add(servicio);}
    public static void addOrdenServicio(OrdenServicio ordenServicio) {listaOrdenServicio.add(ordenServicio);}

    // Carga de Datos
    public static void cargarDatos() {

        // Clientes
        addCliente(new Cliente("0911111111", "Paul Garcia", "Sauces", "0944444444", TipoCliente.PERSONAL));
        addCliente(new Cliente("0922222222", "Raul Menendez", "Urdesa", "0955555555", TipoCliente.EMPRESARIAL));
        addCliente(new Cliente("0933333333", "Daniela Molina", "Via la Costa", "0966666666", TipoCliente.PERSONAL));

        // Tecnicos
        addTecnico(new Tecnico("1000000001", "Juan Pérez", "555-1234", "Mecánica General"));
        addTecnico(new Tecnico("1000000002", "Ana Gómez", "555-5678", "Electricidad Automotriz"));
        addTecnico(new Tecnico("1000000003", "Luis Sánchez", "555-8765", "Diagnóstico de Computadoras de Autos"));

        // Proveedores
        addProveedor(new Proveedor("0977777777", "Repuestos R.C.", "555-1234", "Suministro de repuestos para vehículos."));
        addProveedor(new Proveedor("0988888888", "Herramientas y Equipos HOPE", "555-5678", "Proveedor de herramientas y equipos especializados para talleres mecánicos."));
        addProveedor(new Proveedor("0999999999", "Lubricantes y Aceites JK", "555-9101", "Venta de lubricantes y aceites automotrices para mantenimiento."));

        // Servicios
        addServicio(new Servicio("Cambio de aceite",32.5));
        addServicio(new Servicio("Revisión de frenos",48.0));
        addServicio(new Servicio("Alineación y balanceo",42.0));
        addServicio(new Servicio("Reparación de motor",250.0));
        addServicio(new Servicio("Diagnóstico electrónico",60.0));
        addServicio(new Servicio("Lavado y detallado",25.0));

        // Órdenes de Servicio
        OrdenServicio o1 = new OrdenServicio("0911111111", "1000000001", LocalDate.of(2025,7,15), TipoVehiculo.AUTOMOVIL, "ABC-1234");
        o1.setTotalOrden(150.00);
        OrdenServicio o2 = new OrdenServicio("0922222222", "1000000002", LocalDate.of(2025,7,10), TipoVehiculo.BUS, "XYZ-9876");
        o2.setTotalOrden(230.50);
        OrdenServicio o3 = new OrdenServicio("0933333333", "1000000001", LocalDate.of(2025,6,20), TipoVehiculo.MOTOCICLETA, "JKL-4567");
        o3.setTotalOrden(85.75);
        OrdenServicio o4 = new OrdenServicio("0911111111", "1000000003", LocalDate.of(2025,5,10), TipoVehiculo.AUTOMOVIL, "MNO-2222");
        o4.setTotalOrden(300.00);
        addOrdenServicio(o1);
        addOrdenServicio(o2);
        addOrdenServicio(o3);
        addOrdenServicio(o4);
    }
}
