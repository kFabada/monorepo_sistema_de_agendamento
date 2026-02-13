package com.fabada.agendamento.controller;

import com.fabada.agendamento.dto.TokenDTO;
import com.fabada.agendamento.service.JWTServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class JWTController {
    private final JWTServiceInterface jwtService;

    public JWTController(JWTServiceInterface jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping
    public ResponseEntity<?> createToken(){
        return ResponseEntity.ok().body(jwtService.createToken());
    }
}
