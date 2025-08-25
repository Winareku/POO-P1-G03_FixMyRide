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
import poo.espol.fixmyride.extra.DataRepository;
import poo.espol.fixmyride.model.Servicio;
import poo.espol.fixmyride.adapter.ServicioAdapter;

public class ServicioActivity extends AppCompatActivity implements ServicioAdapter.OnEliminarListener {

    // Variables
    private static ArrayList<Servicio> list;
    private ServicioAdapter adapter;
    
    // Nombre del archivo
    private static final String FILE_NAME = "servicios.ser";

    public static ArrayList<Servicio> getList(){return list;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicios);

        //Intenta cargar la lista (De-Serializa)
        list= UtilSerializable.cargarLista(FILE_NAME);

        if (list == null){
            // Inicializa con 6 servicios por defecto si la lista está vacia.
            list = DataRepository.getServicios();
            //Luego serializa
            UtilSerializable.guardarLista(list,FILE_NAME);
        }

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
                        
                        //Luego de que la lista fuera modificada se vuelve a serializar
                        UtilSerializable.guardarLista(list,FILE_NAME);
                        
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
                    
                    //Luego de que la lista fuera modificada se vuelve a serializar
                    UtilSerializable.guardarLista(list,FILE_NAME);
                    
                    adapter.notifyItemRemoved(position);
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }
}
