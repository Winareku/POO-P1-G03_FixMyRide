package poo.espol.fixmyride;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ProveedorAdapter extends RecyclerView.Adapter<ProveedorAdapter.ProveedorViewHolder> {

    private List<Proveedor> proveedores;

    public ProveedorAdapter(List<Proveedor> proveedores) {
        this.proveedores = proveedores;
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
        holder.tvIdentificacion.setText(proveedor.identificacion);
        holder.tvNombre.setText(proveedor.nombre);
        holder.tvTelefono.setText(proveedor.telefono);
        holder.tvDescripcion.setText(proveedor.descripcion);
    }

    @Override
    public int getItemCount() {
        return proveedores.size();
    }

    public void setProveedores(List<Proveedor> proveedores) {
        this.proveedores = proveedores;
        notifyDataSetChanged();
    }

    static class ProveedorViewHolder extends RecyclerView.ViewHolder {
        TextView tvIdentificacion, tvNombre, tvTelefono, tvDescripcion;
        public ProveedorViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIdentificacion = itemView.findViewById(R.id.tvIdentificacion);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvTelefono = itemView.findViewById(R.id.tvTelefono);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
        }
    }
}