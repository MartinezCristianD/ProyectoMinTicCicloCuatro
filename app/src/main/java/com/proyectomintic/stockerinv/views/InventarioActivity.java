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

public class InventarioActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public ActivityInventarioBinding binding;
    //  String eleccionOrigen;
    //  String eleccionDestino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInventarioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.bottomNavigation.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);

        //Llamado al Fragment


     /*  // Commit a la transacción
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ListaElementosCategoriaFragment fragment1 = new ListaElementosCategoriaFragment();
        fragmentTransaction.add(R.id.fragmento_cocina, fragment1, "fragment");
        fragmentTransaction.commit();

        FragmentTransaction fragmenttransaction = getSupportFragmentManager().beginTransaction();
   HomeFragment regcomplainfragment = new HomeFragment();
   fragmenttransaction.replace(R.id.content_frame, regcomplainfragment).addToBackStack("HomeFragment");
   fragmenttransaction.commit();
*//*
        //Evento click boton cocina
        binding.btnCategoriaCocina.setOnClickListener(v -> {
            Intent intent = new Intent(InventarioActivity.this,ListaElementosCategoriaFragment.class);
            startActivity(intent);

        });

        // Recuperando informacion de RutaActivity
        Intent recibir = getIntent();

        eleccionOrigen = recibir.getExtras().getString("Eleccion_Origen");
        eleccionDestino = recibir.getExtras().getString("Eleccion_Destino");

        binding.textViewOrigen.setText("Lugar origen del inventario " + eleccionOrigen);
        binding.textViewDestino.setText("Destino del inventario " + eleccionDestino);
*/


    }

    // Configuracion de la barra inferior
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.page_to_export:
                Toast.makeText(this, "Enviaremos su Inventario", Toast.LENGTH_LONG).show();
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


