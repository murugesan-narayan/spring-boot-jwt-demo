package com.muru.dcb.jwt.controller;

import com.muru.dcb.jwt.model.JwtRequest;
import com.muru.dcb.jwt.model.JwtResponse;
import com.muru.dcb.jwt.service.JwtService;
import com.muru.dcb.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/")
    public String home() {
        return "Welcome to Daily Code Buffer";
    }

    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        jwtRequest.getUserName(),
                        jwtRequest.getPassword()));
        UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUserName());
        String token = jwtService.generateToken(userDetails);
        return new JwtResponse(token);
    }
}
