package com.example.listarcelulares;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Random;

public class AgregarCelular extends AppCompatActivity {
    private ArrayList<Integer> fotos;
    private EditText marca, modelo, imei, memoria, ram;
    private StorageReference storageReference;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_celular);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        marca = findViewById(R.id.txtMarca);
        modelo =findViewById(R.id.txtModelo);
        imei = findViewById(R.id.txtImei);
        memoria = findViewById(R.id.txtMemoria);
        ram = findViewById(R.id.txtRam);

        fotos = new ArrayList<>();
        fotos.add(R.drawable.apple);
        fotos.add(R.drawable.huawei);
        fotos.add(R.drawable.samsung);
        fotos.add(R.drawable.sony);

        storageReference = FirebaseStorage.getInstance().getReference();
    }

    public void guardar(View v) {
        String mar, mod, ime, mem, ra, id;
        int fot;
        Celular celular;
        InputMethodManager imp = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        if (validar()) {
            mar = marca.getText().toString();
            mod = modelo.getText().toString();
            ime = imei.getText().toString();
            mem = memoria.getText().toString();
            ra = ram.getText().toString();
            fot = foto_aleatoria();
            id = Datos.getId();

            celular = new Celular(mar, mod, ime, mem, ra, fot, id);
            celular.guardar();
            subir_foto(id, fot);
            limpiar();
            imp.hideSoftInputFromWindow(imei.getWindowToken(), 0);

            Snackbar.make(v, getString(R.string.mensajeGuardarC), Snackbar.LENGTH_LONG).show();
        }
    }

    public boolean validar(){
        String e_marca, e_modelo, e_imei, e_memoria, e_ram, e_imei_digitos;

        e_marca="Escriba la marca";
        e_modelo="Esciba el modelo";
        e_imei="Escriba los 15 digitos del IMEI";
        e_memoria="Escriba el tamaño de memoria";
        e_ram ="Escriba el tamaño de RAM";
        e_imei_digitos="Deben ser 15 caracteres";

        if (marca.getText().toString().isEmpty()){
            marca.setError(e_marca);
            marca.requestFocus();
            return false;
        }else if (modelo.getText().toString().isEmpty()){
            modelo.setError(e_modelo);
            modelo.requestFocus();
            return false;
        }else if (imei.getText().toString().isEmpty()){
            imei.setError(e_imei);
            imei.requestFocus();
            return false;
        }else if (memoria.getText().toString().isEmpty()){
            memoria.setError(e_memoria);
            memoria.requestFocus();
            return false;
        }else if (ram.getText().toString().isEmpty()){
            ram.setError(e_ram);
            ram.requestFocus();
            return false;
        }else if (imei.length()!=15){
            imei.setError(e_imei_digitos);
            imei.requestFocus();
            return false;
        }
        return true;
    }


    public void subir_foto(String id, int foto){
        StorageReference child = storageReference.child(id);
        Uri uri = Uri.parse("android.resource://"+R.class.getPackage().getName()+"/"+foto);
        UploadTask uploadTask =child.putFile(uri);

    }
    public void limpiar(View v){
        limpiar();
    }

    public  int foto_aleatoria(){
        int foto_select;
        Random r= new Random();
        foto_select = r.nextInt(this.fotos.size());
        return fotos.get(foto_select);
    }

    public  void limpiar(){
        marca.setText("");
        modelo.setText("");
        imei.setText("");
        memoria.setText("");
        ram.setText("");
        marca.requestFocus();
    }
    public void onBackPressed(){
        finish();
        Intent i = new Intent(AgregarCelular.this, MainActivity.class);
        startActivity(i);
    }
}
