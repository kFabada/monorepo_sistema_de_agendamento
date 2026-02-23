package com.fabada.agendamento.controller;

import com.fabada.agendamento.dto.CreateCodeDTO;
import com.fabada.agendamento.service.CodeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/code")
public class CodeController {
    private final CodeService codeService;

    public CodeController(CodeService codeService) {
        this.codeService = codeService;
    }

    @PostMapping("/generation")
    public ResponseEntity<?> createCode(@Valid @RequestBody CreateCodeDTO createCodeDTO){
        codeService.generateCode(createCodeDTO.email());
        return ResponseEntity.ok().build();
    }
}
