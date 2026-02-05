package com.fabada.agendamento.repository;


import com.fabada.agendamento.model.CodeManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CodeRepository extends JpaRepository<CodeManager, Long> {
    Optional<CodeManager> findByUserId(Long userId);
    Optional<CodeManager> findByCode(int code);
}
