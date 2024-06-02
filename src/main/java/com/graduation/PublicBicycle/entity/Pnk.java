package com.graduation.PublicBicycle.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
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

