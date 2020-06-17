package com.example.listarcelulares;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Adaptador_Celular.OnCelularClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab;
        RecyclerView lstCelulares;
        ArrayList<Celular> celulares;
        LinearLayoutManager llm;
        Adaptador_Celular adaptador;

        lstCelulares = findViewById(R.id.lstCelulares);
        celulares = Datos.obtener();
        llm = new LinearLayoutManager(this);
        adaptador = new Adaptador_Celular(celulares, this);

        llm.setOrientation(RecyclerView.VERTICAL);
        lstCelulares.setLayoutManager(llm);
        lstCelulares.setAdapter(adaptador);
        fab=findViewById(R.id.btnGuardar);

    }
    public void agregar(View v){
        Intent intent;
        intent=new Intent(MainActivity.this, AgregarCelular.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void OnCelularClick(Celular c) {
        Intent intent;
        Bundle bundle;

        bundle = new Bundle();
        bundle.putString("Marca", c.getMarca());
        bundle.putString("Modelo", c.getModelo());
        bundle.putString("Imei", c.getImei());
        bundle.putString("Memoria", c.getMemoria());
        bundle.putString("RAM", c.getRam());

        bundle.putInt("Foto", c.getFoto());

        intent =new Intent(MainActivity.this, DetalleCelular.class);
        intent.putExtra("datos", bundle);
        startActivity(intent);
        finish();
    }
}
