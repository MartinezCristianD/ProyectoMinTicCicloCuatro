package com.proyectomintic.stockerinv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_AppCompat);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botonIniciarSesion= findViewById(R.id.botonIngreso);
        Button botonIngresoInvitado = findViewById(R.id.buttonInvitado);
        Button botonrRegistrarse = findViewById(R.id.botonRegistro);
        Button botonGoogleingreso = findViewById(R.id.ingresoGoogle);

        botonrRegistrarse.setOnClickListener(v -> {
            Intent intent = new Intent (v.getContext(),RegistroActivity.class);
            startActivity(intent);
        });

        botonIniciarSesion.setOnClickListener(v -> {
            Intent intent = new Intent (v.getContext(),CategoriasActivity.class);
            startActivity(intent);
        });

        botonGoogleingreso.setOnClickListener(v -> {
            Intent intent = new Intent (v.getContext(),CategoriasActivity.class);
            startActivity(intent);
        });

        botonIngresoInvitado.setOnClickListener(v -> {
            Intent intent = new Intent (v.getContext(),CategoriasActivity.class);
            startActivity(intent);
        });

    }
}