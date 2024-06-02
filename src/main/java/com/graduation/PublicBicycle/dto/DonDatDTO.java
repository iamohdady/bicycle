package com.graduation.PublicBicycle.dto;

import com.graduation.PublicBicycle.entity.Users;
import lombok.Data;

import java.util.List;

@Data
public class DonDatDTO {
    private String madondat;
    private UserDTO maUser;
    private int soluongdd;
    private boolean trangthaidd;
    private Integer sotien;
    private List<ChitddDTO> chitdds;


}


