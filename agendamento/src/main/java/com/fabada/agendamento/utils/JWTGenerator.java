package com.fabada.agendamento.utils;

import com.fabada.agendamento.dto.TokenDTO;
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
    public TokenDTO generator(String username, String scope) {
        return setJWT(username, scope);
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

    private TokenDTO setJWT(String username, String scope){
        try{
            Instant iat = Instant.now();
            Instant exp = iat.plusSeconds(60 * 60);

            RSAKey rsaKey = setRsaKey();
            JWSSigner signer = jwsSigner(rsaKey);
            JWTClaimsSet claimsSet = claimsSet(username, scope, iat, exp);
            SignedJWT signed = signedJWT(claimsSet, rsaKey);
            signed.sign(signer);

            return new TokenDTO(signed.serialize(),scope, iat, exp) ;
        } catch (JOSEException e) {
            throw new JWTGeneratorException(e.getMessage());
        }
    }

    private JWTClaimsSet claimsSet(String username, String scope, Instant iat, Instant exp){
        return new JWTClaimsSet
                .Builder()
                .claim("username", username)
                .claim("scope", scope)
                .issuer("http://localhost:8080")
                .expirationTime(Date.from(exp))
                .issueTime(Date.from(iat))
                .build();
    }

    private JWSSigner jwsSigner(RSAKey rsaKey) throws JOSEException {
       return new RSASSASigner(rsaKey);
    }

    private SignedJWT signedJWT(JWTClaimsSet claims, RSAKey rsaKey) {
        return new SignedJWT(new JWSHeader.Builder(JWSAlgorithm.RS256).type(JOSEObjectType.JWT).keyID(rsaKey.getKeyID()).build(), claims);
    }
}
