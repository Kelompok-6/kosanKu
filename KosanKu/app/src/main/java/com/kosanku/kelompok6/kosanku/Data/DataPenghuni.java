package com.kosanku.kelompok6.kosanku.Data;

public class DataPenghuni {

    String namaPenghuni, asal, pekerjaan, umur, noKamar, lamaTinggal, pembayaran, noHp, JumlahBayar;

    public DataPenghuni(){

    }

    public DataPenghuni(String namaPenghuni, String asal, String pekerjaan, String umur, String noKamar, String lamaTinggal, String pembayaran, String noHp, String JumlahBayar){
        this.namaPenghuni = namaPenghuni;
        this.asal = asal;
        this.pekerjaan = pekerjaan;
        this.umur = umur;
        this.noKamar = noKamar;
        this.lamaTinggal = lamaTinggal;
        this.pembayaran = pembayaran;
        this.noHp = noHp;
        this.JumlahBayar = JumlahBayar;
    }

    public String getNamaPenghuni() {
        return namaPenghuni;
    }

    public String getAsal() {
        return asal;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public String getUmur() {
        return umur;
    }

    public String getNoKamar() {
        return noKamar;
    }

    public String getLamaTinggal() {
        return lamaTinggal;
    }

    public String getPembayaran() {
        return pembayaran;
    }

    public String getNoHp() {
        return noHp;
    }

    public String getJumlahBayar() {
        return JumlahBayar;
    }
}
