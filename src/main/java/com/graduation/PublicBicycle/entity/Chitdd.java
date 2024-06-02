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

    @Column(name = "madondat")
    private String madondat;

    private LocalDateTime ngaygiomuon;

    private LocalDateTime ngaygiotra;

    @Column(name = "trammuon")
    private String trammuon;

    @Column(name = "tramtra")
    private String tramtra;

    @Column(name = "imeixe")
    private String imeixe;

}