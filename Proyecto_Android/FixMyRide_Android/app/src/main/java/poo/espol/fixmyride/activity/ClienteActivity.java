package poo.espol.fixmyride.activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import poo.espol.fixmyride.model.Cliente;
import poo.espol.fixmyride.adapter.ClienteAdapter;
import poo.espol.fixmyride.R;
import poo.espol.fixmyride.extra.*;
import java.util.ArrayList;
import poo.espol.fixmyride.storage.*;

public class ClienteActivity extends AppCompatActivity implements ClienteAdapter.OnEliminarListener {

    // Variables
    private ArrayList<Cliente> list;
    private ClienteAdapter adapter;

    // Nombre del archivo
    private static final String FILE_NAME = "clientes.ser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        //Intenta cargar la lista (De-Serializa)
        list= UtilSerializable.cargarLista(FILE_NAME);

        if (list == null){
            // Inicializa con 3 clientes por defecto si la lista está vacia.
            list = DataRepository.getClientes();
            //Luego serializa
            UtilSerializable.guardarLista(list,FILE_NAME);
        }


        RecyclerView recyclerView = findViewById(R.id.rvClientes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ClienteAdapter(list, this);
        recyclerView.setAdapter(adapter);

        Button btnAgregar = findViewById(R.id.btnAgregarCliente);
        btnAgregar.setOnClickListener(v -> mostrarDialogoAgregar());
    }

    private void mostrarDialogoAgregar() {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_agregar_cliente, null);
        RadioGroup rgTipoCliente = dialogView.findViewById(R.id.rgTipoCliente);

        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Agregar Cliente")
                .setView(dialogView)
                .setPositiveButton("Agregar", (dialog, which) -> {
                    String identificacion = getTextFromView(dialogView, R.id.etId);
                    String nombre = getTextFromView(dialogView, R.id.etNombre);
                    String direccion = getTextFromView(dialogView, R.id.etDireccion);
                    String telefono = getTextFromView(dialogView, R.id.etTelefono);

                    int selectedTipoId = rgTipoCliente.getCheckedRadioButtonId();
                    String tipo = "";
                    if (selectedTipoId != -1) {
                        RadioButton radioButton = dialogView.findViewById(selectedTipoId);
                        tipo = radioButton.getText().toString();
                    }

                    if (validarCampos(identificacion, nombre, direccion, telefono) && !tipo.isEmpty()) {
                        Cliente cliente = new Cliente(identificacion, nombre, direccion, telefono, Tools.obtenerTipoCliente(tipo));


                        list.add(cliente);

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
                .setTitle("Eliminar Cliente")
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