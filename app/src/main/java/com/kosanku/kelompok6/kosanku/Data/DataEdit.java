package com.kosanku.kelompok6.kosanku.Data;

public class DataEdit {

    String namaPenghuni, asal, pekerjaan, umur, noKamar, lamaTinggal, pembayaran, noHp, JumlahBayar, id;

    public DataEdit() {

    }

    public DataEdit(String namaPenghuni, String asal, String pekerjaan, String umur, String noKamar, String lamaTinggal, String pembayaran, String noHp, String JumlahBayar, String id){
        this.namaPenghuni = namaPenghuni;
        this.asal = asal;
        this.pekerjaan = pekerjaan;
        this.umur = umur;
        this.noKamar = noKamar;
        this.lamaTinggal = lamaTinggal;
        this.pembayaran = pembayaran;
        this.noHp = noHp;
        this.JumlahBayar = JumlahBayar;
        this.id=id;
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

    public String getId() {
        return id;
    }
}
