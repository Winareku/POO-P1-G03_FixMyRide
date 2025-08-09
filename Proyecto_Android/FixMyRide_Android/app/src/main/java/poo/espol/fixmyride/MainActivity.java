package poo.espol.fixmyride;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAdministrarClientes = findViewById(R.id.button1);
        Button btnAdministrarProveedores = findViewById(R.id.button2);
        Button btnAdministrarTecnicos = findViewById(R.id.button3);

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
    }
}