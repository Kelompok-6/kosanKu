package com.kosanku.kelompok6.kosanku.Data;

public class DataKamar {

    String noKamar, statusKamar;

    public DataKamar(){

    }

    public DataKamar(String noKamar, String statusKamar){
        this.noKamar = noKamar;
        this.statusKamar = statusKamar;
    }

    public String getNoKamar() {
        return noKamar;
    }

    public String getStatusKamar() {
        return statusKamar;
    }
}
