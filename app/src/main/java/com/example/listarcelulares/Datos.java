package com.example.listarcelulares;

import java.util.ArrayList;

public class Datos {
    private static ArrayList<Celular> celulares = new ArrayList();

    public static void guardar(Celular c){
        celulares.add(c);
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
