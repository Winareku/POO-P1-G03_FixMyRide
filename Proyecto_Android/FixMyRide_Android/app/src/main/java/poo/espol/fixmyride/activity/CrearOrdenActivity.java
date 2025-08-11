package poo.espol.fixmyride.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;

import poo.espol.fixmyride.R;
import poo.espol.fixmyride.adapter.DetalleServicioAdapter;
import poo.espol.fixmyride.model.DetalleServicio;
import poo.espol.fixmyride.model.Servicio;

public class CrearOrdenActivity extends AppCompatActivity {

    private EditText etIdCliente, etPlaca, etCantidad;
    private Spinner spClientes, spTipoVehiculo, spServicios;
    private TextView tvFecha, tvTotal;
    private Button btnAgregarServicio, btnSeleccionarFecha;
    private RecyclerView recyclerDetalles;

    private ArrayList<DetalleServicio> listaDetalles;
    private DetalleServicioAdapter adapter;

    private ArrayList<Servicio> serviciosDisponibles= ServicioActivity.listaServicios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_crear_orden);

        // Inicialización de vistas
        etIdCliente = findViewById(R.id.etIdCliente);
        etPlaca = findViewById(R.id.etPlaca);
        etCantidad = findViewById(R.id.etCantidad);
        spClientes = findViewById(R.id.spClientes);
        spTipoVehiculo = findViewById(R.id.spTipoVehiculo);
        spServicios = findViewById(R.id.spServicios);
        tvFecha = findViewById(R.id.tvFecha);
        tvTotal = findViewById(R.id.tvTotal);
        btnAgregarServicio = findViewById(R.id.btnAgregarServicio);
        btnSeleccionarFecha = findViewById(R.id.btnSeleccionarFecha);
        recyclerDetalles = findViewById(R.id.recyclerDetalles);


        // Configurar Spinner de servicios
        ArrayAdapter<String> adapterServicios = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                getNombresServicios()
        );
        adapterServicios.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spServicios.setAdapter(adapterServicios);

        // Inicializar lista de detalles
        listaDetalles = new ArrayList<>();
        adapter = new DetalleServicioAdapter(listaDetalles);
        recyclerDetalles.setLayoutManager(new LinearLayoutManager(this));
        recyclerDetalles.setAdapter(adapter);

        // Botón agregar servicio
        btnAgregarServicio.setOnClickListener(v -> agregarServicio());

        // Botón seleccionar fecha
        btnSeleccionarFecha.setOnClickListener(v -> mostrarDatePicker());
    }

    private ArrayList<String> getNombresServicios() {
        ArrayList<String> nombres = new ArrayList<>();
        for (Servicio s : serviciosDisponibles) {
            nombres.add(s.getNombre());
        }
        return nombres;
    }

    private void agregarServicio() {
        int posicion = spServicios.getSelectedItemPosition();
        Servicio servicioSeleccionado = serviciosDisponibles.get(posicion);

        String cantidadStr = etCantidad.getText().toString();
        if (cantidadStr.isEmpty()) return;
        int cantidad = Integer.parseInt(cantidadStr);

        DetalleServicio detalle = new DetalleServicio(servicioSeleccionado, cantidad);
        listaDetalles.add(detalle);
        adapter.notifyItemInserted(listaDetalles.size() - 1);

        actualizarTotal();
    }

    private void actualizarTotal() {
        double total = 0;
        for (DetalleServicio d : listaDetalles) {
            total += d.getTotal();
        }
        tvTotal.setText("Total: $" + String.format("%.2f", total));
    }

    private void mostrarDatePicker() {
        Calendar calendario = Calendar.getInstance();
        int año = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePicker = new DatePickerDialog(
                this,
                (DatePicker view, int year, int month, int dayOfMonth) -> {
                    tvFecha.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                },
                año, mes, dia
        );
        datePicker.show();
    }
}
