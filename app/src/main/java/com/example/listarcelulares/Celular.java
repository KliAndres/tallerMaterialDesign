package com.example.listarcelulares;

public class Celular {
    private String marca;
    private String modelo;
    private String imei;
    private String memoria;
    private String ram;
    private int foto;
    private String id;

    public Celular(String marca, String modelo, String imei, String memoria, String ram, int foto){
        this.marca = marca;
        this.modelo =modelo;
        this.imei = imei;
        this.memoria = memoria;
        this.ram = ram;
        this.foto =foto;
    }
    public Celular(String marca, String modelo, String imei, String memoria, String ram, int foto, String id){
        this.marca = marca;
        this.modelo =modelo;
        this.imei = imei;
        this.memoria = memoria;
        this.ram = ram;
        this.foto =foto;
        this.id = id;
    }
    public Celular(){

    }

    public String getId() {return id; }

    public String getMarca() {return marca;}

    public void setMarca(String marca) {this.marca = marca;}

    public String getModelo() {return modelo;}

    public void setModelo(String modelo) {this.modelo = modelo; }

    public String getImei() {return imei; }

    public void setImei(String imei) {this.imei = imei; }

    public String getMemoria() {return memoria; }

    public void setMemoria(String memoria) {this.memoria = memoria; }

    public String getRam() {return ram; }

    public void setRam(String ram) {this.ram = ram; }

    public int getFoto() {return foto; }

    public void setFoto(int foto) {this.foto = foto; }

    public void guardar(){
        Datos.guardar(this);
    }

    public void eliminar(){Datos.eliminar(this);}
}
