package com.hms.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

    @Value("${jwt.algorithm.key}")
    private String algorithmkey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiry.duration}")
    private int expiryTime;

    private Algorithm algorithm;   //Jwt token has stared
    @PostConstruct     // this annotation helps us to run this method Automatically when project is started.
  public void postContruct(){
//        System.out.println(algorithmkey);
//        System.out.println(issuer);
//        System.out.println(expiryTime);

        algorithm= Algorithm.HMAC256(algorithmkey);

    //algorithm key;- anyone who wants to decode this token (algorithm) should have this key.
        // without this key token decoding is not allowed.
  }

  //generating JWT Token formula= computer engineer is unemployed
  public String generateToken(String username){
     return JWT.create()
              .withClaim("name",username)
              .withExpiresAt(new Date(System.currentTimeMillis()+expiryTime))
              .withIssuer(issuer)
              .sign(algorithm);//consist signature.
  }
  //verify the token
  //junior with boxer Vicky
  public String  getUsername(String token) {
      DecodedJWT decodedJWT =
                      JWT.
                      require(algorithm).
                      withIssuer(issuer)
                      .build()
                      .verify(token);
      return decodedJWT.getClaim("name").asString();
  }

}
