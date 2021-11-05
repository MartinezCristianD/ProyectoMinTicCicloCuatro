package com.proyectomintic.stockerinv.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.proyectomintic.stockerinv.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_AppCompat);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botonRegistrarse = findViewById(R.id.botonRegistro);
        botonRegistrarse.setOnClickListener(v -> {
            Intent i = new Intent(v.getContext(), RegistroActivity.class);
            startActivity(i);
        });

        Button botonInvitado = findViewById(R.id.buttonInvitado);
        botonInvitado.setOnClickListener(v -> {
            Intent i = new Intent(v.getContext(), CategoriasActivity.class);
            startActivity(i);
        });

        Button botonIngreso = findViewById(R.id.botonIngreso);
        botonIngreso.setOnClickListener(v -> {
            Intent i = new Intent(v.getContext(), CategoriasActivity.class);
            startActivity(i);
        });

        Button botonGoogle = findViewById(R.id.ingresoGoogle);
        botonGoogle.setOnClickListener(v -> {
            Intent i = new Intent(v.getContext(), CategoriasActivity.class);
            startActivity(i);
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Bienvenido", Toast.LENGTH_LONG).show();

    }

}