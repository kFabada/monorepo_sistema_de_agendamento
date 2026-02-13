package com.fabada.agendamento.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String cep;
    private String city;
    private String state;
    private String complement;
    private String address;
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.LAZY)
    private Person person;
}
