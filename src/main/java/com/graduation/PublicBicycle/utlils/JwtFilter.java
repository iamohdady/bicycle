package com.graduation.PublicBicycle.utlils;

import jakarta.servlet.FilterChain;
import com.graduation.PublicBicycle.utlils.JwtHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private com.graduation.PublicBicycle.utlils.JwtHelper jwtHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Lấy token mà client truền trên header
        String headerValue = request.getHeader("Authorization");

        if (headerValue != null && headerValue.startsWith("Bearer ")) {
            // Cắt chữ để lấy mình token
            String token = headerValue.substring(7);
            String data = jwtHelper.parserToken(token);
            System.out.println("Kiemtra: " + data);

            if (data != null && !data.isEmpty()) {
                // Xử lý thông tin từ token
                String[] tokenData = data.split(",");
                String username = tokenData[0];
                String role = tokenData[1];

                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(role));
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(username, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } else {
            System.out.println("Nội dung header không hợp lệ.");
        }

        filterChain.doFilter(request, response);
    }
}