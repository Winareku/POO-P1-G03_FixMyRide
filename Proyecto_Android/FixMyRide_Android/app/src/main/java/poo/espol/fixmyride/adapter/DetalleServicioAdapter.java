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

public class DetalleServicioAdapter extends RecyclerView.Adapter<DetalleServicioAdapter.ViewHolder> {

    private ArrayList<DetalleServicio> lista;

    public DetalleServicioAdapter(ArrayList<DetalleServicio> lista) {
        this.lista = lista;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_detalle_servicio, parent, false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DetalleServicio detalle = lista.get(position);
        holder.tvNombre.setText(detalle.getServicio().getNombre());
        holder.tvCantidad.setText("Cantidad: " + detalle.getCantidad());
        holder.tvSubtotal.setText("Subtotal: $" + String.format("%.2f", detalle.getTotal()));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvCantidad, tvSubtotal;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombreServicio);
            tvCantidad = itemView.findViewById(R.id.tvCantidad);
            tvSubtotal = itemView.findViewById(R.id.tvSubtotal);
        }
    }
}
