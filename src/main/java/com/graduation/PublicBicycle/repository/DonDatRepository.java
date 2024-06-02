package com.graduation.PublicBicycle.repository;

import com.graduation.PublicBicycle.entity.Chitdd;
import com.graduation.PublicBicycle.entity.DonDat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonDatRepository extends JpaRepository<DonDat, String> {

    @Query("SELECT d.madondat, d.soluongdd, d.trangthaidd, d.sotien, " +
        "c.ngaygiomuon, c.ngaygiotra, c.trammuon, c.tramtra, c.imeixe, c.thanhtoan, c.trangthai, " +
        "u.id, u.firstname, u.lastname, u.username, u.sdt, u.password, u.role, u.sodu " +
        "FROM DonDat d " +
        "JOIN Users u ON d.maUser.id = u.id "+
        "JOIN Chitdd c ON d.madondat = c.madondat.madondat " +
        "WHERE u.username = :username")
    List<Object[]> findDondatByUsername(@Param("username") String username);

    @Query("SELECT d.madondat, d.soluongdd, d.trangthaidd, d.sotien, " +
        "c.ngaygiomuon, c.ngaygiotra, c.trammuon, c.tramtra, c.imeixe, c.thanhtoan, c.trangthai, " +
        "u.id, u.firstname, u.lastname, u.username, u.sdt, u.password, u.role, u.sodu " +
        "FROM DonDat d " +
        "JOIN Users u ON d.maUser.id  = u.id " +
        "JOIN Chitdd c ON d.madondat = c.madondat.madondat " +
        "WHERE d.trangthaidd = true " +
        "AND u.username = :username")
    List<Object[]> findDonNeedThanhToan(@Param("username") String username);
}
