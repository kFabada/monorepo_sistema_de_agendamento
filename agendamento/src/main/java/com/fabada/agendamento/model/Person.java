package com.fabada.agendamento.model;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class Person {
    private Long id;
    private String nome;
    private String cpf;
    private String rg;
    private Date birthday;
    private Address address;
    private List<Contact> contacts;
    private User user;
}
