package com.develpoment.gobolabali.fundamentalstatistic.Model;

public class DataTeam {
    String id, nama ;

    public DataTeam() {
    }

    public DataTeam(String id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    @Override
    public String toString() {
        return this.nama;
    }
}
