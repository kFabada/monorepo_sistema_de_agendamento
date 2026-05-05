package com.fabada.agendamento.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public record FileNameValidation(String fileName) {

    public List<String> toList(){
        List<String> file;

        if(!fileName.isEmpty()){
            if(fileName.contains(",")) file = Arrays.stream(fileName.split(",")).toList();
            else file = new ArrayList<>(List.of(fileName));
        }else throw new IllegalArgumentException();

        return file;
    }
}
