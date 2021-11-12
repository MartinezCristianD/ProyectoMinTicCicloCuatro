package com.proyectomintic.stockerinv.views;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.proyectomintic.stockerinv.R;
import com.proyectomintic.stockerinv.databinding.ActivityInventarioBinding;
import com.proyectomintic.stockerinv.views.model.Elemento;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class InventarioActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public ActivityInventarioBinding binding;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    ArrayList<Elemento> elementos = new ArrayList<>();
    public static final String ORIGEN = "origen", DESTINO = "destino";
    String textViewContador, crearNombreArticulos, textViewCategoriaElegida, eleccionDestino, eleccionOrigen;
    GoogleSignInClient mGoogleSignInClient;
    GoogleSignInOptions gso;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            LoginFragment dialogo_cero = new LoginFragment();
            dialogo_cero.show(getSupportFragmentManager(), "LoginFragment");
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_AppCompat);
        binding = ActivityInventarioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //Picaso para importar la imagende usuario
        if ((mAuth.getCurrentUser() != null) && (mAuth.getCurrentUser().getPhotoUrl() != null)) {
            Picasso.with(this)
                    .load(mAuth.getCurrentUser().getPhotoUrl().toString())
                    .into(binding.imageViewFotoUsuario);
        }

        //Llamado a la barra de navegacion
        binding.bottomNavigation.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);

        //View listener para llamar los fragments
        View.OnClickListener listener = view -> {
            ListaElementosCategoriaFragment dialogo = new ListaElementosCategoriaFragment();
            Bundle bundle = new Bundle();
            bundle.putString("titulo_categoria", ((Button) view).getText().toString());
            // elementos.stream().filter();
            dialogo.setArguments(bundle);
            dialogo.show(getSupportFragmentManager(), "ListaElementosCategoriaFragment");

        };

        //Evento click boton cocina Llamado al Fragment
        binding.btnCategoriaCocina.setOnClickListener(listener);
        binding.btnCategoriaDecoracion.setOnClickListener(listener);
        binding.btnCategoriaJardin.setOnClickListener(listener);
        binding.btnCategoriaTecnologia.setOnClickListener(listener);
        binding.btnCategoriaElectrodomesticos.setOnClickListener(listener);
        binding.btnCategroiaMuebles.setOnClickListener(listener);
        binding.btnCategoriaLimpieza.setOnClickListener(listener);


      /*  // Recuperando informacion de ElementosActivity
        eleccionOrigen = getIntent().getExtras().getString(ORIGEN);
        eleccionDestino = getIntent().getExtras().getString(DESTINO);
        textViewContador = getIntent().getExtras().getString("texto_contador");
        crearNombreArticulos = getIntent().getExtras().getString("nombre_articulo");
        textViewCategoriaElegida = getIntent().getExtras().getString("seleccion_categoria");*/

        Elemento elemento = new Elemento(crearNombreArticulos, textViewCategoriaElegida, textViewContador, null);
        elementos.add(elemento);

        //Mostrando el String en el TextView
        if (eleccionOrigen != null || eleccionDestino != null) {
            binding.textViewOrigen.setText(eleccionOrigen);
            binding.textViewDestino.setText(eleccionDestino);
        } else {
            String uno = getString(R.string.origen);
            String dos = getString(R.string.destino);
            binding.textViewOrigen.setText(uno);
            binding.textViewDestino.setText(dos);

        }


    }

    // Configuracion de la barra inferior
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.page_to_export:
                Toast.makeText(this, "Enviaremos su Inventario", Toast.LENGTH_LONG).show();
                return true;

            case R.id.page_to_home:
                LoginFragment dialogo_cero = new LoginFragment();
                dialogo_cero.show(getSupportFragmentManager(), "LoginFragment");
                return true;

            case R.id.page_to_add:

                CrearElementoFragment dialogo_dos = new CrearElementoFragment();
                dialogo_dos.show(getSupportFragmentManager(), "CrearElementoFragment");

                return true;

            case R.id.page_to_ruta:

                RutaFragment dialogo_uno = new RutaFragment();
                dialogo_uno.show(getSupportFragmentManager(), "RutaFragment");

                return true;

            default:
                return false;
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mGoogleSignInClient.signOut();
        FirebaseAuth.getInstance().signOut();


    }

}


