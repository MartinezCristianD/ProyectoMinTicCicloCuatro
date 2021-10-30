package com.proyectomintic.stockerinv;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CategoriasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        Button botoncrearCategoria = findViewById(R.id.botonCrearCategoria);
        botoncrearCategoria.setOnClickListener(v -> {
            Intent intent = new Intent(this, ElementosActivity.class);
            startActivity(intent);
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Categoria creada exitosamente", Toast.LENGTH_LONG).show();

    }

}