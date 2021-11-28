// Generated by view binder compiler. Do not edit!
package com.proyectomintic.stockerinv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.gms.maps.MapView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;
import com.proyectomintic.stockerinv.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentRutaBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final MaterialCardView CardViewMapa;

  @NonNull
  public final TextInputLayout InputDestino;

  @NonNull
  public final TextInputLayout InputOrigen;

  @NonNull
  public final Button btnVerificarRuta;

  @NonNull
  public final MaterialButton buttonContinuar;

  @NonNull
  public final MaterialCardView cardViewIngresoDestino;

  @NonNull
  public final MaterialAutoCompleteTextView inputDestinoText;

  @NonNull
  public final MaterialAutoCompleteTextView inputOrigenText;

  @NonNull
  public final MapView mapView;

  @NonNull
  public final TextView textViewDistancia;

  private FragmentRutaBinding(@NonNull ConstraintLayout rootView,
      @NonNull MaterialCardView CardViewMapa, @NonNull TextInputLayout InputDestino,
      @NonNull TextInputLayout InputOrigen, @NonNull Button btnVerificarRuta,
      @NonNull MaterialButton buttonContinuar, @NonNull MaterialCardView cardViewIngresoDestino,
      @NonNull MaterialAutoCompleteTextView inputDestinoText,
      @NonNull MaterialAutoCompleteTextView inputOrigenText, @NonNull MapView mapView,
      @NonNull TextView textViewDistancia) {
    this.rootView = rootView;
    this.CardViewMapa = CardViewMapa;
    this.InputDestino = InputDestino;
    this.InputOrigen = InputOrigen;
    this.btnVerificarRuta = btnVerificarRuta;
    this.buttonContinuar = buttonContinuar;
    this.cardViewIngresoDestino = cardViewIngresoDestino;
    this.inputDestinoText = inputDestinoText;
    this.inputOrigenText = inputOrigenText;
    this.mapView = mapView;
    this.textViewDistancia = textViewDistancia;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentRutaBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentRutaBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_ruta, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentRutaBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.CardViewMapa;
      MaterialCardView CardViewMapa = ViewBindings.findChildViewById(rootView, id);
      if (CardViewMapa == null) {
        break missingId;
      }

      id = R.id.InputDestino;
      TextInputLayout InputDestino = ViewBindings.findChildViewById(rootView, id);
      if (InputDestino == null) {
        break missingId;
      }

      id = R.id.InputOrigen;
      TextInputLayout InputOrigen = ViewBindings.findChildViewById(rootView, id);
      if (InputOrigen == null) {
        break missingId;
      }

      id = R.id.btnVerificarRuta;
      Button btnVerificarRuta = ViewBindings.findChildViewById(rootView, id);
      if (btnVerificarRuta == null) {
        break missingId;
      }

      id = R.id.buttonContinuar;
      MaterialButton buttonContinuar = ViewBindings.findChildViewById(rootView, id);
      if (buttonContinuar == null) {
        break missingId;
      }

      id = R.id.cardViewIngresoDestino;
      MaterialCardView cardViewIngresoDestino = ViewBindings.findChildViewById(rootView, id);
      if (cardViewIngresoDestino == null) {
        break missingId;
      }

      id = R.id.inputDestinoText;
      MaterialAutoCompleteTextView inputDestinoText = ViewBindings.findChildViewById(rootView, id);
      if (inputDestinoText == null) {
        break missingId;
      }

      id = R.id.inputOrigenText;
      MaterialAutoCompleteTextView inputOrigenText = ViewBindings.findChildViewById(rootView, id);
      if (inputOrigenText == null) {
        break missingId;
      }

      id = R.id.mapView;
      MapView mapView = ViewBindings.findChildViewById(rootView, id);
      if (mapView == null) {
        break missingId;
      }

      id = R.id.textViewDistancia;
      TextView textViewDistancia = ViewBindings.findChildViewById(rootView, id);
      if (textViewDistancia == null) {
        break missingId;
      }

      return new FragmentRutaBinding((ConstraintLayout) rootView, CardViewMapa, InputDestino,
          InputOrigen, btnVerificarRuta, buttonContinuar, cardViewIngresoDestino, inputDestinoText,
          inputOrigenText, mapView, textViewDistancia);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
