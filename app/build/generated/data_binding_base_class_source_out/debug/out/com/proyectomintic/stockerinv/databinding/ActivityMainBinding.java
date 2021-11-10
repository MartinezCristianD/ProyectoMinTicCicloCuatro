// Generated by view binder compiler. Do not edit!
package com.proyectomintic.stockerinv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.gms.common.SignInButton;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputLayout;
import com.proyectomintic.stockerinv.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMainBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final MaterialButton botonIngreso;

  @NonNull
  public final MaterialButton botonRegistro;

  @NonNull
  public final MaterialButton buttonInvitado;

  @NonNull
  public final MaterialCardView cardViewLogin;

  @NonNull
  public final TextInputLayout crearClave;

  @NonNull
  public final SignInButton ingresoGoogle;

  @NonNull
  public final MaterialCardView materialCardView;

  @NonNull
  public final TextInputLayout registroCorreo;

  @NonNull
  public final TextView textAunSinCuenta;

  @NonNull
  public final TextView textoOlvidoContraseA;

  private ActivityMainBinding(@NonNull ConstraintLayout rootView,
      @NonNull MaterialButton botonIngreso, @NonNull MaterialButton botonRegistro,
      @NonNull MaterialButton buttonInvitado, @NonNull MaterialCardView cardViewLogin,
      @NonNull TextInputLayout crearClave, @NonNull SignInButton ingresoGoogle,
      @NonNull MaterialCardView materialCardView, @NonNull TextInputLayout registroCorreo,
      @NonNull TextView textAunSinCuenta, @NonNull TextView textoOlvidoContraseA) {
    this.rootView = rootView;
    this.botonIngreso = botonIngreso;
    this.botonRegistro = botonRegistro;
    this.buttonInvitado = buttonInvitado;
    this.cardViewLogin = cardViewLogin;
    this.crearClave = crearClave;
    this.ingresoGoogle = ingresoGoogle;
    this.materialCardView = materialCardView;
    this.registroCorreo = registroCorreo;
    this.textAunSinCuenta = textAunSinCuenta;
    this.textoOlvidoContraseA = textoOlvidoContraseA;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.botonIngreso;
      MaterialButton botonIngreso = ViewBindings.findChildViewById(rootView, id);
      if (botonIngreso == null) {
        break missingId;
      }

      id = R.id.botonRegistro;
      MaterialButton botonRegistro = ViewBindings.findChildViewById(rootView, id);
      if (botonRegistro == null) {
        break missingId;
      }

      id = R.id.buttonInvitado;
      MaterialButton buttonInvitado = ViewBindings.findChildViewById(rootView, id);
      if (buttonInvitado == null) {
        break missingId;
      }

      id = R.id.card_view_login;
      MaterialCardView cardViewLogin = ViewBindings.findChildViewById(rootView, id);
      if (cardViewLogin == null) {
        break missingId;
      }

      id = R.id.crearClave;
      TextInputLayout crearClave = ViewBindings.findChildViewById(rootView, id);
      if (crearClave == null) {
        break missingId;
      }

      id = R.id.ingresoGoogle;
      SignInButton ingresoGoogle = ViewBindings.findChildViewById(rootView, id);
      if (ingresoGoogle == null) {
        break missingId;
      }

      id = R.id.materialCardView;
      MaterialCardView materialCardView = ViewBindings.findChildViewById(rootView, id);
      if (materialCardView == null) {
        break missingId;
      }

      id = R.id.registroCorreo;
      TextInputLayout registroCorreo = ViewBindings.findChildViewById(rootView, id);
      if (registroCorreo == null) {
        break missingId;
      }

      id = R.id.textAunSinCuenta;
      TextView textAunSinCuenta = ViewBindings.findChildViewById(rootView, id);
      if (textAunSinCuenta == null) {
        break missingId;
      }

      id = R.id.textoOlvidoContraseña;
      TextView textoOlvidoContraseA = ViewBindings.findChildViewById(rootView, id);
      if (textoOlvidoContraseA == null) {
        break missingId;
      }

      return new ActivityMainBinding((ConstraintLayout) rootView, botonIngreso, botonRegistro,
          buttonInvitado, cardViewLogin, crearClave, ingresoGoogle, materialCardView,
          registroCorreo, textAunSinCuenta, textoOlvidoContraseA);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
