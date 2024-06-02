package com.graduation.PublicBicycle.repository;


import com.graduation.PublicBicycle.entity.DonDat;
import com.graduation.PublicBicycle.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    @Query("SELECT u FROM Users u WHERE u.username LIKE %:keyword%")
    List<Users> findByNameContainingKeyword(String keyword);

    @Query("SELECT u " +
        "FROM Users u " +
        "WHERE u.username = :username")
    Users findMemberWithCustomerByUsername(@Param("username") String username);

    Users findByUsername (String username);
}
