package com.graduation.PublicBicycle.entity;

import com.graduation.PublicBicycle.entity.Users;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "dondat")
@Data
public class DonDat {

    @Id
    private String madondat;

    @ManyToOne
    @JoinColumn(name = "maUSER")
    private Users maUser;

    private int soluongdd;
    private boolean trangthaidd;
    private Integer sotien;


}

