package com.develpoment.gobolabali.fundamentalstatistic.Model;

public class DataPlayer2 {
    String id,fullname,nickname,posisi, nopunggung,status ,idteam;

    public DataPlayer2() {
    }

    public DataPlayer2(String id, String fullname, String nickname, String posisi, String nopunggung, String status , String idteam) {
        this.id = id;
        this.fullname = fullname;
        this.nickname = nickname;
        this.posisi = posisi;
        this.nopunggung = nopunggung;
        this.status = status;
        this.idteam = idteam;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getNopunggung() {
        return nopunggung;
    }

    public void setNopunggung(String nopunggung) {
        this.nopunggung = nopunggung;
    }

    public String getIdteam() {
        return idteam;
    }

    public void setIdteam(String idteam) {
        this.idteam = idteam;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
