package com.graduation.PublicBicycle.repository;

import com.graduation.PublicBicycle.entity.Chitdd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChitddRepository extends JpaRepository<Chitdd, Integer> {

    // từ maUser tìm ra madondat. từ madondat in ra danh sách
    @Query("SELECT c FROM Chitdd c WHERE c.madondat = :madondat")
    List<Chitdd> findByIdUserChitdd(String madondat);
}
