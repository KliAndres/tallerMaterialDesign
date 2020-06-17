package com.example.listarcelulares;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleCelular extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_celular);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView foto;
        TextView marca, modelo, imei, memoria, ram;
        Bundle bundle;
        Intent intent;

         foto =findViewById(R.id.imgFotoDetalle);
         marca = findViewById(R.id.lblMarcaDetalle);
         modelo = findViewById(R.id.lblModeloDetalle);
         imei = findViewById(R.id.lblImeiDetalle);
         memoria = findViewById(R.id.lblMemoriaDetalle);
         ram = findViewById(R.id.lblRamDetalle);

         intent = getIntent();
         bundle = intent.getBundleExtra("datos");

         foto.setImageResource(bundle.getInt("Foto"));
         marca.setText(bundle.getString("Marca"));
         modelo.setText(bundle.getString("Modelo"));
         imei.setText(bundle.getString("Imei"));
         memoria.setText(bundle.getString("Memoria"));
         ram.setText(bundle.getString("RAM"));
    }
    public void onBackPressed(){
        finish();
        Intent intent = new Intent(DetalleCelular.this, MainActivity.class);
        startActivity(intent);
    }
}
