package com.graduation.PublicBicycle.controller;
import com.graduation.PublicBicycle.dto.UserDTO;
import com.graduation.PublicBicycle.request.UserRequest;
import com.graduation.PublicBicycle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // show all user
    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers(){
        List<UserDTO> listUser = userService.getAllUsers();
        return new ResponseEntity<>(listUser, HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchUsersByKeyword(@RequestBody UserRequest request) {
        String keyword = request.username;
        List<UserDTO> user = userService.searchUsersByKeyword(keyword);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Không có users", HttpStatus.NOT_FOUND);
        }
    }
}
