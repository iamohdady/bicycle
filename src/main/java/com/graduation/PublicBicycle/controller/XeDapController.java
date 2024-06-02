package com.graduation.PublicBicycle.controller;

import com.graduation.PublicBicycle.dto.DonDatDTO;
import com.graduation.PublicBicycle.dto.XeDapDTO;
import com.graduation.PublicBicycle.entity.DonDat;
import com.graduation.PublicBicycle.entity.Tram;
import com.graduation.PublicBicycle.repository.TramRepository;
import com.graduation.PublicBicycle.request.BookRequest;
import com.graduation.PublicBicycle.request.XeTramRequest;
import com.graduation.PublicBicycle.service.XeDapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/xe")
public class XeDapController {

    @Autowired
    private XeDapService xeDapService;

    @Autowired
    private TramRepository tramRepository;


    @GetMapping("/all")
    public ResponseEntity<?> getXe(){
        List<XeDapDTO> list = xeDapService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/tram")
    public ResponseEntity<?> getAllByTram(@RequestBody XeTramRequest request) {
        List<XeDapDTO> list = xeDapService.getAllByTram(request.getMatram());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/datXe")
    public ResponseEntity<?> datXe(@RequestBody BookRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName().substring(10);

        DonDat donDat = xeDapService.taoDonDat(request, username);

        Tram tram = tramRepository.findByTram(request.getMatram());
        xeDapService.taoChiTietDatXe(donDat, tram, request.getSoluong());
        return new ResponseEntity<>("Đặt xe thành công", HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addXe(@RequestBody XeDapDTO request) {
        XeDapDTO saved = xeDapService.addXe(request);
        if (saved == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Xe already exists");
        }
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/update/{imeixe}")
    public ResponseEntity<?> updateXe(@PathVariable("imeixe") String imeixe, @RequestBody XeDapDTO request) {
        XeDapDTO updated = xeDapService.updateXe(imeixe, request);
        if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Xe not found");
        }
        return ResponseEntity.ok(updated);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteXe(@RequestParam("imeixe") String imeixe) {
        try {
            xeDapService.deleteXe(imeixe);
            return ResponseEntity.ok("Xóa xe thành công");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/traXe")
    public ResponseEntity<?> traXe(@RequestParam("matram") String matram) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName().substring(10);

        xeDapService.traXe(username, matram);
        return ResponseEntity.ok("Trả xe thành công");

    }

    @GetMapping("/thanhtoan")
    public ResponseEntity<?> getDanhSachHoaDonCanThanhToan() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName().substring(10);

        List<DonDatDTO> danhSachHoaDon = xeDapService.getDanhSachHoaDonCanThanhToan(username);
        return new ResponseEntity<>(danhSachHoaDon, HttpStatus.OK);
    }
}
