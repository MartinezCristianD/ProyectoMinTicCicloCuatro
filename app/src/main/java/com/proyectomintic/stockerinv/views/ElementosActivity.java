package com.proyectomintic.stockerinv.views;



import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.proyectomintic.stockerinv.R;
import com.proyectomintic.stockerinv.databinding.ActivityElementosBinding;

import java.util.ArrayList;
import java.util.List;

public class ElementosActivity extends AppCompatActivity {

    // Variables
    private ActivityElementosBinding binding;
    AutoCompleteTextView listaCategorias;
    String eleccionOrigen, eleccionDestino, textViewCategoriaElegida, textViewContador, crearNombreArticulo;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 101;
    ImageView fotoArticulo;

    // Funcion para verificar los permisos de la app
    public static boolean checkAndRequestPermissions(final Activity context) {

        //permiso de almacenamiento externo para guardar la foto
        int WExtstorePermission = ContextCompat.checkSelfPermission(context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        //permiso para utilizar la camara del celular
        int cameraPermission = ContextCompat.checkSelfPermission(context,
                Manifest.permission.CAMERA);

        // Array donde se guardan los permisos
        List<String> listPermissionsNeeded = new ArrayList<>();

        //Agregando los permisos en el manifest
        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (WExtstorePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded
                    .add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        // validacion de los permisos
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(context, listPermissionsNeeded
                            .toArray(new String[0]),
                    REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityElementosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Recuperando informacion de RutaActivity
        //  eleccionOrigen = getIntent().getExtras().getString(ORIGEN, "NO FUNCIONA");
        // eleccionDestino = getIntent().getExtras().getString(DESTINO, "NO FUNCIONA");

        // Llenando la lista  para seleccionar la categoria
        listaCategorias = ((AutoCompleteTextView) binding.textViewCategoriaElegida.getEditText());
        if (listaCategorias != null) {
            listaCategorias.setAdapter(new ArrayAdapter<>(this, android.R.layout.select_dialog_item, getResources().getStringArray(R.array.categorias)));
        }


        // Evento click  para crear los Elementos
       /* binding.btnCrearElementos.setOnClickListener(v -> {

            // informacion de los View de la Activity
            textViewCategoriaElegida = binding.textViewCategoriaElegidatext.getText().toString();
            textViewContador = binding.textViewContador.getText().toString();
            crearNombreArticulo = binding.crearNombreArticulotext.getText().toString();

            // Validar los  campos de ingreso de datos
            if (TextUtils.isEmpty(textViewCategoriaElegida) || TextUtils.isEmpty(textViewContador) || TextUtils.isEmpty(crearNombreArticulo)) {
                // Creando dialogo de alerta
                Dialogos.mensajePersonalizadoDialogo(this,getString(R.string.titulo_campo_vacio),getString(R.string.mensaje_campo_vacio));


            } else {

                // Pasar al InventarioActivity
                Intent i = new Intent(this, InventarioActivity.class);

                //pasar datos a la activity
                i.putExtra(ORIGEN, eleccionOrigen);
                i.putExtra(DESTINO, eleccionDestino);
                i.putExtra("texto_contador", textViewContador);
                i.putExtra("nombre_articulo", crearNombreArticulo);
                i.putExtra("seleccion_categoria", textViewCategoriaElegida);

                //para provar el envio de informacion

                Toast.makeText(this, textViewContador, Toast.LENGTH_SHORT).show();
                Toast.makeText(this, textViewCategoriaElegida, Toast.LENGTH_SHORT).show();
                Toast.makeText(this, crearNombreArticulo, Toast.LENGTH_SHORT).show();

                //Iniciar  InventariActividad
                startActivity(i);

            }

        });*/

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
        binding.imageButtonAccederCamara.setOnClickListener(v ->

        {
            //  si otorga los permisos de la camara  lanza el metodo chooseImage en el contecto de la activity actual
            if (checkAndRequestPermissions(ElementosActivity.this)) {
                chooseImage(ElementosActivity.this);
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(ElementosActivity.this);
            builder.create();

        });


    }

    // Handled permission Result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_ID_MULTIPLE_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(ElementosActivity.this,
                    Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(),
                        "StokerInv Requiere el acceso a su Camara.", Toast.LENGTH_SHORT)
                        .show();
            } else if (ContextCompat.checkSelfPermission(ElementosActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(),
                        "StokerInv Requiere el acceso a su Almacenamiento.",
                        Toast.LENGTH_SHORT).show();
            } else {
                chooseImage(ElementosActivity.this);
            }
        }
    }

    // function to let's the user to choose image from camera or gallery
    private void chooseImage(Context context) {
        // crear las opciones de menu en un array
        final CharSequence[] optionsMenu = {"Tomar Una Fotografia Del Articulo", "Elegir desde la galeria", "Regresar"};

        // Creacion del dialogo para mostrar las opciones
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        // Seleccionando los item para mostrar
        builder.setItems(optionsMenu, (dialogInterface, i) -> {
            if (optionsMenu[i].equals("Tomar Una Fotografia Del Articulo")) {

                // Aqui abre la camara y toma la foto
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicture, 0);
            } else if (optionsMenu[i].equals("Elegir desde la galeria")) {
                // Elegeir una foto des de la galeria
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, 1);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        binding.imageViewFotoArticulo.setImageBitmap(selectedImage);
                    }
                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        if (selectedImage != null) {
                            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
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