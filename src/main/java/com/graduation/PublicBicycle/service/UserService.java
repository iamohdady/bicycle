package com.graduation.PublicBicycle.service;

import com.graduation.PublicBicycle.dto.UserDTO;
import com.graduation.PublicBicycle.entity.Users;
import com.graduation.PublicBicycle.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // show all Users
    public List<UserDTO> getAllUsers(){
        List<Users> listUser = userRepository.findAll();
        List<UserDTO> listUserDTO = new ArrayList<>();
        for (Users data : listUser){
            UserDTO userDTO = new UserDTO();
            BeanUtils. copyProperties(data, userDTO);
            listUserDTO.add(userDTO);
        }
        return  listUserDTO;
    }

    // search theo tên
    public List<UserDTO> searchUsersByKeyword(String keyword) {
        List<Users> users = userRepository.findByNameContainingKeyword(keyword);
        List<UserDTO> userDTOS = new ArrayList<>();
        for (Users user : users) {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(user, userDTO);
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }

    public UserDTO findMemberWithCustomerByUsername(String username) {
        System.out.println("username: " + username);
        Users users = userRepository.findMemberWithCustomerByUsername(username);
        if (users != null) {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(users, userDTO);
            return userDTO;
        }
        return null;
    }

}
