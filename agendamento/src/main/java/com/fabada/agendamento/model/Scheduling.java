package com.fabada.agendamento.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Scheduling {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    private Person client;
    @OneToOne(fetch = FetchType.LAZY)
    private Employee employee;
    @OneToOne(fetch = FetchType.LAZY)
    private Person admManager;
    @Column(nullable = false)
    private LocalDateTime scheduledFor;
    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createIn;
    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateIn;
}
