package poo.espol.fixmyride.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Button;
import java.util.ArrayList;

import poo.espol.fixmyride.R;
import poo.espol.fixmyride.model.OrdenServicio;

public class OrdenServicioAdapter extends RecyclerView.Adapter<OrdenServicioAdapter.ViewHolder> {

    private ArrayList<OrdenServicio> listaOrdenes;

    public OrdenServicioAdapter(ArrayList<OrdenServicio> listaOrdenes) {
        this.listaOrdenes = listaOrdenes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_orden_servicio, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrdenServicio orden = listaOrdenes.get(position);
        holder.tvCliente.setText("Cliente: " + orden.getIdCliente());
        holder.tvFecha.setText("Fecha: " + orden.getFechaOrden().toString());
        holder.tvPlaca.setText("Placa: " + orden.getPlacaVehiculo());
        holder.tvTotal.setText("Total a pagar: $" + String.format("%.2f", orden.getTotalOrden()));
    }

    @Override
    public int getItemCount() {
        return listaOrdenes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCliente, tvFecha, tvPlaca, tvTotal;
        Button btnMasDetalle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCliente = itemView.findViewById(R.id.tvCliente);
            tvFecha = itemView.findViewById(R.id.tvFecha);
            tvPlaca = itemView.findViewById(R.id.tvPlaca);
            tvTotal = itemView.findViewById(R.id.tvTotal);
            btnMasDetalle = itemView.findViewById(R.id.btnMasDetalle);
        }
    }
}
