package poo.espol.fixmyride.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Button;
import java.util.ArrayList;
import poo.espol.fixmyride.R;
import poo.espol.fixmyride.controller.ControladorCliente;
import poo.espol.fixmyride.controller.ControladorPersona;
import poo.espol.fixmyride.extra.DataRepository;
import poo.espol.fixmyride.model.DetalleServicio;
import poo.espol.fixmyride.model.OrdenServicio;
import poo.espol.fixmyride.model.Persona;

public class OrdenServicioAdapter extends RecyclerView.Adapter<OrdenServicioAdapter.ViewHolder> {

    // Variables
    private ArrayList<OrdenServicio> list;

    // Constructor
    public OrdenServicioAdapter(ArrayList<OrdenServicio> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_orden_servicio, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrdenServicio orden = list.get(position);
        holder.tvCliente.setText("Cliente: " + ControladorCliente.buscarClientePorId(orden.getIdCliente(), DataRepository.getClientes()));
        holder.tvFecha.setText("Fecha: " + orden.getFechaOrden().toString());
        holder.tvPlaca.setText("Placa: " + orden.getPlacaVehiculo());
        holder.tvTotal.setText("Total: $" + String.format("%.2f", orden.getTotalOrden()));

        // Configurar el botón para mostrar detalles
        holder.btnMasDetalle.setOnClickListener(v -> mostrarDetallesServicios(holder.itemView.getContext(), orden));
    }

    private void mostrarDetallesServicios(android.content.Context context, OrdenServicio orden) {
        // Crear un diálogo para mostrar los detalles de los servicios
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Detalles de Servicios");

        // Crear una vista para mostrar los servicios
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_detalles_orden, null);
        builder.setView(dialogView);

        // Obtener el contenedor de servicios
        ViewGroup container = dialogView.findViewById(R.id.containerDetallesServicios);

        // Limpiar el contenedor por si acaso
        container.removeAllViews();

        // Añadir cada servicio al contenedor
        for (DetalleServicio detalle : orden.getListaDetalleServicio()) {
            View itemView = LayoutInflater.from(context).inflate(R.layout.item_detalle_servicio, container, false);

            TextView tvServicio = itemView.findViewById(R.id.tvNombreServicio);
            TextView tvCantidad = itemView.findViewById(R.id.tvCantidad);
            TextView tvPrecio = itemView.findViewById(R.id.tvPrecio);

            tvServicio.setText(detalle.getServicio().getNombre());
            tvCantidad.setText("Cantidad: " + detalle.getCantidad());
            tvPrecio.setText("Precio: $" + String.format("%.2f", detalle.getTotal()));

            container.addView(itemView);
        }

        builder.setPositiveButton("Cerrar", null);
        builder.show();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCliente, tvFecha, tvPlaca, tvTotal;
        Button btnMasDetalle;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCliente = itemView.findViewById(R.id.tvCliente);
            tvFecha = itemView.findViewById(R.id.tvFecha);
            tvPlaca = itemView.findViewById(R.id.tvPlaca);
            tvTotal = itemView.findViewById(R.id.tvTotal);
            btnMasDetalle = itemView.findViewById(R.id.btnMasDetalles);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}