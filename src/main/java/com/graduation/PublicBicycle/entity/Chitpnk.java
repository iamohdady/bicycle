package com.graduation.PublicBicycle.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Chitpnk {

    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "maPNK")
    private Pnk maPNK;

    private String imeixe;

    private int dongia;

    private String ghichu;

}
