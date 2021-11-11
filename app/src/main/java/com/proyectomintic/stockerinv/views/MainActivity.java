package com.proyectomintic.stockerinv.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.proyectomintic.stockerinv.R;
import com.proyectomintic.stockerinv.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;
    private static final String TAG = "LoginScreen";
    private static final int RC_SIGN_IN = 9001;
    private FirebaseAuth mAuth;
    GoogleSignInClient mGoogleSignInClient;
    public static final String ORIGEN = "origen";
    public static final String DESTINO = "destino";
    String textViewCategoriaElegida, eleccionOrigen, eleccionDestino, textViewContador, crearNombreArticulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        eleccionOrigen = "";
        eleccionDestino = "";

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mAuth = FirebaseAuth.getInstance();

        // eventos click de los botones
        binding.botonRegistro.setOnClickListener(v -> {
            Intent i = new Intent(v.getContext(), RegistroActivity.class);
            startActivity(i);
        });

        binding.buttonInvitado.setOnClickListener(v -> {
            Intent i = new Intent(v.getContext(), InventarioActivity.class);

            startActivity(i);
        });

        binding.botonIngreso.setOnClickListener(v -> {

            Intent i = new Intent(v.getContext(), InventarioActivity.class);
            Toast.makeText(this, "Bienvenido", Toast.LENGTH_LONG).show();
            Bundle bundle = new Bundle();
            bundle.putString(ORIGEN, eleccionOrigen);
            bundle.putString(DESTINO, eleccionDestino);
            bundle.putString("texto_contador", textViewContador);
            bundle.putString("nombre_articulo", crearNombreArticulo);
            bundle.putString("seleccion_categoria", textViewCategoriaElegida);
            // enviarlos

            i.putExtras(bundle);

            startActivity(i);
        });

        binding.ingresoGoogle.setOnClickListener(view -> signIn());

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ingresoGoogle) {
            signIn();
            // ...
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            firebaseAuthWithGoogle(account.getIdToken());
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithCredential:failure", task.getException());
                        Snackbar.make(binding.getRoot(), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                        updateUI(null);
                    }

                    // ...
                });
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    // Cambio del splash screen
    @Override
    protected void onStart() {
        super.onStart();
        setTheme(R.style.Theme_AppCompat);
        updateUI(mAuth.getCurrentUser());
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Intent i = new Intent(this, RutaActivity.class);
            startActivity(i);
        }

    }


}
