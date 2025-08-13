package poo.espol.fixmyride.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import poo.espol.fixmyride.R;
import poo.espol.fixmyride.model.Tecnico;
import java.util.ArrayList;

public class TecnicoAdapter extends RecyclerView.Adapter<TecnicoAdapter.ViewHolder> {

    // Variables
    private ArrayList<Tecnico> list;
    private OnEliminarListener eliminarListener;
    public interface OnEliminarListener {void onEliminar(int position);}

    // Constructor
    public TecnicoAdapter(ArrayList<Tecnico> list, OnEliminarListener eliminarListener) {
        this.list = list;
        this.eliminarListener = eliminarListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tecnico, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Tecnico tecnico = list.get(position);
        holder.tvId.setText("Identificación: " + tecnico.getId());
        holder.tvNombre.setText("Nombre: " + tecnico.getNombre());
        holder.tvTelefono.setText("Teléfono: " + tecnico.getTelefono());
        holder.tvEspecialidad.setText("Especialidad: " + tecnico.getEspecialidad());
        holder.btnEliminar.setOnClickListener(v -> {
            if (eliminarListener != null) eliminarListener.onEliminar(holder.getAdapterPosition());});
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvId, tvNombre, tvTelefono, tvEspecialidad;
        Button btnEliminar;

        public ViewHolder(View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvIdentificacion);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvTelefono = itemView.findViewById(R.id.tvTelefono);
            tvEspecialidad = itemView.findViewById(R.id.tvEspecialidad);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }

    @Override
    public int getItemCount() {return list.size();}
}