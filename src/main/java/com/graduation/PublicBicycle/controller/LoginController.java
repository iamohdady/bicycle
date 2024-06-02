package com.graduation.PublicBicycle.controller;

import com.graduation.PublicBicycle.request.LoginRequest;
import com.graduation.PublicBicycle.response.TokenResponse;
import com.graduation.PublicBicycle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService service;

    @Autowired
    private com.graduation.PublicBicycle.utlils.JwtHelper jwtHelper;

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody LoginRequest request){

        UsernamePasswordAuthenticationToken authen = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        authenticationManager.authenticate(authen);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<SimpleGrantedAuthority> roles = (List<SimpleGrantedAuthority>) authentication.getAuthorities();

        String role = roles.get(0).getAuthority();

        String token = jwtHelper.generateToken(request.getUsername(), role);
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setResult(token);

        return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
    }

}