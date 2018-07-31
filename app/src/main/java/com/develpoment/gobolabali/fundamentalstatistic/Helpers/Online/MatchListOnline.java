package com.develpoment.gobolabali.fundamentalstatistic.Helpers.Online;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MatchListOnline {
    @SerializedName("namapertandingan")
    @Expose
    private String namaPertandingan;

    @SerializedName("idtournament")
    @Expose
    private String idTournament;

    @SerializedName("idteam1")
    @Expose
    private String idTeam1;

    @SerializedName("idteam2")
    @Expose
    private String idTeam2;

    @SerializedName("tanggal")
    @Expose
    private String tanggal;

    @SerializedName("mulai")
    @Expose
    private String mulai;

    @SerializedName("akhir")
    @Expose
    private String akhir;

    @SerializedName("status")
    @Expose
    private String status;

    public MatchListOnline(String namaPertandingan, String idTournament, String idTeam1, String idTeam2, String tanggal, String mulai, String akhir, String status) {
        this.namaPertandingan = namaPertandingan;
        this.idTournament = idTournament;
        this.idTeam1 = idTeam1;
        this.idTeam2 = idTeam2;
        this.tanggal = tanggal;
        this.mulai = mulai;
        this.akhir = akhir;
        this.status = status;
    }

    public String getNamaPertandingan() {
        return namaPertandingan;
    }

    public void setNamaPertandingan(String namaPertandingan) {
        this.namaPertandingan = namaPertandingan;
    }

    public String getIdTournament() {
        return idTournament;
    }

    public void setIdTournament(String idTournament) {
        this.idTournament = idTournament;
    }

    public String getIdTeam1() {
        return idTeam1;
    }

    public void setIdTeam1(String idTeam1) {
        this.idTeam1 = idTeam1;
    }

    public String getIdTeam2() {
        return idTeam2;
    }

    public void setIdTeam2(String idTeam2) {
        this.idTeam2 = idTeam2;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
