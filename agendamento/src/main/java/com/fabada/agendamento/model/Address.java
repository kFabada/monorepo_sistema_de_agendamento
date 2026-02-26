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
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Person person;
}
