package com.qcar.utils;

import com.qcar.model.mongo.Permission;
import com.qcar.model.mongo.User;
import com.qcar.model.service.exception.ErrorCodes;
import com.qcar.model.service.exception.QCarSecurityException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.charset.Charset;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

public final class JwtTokenUtil {

    public static Boolean validateToken(String token){
        try {
            boolean emptyToken = token == null || token.isEmpty();
            if (emptyToken) return false;

            Claims claims = Jwts.parser().setSigningKey(Constants.AUTHENTICATION_SECRET.
                    getBytes("UTF-8")).
                    parseClaimsJws(token).getBody();
            boolean claimsNotEmpty = !claims.isEmpty();
            boolean validUser = claims.get("name", String.class) != null;
            return claimsNotEmpty && validUser;
        }
        catch (Exception io){
            throw new QCarSecurityException(io, ErrorCodes.INVALID_TOKEN);
        }
    }

    public static Optional<String> generateToken(User user){


        try {

            String jwt = Jwts.builder()
                    .setSubject("users/TzMUocMF4p")
                    .setExpiration(GeneralUtils.getDateAfterMinutes(Constants.TOKEN_MINUTES_EXPIRATION))
                    .claim("name", user.getLoginName())
                    .claim("permissions", Permission.getSumPermissions(user.getPermissionList()))
                    .signWith(
                            SignatureAlgorithm.HS256, Constants.AUTHENTICATION_SECRET.
                                    getBytes("UTF-8")

                    )
                    .compact();

            return Optional.of(jwt);
        }
        catch (Exception io){
            return Optional.empty();
        }

    }

}
