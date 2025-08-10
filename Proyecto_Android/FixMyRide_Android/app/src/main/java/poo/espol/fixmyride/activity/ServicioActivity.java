package poo.espol.fixmyride.activity;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import poo.espol.fixmyride.R;
import poo.espol.fixmyride.model.Servicio;
import poo.espol.fixmyride.adapter.ServicioAdapter;

public class ServicioActivity extends AppCompatActivity {

    private RecyclerView recyclerServicios;
    private ServicioAdapter adapter;
    private ArrayList<Servicio> listaServicios;
    private Button btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicios);

        recyclerServicios = findViewById(R.id.recyclerServicios);
        btnAgregar = findViewById(R.id.btnAgregarServicio);

        listaServicios = new ArrayList<>();
        adapter = new ServicioAdapter(this, listaServicios, this::editarServicio);

        recyclerServicios.setLayoutManager(new LinearLayoutManager(this));
        recyclerServicios.setAdapter(adapter);

        btnAgregar.setOnClickListener(v -> mostrarDialogoAgregar());
    }

    private void mostrarDialogoAgregar() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_agregar_servicio);

        EditText etNombre = dialog.findViewById(R.id.etNombreServicio);
        EditText etPrecio = dialog.findViewById(R.id.etPrecioServicio);
        Button btnGuardar = dialog.findViewById(R.id.btnGuardar);
        Button btnCancelar = dialog.findViewById(R.id.btnCancelar);

        btnGuardar.setOnClickListener(v -> {
            String nombre = etNombre.getText().toString().trim();
            double precio = Double.parseDouble(etPrecio.getText().toString());

            Servicio nuevo = new Servicio(nombre, precio);
            listaServicios.add(nuevo);
            adapter.notifyItemInserted(listaServicios.size() - 1);
            dialog.dismiss();
        });

        btnCancelar.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    private void editarServicio(int position) {
        Servicio servicio = listaServicios.get(position);

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_agregar_servicio);

        EditText etNombre = dialog.findViewById(R.id.etNombreServicio);
        EditText etPrecio = dialog.findViewById(R.id.etPrecioServicio);
        Button btnGuardar = dialog.findViewById(R.id.btnGuardar);
        Button btnCancelar = dialog.findViewById(R.id.btnCancelar);

        etNombre.setText(servicio.getNombre());
        etNombre.setEnabled(false); // nombre no editable
        etPrecio.setText(String.valueOf(servicio.getPrecio()));

        btnGuardar.setOnClickListener(v -> {
            double precio = Double.parseDouble(etPrecio.getText().toString());
            servicio.editarPrecio(precio);
            adapter.notifyItemChanged(position);
            dialog.dismiss();
        });

        btnCancelar.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }
}