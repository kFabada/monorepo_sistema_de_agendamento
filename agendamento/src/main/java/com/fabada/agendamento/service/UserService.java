package com.fabada.agendamento.service;

import com.fabada.agendamento.dto.UpdatePasswordDTO;
import com.fabada.agendamento.dto.UpdateRoleDTO;
import com.fabada.agendamento.dto.UserResponsePageDTO;
import com.fabada.agendamento.enums.UserRole;
import com.fabada.agendamento.execption.EmailNotFoundException;
import com.fabada.agendamento.execption.UsernameNotFoundException;
import com.fabada.agendamento.model.CodeManager;
import com.fabada.agendamento.model.User;
import com.fabada.agendamento.repository.CodeRepository;
import com.fabada.agendamento.repository.UserRepository;
import com.fabada.agendamento.repository.spec.UserSpec;
import com.fabada.agendamento.utils.PasswordEncoderInterface;
import com.fabada.agendamento.validated.UserRoleValidatedInferface;
import com.fabada.agendamento.validated.UserUpdatePasswordValidatedInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface{
    private final UserRepository userRepository;
    private final CodeRepository codeRepository;
    private final PasswordEncoderInterface passwordEncoder;
    private final UserRoleValidatedInferface userRoleValidated;
    private final UserUpdatePasswordValidatedInterface userUpdatePasswordValidated;

    public UserService(UserRepository userRepository, CodeRepository codeRepository, PasswordEncoderInterface passwordEncoder, UserRoleValidatedInferface userRoleValidated, UserUpdatePasswordValidatedInterface userUpdatePasswordValidated) {
        this.userRepository = userRepository;
        this.codeRepository = codeRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleValidated = userRoleValidated;
        this.userUpdatePasswordValidated = userUpdatePasswordValidated;
    }

    @Override
    public User findByUsername(String username) {
       return userRepository.findByUsername(username)
               .orElseThrow(() -> new UsernameNotFoundException("username not found"));
    }

    @Override
    public Optional<User> findByOptionalUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByOptionalEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByEmail(String email) {
       return userRepository.findByEmail(email)
               .orElseThrow(() ->  new EmailNotFoundException("email not found"));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void updatePassword(UpdatePasswordDTO passwordDTO) {
         Optional<CodeManager> codeManager = codeRepository.findByCode(Integer.parseInt(passwordDTO.code()));
         User user = userUpdatePasswordValidated.verify(codeManager,passwordDTO);
         user.setPassword(passwordEncoder.encoder(passwordDTO.password()));
         userRepository.save(user);
    }

    @Override
    public void updateRole(UpdateRoleDTO updateRoleDTO) {
        userRoleValidated.verify(updateRoleDTO.role());
        User user = findByUsername(updateRoleDTO.username());
        user.setRole(UserRole.valueOf(updateRoleDTO.role()));
        userRepository.save(user);
    }

    @Override
    public Page<UserResponsePageDTO> getAllPage(Pageable page) {
        return userRepository.findAll(page).map((u) -> new UserResponsePageDTO(
                        u.getId(),
                        u.getUsername(),
                        u.getEmail(),
                        u.getRole(),
                        u.getRegister(),
                        u.getLastUpdate()
                )
        );
    }

    @Override
    public Page<UserResponsePageDTO> getFilterUser(
            Long id, String username, String email, UserRole role, LocalDateTime register, LocalDateTime lastUpdate, Pageable page) {

        Specification<User> s = Specification.where((from, criteriaBuilder) -> criteriaBuilder.conjunction());

        if(id != null) s = s.and(UserSpec.hasId(id));
        if(username != null)  s = s.and(UserSpec.hasUsername(username));
        if(email != null) s = s.and(UserSpec.hasEmail(email));
        if(role != null) s = s.and(UserSpec.hasRole(role));
        if(register != null) s = s.and(UserSpec.hasRegister(register));
        if(register != null) s = s.and(UserSpec.hasLastUpdate(lastUpdate));

        return userRepository.findAll(s, page).map((u) -> new UserResponsePageDTO(
                u.getId(),
                u.getUsername(),
                u.getEmail(),
                u.getRole(),
                u.getRegister(),
                u.getLastUpdate()
        ));
    }
}
