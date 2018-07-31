package com.develpoment.gobolabali.fundamentalstatistic.Helpers.Online;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Base {

    @SerializedName("player")
    @Expose
    private List<PlayerOnline> player = null;

    @SerializedName("team")
    @Expose
    private List<TeamOnline> team = null;

    @SerializedName("tournament")
    @Expose
    private List<TournamentOnline> tournament;

    @SerializedName("matchlist")
    @Expose
    private List<MatchListOnline> matchlist = null;

    public List<PlayerOnline> getPlayer() {
        return player;
    }

    public void setPlayer(List<PlayerOnline> player) {
        this.player = player;
    }

    public List<TeamOnline> getTeam() {
        return team;
    }

    public void setTeam(List<TeamOnline> team) {
        this.team = team;
    }

    public List<TournamentOnline> getTournament() {
        return tournament;
    }

    public void setTournament(List<TournamentOnline> tournament) {
        this.tournament = tournament;
    }

    public List<MatchListOnline> getMatchlist() {
        return matchlist;
    }

    public void setMatchlist(List<MatchListOnline> matchlist) {
        this.matchlist = matchlist;
    }
}
