package com.develpoment.gobolabali.fundamentalstatistic.Model;

/**
 * Created by Rian on 01/06/2018.
 */

public class DataTournament {
    String id, namaTournament ;

    public DataTournament() {
    }

    public DataTournament(String id, String namaTournament) {
        this.id = id;
        this.namaTournament = namaTournament;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaTournament() {
        return namaTournament;
    }

    public void setNamaTournament(String namaTournament) {
        this.namaTournament = namaTournament;
    }

    @Override
    public String toString() {
        return this.namaTournament;
    }
}
