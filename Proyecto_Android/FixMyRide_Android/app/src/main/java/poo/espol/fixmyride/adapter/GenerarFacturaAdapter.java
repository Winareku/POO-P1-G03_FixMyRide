package poo.espol.fixmyride.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

import poo.espol.fixmyride.R;
import poo.espol.fixmyride.model.Factura;

public class GenerarFacturaAdapter extends RecyclerView.Adapter<GenerarFacturaAdapter.ViewHolder> {

    private ArrayList<Factura> listaFacturas;
    private OnDetalleClickListener onDetalleClickListener;

    public GenerarFacturaAdapter(ArrayList<Factura> listaFacturas, OnDetalleClickListener listener) {
        this.listaFacturas = listaFacturas;
        this.onDetalleClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detalle_factura, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Factura factura = listaFacturas.get(position);
        holder.tvEmpresa.setText("Empresa: " + factura.getEmpresa().getNombre());
        
        // Obtener la fecha de hoy
        LocalDate fechaHoy = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        holder.tvFechaCreacion.setText("Fecha de creación: " + fechaHoy.format(formatter));
        
        holder.tvPeriodo.setText("Periodo: " + factura.getPeriodo());

        double totalFactura = calcularTotalFactura(factura);
        holder.tvTotalPagar.setText("Total a pagar: $" + String.format("%.2f", totalFactura));

        holder.btnMasDetalle.setOnClickListener(v -> {
            if (onDetalleClickListener != null) {
                onDetalleClickListener.onDetalleClick(factura);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaFacturas.size();
    }

    private double calcularTotalFactura(Factura factura) {
        double total = 0.0;
        // Sumar el costo de todos los servicios en todas las Órdenes de Servicio
        for (int i = 0; i < factura.getListaOrdenServicio().size(); i++) {
            total += factura.getListaOrdenServicio().get(i).getTotalOrden();
        }
        // Sumar el costo fijo de $50 para clientes empresariales
        total += 50.0;
        return total;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvEmpresa, tvFechaCreacion, tvPeriodo, tvTotalPagar;
        Button btnMasDetalle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEmpresa = itemView.findViewById(R.id.tvEmpresa);
            tvFechaCreacion = itemView.findViewById(R.id.tvFechaCreacion);
            tvPeriodo = itemView.findViewById(R.id.tvPeriodo);
            tvTotalPagar = itemView.findViewById(R.id.tvTotalPagar);
            btnMasDetalle = itemView.findViewById(R.id.btnMasDetalle);
        }
    }

    public interface OnDetalleClickListener {
        void onDetalleClick(Factura factura);
    }
}