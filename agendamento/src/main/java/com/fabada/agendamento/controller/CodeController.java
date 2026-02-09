package com.fabada.agendamento.controller;

import com.fabada.agendamento.dto.CreateCodeDTO;
import com.fabada.agendamento.service.CodeServiceInterface;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/code")
public class CodeController {
    private final CodeServiceInterface codeService;

    public CodeController(CodeServiceInterface codeService) {
        this.codeService = codeService;
    }

    @PostMapping("/generation")
    public ResponseEntity<?> createCode(@Valid @RequestBody CreateCodeDTO createCodeDTO){
        codeService.gererateCode(createCodeDTO.username());
        return ResponseEntity.ok().build();
    }
}
