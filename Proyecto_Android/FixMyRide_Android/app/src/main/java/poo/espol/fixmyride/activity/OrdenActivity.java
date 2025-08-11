package poo.espol.fixmyride.activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

import poo.espol.fixmyride.R;
import poo.espol.fixmyride.adapter.OrdenServicioAdapter;
import poo.espol.fixmyride.model.TipoVehiculo;
import poo.espol.fixmyride.model.OrdenServicio;

public class OrdenActivity extends AppCompatActivity {

    RecyclerView recyclerOrdenes;
    OrdenServicioAdapter adapter;
    ArrayList<OrdenServicio> listaOrdenes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orden_servicio);

        recyclerOrdenes = findViewById(R.id.recyclerOrdenes);
        recyclerOrdenes.setLayoutManager(new LinearLayoutManager(this));

        listaOrdenes = getOrdenesDeEjemplo();
        adapter = new OrdenServicioAdapter(listaOrdenes);
        recyclerOrdenes.setAdapter(adapter);

        findViewById(R.id.btnCrearOrden).setOnClickListener(v -> {
            startActivity(new Intent(this, com.example.fixmyride.CrearOrdenActivity.class));
        });
    }

    private ArrayList<OrdenServicio> getOrdenesDeEjemplo() {
        ArrayList<OrdenServicio> lista = new ArrayList<>();
        OrdenServicio o1 = new OrdenServicio("Juan Pérez", "T1", LocalDate.of(2025,7,15), TipoVehiculo.AUTOMOVIL, "ABC-1234");
        o1.setTotalOrden(150.00);
        lista.add(o1);

        OrdenServicio o2 = new OrdenServicio("María Gómez", "T2", LocalDate.of(2025,7,10), TipoVehiculo.BUS, "XYZ-9876");
        o2.setTotalOrden(230.50);
        lista.add(o2);

        OrdenServicio o3 = new OrdenServicio("Pedro López", "T3", LocalDate.of(2025,6,20), TipoVehiculo.MOTOCICLETA, "JKL-4567");
        o3.setTotalOrden(85.75);
        lista.add(o3);

        OrdenServicio o4 = new OrdenServicio("Ana Torres", "T4", LocalDate.of(2025,5,10), TipoVehiculo.AUTOMOVIL, "MNO-2222");
        o4.setTotalOrden(300.00);
        lista.add(o4);

        return lista;
    }
}