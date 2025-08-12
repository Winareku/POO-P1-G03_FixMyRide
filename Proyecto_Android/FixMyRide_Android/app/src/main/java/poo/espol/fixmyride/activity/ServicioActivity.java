package poo.espol.fixmyride.activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

import poo.espol.fixmyride.R;
import poo.espol.fixmyride.model.Proveedor;
import poo.espol.fixmyride.model.Servicio;
import poo.espol.fixmyride.adapter.ServicioAdapter;

public class ServicioActivity extends AppCompatActivity implements ServicioAdapter.OnEliminarClickListener {
    private ArrayList<Servicio> lista;
    private ServicioAdapter adapter;
<<<<<<< Updated upstream
    public static ArrayList<Servicio> listaServicios;
    private Button btnAgregar;
=======
>>>>>>> Stashed changes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicios);

        // Inicializa con 6 servicios por defecto
        lista = new ArrayList<>(Arrays.asList(
                new Servicio("Cambio de aceite",32.5), new Servicio("Revisión de frenos",48.0),
                new Servicio("Alineación y balanceo",42.0), new Servicio("Reparación de motor",250.0),
                new Servicio("Diagnóstico electrónico",60.0), new Servicio("Lavado y detallado",25.0)
        ));

<<<<<<< Updated upstream
        listaServicios = new ArrayList<>();
        this.cargarServicios();
        adapter = new ServicioAdapter(this, listaServicios, this::editarServicio);

        recyclerServicios.setLayoutManager(new LinearLayoutManager(this));
        recyclerServicios.setAdapter(adapter);
=======
        RecyclerView recyclerView = findViewById(R.id.rvServicios);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ServicioAdapter(lista, this);
        recyclerView.setAdapter(adapter);
>>>>>>> Stashed changes

        Button btnAgregar = findViewById(R.id.btnAgregarServicio);
        btnAgregar.setOnClickListener(v -> mostrarDialogoAgregar());
    }
/*
    private void mostrarDialogoAgregar() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_agregar_servicio, null);
        builder.setView(dialogView);

        EditText etNombre = dialogView.findViewById(R.id.etNombreServicio);
        EditText etPrecio = dialogView.findViewById(R.id.etPrecioServicio);

        builder.setTitle("Agregar Servicio");
        builder.setPositiveButton("Agregar", (dialog, which) -> {
            String nombre = etNombre.getText().toString().trim();
            String precio = etPrecio.getText().toString().trim();

            if (nombre.isEmpty() || precio.isEmpty()) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
            } else {
                Servicio servicio = new Servicio(nombre, Integer.parseInt(precio));
                lista.add(servicio);
                adapter.notifyItemInserted(lista.size() - 1);
            }
        });
        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }
*/
    private void mostrarDialogoAgregar() {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_agregar_servicio, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Agregar Servicio")
                .setView(dialogView)
                .setPositiveButton("Agregar", (dialog, which) -> {
                    String nombre = getTextFromView(dialogView, R.id.etNombre);
                    String precio = getTextFromView(dialogView, R.id.etPrecio);
                    if (validarCampos(nombre, precio)) {
                        lista.add(new Servicio(nombre, Float.parseFloat(precio)));
                        adapter.notifyItemInserted(lista.size() - 1);
                    } else {Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();}
                })
                .setNegativeButton("Cancelar", null);
        builder.show();
    }

    private String getTextFromView(View parentView, int viewId) {return ((android.widget.EditText) parentView.findViewById(viewId)).getText().toString();}

    private boolean validarCampos(String... campos) {
        for (String campo : campos) {if (campo.trim().isEmpty()) return false;}
        return true;
    }

    @Override
    public void onEliminar(int position){
        new AlertDialog.Builder(this)
                .setTitle("Eliminar Servicio")
                .setMessage("¿Está seguro que desea eliminar el registro?")
                .setPositiveButton("Eliminar", (dialog, which) -> {
                    lista.remove(position);
                    adapter.notifyItemRemoved(position);
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }
    private void cargarServicios(){
        listaServicios.add(new Servicio("Alineación y balanceo", 42.00));
        listaServicios.add(new Servicio("Cambio de aceite", 32.50));
        listaServicios.add(new Servicio("Reparación de motor", 250.00));
        listaServicios.add(new Servicio("Revisión de frenos", 48.00));
        listaServicios.add(new Servicio("Lavado y detallado", 25.00));
        listaServicios.add(new Servicio("Diagnóstico electrónico", 60.00));
    }

}
