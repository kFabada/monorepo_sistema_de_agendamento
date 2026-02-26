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
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true, length = 14)
    private String cpf;
    @Column(nullable = false, unique = true, length = 11)
    private String rg;
    @Column(nullable = false)
    private Date birthday;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "person")
    private Address address;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
    private List<Contact> contacts;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    public Person(String name, String cpf, String rg, Date birthday, User user) {
        this.name = name;
        this.cpf = cpf;
        this.rg = rg;
        this.birthday = birthday;
        this.user = user;
    }

    public Person(Long id, String name, String cpf, String rg, Date birthday) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.rg = rg;
        this.birthday = birthday;
    }
}
