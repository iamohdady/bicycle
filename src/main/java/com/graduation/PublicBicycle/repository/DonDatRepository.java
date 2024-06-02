package com.graduation.PublicBicycle.repository;

import com.graduation.PublicBicycle.entity.Chitdd;
import com.graduation.PublicBicycle.entity.DonDat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DonDatRepository extends JpaRepository<DonDat, String> {
    @Query("SELECT d FROM DonDat d WHERE d.soluongdd = :soluongdd")
    List<Chitdd> findByIdUserDD(int soluongdd);
}
