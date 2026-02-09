package com.fabada.agendamento.repository;

import com.fabada.agendamento.enums.UserRole;
import com.fabada.agendamento.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    @Query("select u from users u where u.username = ?1")
    Optional<User> findByUsername(String username);
    @Query("select u from users u where u.email = ?1")
    Optional<User> findByEmail(String email);
    Page<User> findAll(Pageable page);
}
