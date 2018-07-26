package com.develpoment.gobolabali.fundamentalstatistic.Model;

public class DataMatchList {
    String id, namaPertandingan, idtournament, idteam1, idteam2, tanggal, mulai, akhir;

    public DataMatchList() {

    }

    public DataMatchList(String id, String namaPertandingan, String idtournament, String idteam1, String idteam2, String tanggal, String mulai, String akhir) {
        this.id = id;
        this.namaPertandingan = namaPertandingan;
        this.idtournament = idtournament;
        this.idteam1 = idteam1;
        this.idteam2 = idteam2;
        this.tanggal = tanggal;
        this.mulai = mulai;
        this.akhir = akhir;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaPertandingan() {
        return namaPertandingan;
    }

    public void setNamaPertandingan(String namaPertandingan) {
        this.namaPertandingan = namaPertandingan;
    }

    public String getIdtournament() {
        return idtournament;
    }

    public void setIdtournament(String idtournament) {
        this.idtournament = idtournament;
    }

    public String getIdteam1() {
        return idteam1;
    }

    public void setIdteam1(String idteam1) {
        this.idteam1 = idteam1;
    }

    public String getIdteam2() {
        return idteam2;
    }

    public void setIdteam2(String idteam2) {
        this.idteam2 = idteam2;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getMulai() {
        return mulai;
    }

    public void setMulai(String mulai) {
        this.mulai = mulai;
    }

    public String getAkhir() {
        return akhir;
    }

    public void setAkhir(String akhir) {
        this.akhir = akhir;
    }

    @Override
    public String toString() {
        return this.namaPertandingan;
    }
}
