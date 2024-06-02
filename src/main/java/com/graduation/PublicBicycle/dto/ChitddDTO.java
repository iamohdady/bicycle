package com.graduation.PublicBicycle.dto;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Table(name = "chitdd")
@Data
public class ChitddDTO {

        @Column(name = "madondat")
        private String madondat;

        private LocalDateTime ngaygiomuon;

        private LocalDateTime ngaygiotra;

        @Column(name = "diadiemtram")
        private String trammuon;

        @Column(name = "diadiemtram")
        private String tramtra;

        @Column(name = "imeixe")
        private String imeixe;

}
