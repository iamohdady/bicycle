package com.graduation.PublicBicycle.service;

import com.graduation.PublicBicycle.dto.TramDTO;
import com.graduation.PublicBicycle.entity.Tram;
import com.graduation.PublicBicycle.repository.TramRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TramService {
    @Autowired
    private TramRepository tramRepository;

    //show all Trams
    public List<TramDTO> getAllTrams(){
        List<Tram> listTram = tramRepository.findAll();
        List<TramDTO> listTramDTO = new ArrayList<>();
        for (Tram data : listTram){
            TramDTO tramDTO = new TramDTO();
            BeanUtils.copyProperties(data, tramDTO);
            listTramDTO.add(tramDTO);
        }
        return listTramDTO;
    }
    // search Tram
    public List<TramDTO> searchTramsByKeyword(String keyword) {
        List<Tram> trams = tramRepository.findByLocationContainingKeyword(keyword);
        List<TramDTO> tramDTOs = new ArrayList<>();
        for (Tram tram : trams) {
            TramDTO tramDTO = new TramDTO();
            BeanUtils.copyProperties(tram, tramDTO);
            tramDTOs.add(tramDTO);
        }

        return tramDTOs;
    }

}
