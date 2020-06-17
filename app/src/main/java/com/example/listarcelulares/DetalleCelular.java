package com.example.listarcelulares;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleCelular extends AppCompatActivity {
    private Celular c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_celular);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView foto;
        TextView marca, modelo, imei, memoria, ram;
        Bundle bundle;
        Intent intent;
        String mar, mod, ime, mem, ra;
        int fot;

         foto =findViewById(R.id.imgFotoDetalle);
         marca = findViewById(R.id.lblMarcaDetalle);
         modelo = findViewById(R.id.lblModeloDetalle);
         imei = findViewById(R.id.lblImeiDetalle);
         memoria = findViewById(R.id.lblMemoriaDetalle);
         ram = findViewById(R.id.lblRamDetalle);

         intent = getIntent();
         bundle = intent.getBundleExtra("datos");

         fot = bundle.getInt("Foto");
         mar=bundle.getString("Marca");
         mod=bundle.getString("Modelo");
         ime=bundle.getString("Imei");
         mem=bundle.getString("Memoria");
         ra=bundle.getString("RAM");

         foto.setImageResource(fot);
         marca.setText(mar);
         modelo.setText(mod);
         imei.setText(ime);
         memoria.setText(mem);
         ram.setText(ra);

         c= new Celular(mar, mod, ime, mem, ra, fot);
    }
    public void onBackPressed(){
        finish();
        Intent intent = new Intent(DetalleCelular.this, MainActivity.class);
        startActivity(intent);
    }
    public void eliminar(View v){
        String Pos, Neg;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.eliminar_equipo);
        builder.setMessage(R.string.seguro_eliminar);
        Pos = getString(R.string.si);
        Neg =getString(R.string.no);

        builder.setPositiveButton(Pos, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                c.eliminar();
                onBackPressed();
            }
        });
        builder.setNegativeButton(Neg, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
