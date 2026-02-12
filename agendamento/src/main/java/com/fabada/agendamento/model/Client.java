package com.fabada.agendamento.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Client extends Person{
    private Long id;
    private Person person;
    private int xp;
}
