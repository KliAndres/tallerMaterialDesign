package com.example.listarcelulares;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class IndexInicial extends AppCompatActivity {
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_inicial);
    }

    public void Entrar(View v){
        intent = new Intent(IndexInicial.this, MainActivity.class);
        startActivity(intent);
    }
}
