package com.fabada.agendamento.service;

import com.fabada.agendamento.dto.UpdatePasswordDTO;
import com.fabada.agendamento.dto.UpdateRoleDTO;
import com.fabada.agendamento.model.User;

public interface UserServiceInterface {
    User userbyUsername(String username);
    User userByEmail(String email);
    User save (User user);
    void updatePassword(UpdatePasswordDTO updatePassword);
    void updateRole(UpdateRoleDTO updateRoleDTO);
}
