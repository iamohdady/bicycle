package com.graduation.PublicBicycle.repository;

import com.graduation.PublicBicycle.entity.Tram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TramRepository extends JpaRepository<Tram, Integer> {
    @Query("SELECT t FROM Tram t WHERE t.diadiemtram LIKE %:keyword%")
    List<Tram> findByLocationContainingKeyword(@Param("keyword") String keyword);

    @Query("SELECT t FROM Tram t WHERE t.matram = :matram")
    Tram findByTram(@Param("matram") String matram);

}
