package poo.espol.fixmyride.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import poo.espol.fixmyride.R;
import poo.espol.fixmyride.model.Cliente;
import java.util.ArrayList;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ViewHolder> {

    // Variables
    private ArrayList<Cliente> list;
    private OnEliminarListener eliminarListener;
    public interface OnEliminarListener {void onEliminar(int position);}

    // Constructor
    public ClienteAdapter(ArrayList<Cliente> list, OnEliminarListener eliminarListener) {
        this.list = list;
        this.eliminarListener = eliminarListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cliente, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Cliente cliente = list.get(position);
        holder.tvId.setText("Identificación: " + cliente.getId());
        holder.tvNombre.setText("Nombre: " + cliente.getNombre());
        holder.tvDireccion.setText("Dirección: " + cliente.getDireccion());
        holder.tvTelefono.setText("Teléfono: " + cliente.getTelefono());
        holder.tvTipoCliente.setText("Tipo de Cliente: " + cliente.getTipoCliente());
        holder.btnEliminar.setOnClickListener(v -> {
            if (eliminarListener != null) {eliminarListener.onEliminar(holder.getAdapterPosition());}});
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvId, tvNombre, tvDireccion, tvTelefono, tvTipoCliente;
        Button btnEliminar;

        public ViewHolder(View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvIdentificacion);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            tvTelefono = itemView.findViewById(R.id.tvTelefono);
            tvTipoCliente = itemView.findViewById(R.id.tvTipoCliente);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }

    @Override
    public int getItemCount() {return list.size();}
}