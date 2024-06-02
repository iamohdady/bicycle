package com.graduation.PublicBicycle.service;

import com.graduation.PublicBicycle.dto.ChitddDTO;
import com.graduation.PublicBicycle.dto.TramDTO;
import com.graduation.PublicBicycle.entity.Chitdd;
import com.graduation.PublicBicycle.entity.Tram;
import com.graduation.PublicBicycle.repository.ChitddRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChitddService {

    @Autowired
    private ChitddRepository chitddRepository;
//
//    @Override
//    public List<ChitddDTO> getByUserId( int userId) {
//        return chitddRepository.findByIdUserChitdd(madondat).stream()
//                .map(this::convertToDto)
//                .collect(Collectors.toList());
//    }
//
//    private MuonTraXeDTO convertToDto(MuonTraXeEntity entity) {
//        MuonTraXeDTO dto = new MuonTraXeDTO();
//        dto.setNgayGioMuon(entity.getNgayGioMuon());
//        dto.setNgayGioTra(entity.getNgayGioTra());
//        dto.setSoluongdd(entity.getSoluongdd());
//        dto.setTramMuon(entity.getTramMuon().getTenTram());
//        dto.setTramTra(entity.getTramTra().getTenTram());
//        return dto;
//    }


}
