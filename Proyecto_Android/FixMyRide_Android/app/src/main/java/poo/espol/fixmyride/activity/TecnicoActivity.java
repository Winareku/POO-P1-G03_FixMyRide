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
import poo.espol.fixmyride.model.Tecnico;
import poo.espol.fixmyride.adapter.TecnicoAdapter;

public class TecnicoActivity extends AppCompatActivity implements TecnicoAdapter.OnTecnicoEliminarListener{
    private ArrayList<Tecnico> lista;
    private TecnicoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tecnicos);

        // Inicializa con 3 tecnicos por defecto
        lista = new ArrayList<>(Arrays.asList(
                new Tecnico("1000000001", "Juan Pérez", "555-1234", "Mecánica General"),
                new Tecnico("1000000002", "Ana Gómez", "555-5678", "Electricidad Automotriz"),
                new Tecnico("1000000003", "Luis Sánchez", "555-8765", "Diagnóstico de Computadoras de Autos")
        ));

        RecyclerView recyclerView = findViewById(R.id.rvTecnicos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TecnicoAdapter(lista, this);
        recyclerView.setAdapter(adapter);

        Button btnAgregar = findViewById(R.id.btnAgregarTecnico);
        btnAgregar.setOnClickListener(v -> mostrarDialogoAgregar());
    }

    private void mostrarDialogoAgregar() {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_agregar_tecnico, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Agregar Técnico")
                .setView(dialogView)
                .setPositiveButton("Agregar", (dialog, which) -> {
                    String id = getTextFromView(dialogView, R.id.etId);
                    String nombre = getTextFromView(dialogView, R.id.etNombre);
                    String telefono = getTextFromView(dialogView, R.id.etTelefono);
                    String especialidad = getTextFromView(dialogView, R.id.etEspecialidad);

                    if (validarCampos(id, nombre, telefono, especialidad)) {
                        lista.add(new Tecnico(id, nombre, telefono, especialidad));
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
                .setTitle("Eliminar Técnico")
                .setMessage("¿Está seguro que desea eliminar el registro?")
                .setPositiveButton("Eliminar", (dialog, which) -> {
                    lista.remove(position);
                    adapter.notifyItemRemoved(position);
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }
}