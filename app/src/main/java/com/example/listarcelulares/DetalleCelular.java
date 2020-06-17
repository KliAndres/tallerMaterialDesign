package com.example.listarcelulares;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class DetalleCelular extends AppCompatActivity {
    private Celular c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_celular);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ImageView foto;
        TextView marca, modelo, imei, memoria, ram;
        Bundle bundle;
        Intent intent;
        String id, mar, mod, ime, mem, ra;
        int fot;
        StorageReference storageReference;

         foto =findViewById(R.id.imgFotoDetalle);
         marca = findViewById(R.id.lblMarcaDetalle);
         modelo = findViewById(R.id.lblModeloDetalle);
         imei = findViewById(R.id.lblImeiDetalle);
         memoria = findViewById(R.id.lblMemoriaDetalle);
         ram = findViewById(R.id.lblRamDetalle);

         intent = getIntent();
         bundle = intent.getBundleExtra("datos");

         id = bundle.getString("id");
         mar=bundle.getString("Marca");
         mod=bundle.getString("Modelo");
         ime=bundle.getString("Imei");
         mem=bundle.getString("Memoria");
         ra=bundle.getString("RAM");

         storageReference = FirebaseStorage.getInstance().getReference();
         storageReference.child(id).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
             @Override
             public void onSuccess(Uri uri) {
                 Picasso.get().load(uri).into(foto);
             }
         });
         //foto.setImageResource(fot);
         marca.setText(mar);
         modelo.setText(mod);
         imei.setText(ime);
         memoria.setText(mem);
         ram.setText(ra);

         c= new Celular(mar, mod, ime, mem, ra, 0, id);
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
