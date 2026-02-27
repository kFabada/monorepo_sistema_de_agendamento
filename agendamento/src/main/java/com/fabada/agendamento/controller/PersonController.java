package com.fabada.agendamento.controller;

import com.fabada.agendamento.dto.PersonRegisterDTO;
import com.fabada.agendamento.dto.PersonUpdateDTO;
import com.fabada.agendamento.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
@EnableMethodSecurity
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    @PostMapping
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public ResponseEntity<?> registerPerson(@Valid @RequestBody PersonRegisterDTO personRegisterDTO){
        personService.registerPerson(personRegisterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public ResponseEntity<?> updatePerson(@Valid @RequestBody PersonUpdateDTO personUpdateDTO){
        personService.updatePerson(personUpdateDTO);
        return ResponseEntity.ok().build();
    }
}
