package poo.espol.fixmyride;
import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder> {

    private List<Cliente> clientes;
    private OnClienteActionListener listener;

    public interface OnClienteActionListener {
        void onEliminar(int position);
    }

    public ClienteAdapter(List<Cliente> clientes, OnClienteActionListener listener) {
        this.clientes = clientes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cliente, parent, false);
        return new ClienteViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ClienteViewHolder holder, int position) {
        Cliente cliente = clientes.get(position);
        holder.tvIdentificacion.setText("Identificación: " + cliente.getIdentificacion());
        holder.tvNombre.setText("Nombre: " + cliente.getNombre());
        holder.tvDireccion.setText("Dirección: " + cliente.getDireccion());
        holder.tvTelefono.setText("Teléfono: " + cliente.getTelefono());
        holder.tvTipoCliente.setText("Tipo de Cliente: " + cliente.getTipoCliente());

        holder.btnEliminar.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEliminar(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return clientes.size();
    }

    public static class ClienteViewHolder extends RecyclerView.ViewHolder {
        TextView tvIdentificacion, tvNombre, tvDireccion, tvTelefono, tvTipoCliente;
        Button btnEliminar;

        public ClienteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIdentificacion = itemView.findViewById(R.id.tvIdentificacion);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            tvTelefono = itemView.findViewById(R.id.tvTelefono);
            tvTipoCliente = itemView.findViewById(R.id.tvTipoCliente);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }
}