package com.example.listarcelulares;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adaptador_Celular extends RecyclerView.Adapter<Adaptador_Celular.CelularViewHolder>{
    private ArrayList<Celular> celulares;

    public Adaptador_Celular(ArrayList<Celular> celulares){
        this.celulares = celulares;
    }

    @NonNull
    @Override
    public CelularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_celular, parent,false);
        return new CelularViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CelularViewHolder holder, int position) {
        Celular c = celulares.get(position);
        holder.foto.setImageResource(c.getFoto());
        holder.marca.setText(c.getMarca());
        holder.modelo.setText(c.getModelo());
        holder.imei.setText(c.getImei());
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
}
