package com.graduation.PublicBicycle.dto;

import com.graduation.PublicBicycle.entity.Users;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class DonDatDTO {
    private String madondat;
    private Users maUser;
    private int soluongdd;
    private String trangthaidd;
    private Integer sotien;


}


