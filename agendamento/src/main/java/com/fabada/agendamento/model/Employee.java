package com.fabada.agendamento.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.LAZY)
    private Person person;
    private BigDecimal salary;
}
