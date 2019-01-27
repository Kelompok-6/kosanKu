package com.kosanku.kelompok6.kosanku.Data;

public class DataKamar {

    String nomorKamar, statusKamar, id;

    public DataKamar(){

    }

    public DataKamar(String nomorKamar, String statusKamar, String idkamar){
        this.nomorKamar = nomorKamar;
        this.statusKamar = statusKamar;
        this.id = idkamar;
    }

    public String getNomorKamar() {
        return nomorKamar;
    }

    public String getStatusKamar() {
        return statusKamar;
    }

    public String getId() {
        return id;
    }
}
