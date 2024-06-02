package com.graduation.PublicBicycle.controller;

import com.graduation.PublicBicycle.dto.TramDTO;
import com.graduation.PublicBicycle.request.TramRequest;
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
    public ResponseEntity<?> getMovie(){
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
}

