package com.fabada.agendamento.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.LAZY)
    private Person person;
    private int xp;
}
