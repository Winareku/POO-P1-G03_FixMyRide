package poo.espol.fixmyride.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import poo.espol.fixmyride.R;
import poo.espol.fixmyride.model.Proveedor;

public class ProveedorAdapter extends RecyclerView.Adapter<ProveedorAdapter.ProveedorViewHolder> {

    private List<Proveedor> proveedores;
    private OnProveedorActionListener listener;

    public interface OnProveedorActionListener {
        void onEliminar(int position);
    }

    public ProveedorAdapter(List<Proveedor> proveedores, OnProveedorActionListener listener) {
        this.proveedores = proveedores;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProveedorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_proveedor, parent, false);
        return new ProveedorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProveedorViewHolder holder, int position) {
        Proveedor proveedor = proveedores.get(position);
        holder.tvIdentificacion.setText("Identificación: " + proveedor.getId());
        holder.tvNombre.setText("Nombre: " + proveedor.getNombre());
        holder.tvTelefono.setText("Teléfono: " + proveedor.getTelefono());
        holder.tvDescripcion.setText("Descripción: " + proveedor.getDescripcion());

        holder.btnEliminar.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEliminar(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return proveedores.size();
    }

    static class ProveedorViewHolder extends RecyclerView.ViewHolder {
        TextView tvIdentificacion, tvNombre, tvTelefono, tvDescripcion;
        Button btnEliminar;

        public ProveedorViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIdentificacion = itemView.findViewById(R.id.tvIdentificacion);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvTelefono = itemView.findViewById(R.id.tvTelefono);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }
}