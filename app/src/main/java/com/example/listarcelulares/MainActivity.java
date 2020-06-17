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

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab;
        RecyclerView lstCelulares;
        ArrayList<Celular> celulares;
        LinearLayoutManager llm;
        Adaptador_Celular adaptador;

        lstCelulares = findViewById(R.id.lstCelulares);
        celulares = Datos.obtener();
        llm = new LinearLayoutManager(this);
        adaptador = new Adaptador_Celular(celulares);

        llm.setOrientation(RecyclerView.VERTICAL);
        lstCelulares.setLayoutManager(llm);
        lstCelulares.setAdapter(adaptador);
        fab=findViewById(R.id.btnGuardar);

    }
    public void agregar(View v){
        Intent i;
        i=new Intent(MainActivity.this, AgregarCelular.class);
        startActivity(i);
        finish();
    }



}
