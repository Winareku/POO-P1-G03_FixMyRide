package poo.espol.fixmyride;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ProveedoresActivity extends AppCompatActivity {

    private List<Proveedor> proveedores = new ArrayList<>();
    private ProveedorAdapter proveedorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proveedores);

        RecyclerView rvProveedores = findViewById(R.id.rvProveedores);
        rvProveedores.setLayoutManager(new LinearLayoutManager(this));

        proveedorAdapter = new ProveedorAdapter(proveedores);
        rvProveedores.setAdapter(proveedorAdapter);

        Button btnAgregar = findViewById(R.id.btnAgregarProveedor);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarDialogoAgregarProveedor();
            }
        });
    }

    private void mostrarDialogoAgregarProveedor() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_agregar_proveedor, null);
        builder.setView(dialogView);

        EditText etIdentificacion = dialogView.findViewById(R.id.etIdentificacion);
        EditText etNombreProveedor = dialogView.findViewById(R.id.etNombreProveedor);
        EditText etTelefonoProveedor = dialogView.findViewById(R.id.etTelefonoProveedor);
        EditText etDescripcionProveedor = dialogView.findViewById(R.id.etDescripcionProveedor);

        builder.setTitle("Agregar Proveedor");
        builder.setPositiveButton("Agregar", (dialog, which) -> {
            String identificacion = etIdentificacion.getText().toString();
            String nombre = etNombreProveedor.getText().toString();
            String telefono = etTelefonoProveedor.getText().toString();
            String descripcion = etDescripcionProveedor.getText().toString();

            Proveedor proveedor = new Proveedor(identificacion, nombre, telefono, descripcion);
            proveedores.add(proveedor);
            proveedorAdapter.notifyDataSetChanged();
        });
        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }
}