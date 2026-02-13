package com.fabada.agendamento.service;

import com.fabada.agendamento.dto.UpdatePasswordDTO;
import com.fabada.agendamento.dto.UpdateRoleDTO;
import com.fabada.agendamento.dto.UserResponsePageDTO;
import com.fabada.agendamento.enums.UserRole;
import com.fabada.agendamento.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UserService {
    User findByUsername(String username);
    Optional<User> findByOptionalUsername(String username);
    Optional<User> findByOptionalEmail(String email);
    User findByEmail(String email);
    User save (User user);
    void updatePassword(UpdatePasswordDTO updatePassword);
    void updateRole(UpdateRoleDTO updateRoleDTO);
    Page<UserResponsePageDTO> getAllPage(Pageable page);
    Page<UserResponsePageDTO> getFilterUser(Long id, String username, String email, UserRole role, LocalDateTime register, LocalDateTime lastUpdate, Pageable page);


}
