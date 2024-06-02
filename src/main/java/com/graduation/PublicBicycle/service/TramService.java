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

    public TramDTO getDetailTram(String matram) {
        Tram tram = tramRepository.findByTram(matram);
        if (tram != null) {
            TramDTO tramDTO = new TramDTO();
            BeanUtils.copyProperties(tram, tramDTO);
            return tramDTO;
        }
        return null;
    }

    public TramDTO addTram(TramDTO tramDTO) {
        Tram exist = tramRepository.findByTram(tramDTO.getMatram());
        if (exist != null) {
            return null;
        }
        Tram tram = new Tram();
        BeanUtils.copyProperties(tramDTO, tram);
        Tram saved = tramRepository.save(tram);
        TramDTO savedDTO = new TramDTO();
        BeanUtils.copyProperties(saved, savedDTO);
        return savedDTO;
    }

    public TramDTO updateTram(String matram, TramDTO tramDTO) {
        Tram tram = tramRepository.findByTram(matram);
        if (tram == null) {
            return null;
        }
        BeanUtils.copyProperties(tramDTO, tram);
        Tram updated = tramRepository.save(tram);
        TramDTO updatedDTO = new TramDTO();
        BeanUtils.copyProperties(updated, updatedDTO);
        return updatedDTO;
    }

    public void deleteTram(String matram) {
        Tram tram = tramRepository.findByTram(matram);
        if (tram != null) {
            tramRepository.delete(tram);
        } else {
            throw new RuntimeException("Tram with ID " + matram + " does not exist.");
        }
    }


}
