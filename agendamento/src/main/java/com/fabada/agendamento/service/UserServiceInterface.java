package com.fabada.agendamento.service;

import com.fabada.agendamento.dto.UpdatePasswordDTO;
import com.fabada.agendamento.dto.UpdateRoleDTO;
import com.fabada.agendamento.model.User;

public interface UserServiceInterface {
    User getUserbyUsername(String username);
    User save (User user);
    void updatePassword(UpdatePasswordDTO updatePassword);
    void updateRole(UpdateRoleDTO updateRoleDTO);
}
