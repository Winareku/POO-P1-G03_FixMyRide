package poo.espol.fixmyride.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import poo.espol.fixmyride.R;
import poo.espol.fixmyride.extra.DataRepository;
import poo.espol.fixmyride.adapter.OrdenServicioAdapter;
import poo.espol.fixmyride.model.Cliente;
import poo.espol.fixmyride.model.DetalleServicio;
import poo.espol.fixmyride.model.OrdenServicio;
import poo.espol.fixmyride.model.Servicio;
import poo.espol.fixmyride.model.Tecnico;
import poo.espol.fixmyride.model.TipoVehiculo;
import poo.espol.fixmyride.storage.UtilSerializable

public class OrdenActivity extends AppCompatActivity {

    // Variables
    private ArrayList<OrdenServicio> list;
    private OrdenServicioAdapter adapter;
    
    // Nombre del archivo
    private static final String FILE_NAME = "ordenes.ser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orden_servicio);

        //Intenta cargar la lista (De-Serializa)
        list= UtilSerializable.cargarLista(FILE_NAME);

        if (list == null){
            // Inicializa con 3 ordenes por defecto si la lista está vacia.
            list = DataRepository.getOrdenServicios();
            //Luego serializa
            UtilSerializable.guardarLista(list,FILE_NAME);
        }

        RecyclerView recyclerView = findViewById(R.id.rvOrdenes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new OrdenServicioAdapter(list);
        recyclerView.setAdapter(adapter);

        Button btnAgregar = findViewById(R.id.btnAgregarOrden);
        btnAgregar.setOnClickListener(v -> mostrarDialogoAgregar());
    }

    private void mostrarDialogoAgregar() {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_agregar_orden, null);

        // Componentes del Diálogo
        Spinner spinnerClientes = dialogView.findViewById(R.id.spinnerClientes);
        Spinner spinnerTecnicos = dialogView.findViewById(R.id.spinnerTecnicos);
        Spinner spinnerServicios = dialogView.findViewById(R.id.spinnerServicios);
        EditText etFecha = dialogView.findViewById(R.id.etFecha);
        Spinner spinnerTipoVehiculo = dialogView.findViewById(R.id.spinnerTipoVehiculo);
        EditText etPlaca = dialogView.findViewById(R.id.etPlaca);
        EditText etCantidad = dialogView.findViewById(R.id.etCantidad);
        Button btnAgregarServicio = dialogView.findViewById(R.id.btnAgregarServicio);
        LinearLayout containerServicios = dialogView.findViewById(R.id.containerServicios);
        TextView tvTotal = dialogView.findViewById(R.id.tvTotal);

        // Lista temporal para guardar los detalles de servicio
        List<DetalleServicio> detallesServicios = new ArrayList<>();
        double[] total = {0.0}; // Usamos array para poder modificar dentro de listeners

        // Spinner de clientes
        ArrayAdapter<Cliente> clienteAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, DataRepository.getClientes());
        clienteAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClientes.setAdapter(clienteAdapter);

        // Spinner de técnicos
        ArrayAdapter<Tecnico> tecnicoAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, DataRepository.getTecnicos());
        tecnicoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTecnicos.setAdapter(tecnicoAdapter);

        // Spinner de tipo de vehículo
        ArrayAdapter<TipoVehiculo> tipoAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, TipoVehiculo.values());
        tipoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoVehiculo.setAdapter(tipoAdapter);

        // Spinner de servicios
        ArrayAdapter<Servicio> servicioAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, DataRepository.getServicios());
        servicioAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerServicios.setAdapter(servicioAdapter);

        // Configurar botón para agregar servicio
        btnAgregarServicio.setOnClickListener(v -> {
            Servicio servicio = (Servicio) spinnerServicios.getSelectedItem();
            String cantidadStr = etCantidad.getText().toString().trim();

            if (cantidadStr.isEmpty()) {
                Toast.makeText(this, "Ingrese una cantidad", Toast.LENGTH_SHORT).show();
                return;
            }

            int cantidad = Integer.parseInt(cantidadStr);
            if (cantidad <= 0) {
                Toast.makeText(this, "Cantidad debe ser mayor a 0", Toast.LENGTH_SHORT).show();
                return;
            }

            // Crear detalle de servicio
            DetalleServicio detalle = new DetalleServicio(servicio, cantidad);
            detallesServicios.add(detalle);

            // Actualizar total
            total[0] += detalle.getTotal();
            tvTotal.setText(String.format("Total: $%.2f", total[0]));

            // Agregar vista del servicio al contenedor
            View itemView = LayoutInflater.from(this).inflate(R.layout.item_detalle_servicio, containerServicios, false);
            TextView tvNombre = itemView.findViewById(R.id.tvNombreServicio);
            TextView tvCant = itemView.findViewById(R.id.tvCantidad);
            TextView tvPrecio = itemView.findViewById(R.id.tvPrecio);

            tvNombre.setText(servicio.getNombre());
            tvCant.setText("x" + cantidad);
            tvPrecio.setText(String.format("$%.2f", detalle.getTotal()));

            containerServicios.addView(itemView);

            // Limpiar cantidad
            etCantidad.setText("");
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Agregar Orden de Servicio")
                .setView(dialogView)
                .setPositiveButton("Agregar", (dialog, which) -> {
                    // Obtener datos seleccionados
                    Cliente cliente = (Cliente) spinnerClientes.getSelectedItem();
                    Tecnico tecnico = (Tecnico) spinnerTecnicos.getSelectedItem();
                    String fechaStr = etFecha.getText().toString().trim();
                    TipoVehiculo tipoVehiculo = (TipoVehiculo) spinnerTipoVehiculo.getSelectedItem();
                    String placa = etPlaca.getText().toString().trim();

                    // Validar campos básicos y que se haya agregado al menos un servicio
                    if (validarCampos(fechaStr, placa) && !detallesServicios.isEmpty()) {
                        try {
                            // Convertir fecha a LocalDate
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            LocalDate fecha = LocalDate.parse(fechaStr, formatter);

                            // Crear nueva orden
                            OrdenServicio nuevaOrden = new OrdenServicio(
                                    cliente.getId(),
                                    tecnico.getId(),
                                    fecha,
                                    tipoVehiculo,
                                    placa
                            );

                            // Asignar los detalles de servicio
                            nuevaOrden.getListaDetalleServicio().addAll(detallesServicios);

                            // Calcular y asignar el total
                            nuevaOrden.setTotalOrden(total[0]);

                            list.add(nuevaOrden);
                            
                            //Luego de que la lista fuera modificada se vuelve a serializar
                            UtilSerializable.guardarLista(list,FILE_NAME);
                            
                            adapter.notifyItemInserted(list.size() - 1);
                        } catch (Exception e) {
                            Toast.makeText(this, "Formato de fecha inválido (dd/MM/yyyy)", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        String mensaje;
                        if (detallesServicios.isEmpty()) {
                            mensaje = "Debe agregar al menos un servicio";
                        } else {
                            mensaje = "Todos los campos son obligatorios";
                        }
                        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancelar", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private boolean validarCampos(String... campos) {
        for (String campo : campos) {
            if (campo.trim().isEmpty())
                return false;
        }
        return true;
    }
}