package poo.espol.fixmyride;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientesActivity extends AppCompatActivity {

    private List<Cliente> clientes;
    private ClienteAdapter clienteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        // Inicializa con 3 clientes por defecto
        clientes = new ArrayList<>(Arrays.asList(
                new Cliente("0911111111", "Paul Garcia", "Sauces", "0944444444", "Personal"),
                new Cliente("0922222222", "Empresa S.A..", "Urdesa", "0955555555", "Empresarial"),
                new Cliente("0933333333", "Daniela Molina", "Via la Costa", "0966666666", "Personal")
        ));

        RecyclerView rvClientes = findViewById(R.id.rvClientes);
        rvClientes.setLayoutManager(new LinearLayoutManager(this));
        clienteAdapter = new ClienteAdapter(clientes);
        rvClientes.setAdapter(clienteAdapter);

        Button btnAgregarCliente = findViewById(R.id.btnAgregarCliente);
        btnAgregarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogoAgregarCliente();
            }
        });
    }

    private void mostrarDialogoAgregarCliente() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_agregar_cliente, null);
        builder.setView(dialogView);

        EditText etIdentificacion = dialogView.findViewById(R.id.etIdentificacion);
        EditText etNombre = dialogView.findViewById(R.id.etNombreCliente);
        EditText etDireccion = dialogView.findViewById(R.id.etDireccionCliente);
        EditText etTelefono = dialogView.findViewById(R.id.etTelefonoCliente);
        RadioGroup rgTipoCliente = dialogView.findViewById(R.id.rgTipoCliente);

        builder.setTitle("Agregar Cliente");
        builder.setPositiveButton("Agregar", (dialog, which) -> {
            String identificacion = etIdentificacion.getText().toString();
            String nombre = etNombre.getText().toString();
            String direccion = etDireccion.getText().toString();
            String telefono = etTelefono.getText().toString();

            int selectedTipoId = rgTipoCliente.getCheckedRadioButtonId();
            String tipo = "";
            if (selectedTipoId != -1) {
                RadioButton radioButton = dialogView.findViewById(selectedTipoId);
                tipo = radioButton.getText().toString();
            }

            Cliente cliente = new Cliente(identificacion, nombre, direccion, telefono, tipo);
            clientes.add(cliente);
            clienteAdapter.notifyDataSetChanged();
        });
        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }
}