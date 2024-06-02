package com.graduation.PublicBicycle.controller;

import com.graduation.PublicBicycle.dto.DonDatDTO;
import com.graduation.PublicBicycle.service.DonDatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dondat")
public class DonDatController {

    @Autowired
    private DonDatService donDatService;

    @GetMapping("/list")
    public ResponseEntity<?> getListTDonDat() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName().substring(10);

        List<DonDatDTO> dondat = donDatService.findDondatByUsername(username);
        return new ResponseEntity<>(dondat, HttpStatus.OK);
    }
}
