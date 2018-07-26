package com.develpoment.gobolabali.fundamentalstatistic.Model;

/**
 * Created by Rian on 30/06/2018.
 */

public class DataPlayerMatch {

    String idMatch, idTournament, idTeam, idPlayer, posisi, nopunggun, status, nickname;

    public DataPlayerMatch() {
    }

    public DataPlayerMatch(String idMatch, String idTournament, String idTeam, String idPlayer, String posisi, String nopunggun, String status, String nickname) {
        this.idMatch = idMatch;
        this.idTournament = idTournament;
        this.idTeam = idTeam;
        this.idPlayer = idPlayer;
        this.posisi = posisi;
        this.nopunggun = nopunggun;
        this.status = status;
        this.nickname = nickname;
    }

    public String getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(String idMatch) {
        this.idMatch = idMatch;
    }

    public String getIdTournament() {
        return idTournament;
    }

    public void setIdTournament(String idTournament) {
        this.idTournament = idTournament;
    }

    public String getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(String idTeam) {
        this.idTeam = idTeam;
    }

    public String getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(String idPlayer) {
        this.idPlayer = idPlayer;
    }

    public String getPosisi() {
        return posisi;
    }

    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }

    public String getNopunggun() {
        return nopunggun;
    }

    public void setNopunggun(String nopunggun) {
        this.nopunggun = nopunggun;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
