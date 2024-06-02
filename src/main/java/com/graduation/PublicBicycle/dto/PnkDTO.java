package com.graduation.PublicBicycle.dto;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class PnkDTO {
    private String maPNK;
    private LocalDateTime thoigiangiao;
    private int maNCC;
    private int maUSRER;
    private int soluongnhap;
    private Boolean trangthaithanhtoan;
}
