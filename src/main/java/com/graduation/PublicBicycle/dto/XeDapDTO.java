package com.graduation.PublicBicycle.dto;

import com.graduation.PublicBicycle.entity.Tram;
import lombok.Data;

@Data
public class XeDapDTO {
    private String imeixe;

    private String bienso;

    private Tram matram;

    private boolean trangthaixe;

    private String mota;
}
