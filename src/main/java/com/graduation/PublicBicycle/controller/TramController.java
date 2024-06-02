package com.graduation.PublicBicycle.controller;

import com.graduation.PublicBicycle.dto.TramDTO;
import com.graduation.PublicBicycle.request.TramRequest;
import com.graduation.PublicBicycle.request.XeTramRequest;
import com.graduation.PublicBicycle.service.TramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tram")
public class TramController {

    @Autowired
    private TramService tramService;

    // show all trams
    @GetMapping("/all")
    public ResponseEntity<?> getTram(){
        List<TramDTO> listMovie = tramService.getAllTrams();
        return new ResponseEntity<>(listMovie, HttpStatus.OK);
    }

    // search trams
    @PostMapping("/search/key")
    public ResponseEntity<?> searchTramsByKeyword(@RequestBody TramRequest request) {
        String keyword = request.diadiemtram;
        List<TramDTO> trams = tramService.searchTramsByKeyword(keyword);
        if (trams != null) {
            return new ResponseEntity<>(trams, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Không có Trạm Của NTGo tại địa điêm này", HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/details")
    public ResponseEntity<?> getDetailById(@RequestBody XeTramRequest request) {
        TramDTO tramDTO = tramService.getDetailTram(request.getMatram());
        if (tramDTO != null) {
            return new ResponseEntity<>(tramDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Trạm not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addTram(@RequestBody TramDTO tramDTO) {
        TramDTO saved = tramService.addTram(tramDTO);
        if (saved == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Tram already exists");
        }
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/update/{matram}")
    public ResponseEntity<?> updateTram(@PathVariable("matram") String matram, @RequestBody TramDTO tramDTO) {
        TramDTO updated = tramService.updateTram(matram, tramDTO);
        if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tram not found");
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{matram}")
    public ResponseEntity<?> deleteTram(@PathVariable("matram") String matram) {
        try {
            tramService.deleteTram(matram);
            return ResponseEntity.ok("Xóa trạm thành công");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}

