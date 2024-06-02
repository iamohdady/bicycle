package com.graduation.PublicBicycle.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Tram {
    @Id
    private String matram;
    private String tentram;
    private String diadiemtram;
    private int soluong;

    public String getMatram() {
        return matram;
    }

    public void setMatram(String matram) {
        this.matram = matram;
    }

    public String getTentram() {
        return tentram;
    }

    public void setTentram(String tentram) {
        this.tentram = tentram;
    }

    public String getDiadiemtram() {
        return diadiemtram;
    }

    public void setDiadiemtram(String diadiemtram) {
        this.diadiemtram = diadiemtram;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
