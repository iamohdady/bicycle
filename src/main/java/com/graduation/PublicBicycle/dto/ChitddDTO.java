package com.graduation.PublicBicycle.dto;

import com.graduation.PublicBicycle.entity.DonDat;
import com.graduation.PublicBicycle.entity.Tram;
import com.graduation.PublicBicycle.entity.XeDap;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChitddDTO {

        private DonDat madondat;

        private LocalDateTime ngaygiomuon;

        private LocalDateTime ngaygiotra;

        private Tram trammuon;

        private Tram tramtra;

        private XeDap imeixe;

        private double thanhtoan;

        private boolean trangthai;
}
