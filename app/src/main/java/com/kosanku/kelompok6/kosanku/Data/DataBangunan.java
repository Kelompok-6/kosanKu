package com.kosanku.kelompok6.kosanku.Data;

public class DataBangunan {

    String namaBangunan, jumlahKamar, id;

    public DataBangunan(){

    }

    public DataBangunan(String namaBangunan, String jumlahKamar, String idbangunan){
        this.namaBangunan = namaBangunan;
        this.jumlahKamar = jumlahKamar;
        this.id = idbangunan;
    }

    public String getNamaBangunan() {
        return namaBangunan;
    }

    public String getJumlah() {
        return jumlahKamar;
    }

    public String getId() {
        return id;
    }


}
