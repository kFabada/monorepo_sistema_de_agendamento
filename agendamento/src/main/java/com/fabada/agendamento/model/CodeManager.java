package com.fabada.agendamento.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;

@Entity(name = "code_manager")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CodeManager {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private int code;
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
    private LocalDateTime register;
    @Column(name = "time_valid")
    private LocalDateTime timeValid;
    @ColumnDefault("false")
    private boolean isUsed;


}
