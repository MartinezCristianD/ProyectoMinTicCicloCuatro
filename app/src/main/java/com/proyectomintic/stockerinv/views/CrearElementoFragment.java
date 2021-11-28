package com.proyectomintic.stockerinv.views;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.proyectomintic.stockerinv.R;
import com.proyectomintic.stockerinv.databinding.FragmentCrearElementoBinding;
import com.proyectomintic.stockerinv.utils.Dialogos;
import com.proyectomintic.stockerinv.utils.PermisosFragment;

import java.util.ArrayList;

//PASO 1 Heredar de la clase permisos fregment
public class CrearElementoFragment extends PermisosFragment {

    FragmentCrearElementoBinding binding;
    String textViewCategoriaElegida, textViewContador, crearNombreArticulo;
    public static final String CATEGORIA_ELEGIDA = "CATEGORIA_ELEGIDA";
    public static final String NOMBRE_ARTICULO = "NOMBRE_ARTICULO";
    public static final String CONTADOR = "CONTADOR";
    public static final String FRAGMENT_ELEMENTO_RESULT_KEY = "FRAGMENT_ELEMENTO_RESULT_KEY";
    Bitmap fotoArticulo;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Llenando la lista  para seleccionar la categoria
        AutoCompleteTextView listaCategorias = ((AutoCompleteTextView) binding.textViewCategoriaElegida.getEditText());
        if (listaCategorias != null) {
            listaCategorias.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.select_dialog_item, getResources().getStringArray(R.array.categorias)));
        }

        // Evento click  para crear los Elementos
        binding.btnCrearElementos.setOnClickListener(v -> {

            // informacion de los View de la Activity
            textViewCategoriaElegida = binding.textViewCategoriaElegidatext.getText().toString();
            textViewContador = binding.textViewContador.getText().toString();
            crearNombreArticulo = binding.crearNombreArticulotext.getText().toString();

            // Validar los  campos de ingreso de datos
            if (TextUtils.isEmpty(textViewCategoriaElegida) || TextUtils.isEmpty(textViewContador) || TextUtils.isEmpty(crearNombreArticulo)) {
                // Creando dialogo de alerta
                Dialogos.mensajePersonalizadoDialogo(requireContext(), getString(R.string.titulo_campo_vacio), getString(R.string.mensaje_campo_vacio));

            } else {

                Bundle i = new Bundle();
                i.putString(CONTADOR, textViewContador);
                i.putString(NOMBRE_ARTICULO, crearNombreArticulo);
                i.putString(CATEGORIA_ELEGIDA, textViewCategoriaElegida);

                getParentFragmentManager().setFragmentResult(FRAGMENT_ELEMENTO_RESULT_KEY, i);
                dismiss();

            }

        });

        // SeekBar Valor Inicial y Valor Final
        binding.seekBar.setProgress(0);
        binding.seekBar.setMax(20);

        // Evento estado de cambio de la barra inferior (seekbar)
        binding.seekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    //hace un llamado a la perilla cuando se arrastra

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        String textoProgressBar = getString(R.string.texto_unidades);
                        binding.textViewContador.setText(progress + " " + textoProgressBar);
                    }

                    //hace un llamado  cuando se toca la perilla
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    //hace un llamado  cuando se detiene la perilla
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                });

        //Evento click para tomar una foto
        binding.imageButtonAccederCamara.setOnClickListener(v -> {
            //  si otorga los permisos de la camara  lanza el metodo chooseImage en el contexto de la activity actual
            chooseImage(requireContext());

        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // selecciona el layout para mostrar en el fragment
        binding = FragmentCrearElementoBinding.inflate(inflater);

        return binding.getRoot();
    }

    // function to let's the user to choose image from camera or gallery
    private void chooseImage(Context context) {
        // crear las opciones de menu en un array
        final CharSequence[] optionsMenu = {"Tomar Una Fotografia Del Articulo", "Elegir desde la galeria", "Regresar"};

        // Creacion del dialogo para mostrar las opciones
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        // Seleccionando los item para mostrar
        builder.setItems(optionsMenu, (dialogInterface, i) -> {

            // PASO 2 permisos acceso _crear el array con los permisos 1 o mas
            ArrayList<String> permisoRequerido = new ArrayList<>();

            if (optionsMenu[i].equals("Tomar Una Fotografia Del Articulo")) {

                // Aqui abre la camara y toma la foto

                // Agregamos el permiso requerido al array de permisos
                permisoRequerido.add(Manifest.permission.CAMERA);

                // PASO 3 lugar donde se quiere pedir el permiso
                verificarPermisos(permisoRequerido, (otorgados, noOtorgados) -> {

                    if (otorgados.contains(Manifest.permission.CAMERA)) {
                        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(takePicture, 0);
                    } else {
                        Dialogos.mensajePersonalizadoDialogo(requireContext(), "Requerimiento",
                                "Para acceder a la camara se requiere la autorizacion");
                    }

                });

            } else if (optionsMenu[i].equals("Elegir desde la galeria")) {
                // Elegeir una foto des de la galeria

                // Agregamos el permiso requerido al array de permisos
                permisoRequerido.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);

                // PASO 3 lugar donde se quiere pedir el permiso
                verificarPermisos(permisoRequerido, (otorgados, noOtorgados) -> {
                    if (otorgados.contains(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(pickPhoto, 1);
                    } else {
                        Dialogos.mensajePersonalizadoDialogo(requireContext(), "Requerimiento",
                                "Para acceder a la galeria de fotos  se requiere la autorizacion");
                    }
                });

            } else if (optionsMenu[i].equals("Regresar")) {
                // Cerramos el dialogo
                dialogInterface.dismiss();
            }
        });
        // muestra el dialogo
        builder.show();
    }

    // Los resultados de la seleccion en el dialogo
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        binding.imageButtonAccederCamara.setImageBitmap(selectedImage);
                    }
                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        if (selectedImage != null) {
                            Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                            if (cursor != null) {
                                cursor.moveToFirst();
                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String picturePath = cursor.getString(columnIndex);
                                binding.imageButtonAccederCamara.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                                cursor.close();
                            }
                        }
                    }
                    break;
            }
        }
    }

}

