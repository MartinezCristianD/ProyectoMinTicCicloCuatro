package com.proyectomintic.stockerinv;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_AppCompat);
        setContentView(R.layout.activity_registro);

        ImageButton botonRegresar = findViewById(R.id.botonRegresar);
        botonRegresar.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        Button botonRegistrarse = findViewById(R.id.btnRegistro);
        botonRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CategoriasActivity.class);
                startActivity(intent);

            }

        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Gracias por Elegir °StockerInv°", Toast.LENGTH_LONG).show();

    }
}