package com.fabada.agendamento.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomCodeImpl implements RandomCode{
    @Override
    public String createCode(int quantityNumbers, int limitRangeNumber) {

        if(quantityNumbers <= 0 || limitRangeNumber <= 0) throw new IllegalArgumentException("");
        String code = "";
        Random random = new Random();

        for (int range = 0; range <= quantityNumbers; range++){
            code = code.concat(String.valueOf(random.nextInt(limitRangeNumber)));
        }

        return code;
    }
}
