package com.graduation.PublicBicycle.repository;

import com.graduation.PublicBicycle.entity.XeDap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface XeDapRepository extends JpaRepository<XeDap, String> {

    @Query("SELECT x FROM XeDap x WHERE x.matram.matram = :matram")
    List<XeDap> findByTram(@Param("matram") String matram);

    @Query("SELECT x FROM XeDap x WHERE x.imeixe = :imeixe")
    XeDap findByImeixe(@Param("imeixe") String imeixe);
}
