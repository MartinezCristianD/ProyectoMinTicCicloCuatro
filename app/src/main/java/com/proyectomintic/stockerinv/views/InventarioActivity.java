package com.proyectomintic.stockerinv.views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.proyectomintic.stockerinv.R;
import com.proyectomintic.stockerinv.databinding.ActivityInventarioBinding;

public class InventarioActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public ActivityInventarioBinding binding;
    String eleccionOrigen, eleccionDestino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInventarioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //DATOS DE USUARIO
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();
        }

        binding.bottomNavigation.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);

        // Recuperando informacion de ElementosActivity
        eleccionOrigen = getIntent().getExtras().getString("origen");
        eleccionDestino = getIntent().getExtras().getString("destino");

        //Mostrando el String en el TextView
        binding.textViewOrigen.setText(eleccionOrigen);
        binding.textViewDestino.setText(eleccionDestino);

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FirebaseAuth.getInstance().signOut();
    }


}


