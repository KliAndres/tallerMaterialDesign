package com.example.listarcelulares;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.service.autofill.Dataset;
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
        final ArrayList<Celular> celulares;
        LinearLayoutManager llm;
        final Adaptador_Celular adaptador;
        DatabaseReference databaseReference;
        String db = "Celulares";

        lstCelulares = findViewById(R.id.lstCelulares);
        celulares = new ArrayList<>();
        llm = new LinearLayoutManager(this);
        adaptador = new Adaptador_Celular(celulares, this);

        llm.setOrientation(RecyclerView.VERTICAL);
        lstCelulares.setLayoutManager(llm);
        lstCelulares.setAdapter(adaptador);

        fab=findViewById(R.id.btnGuardar);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child(db).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                celulares.clear();
                if (dataSnapshot.exists()){
                    for (DataSnapshot snapshot :dataSnapshot.getChildren()){
                        Celular c= snapshot.getValue(Celular.class);
                        celulares.add(c);
                    }
                }
                adaptador.notifyDataSetChanged();
                Datos.setCelulares(celulares);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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
