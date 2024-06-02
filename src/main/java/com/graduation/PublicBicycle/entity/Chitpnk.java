package com.graduation.PublicBicycle.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "chitpnk")
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
