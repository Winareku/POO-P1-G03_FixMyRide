package poo.espol.fixmyride;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class TecnicosActivity extends AppCompatActivity {
    private ArrayList<Tecnico> listaTecnicos;
    private TecnicoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tecnicos);

        listaTecnicos = new ArrayList<>();
        listaTecnicos.add(new Tecnico("1000000001", "Juan Pérez", "555-1234", "Mecánica General"));
        listaTecnicos.add(new Tecnico("1000000002", "Ana Gómez", "555-5678", "Electricidad Automotriz"));
        listaTecnicos.add(new Tecnico("1000000003", "Luis Sánchez", "555-8765", "Diagnóstico de Computadoras de Autos"));

        RecyclerView recyclerView = findViewById(R.id.rvTecnicos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TecnicoAdapter(listaTecnicos, position -> confirmarEliminar(position));
        recyclerView.setAdapter(adapter);

        Button btnAgregar = findViewById(R.id.btnAgregarTecnico);
        btnAgregar.setOnClickListener(v -> mostrarDialogAgregar());
    }

    private void mostrarDialogAgregar() {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_agregar_tecnico, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Agregar Técnico")
                .setView(dialogView)
                .setPositiveButton("Agregar", (dialog, which) -> {
                    String id = ((android.widget.EditText) dialogView.findViewById(R.id.etIdentificacion)).getText().toString();
                    String nombre = ((android.widget.EditText) dialogView.findViewById(R.id.etNombre)).getText().toString();
                    String telefono = ((android.widget.EditText) dialogView.findViewById(R.id.etTelefono)).getText().toString();
                    String especialidad = ((android.widget.EditText) dialogView.findViewById(R.id.etEspecialidad)).getText().toString();
                    if (!id.isEmpty() && !nombre.isEmpty() && !telefono.isEmpty() && !especialidad.isEmpty()) {
                        listaTecnicos.add(new Tecnico(id, nombre, telefono, especialidad));
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancelar", null);
        builder.show();
    }

    private void confirmarEliminar(int position) {
        new AlertDialog.Builder(this)
                .setTitle("Eliminar Técnico")
                .setMessage("¿Está seguro que desea eliminar el registro?")
                .setPositiveButton("Eliminar", (dialog, which) -> {
                    listaTecnicos.remove(position);
                    adapter.notifyDataSetChanged();
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }
}