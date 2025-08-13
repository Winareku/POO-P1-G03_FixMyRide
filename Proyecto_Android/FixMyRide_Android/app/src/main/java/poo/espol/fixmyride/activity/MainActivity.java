package poo.espol.fixmyride.activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import poo.espol.fixmyride.R;
import poo.espol.fixmyride.extra.DataRepository;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataRepository.cargarDatos();

        // Variables
        Button btnAdministrarClientes = findViewById(R.id.button1);
        Button btnAdministrarProveedores = findViewById(R.id.button2);
        Button btnAdministrarTecnicos = findViewById(R.id.button3);
        Button btnAdministrarServicios= findViewById(R.id.button4);
        Button btnAdministrarOrdenServicio = findViewById(R.id.button5);

        // Métodos
        btnAdministrarClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ClienteActivity.class);
                startActivity(intent);
            }
        });

        btnAdministrarProveedores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProveedorActivity.class);
                startActivity(intent);
            }
        });

        btnAdministrarTecnicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TecnicoActivity.class);
                startActivity(intent);
            }
        });
        btnAdministrarServicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ServicioActivity.class);
                startActivity(intent);
            }
        });
        btnAdministrarOrdenServicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OrdenActivity.class);
                startActivity(intent);
            }
        });
    }
}