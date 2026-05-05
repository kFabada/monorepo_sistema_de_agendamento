package com.fabada.agendamento.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nome;
    private String cpf;
    private String rg;
    private Date birthday;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "person")
    private Address address;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
    private List<Contact> contacts;
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
    private String imagePath;
}
