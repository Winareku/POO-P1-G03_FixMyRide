package poo.espol.fixmyride.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import poo.espol.fixmyride.R;
import poo.espol.fixmyride.adapter.GenerarFacturaAdapter;
import poo.espol.fixmyride.controller.ControladorCliente;
import poo.espol.fixmyride.controller.ControladorEmpresa;
import poo.espol.fixmyride.controller.ControladorFactura;
import poo.espol.fixmyride.controller.ControladorOrdenServicio;
import poo.espol.fixmyride.model.DetalleServicio;
import poo.espol.fixmyride.model.Empresa;
import poo.espol.fixmyride.model.Factura;
import poo.espol.fixmyride.model.OrdenServicio;

public class GenerarFacturaActivity extends AppCompatActivity {

    private Spinner spEmpresas, spMes;
    private EditText etAnio;
    private Button btnGenerarFactura;
    private RecyclerView recyclerDetalles;
    private TextView tvTotal;

    private GenerarFacturaAdapter adapter;
    private ArrayList<DetalleServicio> listaDetallesFactura;

    private ControladorEmpresa controladorEmpresa;
    private ControladorFactura controladorFactura;
    private ControladorCliente controladorCliente;
    private ControladorOrdenServicio controladorOrdenServicio;

    private Empresa empresaSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generar_factura);

        // Inicialización de vistas
        spEmpresas = findViewById(R.id.spEmpresas);
        spMes = findViewById(R.id.spMes);
        etAnio = findViewById(R.id.etAnio);
        btnGenerarFactura = findViewById(R.id.btnGenerarFactura);
        recyclerDetalles = findViewById(R.id.recyclerDetallesFactura);
        tvTotal = findViewById(R.id.tvTotalFactura);

        // RecyclerView
        listaDetallesFactura = new ArrayList<>();
        adapter = new GenerarFacturaAdapter(listaDetallesFactura);
        recyclerDetalles.setLayoutManager(new LinearLayoutManager(this));
        recyclerDetalles.setAdapter(adapter);

        // Inicialización de controladores
        controladorEmpresa = new ControladorEmpresa();
        controladorCliente = new ControladorCliente(controladorEmpresa);
        controladorOrdenServicio = new ControladorOrdenServicio();
        controladorFactura = new ControladorFactura(controladorCliente);

        // Cargar datos en spinners
        cargarEmpresasEnSpinner();
        cargarMesesEnSpinner();

        // Botón Generar Factura
        btnGenerarFactura.setOnClickListener(v -> generarFactura());
    }

    private void cargarEmpresasEnSpinner() {
        ArrayList<String> nombresEmpresas = new ArrayList<>();
        for (Empresa e : ControladorEmpresa.lista) {
            nombresEmpresas.add(e.getNombre());
        }
        ArrayAdapter<String> adapterEmpresas = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, nombresEmpresas);
        adapterEmpresas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spEmpresas.setAdapter(adapterEmpresas);

        spEmpresas.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
                empresaSeleccionada = ControladorEmpresa.lista.get(position);
            }
            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {}
        });
    }

    private void cargarMesesEnSpinner() {
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo",
                "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        ArrayAdapter<String> adapterMes = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, meses);
        adapterMes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMes.setAdapter(adapterMes);
    }

    private void generarFactura() {
        if (empresaSeleccionada == null) {
            Toast.makeText(this, "Seleccione una empresa", Toast.LENGTH_SHORT).show();
            return;
        }
        if (etAnio.getText().toString().isEmpty()) {
            Toast.makeText(this, "Ingrese un año", Toast.LENGTH_SHORT).show();
            return;
        }

        int anio = Integer.parseInt(etAnio.getText().toString());
        int mes = spMes.getSelectedItemPosition() + 1;

        Factura factura = controladorFactura.crearFactura(
                empresaSeleccionada.getCodigo(), anio, mes,
                empresaSeleccionada, controladorOrdenServicio.getLista()
        );

        listaDetallesFactura.clear();
        double total = 0;

        if (factura.getHayCoincidencias()) {
            for (OrdenServicio orden : factura.getListaOrdenServicio()) {
                listaDetallesFactura.addAll(orden.getListaDetalleServicio());
                total += orden.getTotalOrden();
            }
            // Cargo fijo $50 para clientes empresariales
            total += 50.0;
        }

        adapter.notifyDataSetChanged();
        tvTotal.setText("Total a pagar: $" + String.format("%.2f", total));
    }
}

