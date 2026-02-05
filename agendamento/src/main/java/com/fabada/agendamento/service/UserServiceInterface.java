package com.fabada.agendamento.service;

import com.fabada.agendamento.model.User;
import org.springframework.stereotype.Service;

public interface UserServiceInterface {
    User getUserbyUsername(String username);
    User save (User user);
    void userUpdatePassword(String username, String password);
    void userUpdateRole(String username, String role);
}
