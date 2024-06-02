package com.graduation.PublicBicycle.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tram")
@Data
public class Tram {
    @Id
    private String matram;
    private String tentram;
    private String diadiemtram;
    private double latitude;
    private double longitude;
    private int soluong;
}
