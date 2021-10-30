package com.proyectomintic.stockerinv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_AppCompat);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button registrarse = findViewById(R.id.botonRegistro);
        registrarse.setOnClickListener(v -> {
            Intent intent = new Intent (v.getContext(), RegistroActivity.class);
            startActivity(intent);
        });

    }
}