package com.graduation.PublicBicycle.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "chitdd")
@Data
public class Chitdd {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "madondat")
    private DonDat madondat;

    private LocalDateTime ngaygiomuon;

    private LocalDateTime ngaygiotra;

    @ManyToOne
    @JoinColumn(name = "trammuon")
    private Tram trammuon;

    @ManyToOne
    @JoinColumn(name = "tramtra")
    private Tram tramtra;

    @ManyToOne
    @JoinColumn(name = "imeixe")
    private XeDap imeixe;

    private double thanhtoan;

    private boolean trangthai;
}