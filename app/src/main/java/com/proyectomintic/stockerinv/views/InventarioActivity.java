package com.proyectomintic.stockerinv.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationView;
import com.proyectomintic.stockerinv.R;
import com.proyectomintic.stockerinv.databinding.ActivityInventarioBinding;

public class InventarioActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    public ActivityInventarioBinding binding;
    String eleccionOrigen;
    String eleccionDestino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInventarioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Recuperando informacion de RutaActivity
        Bundle parametros = this.getIntent().getExtras();
        if(parametros !=null){
            eleccionOrigen = parametros.getString("Eleccion_Origen");
            eleccionDestino = parametros.getString("Eleccion_Destino");
            binding.textViewOrigen.setText("Lugar origen del inventario " + eleccionOrigen);
            binding.textViewDestino.setText("Destino del inventario " + eleccionDestino);
        }

        binding.bottomNavigation.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);

        }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.page_to_export:
                Toast.makeText(this,"Enviaremos su Inventario",Toast.LENGTH_LONG).show();
                break;

            case R.id.page_to_home:
                Intent a = new Intent(this, MainActivity.class);
                startActivity(a);
                break;
            case R.id.page_to_add:
                Intent b = new Intent(this, ElementosActivity.class);
                startActivity(b);
                break;
            case R.id.page_to_ruta:
                Intent intent = new Intent(this, RutaActivity.class);
                startActivity(intent);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }
            return false;
    }
}


