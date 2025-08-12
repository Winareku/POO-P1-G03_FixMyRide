package poo.espol.fixmyride.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import poo.espol.fixmyride.R;
import poo.espol.fixmyride.model.Servicio;

public class ServicioAdapter extends RecyclerView.Adapter<ServicioAdapter.ServicioViewHolder> {
    private ArrayList<Servicio> list;
    private OnEliminarClickListener eliminarListener;
    public interface OnEliminarClickListener {void onEliminar(int position);}

    public ServicioAdapter(ArrayList<Servicio> list, OnEliminarClickListener eliminarListener) {
        this.list = list;
        this.eliminarListener = eliminarListener;
    }

    @Override
    public ServicioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_servicio, parent, false);
        return new ServicioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ServicioViewHolder holder, int position) {
        Servicio servicio = list.get(position);
        holder.tvCodigo.setText("Código: " + servicio.getCodigo());
        holder.tvNombre.setText("Nombre: " + servicio.getNombre());
        holder.tvPrecio.setText("Precio: $" + servicio.getPrecio());
        holder.btnEliminar.setOnClickListener(v -> {
            if (eliminarListener != null) {eliminarListener.onEliminar(holder.getAdapterPosition());}
        });
    }


    @Override
    public int getItemCount() {return list.size();}

    public static class ServicioViewHolder extends RecyclerView.ViewHolder {
        TextView tvCodigo, tvNombre, tvPrecio;
        Button btnEditar, btnEliminar;

        public ServicioViewHolder(View itemView) {
            super(itemView);
            tvCodigo = itemView.findViewById(R.id.tvCodigo);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }
}



