package com.develpoment.gobolabali.fundamentalstatistic.Helpers.Online;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeamOnline {
    @SerializedName("id")
    @Expose
    private String idTeam;
    @SerializedName("nama")
    @Expose
    private String namaTeam;

    public TeamOnline(String idTeam, String namaTeam) {
        this.idTeam = idTeam;
        this.namaTeam = namaTeam;
    }

    public String getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(String idTeam) {
        this.idTeam = idTeam;
    }

    public String getNamaTeam() {
        return namaTeam;
    }

    public void setNamaTeam(String namaTeam) {
        this.namaTeam = namaTeam;
    }
}
