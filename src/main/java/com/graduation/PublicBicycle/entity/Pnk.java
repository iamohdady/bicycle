package com.graduation.PublicBicycle.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "pnk")
@Data
public class Pnk {
    @Id
    private String maPNK;
    private LocalDateTime thoigiangiao;

    @ManyToOne
    @JoinColumn(name = "maNCC")
    private NhaCungCap tenNCC;

    @ManyToOne
    @JoinColumn(name = "maUSER")
    private Users maUser;

    private int soluongnhap;
    private Boolean trangthaithanhtoan;

}

