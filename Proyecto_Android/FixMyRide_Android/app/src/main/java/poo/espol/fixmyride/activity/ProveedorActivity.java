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

import poo.espol.fixmyride.model.Proveedor;
import poo.espol.fixmyride.adapter.ProveedorAdapter;
import poo.espol.fixmyride.R;
import poo.espol.fixmyride.model.Tecnico;

public class ProveedorActivity extends AppCompatActivity implements ProveedorAdapter.OnProveedorEliminarListener {
    private ArrayList<Proveedor> lista;
    private ProveedorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proveedores);

        // Inicializa con 3 proveedores por defecto
        lista = new ArrayList<>(Arrays.asList(
                new Proveedor("0977777777", "Repuestos R.C.", "555-1234", "Suministro de repuestos para vehículos."),
                new Proveedor("0988888888", "Herramientas y Equipos HOPE", "555-5678", "Proveedor de herramientas y equipos especializados para talleres mecánicos."),
                new Proveedor("0999999999", "Lubricantes y Aceites JK", "555-9101", "Venta de lubricantes y aceites automotrices para mantenimiento.")
        ));

        RecyclerView recyclerView = findViewById(R.id.rvProveedores);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProveedorAdapter(lista, this);
        recyclerView.setAdapter(adapter);

        Button btnAgregar = findViewById(R.id.btnAgregarProveedor);
        btnAgregar.setOnClickListener(v -> mostrarDialogoAgregar());
    }

    private void mostrarDialogoAgregar() {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_agregar_proveedor, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Agregar Proveedor")
                .setView(dialogView)
                .setPositiveButton("Agregar", (dialog, which) -> {
                    String id = getTextFromView(dialogView, R.id.etId);
                    String nombre = getTextFromView(dialogView, R.id.etNombre);
                    String telefono = getTextFromView(dialogView, R.id.etTelefono);
                    String descripcion = getTextFromView(dialogView, R.id.etDescripcion);
                    if (validarCampos(id, nombre, telefono, descripcion)) {
                        lista.add(new Proveedor(id, nombre, telefono, descripcion));
                        adapter.notifyItemInserted(lista.size() - 1);
                    } else {Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();}
                })
                .setNegativeButton("Cancelar", null);
        builder.show();
    }

    private String getTextFromView(View parentView, int viewId) {
        return ((android.widget.EditText) parentView.findViewById(viewId)).getText().toString();
    }

    private boolean validarCampos(String... campos) {
        for (String campo : campos) {if (campo.trim().isEmpty()) return false;}
        return true;
    }

    @Override
    public void onEliminar(int position) {
        new AlertDialog.Builder(this)
                .setTitle("Eliminar Proveedor")
                .setMessage("¿Está seguro que desea eliminar el registro?")
                .setPositiveButton("Eliminar", (dialog, which) -> {
                    lista.remove(position);
                    adapter.notifyItemRemoved(position);
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }
}