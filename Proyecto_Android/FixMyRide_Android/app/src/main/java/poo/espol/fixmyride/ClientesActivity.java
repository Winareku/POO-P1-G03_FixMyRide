package poo.espol.fixmyride;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ClientesActivity extends AppCompatActivity {

    private RecyclerView rvClientes;
    private ClienteAdapter adapter;
    private ArrayList<Cliente> listaClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        rvClientes = findViewById(R.id.rvClientes);
        listaClientes = new ArrayList<>();

        adapter = new ClienteAdapter(listaClientes);
        rvClientes.setLayoutManager(new LinearLayoutManager(this));
        rvClientes.setAdapter(adapter);

        Button btnAgregarCliente = findViewById(R.id.btnAgregarCliente);
        btnAgregarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogAgregarCliente();
            }
        });
    }

    private void mostrarDialogAgregarCliente() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_agregar_cliente, null);
        builder.setView(view);

        EditText etIdentificacion = view.findViewById(R.id.etIdentificacion);
        EditText etNombre = view.findViewById(R.id.etNombre);
        EditText etDireccion = view.findViewById(R.id.etDireccion);
        EditText etTelefono = view.findViewById(R.id.etTelefono);
        RadioGroup rgTipoCliente = view.findViewById(R.id.rgTipoCliente);

        builder.setPositiveButton("Agregar", (dialog, which) -> {
            String id = etIdentificacion.getText().toString();
            String nombre = etNombre.getText().toString();
            String direccion = etDireccion.getText().toString();
            String telefono = etTelefono.getText().toString();
            String tipoCliente = rgTipoCliente.getCheckedRadioButtonId() == R.id.rbPersonal ? "Personal" : "Empresarial";

            Cliente cliente = new Cliente(id, nombre, direccion, telefono, tipoCliente);
            listaClientes.add(cliente);
            adapter.notifyDataSetChanged();
        });

        builder.setNegativeButton("Cancelar", null);

        builder.create().show();
    }
}