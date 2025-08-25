package poo.espol.fixmyride.activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import poo.espol.fixmyride.R;
import poo.espol.fixmyride.extra.*;
import poo.espol.fixmyride.model.Tecnico;
import poo.espol.fixmyride.adapter.TecnicoAdapter;
import java.util.ArrayList;

public class TecnicoActivity extends AppCompatActivity implements TecnicoAdapter.OnEliminarListener {

    // Variables
    private ArrayList<Tecnico> list;
    private TecnicoAdapter adapter;
    // Nombre del archivo
    private static final String FILE_NAME = "tecnicos.ser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tecnicos);

        //Intenta cargar la lista (De-Serializa)
        list= UtilSerializable.cargarLista(FILE_NAME);

        if (list == null){
            // Inicializa con 6 servicios por defecto si la lista está vacia.
            list = DataRepository.getServicios();
            //Luego serializa
            UtilSerializable.guardarLista(list,FILE_NAME);
        }

        RecyclerView recyclerView = findViewById(R.id.rvTecnicos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TecnicoAdapter(list, this);
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
                        list.add(new Tecnico(id, nombre, telefono, especialidad));
                        //Luego de que la lista fuera modificada se vuelve a serializar
                        UtilSerializable.guardarLista(list,FILE_NAME);
                        
                        adapter.notifyItemInserted(list.size() - 1);
                    } else {
                        Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                    }
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
                    list.remove(position);
                    
                    //Luego de que la lista fuera modificada se vuelve a serializar
                    UtilSerializable.guardarLista(list,FILE_NAME);
                    
                    adapter.notifyItemRemoved(position);
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }
}