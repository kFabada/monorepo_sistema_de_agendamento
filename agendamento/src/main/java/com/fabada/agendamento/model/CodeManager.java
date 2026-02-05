package com.fabada.agendamento.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;

@Entity(name = "code_manager")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CodeManager {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(length = 6)
    private int code;
    @OneToOne(fetch = FetchType.LAZY)
    @Column(name = "user_id", nullable = false, unique = true)
    private User userId;
    private LocalDateTime register;
    @Column(name = "time_valid")
    private LocalDateTime timeValid;
}
