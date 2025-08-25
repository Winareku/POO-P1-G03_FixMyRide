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
import poo.espol.fixmyride.model.Cliente;
import poo.espol.fixmyride.model.Empresa;
import poo.espol.fixmyride.model.Factura;
import poo.espol.fixmyride.model.OrdenServicio;
import poo.espol.fixmyride.model.TipoCliente;

public class GenerarFacturaActivity extends AppCompatActivity implements GenerarFacturaAdapter.OnDetalleClickListener {

    // Variables
    private ArrayList<Factura> listaFacturas;
    private RecyclerView rvFacturas;
    private GenerarFacturaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_facturas);

        // Inicializar la lista de facturas (simulamos datos por defecto)
        listaFacturas = DataRepository.getFacturas();

        rvFacturas = findViewById(R.id.rvFacturas);
        rvFacturas.setLayoutManager(new LinearLayoutManager(this));
        adapter = new GenerarFacturaAdapter(listaFacturas, this);
        rvFacturas.setAdapter(adapter);

        Button btnGenerarFactura = findViewById(R.id.btnGenerarFactura);
        btnGenerarFactura.setOnClickListener(v -> mostrarDialogoGenerarFactura());
    }

    private void mostrarDialogoGenerarFactura() {
        View dialogView = getLayoutInflater().inflate(R.layout.activity_generar_factura, null);

        Spinner spinnerEmpresa = dialogView.findViewById(R.id.spinnerEmpresa);
        EditText etAnio = dialogView.findViewById(R.id.etAnio);
        Spinner spinnerMes = dialogView.findViewById(R.id.spinnerMes);
        Button btnGenerar = dialogView.findViewById(R.id.btnGenerar);

        LinearLayout llDetalleServicios = dialogView.findViewById(R.id.llDetalleServicios);

        // Spinner de empresas: se obtiene la lista de empresas
        ArrayList<Empresa> listaEmpresas = DataRepository.getEmpresas();
        ArrayAdapter<Empresa> empresaAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, listaEmpresas);
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

            // Obtener las órdenes de servicio para la empresa y periodo
            ArrayList<OrdenServicio> ordenesDelPeriodo = new ArrayList<>();
            boolean hayCoincidencias = false;

            // Iterar sobre las órdenes de servicio
            for (OrdenServicio orden : DataRepository.getOrdenServicios()) {
                // Buscar el cliente de la orden y verificar si es empresarial
                Cliente clienteOrden = buscarClientePorId(orden.getIdCliente());
                
                if (clienteOrden != null && clienteOrden.getTipoCliente() == TipoCliente.EMPRESARIAL && clienteOrden.getEmpresa() != null) {
                    // Verificar si la empresa del cliente coincide con la empresa seleccionada
                    if (clienteOrden.getEmpresa().equals(empresaSeleccionada)) {
                        // Verificar si la fecha de la orden coincide con el año y mes seleccionados
                        LocalDate fechaOrden = orden.getFechaOrden();
                        if (fechaOrden.getYear() == anio && fechaOrden.getMonth() == mesEnum) {
                            ordenesDelPeriodo.add(orden);
                            hayCoincidencias = true;
                        }
                    }
                }
            }
            
            if (!hayCoincidencias) {
                Toast.makeText(this, "No se encontraron servicios para el periodo seleccionado", Toast.LENGTH_SHORT).show();
                llDetalleServicios.setVisibility(View.GONE);
                return;
            }

            // Crear y agregar la factura a la lista
            String periodo = mesStr + " " + anio;
            Factura nuevaFactura = new Factura(periodo, empresaSeleccionada, ordenesDelPeriodo, hayCoincidencias);
            listaFacturas.add(0, nuevaFactura);
            adapter.notifyItemInserted(0);
            
            dialog.dismiss();
            Toast.makeText(this, "Factura generada exitosamente", Toast.LENGTH_SHORT).show();
        });
    }

    private Cliente buscarClientePorId(String idCliente) {
        for (Cliente cliente : DataRepository.getClientes()) {
            if (cliente.getId().equals(idCliente)) {
                return cliente;
            }
        }
        return null;
    }

    @Override
    public void onDetalleClick(Factura factura) {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_detalle_factura, null);
        RecyclerView rvDetalleServicios = dialogView.findViewById(R.id.rvDetalleServicios);
        TextView tvTotalPagar = dialogView.findViewById(R.id.tvTotalPagar);

        rvDetalleServicios.setLayoutManager(new LinearLayoutManager(this));
        
        ArrayList<DetalleServicio> detallesConsolidados = new ArrayList<>();
        double totalFactura = 0.0;
        for (OrdenServicio orden : factura.getListaOrdenServicio()) {
            detallesConsolidados.addAll(orden.getListaDetalleServicio());
            totalFactura += orden.getTotalOrden();
        }
        
        totalFactura += 50.0;

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