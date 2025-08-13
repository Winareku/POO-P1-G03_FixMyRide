package poo.espol.fixmyride.activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import poo.espol.fixmyride.model.Cliente;
import poo.espol.fixmyride.adapter.ClienteAdapter;
import poo.espol.fixmyride.R;
import poo.espol.fixmyride.model.TipoCliente;
import poo.espol.fixmyride.extra.*;

public class ClienteActivity extends AppCompatActivity implements ClienteAdapter.OnClienteEliminarListener {

    // Variables
    private ArrayList<Cliente> list;
    private ClienteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        // Inicializa con 3 clientes por defecto
        list = DataRepository.getClientes();

        RecyclerView recyclerView = findViewById(R.id.rvClientes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ClienteAdapter(list, this);
        recyclerView.setAdapter(adapter);

        Button btnAgregar = findViewById(R.id.btnAgregarCliente);
        btnAgregar.setOnClickListener(v -> mostrarDialogoAgregar());
    }

    private void mostrarDialogoAgregar() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_agregar_cliente, null);
        builder.setView(dialogView);

        EditText etIdentificacion = dialogView.findViewById(R.id.etId);
        EditText etNombre = dialogView.findViewById(R.id.etNombre);
        EditText etDireccion = dialogView.findViewById(R.id.etDireccion);
        EditText etTelefono = dialogView.findViewById(R.id.etTelefono);
        RadioGroup rgTipoCliente = dialogView.findViewById(R.id.rgTipoCliente);

        builder.setTitle("Agregar Cliente");
        builder.setPositiveButton("Agregar", (dialog, which) -> {
            String identificacion = etIdentificacion.getText().toString().trim();
            String nombre = etNombre.getText().toString().trim();
            String direccion = etDireccion.getText().toString().trim();
            String telefono = etTelefono.getText().toString().trim();

            int selectedTipoId = rgTipoCliente.getCheckedRadioButtonId();
            String tipo = "";
            if (selectedTipoId != -1) {
                RadioButton radioButton = dialogView.findViewById(selectedTipoId);
                tipo = radioButton.getText().toString();
            }

            if (identificacion.isEmpty() || nombre.isEmpty() || direccion.isEmpty() || telefono.isEmpty() || tipo.isEmpty()) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
            } else {
                Cliente cliente = new Cliente(identificacion, nombre, direccion, telefono, Tools.obtenerTipoCliente(tipo));
                list.add(cliente);
                adapter.notifyItemInserted(list.size() - 1);
            }
        });
        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }

    @Override
    public void onEliminar(int position) {
        new AlertDialog.Builder(this)
                .setTitle("Eliminar Cliente")
                .setMessage("¿Está seguro que desea eliminar el registro?")
                .setPositiveButton("Eliminar", (dialog, which) -> {
                    list.remove(position);
                    adapter.notifyItemRemoved(position);
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }
}