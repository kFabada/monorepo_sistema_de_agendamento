package com.fabada.agendamento.service;

import com.fabada.agendamento.execption.UsernameNotFoundException;
import com.fabada.agendamento.model.User;
import com.fabada.agendamento.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserServiceInterface{
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserbyUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()) throw new UsernameNotFoundException("username not found");
        return user.get();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void userUpdatePassword(String username, String password) {

    }

    @Override
    public void userUpdateRole(String username, String role) {

    }
}
