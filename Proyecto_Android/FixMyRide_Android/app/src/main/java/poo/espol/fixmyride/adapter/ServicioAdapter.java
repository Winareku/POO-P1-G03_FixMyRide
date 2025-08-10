package poo.espol.fixmyride.adapter;
import android.content.Context;
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

    private Context context;
    private ArrayList<Servicio> listaServicios;
    private OnEditarClickListener listener;

    public interface OnEditarClickListener {
        void onEditarClick(int position);
    }

    public ServicioAdapter(Context context, ArrayList<Servicio> listaServicios, OnEditarClickListener listener) {
        this.context = context;
        this.listaServicios = listaServicios;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ServicioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(context).inflate(R.layout.item_servicio, parent, false);
        return new ServicioViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ServicioViewHolder holder, int position) {
        Servicio servicio = listaServicios.get(position);
        holder.tvCodigo.setText("Código: S" + servicio.getCodigo());
        holder.tvNombre.setText("Nombre del Servicio: " + servicio.getNombre());
        holder.tvPrecio.setText(String.format("Precio: $%.2f", servicio.getPrecio()));

        holder.btnEditar.setOnClickListener(v -> listener.onEditarClick(position));
    }

    @Override
    public int getItemCount() {
        return listaServicios.size();
    }

    public static class ServicioViewHolder extends RecyclerView.ViewHolder {
        TextView tvCodigo, tvNombre, tvPrecio;
        Button btnEditar;

        public ServicioViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCodigo = itemView.findViewById(R.id.tvCodigo);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            btnEditar = itemView.findViewById(R.id.btnEditar);
        }
    }
}



