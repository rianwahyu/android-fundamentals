package com.develpoment.gobolabali.fundamentalstatistic.Helpers.Online;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayerOnline {
    @SerializedName("nik")
    @Expose
    private String nik;

    @SerializedName("fullname")
    @Expose
    private String fullname;

    @SerializedName("nickname")
    @Expose
    private String nickname;

    @SerializedName("posisi")
    @Expose
    private String posisi;

    @SerializedName("posnomor")
    @Expose
    private String posnomor;

    @SerializedName("nopunggung")
    @Expose
    private String nopunggung;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("idteam")
    @Expose
    private String idTeam;

    public PlayerOnline(String nik, String fullname, String nickname, String posisi, String posnomor, String nopunggung, String status, String idTeam) {
        this.nik = nik;
        this.fullname = fullname;
        this.nickname = nickname;
        this.posisi = posisi;
        this.posnomor = posnomor;
        this.nopunggung = nopunggung;
        this.status = status;
        this.idTeam = idTeam;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPosisi() {
        return posisi;
    }

    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }

    public String getPosnomor() {
        return posnomor;
    }

    public void setPosnomor(String posnomor) {
        this.posnomor = posnomor;
    }

    public String getNopunggung() {
        return nopunggung;
    }

    public void setNopunggung(String nopunggung) {
        this.nopunggung = nopunggung;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(String idTeam) {
        this.idTeam = idTeam;
    }
}
