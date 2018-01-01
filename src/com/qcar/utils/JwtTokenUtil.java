package com.qcar.utils;

import com.qcar.model.mongo.Permission;
import com.qcar.model.mongo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

public final class JwtTokenUtil {

    public static Boolean validateToken(String token){
       Claims claims= Jwts.parser().parseClaimsJws(token).getBody();
       return !claims.isEmpty();
    }

    public static Optional<String> generateToken(User user){


        try {
            String jwt = Jwts.builder()
                    .setSubject("users/TzMUocMF4p")
                    .setExpiration(new Date())
                    .claim("name", user.getLoginName())
                    .claim("permissions", Permission.getSumPermissions(user.getPermissionList()))
                    .signWith(
                            SignatureAlgorithm.HS256,
                            "secret".getBytes("UTF-8")
                    )
                    .compact();

            return Optional.of(jwt);
        }
        catch (Exception io){
            return Optional.empty();
        }

    }

}
