package com.develpoment.gobolabali.fundamentalstatistic.Helpers.Online;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TournamentOnline {
    @SerializedName("id")
    @Expose
    private String idTournament;
    @SerializedName("namatournament")
    @Expose
    private String namaTournament;

    public TournamentOnline(String idTournament, String namaTournament) {
        this.idTournament = idTournament;
        this.namaTournament = namaTournament;
    }

    public String getIdTournament() {
        return idTournament;
    }

    public void setIdTournament(String idTournament) {
        this.idTournament = idTournament;
    }

    public String getNamaTournament() {
        return namaTournament;
    }

    public void setNamaTournament(String namaTournament) {
        this.namaTournament = namaTournament;
    }
}
