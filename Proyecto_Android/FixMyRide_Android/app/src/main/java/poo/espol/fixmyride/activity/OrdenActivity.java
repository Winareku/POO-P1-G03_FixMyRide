package poo.espol.fixmyride.activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import poo.espol.fixmyride.R;
import poo.espol.fixmyride.adapter.OrdenServicioAdapter;
import poo.espol.fixmyride.extra.DataRepository;
import poo.espol.fixmyride.model.OrdenServicio;

public class OrdenActivity extends AppCompatActivity {

    // Variables
    private ArrayList<OrdenServicio> list;
    private OrdenServicioAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orden_servicio);

        // Inicializa con 3 clientes por defecto
        list = DataRepository.getOrdenServicio();

        RecyclerView recyclerView = findViewById(R.id.recyclerOrdenes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new OrdenServicioAdapter(list);
        recyclerView.setAdapter(adapter);

        Button btnAgregar = findViewById(R.id.btnCrearOrden);
        //btnAgregar.setOnClickListener(v -> mostrarDialogoAgregar());
        /*
        findViewById(R.id.btnCrearOrden).setOnClickListener(v -> {
            startActivity(new Intent(this, CrearOrdenActivity.class));
        });
        */


        /*
        private void mostrarDialogoAgregar() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_agregar_orden, null);
        builder.setView(dialogView);

        EditText etIdentificacion = dialogView.findViewById(R.id.etId);
        EditText etNombre = dialogView.findViewById(R.id.etNombre);
        EditText etDireccion = dialogView.findViewById(R.id.etDireccion);
        EditText etTelefono = dialogView.findViewById(R.id.etTelefono);
        RadioGroup rgTipoCliente = dialogView.findViewById(R.id.rgTipoCliente);

        builder.setTitle("Agregar Cliente");
        builder.setPositiveButton("Agregar", (dialog, which) -> {
            String identificacion = etIdentificacion.getText().toString().trim();
            String nombre = etNombre.getText().toString().trim();
            String direccion = etDireccion.getText().toString().trim();
            String telefono = etTelefono.getText().toString().trim();

            int selectedTipoId = rgTipoCliente.getCheckedRadioButtonId();
            String tipo = "";
            if (selectedTipoId != -1) {
                RadioButton radioButton = dialogView.findViewById(selectedTipoId);
                tipo = radioButton.getText().toString();
            }

            if (identificacion.isEmpty() || nombre.isEmpty() || direccion.isEmpty() || telefono.isEmpty() || tipo.isEmpty()) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
            } else {
                Cliente cliente = new Cliente(identificacion, nombre, direccion, telefono, Tools.obtenerTipoCliente(tipo));
                list.add(cliente);
                adapter.notifyItemInserted(list.size() - 1);
            }
        });
        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }
        */
    }
}