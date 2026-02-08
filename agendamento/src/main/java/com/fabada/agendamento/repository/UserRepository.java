package com.fabada.agendamento.repository;

import com.fabada.agendamento.enums.UserRole;
import com.fabada.agendamento.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from users u where u.username = ?1")
    Optional<User> findByUsername(String username);
    @Query("select u from users u where u.email = ?1")
    Optional<User> findByEmail(String email);
    Page<User> findAll(Pageable page);
    @NativeQuery(
            "SELECT * FROM users WHERE (id IS NULL OR id = ?1) AND (username IS NULL OR username like %?2) AND (email IS NULL OR email like %?3) AND (role IS NULL OR role like %?4) AND (register IS NULL OR register = ?4) AND (last_update IS NULL OR last_update = ?6)")
    Page<User> findFilter
            (Long id, String username, String email, UserRole role, LocalDateTime register, LocalDateTime lastUpdate, Pageable page);
}
