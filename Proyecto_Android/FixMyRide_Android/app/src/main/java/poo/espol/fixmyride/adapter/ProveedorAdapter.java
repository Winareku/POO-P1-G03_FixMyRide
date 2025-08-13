package poo.espol.fixmyride.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import poo.espol.fixmyride.R;
import poo.espol.fixmyride.model.Proveedor;
import java.util.ArrayList;

public class ProveedorAdapter extends RecyclerView.Adapter<ProveedorAdapter.ViewHolder> {

    // Variables
    private ArrayList<Proveedor> list;
    private OnEliminarListener eliminarListener;
    public interface OnEliminarListener {void onEliminar(int position);}

    // Constructor
    public ProveedorAdapter(ArrayList<Proveedor> list, OnEliminarListener eliminarListener) {
        this.list = list;
        this.eliminarListener = eliminarListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_proveedor, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Proveedor proveedor = list.get(position);
        holder.tvId.setText("Identificación: " + proveedor.getId());
        holder.tvNombre.setText("Nombre: " + proveedor.getNombre());
        holder.tvTelefono.setText("Teléfono: " + proveedor.getTelefono());
        holder.tvDescripcion.setText("Descripción: " + proveedor.getDescripcion());
        holder.btnEliminar.setOnClickListener(v -> {
            if (eliminarListener != null) {eliminarListener.onEliminar(holder.getAdapterPosition());}});
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvId, tvNombre, tvTelefono, tvDescripcion;
        Button btnEliminar;

        public ViewHolder(View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvIdentificacion);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvTelefono = itemView.findViewById(R.id.tvTelefono);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }

    @Override
    public int getItemCount() {return list.size();}
}