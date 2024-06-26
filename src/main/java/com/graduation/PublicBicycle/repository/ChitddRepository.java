package com.graduation.PublicBicycle.repository;

import com.graduation.PublicBicycle.entity.Chitdd;
import com.graduation.PublicBicycle.entity.XeDap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChitddRepository extends JpaRepository<Chitdd, Integer> {

    @Query("SELECT c FROM Chitdd c WHERE c.madondat.madondat = :madondat")
    List<Chitdd> findByIdDonDat(String madondat);

    @Query("SELECT c FROM Chitdd c WHERE c.trammuon.matram= :matram")
    List<XeDap> findByTram(@Param("matram") String matram);

}
