package poo.espol.fixmyride.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import poo.espol.fixmyride.R;
import poo.espol.fixmyride.model.Servicio;

public class ServicioAdapter extends RecyclerView.Adapter<ServicioAdapter.ViewHolder> {

    // Variables
    private ArrayList<Servicio> list;
    private OnEliminarListener eliminarListener;
    public interface OnEliminarListener {void onEliminar(int position);}

    // Constructor
    public ServicioAdapter(ArrayList<Servicio> list, OnEliminarListener eliminarListener) {
        this.list = list;
        this.eliminarListener = eliminarListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_servicio, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Servicio servicio = list.get(position);
        holder.tvCodigo.setText("Código: " + servicio.getCodigo());
        holder.tvNombre.setText("Nombre: " + servicio.getNombre());
        holder.tvPrecio.setText("Precio: $" + servicio.getPrecio());
        holder.btnEliminar.setOnClickListener(v -> {
            if (eliminarListener != null) {eliminarListener.onEliminar(holder.getAdapterPosition());}
        });
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCodigo, tvNombre, tvPrecio;
        Button btnEditar, btnEliminar;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCodigo = itemView.findViewById(R.id.tvCodigo);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }

    @Override
    public int getItemCount() {return list.size();}
}



