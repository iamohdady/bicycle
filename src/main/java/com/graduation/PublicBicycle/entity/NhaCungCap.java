package com.graduation.PublicBicycle.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "nhacungcap")
@Data
public class NhaCungCap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;

    private String tenNCC;

}
