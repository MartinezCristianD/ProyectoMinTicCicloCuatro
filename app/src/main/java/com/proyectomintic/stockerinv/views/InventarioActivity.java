package com.proyectomintic.stockerinv.views;

import static com.proyectomintic.stockerinv.views.CrearElementoFragment.CATEGORIA_ELEGIDA;
import static com.proyectomintic.stockerinv.views.CrearElementoFragment.CONTADOR;
import static com.proyectomintic.stockerinv.views.CrearElementoFragment.FRAGMENT_ELEMENTO_RESULT_KEY;
import static com.proyectomintic.stockerinv.views.CrearElementoFragment.NOMBRE_ARTICULO;
import static com.proyectomintic.stockerinv.views.RutaFragment.FRAGMENT_RUTA_RESULT_KEY;

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
import com.proyectomintic.stockerinv.model.Elemento;
import com.proyectomintic.stockerinv.utils.Dialogos;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class InventarioActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public ActivityInventarioBinding binding;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    ArrayList<Elemento> elementos = new ArrayList<>();
    public static final String ORIGEN = "origen", DESTINO = "destino", LISTA_CATEGORIAS = "LISTA_CATEGORIAS";
    String textViewContador, crearNombreArticulo, textViewCategoriaElegida, eleccionDestino, eleccionOrigen;
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

        // Actualizar los datos de origen y destino
        getSupportFragmentManager().setFragmentResultListener(FRAGMENT_RUTA_RESULT_KEY, this, (requestKey, result) -> {
            eleccionOrigen = result.getString(ORIGEN);
            eleccionDestino = result.getString(DESTINO);
            actualizarRuta();

        });

        // Actualizar los datos elementos
        getSupportFragmentManager().setFragmentResultListener(FRAGMENT_ELEMENTO_RESULT_KEY, this, (requestKey, result) -> {
            textViewCategoriaElegida = result.getString(CATEGORIA_ELEGIDA);
            textViewContador = result.getString(CONTADOR);
            crearNombreArticulo = result.getString(NOMBRE_ARTICULO);

            Elemento elemento = new Elemento(crearNombreArticulo, textViewCategoriaElegida, textViewContador, null);
            elementos.add(elemento);

            actualizarListaElementos();

        });

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
            String categoriaElegida = ((Button) view).getText().toString();

            ArrayList<Elemento> listaFiltrada = new ArrayList<>();
            for (Elemento elemento : elementos) {
                if (elemento.categoria.equals(categoriaElegida)) {
                    listaFiltrada.add(elemento);
                }
            }

            bundle.putString(CATEGORIA_ELEGIDA, categoriaElegida);
            bundle.putParcelableArrayList(LISTA_CATEGORIAS, listaFiltrada);
            dialogo.setArguments(bundle);
            dialogo.show(getSupportFragmentManager(), "ListaElementosCategoriaFragment");

        };

        // Actualizar  los datos de origen y destino( antes de que el usuario ingrese algun dato)
        actualizarRuta();

        //Evento click boton cocina Llamado al Fragment
        binding.btnCategoriaCocina.setOnClickListener(listener);
        binding.btnCategoriaDecoracion.setOnClickListener(listener);
        binding.btnCategoriaJardin.setOnClickListener(listener);
        binding.btnCategoriaTecnologia.setOnClickListener(listener);
        binding.btnCategoriaElectrodomesticos.setOnClickListener(listener);
        binding.btnCategroiaMuebles.setOnClickListener(listener);
        binding.btnCategoriaLimpieza.setOnClickListener(listener);

    }

    private void actualizarListaElementos() {
        elementos.forEach(elemento -> {
            switch (elemento.categoria) {

                case ("Cocina"):

                    break;
                case ("Limpieza"):

                    break;
                case ("Jardin"):

                    break;
                case ("Muebles"):

                    break;
                case ("Electrodomesticos"):

                    break;
                case ("Tecnologia"):

                    break;
                case ("Decoracion"):

                    break;

                default:
                    Dialogos.mensajePersonalizadoDialogo(this, "REQUERIDO", "Selecciona una categoria de la lista");
            }
        });
    }

    private void actualizarRuta() {
        //Mostrando el String en el TextView
        if (eleccionOrigen != null || eleccionDestino != null) {
            binding.textViewOrigen.setText(String.valueOf(eleccionOrigen));
            binding.textViewDestino.setText(String.valueOf(eleccionDestino));
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

    //cierre de sesion
    @Override
    public void onDestroy() {
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mGoogleSignInClient.signOut();
        FirebaseAuth.getInstance().signOut();
        super.onDestroy();

    }

}