package com.fabada.agendamento.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee extends Person{
    private Long id;
    private Person person;
    private BigDecimal salary;
}
