package poo.espol.fixmyride.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import poo.espol.fixmyride.model.Proveedor;
import poo.espol.fixmyride.adapter.ProveedorAdapter;
import poo.espol.fixmyride.R;

public class ProveedorActivity extends AppCompatActivity implements ProveedorAdapter.OnProveedorActionListener {

    private List<Proveedor> proveedores;
    private ProveedorAdapter proveedorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proveedores);

        // Inicializa con 3 proveedores por defecto
        proveedores = new ArrayList<>(Arrays.asList(
                new Proveedor("0977777777", "Repuestos R.C.", "555-1234", "Suministro de repuestos para vehículos."),
                new Proveedor("0988888888", "Herramientas y Equipos HOPE", "555-5678", "Proveedor de herramientas y equipos especializados para talleres mecánicos."),
                new Proveedor("0999999999", "Lubricantes y Aceites JK", "555-9101", "Venta de lubricantes y aceites automotrices para mantenimiento.")
        ));

        RecyclerView rvProveedores = findViewById(R.id.rvProveedores);
        rvProveedores.setLayoutManager(new LinearLayoutManager(this));
        proveedorAdapter = new ProveedorAdapter(proveedores, this);
        rvProveedores.setAdapter(proveedorAdapter);

        Button btnAgregarProveedor = findViewById(R.id.btnAgregarProveedor);
        btnAgregarProveedor.setOnClickListener(v -> mostrarDialogoAgregarProveedor());
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
            String identificacion = etIdentificacion.getText().toString().trim();
            String nombre = etNombreProveedor.getText().toString().trim();
            String telefono = etTelefonoProveedor.getText().toString().trim();
            String descripcion = etDescripcionProveedor.getText().toString().trim();

            if (identificacion.isEmpty() || nombre.isEmpty() || telefono.isEmpty() || descripcion.isEmpty()) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
            } else {
                Proveedor proveedor = new Proveedor(identificacion, nombre, telefono, descripcion);
                proveedores.add(proveedor);
                proveedorAdapter.notifyItemInserted(proveedores.size() - 1);
            }
        });
        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }

    @Override
    public void onEliminar(int position) {
        new AlertDialog.Builder(this)
                .setTitle("Eliminar Proveedor")
                .setMessage("¿Está seguro que desea eliminar el registro?")
                .setPositiveButton("Eliminar", (dialog, which) -> {
                    proveedores.remove(position);
                    proveedorAdapter.notifyItemRemoved(position);
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }
}