package com.kosanku.kelompok6.kosanku.Data;

public class DataBangunan {

    String namaBangunan, jumlah;

    public DataBangunan(){

    }

    public DataBangunan(String namaBangunan, String jumlah){
        this.namaBangunan = namaBangunan;
        this.jumlah = jumlah;
    }

    public String getNamaBangunan() {
        return namaBangunan;
    }

    public String getJumlah() {
        return jumlah;
    }
}
