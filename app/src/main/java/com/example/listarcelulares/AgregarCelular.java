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
    //private Object[] imeia =new Object[15];

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

    public void guardar(final View v) {
        String mar, mod, ime, mem, ra, id, pos, neg;
        int fot;
        final Celular celular;
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
            //celular.guardar();
            subir_foto(id, fot);

            AlertDialog.Builder builder =new AlertDialog.Builder(this);
            builder.setTitle(R.string.informacion_titutlo);
            builder.setMessage(R.string.informacion_correcta);
            pos=getString(R.string.si);
            neg=getString(R.string.no);

            builder.setPositiveButton(pos, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    celular.guardar();
                    Snackbar.make(v, getString(R.string.mensajeGuardarC), Snackbar.LENGTH_LONG).show();
                    limpiar(v);

                }
            });

            builder.setNegativeButton(neg, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    limpiar(v);
                }
            });
            imp.hideSoftInputFromWindow(imei.getWindowToken(), 0);
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    public boolean validar(){
        String e_marca, e_modelo, e_imei, e_memoria, e_ram, e_imei_digitos;
        e_marca=getString(R.string.error_marca);
        e_modelo=getString(R.string.error_modelo);
        e_imei=getString(R.string.error_imei);
        e_memoria=getString(R.string.error_memoria);
        e_ram =getString(R.string.error_ram);
        e_imei_digitos=getString(R.string.error_digitos_imei);

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
        //int im=Integer.parseInt(imei.getText().toString());
        //for (int i = 0; i < imeia.length; i++){
            //if (imei.get(i).getImei.equals(c.getImei())){
          //  }
        //}
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
