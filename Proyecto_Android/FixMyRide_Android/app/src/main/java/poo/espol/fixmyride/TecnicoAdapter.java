package poo.espol.fixmyride;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class TecnicoAdapter extends RecyclerView.Adapter<TecnicoAdapter.TecnicoViewHolder> {
    private ArrayList<Tecnico> tecnicos;
    private OnTecnicoActionListener listener;

    public interface OnTecnicoActionListener {
        void onEliminar(int position);
    }

    public TecnicoAdapter(ArrayList<Tecnico> tecnicos, OnTecnicoActionListener listener) {
        this.tecnicos = tecnicos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TecnicoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tecnico, parent, false);
        return new TecnicoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TecnicoViewHolder holder, int position) {
        Tecnico tecnico = tecnicos.get(position);
        holder.tvIdentificacion.setText("Identificación: " + tecnico.getIdentificacion());
        holder.tvNombre.setText("Nombre: " + tecnico.getNombre());
        holder.tvTelefono.setText("Teléfono: " + tecnico.getTelefono());
        holder.tvEspecialidad.setText("Especialidad: " + tecnico.getEspecialidad());

        holder.btnEliminar.setOnClickListener(v -> {
            if (listener != null) listener.onEliminar(holder.getAdapterPosition());
        });
    }

    @Override
    public int getItemCount() {
        return tecnicos.size();
    }

    public static class TecnicoViewHolder extends RecyclerView.ViewHolder {
        TextView tvIdentificacion, tvNombre, tvTelefono, tvEspecialidad;
        Button btnEliminar;

        public TecnicoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIdentificacion = itemView.findViewById(R.id.tvIdentificacion);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvTelefono = itemView.findViewById(R.id.tvTelefono);
            tvEspecialidad = itemView.findViewById(R.id.tvEspecialidad);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }
}