package poo.espol.fixmyride.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

import poo.espol.fixmyride.R;
import poo.espol.fixmyride.adapter.DetalleServicioAdapter;
import poo.espol.fixmyride.adapter.GenerarFacturaAdapter;
import poo.espol.fixmyride.extra.DataRepository;
import poo.espol.fixmyride.model.Empresa;
import poo.espol.fixmyride.model.Factura;
import poo.espol.fixmyride.model.OrdenServicio;

public class GenerarFacturaActivity extends AppCompatActivity implements GenerarFacturaAdapter.OnDetalleClickListener {

    // Variables
    private ArrayList<Factura> listaFacturas;
    private RecyclerView rvFacturas;
    private GenerarFacturaAdapter adapter;
    
    private static final String FILE_NAME = "servicios.ser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_facturas); // Layout para mostrar el listado

        //Intenta cargar la lista (De-Serializa)
        list= UtilSerializable.cargarLista(FILE_NAME);

        if (list == null){
            // Inicializa con 3 facturas por defecto si la lista está vacia.
            list = DataRepository.getFacturas();
            //Luego serializa
            UtilSerializable.guardarLista(list,FILE_NAME);
        }

        rvFacturas = findViewById(R.id.rvFacturas);
        rvFacturas.setLayoutManager(new LinearLayoutManager(this));
        adapter = new GenerarFacturaAdapter(listaFacturas, this);
        rvFacturas.setAdapter(adapter);

        Button btnGenerarFactura = findViewById(R.id.btnGenerarFactura);
        btnGenerarFactura.setOnClickListener(v -> mostrarDialogoGenerarFactura());
    }

    private void mostrarDialogoGenerarFactura() {
        // Se infla el layout para generar factura dentro de un dialogo
        View dialogView = getLayoutInflater().inflate(R.layout.activity_generar_factura, null);

        Spinner spinnerEmpresa = dialogView.findViewById(R.id.spinnerEmpresa);
        EditText etAnio = dialogView.findViewById(R.id.etAnio);
        Spinner spinnerMes = dialogView.findViewById(R.id.spinnerMes);
        Button btnGenerar = dialogView.findViewById(R.id.btnGenerar);

        // Ocultar la sección de detalles de servicio inicialmente
        LinearLayout llDetalleServicios = dialogView.findViewById(R.id.llDetalleServicios);

        // Spinner de empresas
        ArrayAdapter<Empresa> empresaAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, DataRepository.getEmpresas());
        empresaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEmpresa.setAdapter(empresaAdapter);

        // Spinner de meses
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        ArrayAdapter<String> mesesAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, meses);
        mesesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMes.setAdapter(mesesAdapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Generar Factura")
                .setView(dialogView)
                .setNegativeButton("Cerrar", null);

        AlertDialog dialog = builder.create();
        dialog.show();

        // Botón Generar dentro del diálogo
        btnGenerar.setOnClickListener(v -> {
            Empresa empresaSeleccionada = (Empresa) spinnerEmpresa.getSelectedItem();
            String anioStr = etAnio.getText().toString().trim();
            String mesStr = (String) spinnerMes.getSelectedItem();

            if (empresaSeleccionada == null || anioStr.isEmpty() || mesStr == null) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                return;
            }

            int anio = Integer.parseInt(anioStr);
            Month mesEnum = Month.valueOf(mesStr.toUpperCase(Locale.getDefault()));

            // Obtener órdenes de servicio para la empresa y periodo
            ArrayList<OrdenServicio> ordenesDelPeriodo = new ArrayList<>();
            double totalAPagar = 50.0; // Costo fijo por prioridad
            boolean hayCoincidencias = false;
            
            for (OrdenServicio orden : DataRepository.getOrdenServicios()) {
                LocalDate fechaOrden = orden.getFechaOrden();
                if (fechaOrden.getYear() == anio && fechaOrden.getMonth() == mesEnum) {
                    // Simular la busqueda de la empresa dentro de la orden
                    // Asumimos que la orden tiene una referencia al cliente y el cliente a la empresa
                    // Para este ejemplo, simplificamos la lógica a una coincidencia de código
                    if (orden.getIdCliente().equals("id_empresa_ejemplo")) { // Reemplazar con la lógica real de busqueda
                         ordenesDelPeriodo.add(orden);
                         totalAPagar += orden.getTotalOrden();
                         hayCoincidencias = true;
                    }
                }
            }
            
            // Si no hay órdenes, se muestra un mensaje de error y se sigue mostrando el formulario
            if (!hayCoincidencias) {
                Toast.makeText(this, "No se encontraron servicios para el periodo seleccionado", Toast.LENGTH_SHORT).show();
                llDetalleServicios.setVisibility(View.GONE);
                return;
            }

            // Crear y agregar la factura a la lista
            String periodo = mesStr + " " + anio;
            Factura nuevaFactura = new Factura(periodo, empresaSeleccionada, ordenesDelPeriodo, hayCoincidencias);
            listaFacturas.add(0, nuevaFactura); // Agregar al inicio para que aparezca primero en la lista
            
            //Luego de que la lista fuera modificada se vuelve a serializar
            UtilSerializable.guardarLista(listaFacturas,FILE_NAME);
            
            adapter.notifyItemInserted(0);
            
            // Ocultar el formulario y mostrar el listado de facturas
            dialog.dismiss();
            Toast.makeText(this, "Factura generada exitosamente", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onDetalleClick(Factura factura) {
        // Se infla el layout para mostrar los detalles de la factura
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_detalle_factura, null);
        RecyclerView rvDetalleServicios = dialogView.findViewById(R.id.rvDetalleServicios);
        TextView tvTotalPagar = dialogView.findViewById(R.id.tvTotalPagar);

        rvDetalleServicios.setLayoutManager(new LinearLayoutManager(this));
        
        // Simular la union de todos los detalles de servicio en una sola lista
        ArrayList<DetalleServicio> detallesConsolidados = new ArrayList<>();
        double totalFactura = 0.0;
        for (OrdenServicio orden : factura.getListaOrdenServicio()) {
            detallesConsolidados.addAll(orden.getListaDetalleServicio());
            totalFactura += orden.getTotalOrden();
        }
        
        totalFactura += 50.0; // Agregar el costo fijo

        DetalleServicioAdapter detalleAdapter = new DetalleServicioAdapter(detallesConsolidados);
        rvDetalleServicios.setAdapter(detalleAdapter);

        tvTotalPagar.setText(String.format("Total a pagar: $%.2f", totalFactura));

        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Detalle de Factura")
                .setView(dialogView)
                .setPositiveButton("Cerrar", null);
        builder.create().show();
    }
}