package com.proyectomintic.stockerinv.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.proyectomintic.stockerinv.R;
import com.proyectomintic.stockerinv.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.botonRegistro.setOnClickListener(v -> {
            Intent i = new Intent(v.getContext(), RegistroActivity.class);
            startActivity(i);
        });

        binding.buttonInvitado.setOnClickListener(v -> {
            Intent i = new Intent(v.getContext(), ElementosActivity.class);
            Toast.makeText(this, "Bienvenido", Toast.LENGTH_LONG).show();
            startActivity(i);
        });

        binding.botonIngreso.setOnClickListener(v -> {
            Intent i = new Intent(v.getContext(), RutaActivity.class);
            Toast.makeText(this, "Bienvenido", Toast.LENGTH_LONG).show();
            startActivity(i);
        });

        binding.ingresoGoogle.setOnClickListener(v -> {
            Intent i = new Intent(v.getContext(), RutaActivity.class);
            Toast.makeText(this, "Bienvenido", Toast.LENGTH_LONG).show();
            startActivity(i);
        });

    }

    @Override
    protected  void onStart() {
        super.onStart();
        setTheme(R.style.Theme_AppCompat);
    }

}