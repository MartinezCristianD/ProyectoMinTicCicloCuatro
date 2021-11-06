package com.proyectomintic.stockerinv.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.proyectomintic.stockerinv.R;

public class RutaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruta);

        Button botonContinuar = findViewById(R.id.buttonContinuar);
        botonContinuar.setOnClickListener(v -> {
            Intent i = new Intent(v.getContext(), CategoriasActivity.class);
            startActivity(i);
        });
    }
}