package com.fabada.agendamento.service;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class JWTGenerator implements JWTGeneratorInterface{

    private RSAPrivateKey rsaPrivateKey;
    private RSAPublicKey rsaPublicKey;

    @Override
    public String generator() {
        return "";
    }

//    private String setJWT(){
//
//    }
//
//    private JWTClaimsSet claimsSet(){
//
//    }
//
//    private JWSSigner jwsSigner(){
//
//    }
//
//    private SignedJWT signedJWT(JWTClaimsSet claims){
//
//    }

}
