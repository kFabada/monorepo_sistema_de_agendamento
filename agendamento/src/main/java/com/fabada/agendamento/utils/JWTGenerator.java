package com.fabada.agendamento.utils;

import com.fabada.agendamento.execption.JWTGeneratorException;
import com.nimbusds.jose.*;

import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Component
public class JWTGenerator implements JWTGeneratorInterface{

    @Value("${spring.key.rsa-private}")
    private RSAPrivateKey rsaPrivateKey;
    @Value("${spring.key.rsa-public}")
    private RSAPublicKey rsaPublicKey;

    @Override
    public String generator(String username, String role) {
        return setJWT(username,role);
    }

    @Override
    public boolean validaJWT() {
        return false;
    }

    private RSAKey setRsaKey(){
      return new RSAKey
                .Builder(rsaPublicKey)
                .privateKey(rsaPrivateKey)
                .build();
    }

    private String setJWT(String username, String role){
        try{
            RSAKey rsaKey = setRsaKey();
            JWSSigner signer = jwsSigner(rsaKey);
            JWTClaimsSet claimsSet = claimsSet(username, role);
            SignedJWT signed = signedJWT(claimsSet, rsaKey);

            signed.sign(signer);
            return signed.serialize();
        } catch (JOSEException e) {
            throw new JWTGeneratorException(e.getMessage());
        }
    }

    private JWTClaimsSet claimsSet(String username, String role){
        Instant now = Instant.now();
        Date issueTime = Date.from(now);

        return new JWTClaimsSet
                .Builder()

                .claim("username", username)
                .claim("role", role)
                .issuer("http://localhost:8080")
                .expirationTime(Date.from(now.plusSeconds(60 * 60)))
                .issueTime(issueTime)
                .build();
    }

    private JWSSigner jwsSigner(RSAKey rsaKey) throws JOSEException {
       return new RSASSASigner(rsaKey);
    }

    private SignedJWT signedJWT(JWTClaimsSet claims, RSAKey rsaKey) {
        return new SignedJWT(new JWSHeader.Builder(JWSAlgorithm.RS256).type(JOSEObjectType.JWT).keyID(rsaKey.getKeyID()).build(), claims);
    }
}
