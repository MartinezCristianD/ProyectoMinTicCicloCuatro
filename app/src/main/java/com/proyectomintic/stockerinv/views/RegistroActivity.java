package com.proyectomintic.stockerinv.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.proyectomintic.stockerinv.R;
import com.proyectomintic.stockerinv.databinding.ActivityRegistroBinding;

public class RegistroActivity extends AppCompatActivity {

    private ActivityRegistroBinding binding;
    private static final String TAG = "tag";
    private FirebaseAuth mAuth;
    private String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setTheme(R.style.Theme_AppCompat);
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();

        email = binding.registroCorreo.getEditText().toString();
        password = binding.confirmarClave.getEditText().toString();


        binding.btnRegistro.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), MainActivity.class);
            startActivity(intent);

        });

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegistroActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });

    }

    private void updateUI(FirebaseUser user) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Gracias por Elegir °StockerInv°", Toast.LENGTH_LONG).show();

    }
}