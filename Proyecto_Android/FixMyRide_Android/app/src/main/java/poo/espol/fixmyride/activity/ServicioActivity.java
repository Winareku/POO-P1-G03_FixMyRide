package poo.espol.fixmyride.activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

import poo.espol.fixmyride.R;
import poo.espol.fixmyride.model.Servicio;
import poo.espol.fixmyride.adapter.ServicioAdapter;

public class ServicioActivity extends AppCompatActivity implements ServicioAdapter.OnEliminarClickListener {
    private static ArrayList<Servicio> list;
    private ServicioAdapter adapter;

    public static ArrayList<Servicio> getList(){return list;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicios);

        // Inicializa con 6 servicios por defecto
        list = new ArrayList<>(Arrays.asList(
                new Servicio("Cambio de aceite",32.5), new Servicio("Revisión de frenos",48.0),
                new Servicio("Alineación y balanceo",42.0), new Servicio("Reparación de motor",250.0),
                new Servicio("Diagnóstico electrónico",60.0), new Servicio("Lavado y detallado",25.0)
        ));

        RecyclerView recyclerView = findViewById(R.id.rvServicios);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ServicioAdapter(list, this);
        recyclerView.setAdapter(adapter);

        Button btnAgregar = findViewById(R.id.btnAgregarServicio);
        btnAgregar.setOnClickListener(v -> mostrarDialogoAgregar());
    }

    private void mostrarDialogoAgregar() {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_agregar_servicio, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Agregar Servicio")
                .setView(dialogView)
                .setPositiveButton("Agregar", (dialog, which) -> {
                    String nombre = getTextFromView(dialogView, R.id.etNombre);
                    String precio = getTextFromView(dialogView, R.id.etPrecio);
                    if (validarCampos(nombre, precio)) {
                        list.add(new Servicio(nombre, Float.parseFloat(precio)));
                        adapter.notifyItemInserted(list.size() - 1);
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
                    list.remove(position);
                    adapter.notifyItemRemoved(position);
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }
}
