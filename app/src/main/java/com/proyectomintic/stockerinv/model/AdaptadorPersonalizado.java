package com.proyectomintic.stockerinv.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.proyectomintic.stockerinv.R;

import java.util.ArrayList;

import kotlin.jvm.functions.Function1;

public class AdaptadorPersonalizado extends RecyclerView.Adapter<AdaptadorPersonalizado.ViewHolder> {
    // Declare Variables
    ArrayList<Elemento> elementos;
    Function1<Elemento, Void> borrar;

    // constructor
    public AdaptadorPersonalizado(ArrayList<Elemento> elementos, Function1<Elemento, Void> borrar) {
        this.elementos = elementos;
        this.borrar = borrar;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //creacion del layout del item en blanco
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // AQUI SE USA EL RECICLADO
        holder.bind(elementos.get(position), holder.itemView, borrar);
    }

    @Override
    public int getItemCount() {
        // es el numero  de datos que tiene la lista para renderizar
        return elementos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        }

        // reciclado de los View y asignacion de datos
        public void bind(Elemento elemento, View view, Function1<Elemento, Void> borrar) {
            ((TextView) view.findViewById(R.id.nombreElemento)).setText(elemento.nombre);
            ((TextView) view.findViewById(R.id.cantidadArticulos)).setText(elemento.cantidad);
            if (elemento.imagen == null) {
                ((ImageView) view.findViewById(R.id.imagenElementos)).setImageResource(R.drawable.logoapp);
            } else {
                ((ImageView) view.findViewById(R.id.imagenElementos)).setImageBitmap(elemento.imagen);
            }

            ((ImageButton) view.findViewById(R.id.borrarArticulo)).setOnClickListener(view1 -> {
                borrar.invoke(elemento);

            });

        }
    }

}