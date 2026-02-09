package com.fabada.agendamento.repository.spec;

import com.fabada.agendamento.enums.UserRole;
import com.fabada.agendamento.model.User;
import org.springframework.data.jpa.domain.PredicateSpecification;

import java.time.LocalDateTime;

public class UserSpec {

    public static PredicateSpecification<User> hasId(Long id){
        return (from, criteriaBuilder) ->
                criteriaBuilder.equal(from.get("id"), id);
    }

    public static PredicateSpecification<User> hasUsername(String username){
        return (from, criteriaBuilder) ->
                criteriaBuilder.like(from.get("username"), "%" +  username + "%");
    }

    public static PredicateSpecification<User> hasEmail(String email){
        return (from, criteriaBuilder) ->
                criteriaBuilder.like(from.get("email"), "%" + email + "%");
    }

    public static PredicateSpecification<User> hasRole(UserRole role){
        return (from, criteriaBuilder) ->
                criteriaBuilder.like(from.get("role"),"%" + role + "%");
    }

    public static PredicateSpecification<User> hasRegister(LocalDateTime register){
        return (from, criteriaBuilder) ->
                criteriaBuilder.equal(from.get("register"), register);
    }

    public static PredicateSpecification<User> hasLastUpdate(LocalDateTime lastUpdate){
        return (from, criteriaBuilder) ->
                criteriaBuilder.equal(from.get("last_update"), lastUpdate);
    }
}
