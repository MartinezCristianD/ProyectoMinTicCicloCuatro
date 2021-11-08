package com.proyectomintic.stockerinv.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.proyectomintic.stockerinv.R;
import com.proyectomintic.stockerinv.databinding.ActivityRegistroBinding;

public class RegistroActivity extends AppCompatActivity {

    private ActivityRegistroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setTheme(R.style.Theme_AppCompat);
        setContentView(binding.getRoot());

        binding.btnRegistro.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), MainActivity.class);
            startActivity(intent);

        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Gracias por Elegir °StockerInv°", Toast.LENGTH_LONG).show();

    }
}