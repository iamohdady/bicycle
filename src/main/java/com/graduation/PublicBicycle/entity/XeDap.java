package com.graduation.PublicBicycle.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "xedap")
@Data
public class XeDap {

    @Id
    private String imeixe;

    private String bienso;

    @ManyToOne
    @JoinColumn(name = "matram")
    private Tram matram;

    private boolean trangthaixe;

    private String mota;
}
