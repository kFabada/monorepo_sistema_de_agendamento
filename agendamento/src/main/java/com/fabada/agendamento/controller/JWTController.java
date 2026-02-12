package com.fabada.agendamento.controller;

import com.fabada.agendamento.service.JWTService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class JWTController {
    private final JWTService jwtService;

    public JWTController(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping
    public ResponseEntity<?> createToken(){
        return ResponseEntity.ok().body(jwtService.createToken());
    }
}
