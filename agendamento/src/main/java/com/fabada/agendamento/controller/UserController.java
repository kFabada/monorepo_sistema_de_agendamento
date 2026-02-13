package com.fabada.agendamento.controller;

import com.fabada.agendamento.dto.*;
import com.fabada.agendamento.enums.UserRole;
import com.fabada.agendamento.service.UserService;
import com.fabada.agendamento.utils.PasswordEncoder;
import com.fabada.agendamento.validated.UserValidatedRegister;
import jakarta.validation.Valid;
import com.fabada.agendamento.model.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@EnableMethodSecurity
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserValidatedRegister userValidatedRegister;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, UserValidatedRegister userValidatedRegister, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userValidatedRegister = userValidatedRegister;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegisterDTO userDTOValidated){
       User userMap = userDTOValidated.mapToUser();

       userValidatedRegister.verify(userMap);
       userMap.setPassword(passwordEncoder.encoder(userMap.getPassword()));

       User user = userService.save(userMap);
       return ResponseEntity
               .status(HttpStatus.CREATED)
               .body(new UserResponseDTO(
                   user.getId(),
                   user.getUsername(),
                   user.getEmail(),
                   user.getRole()));
    }

    @PutMapping("/password_update")
    public ResponseEntity<?> updatePassword(@Valid @RequestBody UpdatePasswordDTO updatePasswordDTO){
        userService.updatePassword(updatePasswordDTO);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_ADM')")
    @PutMapping("/role_update")
    public ResponseEntity<?> updateRole(@Valid @RequestBody UpdateRoleDTO updateRoleDTO){
        userService.updateRole(updateRoleDTO);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_ADM')")
    @GetMapping("/all_page{page}{size}{sort}")
    public ResponseEntity<?> getAllPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean asc){
        Sort sort = asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        return ResponseEntity.ok(userService.getAllPage(PageRequest.of(page, size, sort)));
    }

    @PreAuthorize("hasRole('ROLE_ADM')")
    @GetMapping("/search_filter{id}{username}{email}{role}{register}{lastUpdate}{page}{size}{sortBy}")
    public ResponseEntity<?> getFilterUser(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) UserRole role,
            @RequestParam(required = false) LocalDateTime register,
            @RequestParam(required = false) LocalDateTime lastUpdate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "true") boolean asc){

        PageRequest p = PageRequest.of(page, size);
        return ResponseEntity.ok(userService.getFilterUser(id, username, email, role, register, lastUpdate, p));
    }
}
