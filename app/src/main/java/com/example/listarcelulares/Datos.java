package com.example.listarcelulares;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Datos {
    private static String db ="Celulares";
    private static DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
    private static ArrayList<Celular> celulares = new ArrayList();

    public static String getId(){
        return databaseReference.push().getKey();
    }

    public static void guardar(Celular c){
        databaseReference.child(db).child(c.getId()).setValue(c);
    }

    public static void setCelulares(ArrayList<Celular> celulares){
        celulares = celulares;
    }

    public static ArrayList<Celular> obtener(){ return celulares; }

    public static void eliminar(Celular c){
        for (int i=0; i<celulares.size(); i++){
            if (celulares.get(i).getImei().equals(c.getImei())){
                celulares.remove(i);
                break;
            }
        }
    }
}
