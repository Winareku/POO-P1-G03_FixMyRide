package poo.espol.fixmyride.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import poo.espol.fixmyride.R;
import poo.espol.fixmyride.model.Proveedor;

public class ProveedorAdapter extends RecyclerView.Adapter<ProveedorAdapter.ProveedorViewHolder> {
    private ArrayList<Proveedor> list;
    private OnProveedorEliminarListener eliminarListener;
    public interface OnProveedorEliminarListener {void onEliminar(int position);}

    public ProveedorAdapter(ArrayList<Proveedor> list, OnProveedorEliminarListener eliminarListener) {
        this.list = list;
        this.eliminarListener = eliminarListener;
    }

    @Override
    public ProveedorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_proveedor, parent, false);
        return new ProveedorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProveedorViewHolder holder, int position) {
        Proveedor proveedor = list.get(position);
        holder.tvId.setText("Identificación: " + proveedor.getId());
        holder.tvNombre.setText("Nombre: " + proveedor.getNombre());
        holder.tvTelefono.setText("Teléfono: " + proveedor.getTelefono());
        holder.tvDescripcion.setText("Descripción: " + proveedor.getDescripcion());
        holder.btnEliminar.setOnClickListener(v -> {
            if (eliminarListener != null) {eliminarListener.onEliminar(holder.getAdapterPosition());}});
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ProveedorViewHolder extends RecyclerView.ViewHolder {
        TextView tvId, tvNombre, tvTelefono, tvDescripcion;
        Button btnEliminar;

        public ProveedorViewHolder(View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvIdentificacion);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvTelefono = itemView.findViewById(R.id.tvTelefono);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }
}