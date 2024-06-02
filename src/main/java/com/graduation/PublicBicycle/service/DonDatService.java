package com.graduation.PublicBicycle.service;

import com.graduation.PublicBicycle.dto.ChitddDTO;
import com.graduation.PublicBicycle.dto.DonDatDTO;
import com.graduation.PublicBicycle.dto.UserDTO;
import com.graduation.PublicBicycle.entity.*;
import com.graduation.PublicBicycle.repository.ChitddRepository;
import com.graduation.PublicBicycle.repository.DonDatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DonDatService {
    @Autowired
    private DonDatRepository donDatRepository;

    @Autowired
    private ChitddRepository chitddRepository;

    public List<DonDatDTO> findDondatByUsername(String username) {
        List<Object[]> resultList = donDatRepository.findDondatByUsername(username);
        List<DonDatDTO> donDatDTOList = new ArrayList<>();

        for (Object[] result : resultList) {
            DonDatDTO donDatDTO = new DonDatDTO();
            donDatDTO.setMadondat((String) result[0]);
            donDatDTO.setSoluongdd((Integer) result[1]);
            donDatDTO.setTrangthaidd((Boolean) result[2]);
            donDatDTO.setSotien((Integer) result[3]);

            // Tạo đối tượng UserDTO
            UserDTO userDTO = new UserDTO();
            userDTO.setId((Integer) result[11]);
            userDTO.setFirstname((String) result[12]);
            userDTO.setLastname((String) result[13]);
            userDTO.setUsername((String) result[14]);
            userDTO.setSdt((String) result[15]);
            userDTO.setPassword((String) result[16]);
            userDTO.setRole((String) result[17]);
            userDTO.setSodu((Integer) result[18]);
            donDatDTO.setMaUser(userDTO);

            List<ChitddDTO> chitdds = new ArrayList<>();
            ChitddDTO chitddDTO = new ChitddDTO();
            chitddDTO.setNgaygiomuon((LocalDateTime) result[4]);
            chitddDTO.setNgaygiotra((LocalDateTime) result[5]);
            chitddDTO.setTrammuon((Tram) result[6]);
            chitddDTO.setTramtra((Tram) result[7]);
            chitddDTO.setImeixe((XeDap) result[8]);
            chitddDTO.setThanhtoan((Double) result[9]);
            chitddDTO.setTrangthai((Boolean) result[10]);

            chitdds.add(chitddDTO);
            donDatDTO.setChitdds(chitdds);

            donDatDTOList.add(donDatDTO);
        }
        return donDatDTOList;
    }

}
