package poo.espol.fixmyride.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import poo.espol.fixmyride.R;
import poo.espol.fixmyride.model.DetalleServicio;

public class GenerarFacturaAdapter extends RecyclerView.Adapter<GenerarFacturaAdapter.ViewHolder> {

    private ArrayList<DetalleServicio> listaDetalles;

    public GenerarFacturaAdapter(ArrayList<DetalleServicio> listaDetalles) {
        this.listaDetalles = listaDetalles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_detalle_servicio, parent, false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DetalleServicio detalle = listaDetalles.get(position);
        holder.tvNombre.setText(detalle.getServicio().getNombre());
        holder.tvCantidad.setText("Cantidad: " + detalle.getCantidad());
        holder.tvSubtotal.setText("Subtotal: $" + String.format("%.2f", detalle.getTotal()));
    }

    @Override
    public int getItemCount() {
        return listaDetalles.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvCantidad, tvSubtotal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombreServicio);
            tvCantidad = itemView.findViewById(R.id.tvCantidad);
            tvSubtotal = itemView.findViewById(R.id.tvPrecio); // Se usa tvPrecio como subtotal
        }
    }
}