package com.fabada.agendamento.model;

import com.fabada.agendamento.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;

@Entity(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false, length = 255, unique = true)
    private String email;
    @Column(nullable = false, length = 255, unique = true)
    private String username;
    @Column(nullable = false, length = 255)
    private String password;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @Column(nullable = false, updatable = false)
    @CurrentTimestamp
    private LocalDateTime register;
    @Column(nullable = false)
    @CurrentTimestamp
    private LocalDateTime lastUpdate;

    public User(String username, String email, String password, UserRole role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
    }
}
