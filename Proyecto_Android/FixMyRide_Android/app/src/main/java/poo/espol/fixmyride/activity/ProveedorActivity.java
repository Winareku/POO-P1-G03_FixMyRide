package poo.espol.fixmyride.activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import poo.espol.fixmyride.extra.*;
import poo.espol.fixmyride.model.Proveedor;
import poo.espol.fixmyride.adapter.ProveedorAdapter;
import poo.espol.fixmyride.R;
import java.util.ArrayList;

public class ProveedorActivity extends AppCompatActivity implements ProveedorAdapter.OnEliminarListener {

    // Variables
    private ArrayList<Proveedor> list;
    private ProveedorAdapter adapter;
    
    // Nombre del archivo
    private static final String FILE_NAME = "proveedores.ser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proveedores);

        //Intenta cargar la lista (De-Serializa)
        list= UtilSerializable.cargarLista(FILE_NAME);

        if (list == null){
            // Inicializa con 3 proveedores por defecto si la lista está vacia.
            list = DataRepository.getProveedores();
            //Luego serializa
            UtilSerializable.guardarLista(list,FILE_NAME);
        }

        RecyclerView recyclerView = findViewById(R.id.rvProveedores);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProveedorAdapter(list, this);
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
                        list.add(new Proveedor(id, nombre, telefono, descripcion));
                        
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
                .setTitle("Eliminar Proveedor")
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