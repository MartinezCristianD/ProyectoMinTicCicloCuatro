package com.proyectomintic.stockerinv.views;

import static com.proyectomintic.stockerinv.views.RutaActivity.DESTINO;
import static com.proyectomintic.stockerinv.views.RutaActivity.ORIGEN;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.proyectomintic.stockerinv.R;
import com.proyectomintic.stockerinv.databinding.ActivityInventarioBinding;
import com.squareup.picasso.Picasso;

public class InventarioActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public ActivityInventarioBinding binding;
    String eleccionOrigen, eleccionDestino;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityInventarioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //picaso

        if ((mAuth.getCurrentUser() != null) && (mAuth.getCurrentUser().getPhotoUrl() != null)) {
            Picasso.with(this)
                    .load(mAuth.getCurrentUser().getPhotoUrl().toString())
                    .into(binding.imageViewFotoUsuario);
        }

        //Llamado a la barra de navegacion
        binding.bottomNavigation.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);

        // Recuperando informacion de ElementosActivity
        eleccionOrigen = getIntent().getExtras().getString(ORIGEN);
        eleccionDestino = getIntent().getExtras().getString(DESTINO);

        //Mostrando el String en el TextView
        binding.textViewOrigen.setText("Origen " + eleccionOrigen);
        binding.textViewDestino.setText("Destino " + eleccionDestino);

        //Llamado al Fragment

        //Evento click boton cocina
        binding.btnCategoriaCocina.setOnClickListener(v -> {
            ListaElementosCategoriaFragment dialogo = new ListaElementosCategoriaFragment();
            dialogo.show(getSupportFragmentManager(), "ListaElementosCategoriaFragment");

        });

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
            case R.id.page_to_Exit:
                FirebaseAuth.getInstance().signOut();
                finish();
            default:
                throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }
        return false;
    }

}


