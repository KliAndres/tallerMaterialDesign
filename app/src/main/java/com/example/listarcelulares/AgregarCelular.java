package com.example.listarcelulares;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Random;

public class AgregarCelular extends AppCompatActivity {
    private ArrayList<Integer> fotos;
    private EditText marca, modelo, imei, memoria, ram;

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
    }

    public void guardar(View v){
        String mar, mod, ime,mem,ra, id;
        int fot;
        Celular celular;

        InputMethodManager imp= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        mar = marca.getText().toString();
        mod = modelo.getText().toString();
        ime = imei.getText().toString();
        mem = memoria.getText().toString();
        ra = ram.getText().toString();
        fot = foto_aleatoria();
        id = Datos.getId();

        celular = new Celular(mar, mod, ime, mem, ra, fot, id);
        celular.guardar();
        limpiar();
        imp.hideSoftInputFromWindow(imei.getWindowToken(), 0);

        Snackbar.make(v, getString(R.string.mensajeGuardarC),Snackbar.LENGTH_LONG).show();
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
