package poo.espol.fixmyride.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import poo.espol.fixmyride.R;
import poo.espol.fixmyride.model.Tecnico;

public class TecnicoAdapter extends RecyclerView.Adapter<TecnicoAdapter.TecnicoViewHolder> {
    private ArrayList<Tecnico> list;
    private OnTecnicoEliminarListener eliminarListener;
    public interface OnTecnicoEliminarListener {void onEliminar(int position);}

    public TecnicoAdapter(ArrayList<Tecnico> list, OnTecnicoEliminarListener eliminarListener) {
        this.list = list;
        this.eliminarListener = eliminarListener;
    }

    @Override
    public TecnicoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tecnico, parent, false);
        return new TecnicoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TecnicoViewHolder holder, int position) {
        Tecnico tecnico = list.get(position);
        holder.tvId.setText("Identificación: " + tecnico.getId());
        holder.tvNombre.setText("Nombre: " + tecnico.getNombre());
        holder.tvTelefono.setText("Teléfono: " + tecnico.getTelefono());
        holder.tvEspecialidad.setText("Especialidad: " + tecnico.getEspecialidad());
        holder.btnEliminar.setOnClickListener(v -> {
            if (eliminarListener != null) eliminarListener.onEliminar(holder.getAdapterPosition());});
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class TecnicoViewHolder extends RecyclerView.ViewHolder {
        TextView tvId, tvNombre, tvTelefono, tvEspecialidad;
        Button btnEliminar;

        public TecnicoViewHolder(View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvIdentificacion);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvTelefono = itemView.findViewById(R.id.tvTelefono);
            tvEspecialidad = itemView.findViewById(R.id.tvEspecialidad);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }
}