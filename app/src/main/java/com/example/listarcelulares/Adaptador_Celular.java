package com.example.listarcelulares;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adaptador_Celular extends RecyclerView.Adapter<Adaptador_Celular.CelularViewHolder>{
    private ArrayList<Celular> celulares;
    private OnCelularClickListener clickListener;


    public Adaptador_Celular(ArrayList<Celular> celulares, OnCelularClickListener clickListener){
        this.celulares = celulares;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public CelularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_celular, parent,false);
        return new CelularViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final CelularViewHolder holder, int position) {
        final Celular c = celulares.get(position);

        StorageReference storageReference;
        storageReference = FirebaseStorage.getInstance().getReference();

        storageReference.child(c.getId()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(holder.foto);
            }
        });

        holder.marca.setText(c.getMarca());
        holder.modelo.setText(c.getModelo());
        holder.imei.setText(c.getImei());

        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.OnCelularClick(c);
            }
        });
    }

    @Override
    public int getItemCount() {
        return celulares.size();
    }

    public static class CelularViewHolder extends RecyclerView.ViewHolder{
        private ImageView foto;
        private TextView marca;
        private TextView modelo;
        private TextView imei;
        private View v;

        public CelularViewHolder(View itemView){
            super (itemView);
            v=itemView;
            foto = v.findViewById(R.id.imgFoto);
            marca = v.findViewById(R.id.lblMarca);
            modelo = v.findViewById(R.id.lblModelo);
            imei = v.findViewById(R.id.lblImei);

        }
    }
    public interface OnCelularClickListener{
        void OnCelularClick(Celular c);
    }
}
