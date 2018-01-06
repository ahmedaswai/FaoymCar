package com.qcar.security;

import com.qcar.model.mongo.choicelist.Permission;
import com.qcar.model.mongo.entity.User;
import com.qcar.model.service.exception.ErrorCodes;
import com.qcar.model.service.exception.QCarSecurityException;
import com.qcar.utils.Constants;
import com.qcar.utils.GeneralUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Optional;

public final class JwtTokenUtil {

    public static User validateToken(String token){
        try {
            boolean emptyToken = token == null || token.isEmpty();
            if (emptyToken){
                throw new QCarSecurityException(ErrorCodes.INVALID_TOKEN);
            }

            Claims claims = Jwts.parser().setSigningKey(Constants.AUTHENTICATION_SECRET.
                    getBytes("UTF-8")).
                    parseClaimsJws(token).getBody();
            boolean claimsNotEmpty = !claims.isEmpty();
            boolean validUser = claims.get("user-name", String.class) != null;

            if(!(claimsNotEmpty && validUser)){
                throw new QCarSecurityException(ErrorCodes.INVALID_TOKEN);
            }

            String userName=claims.get("user-name", String.class);
            Long userId=claims.get("user-id", Long.class);
            Long permissions=claims.get("permissions",Long.class);


            return User.instance().loginName(userName).
                    id(userId).permissionList(Permission.getPermissions(permissions));

        }
        catch (Exception io){
            throw new QCarSecurityException(io, ErrorCodes.INVALID_TOKEN);
        }
    }

    public static Optional<String> generateToken(User user){


        try {

            /* add check for validating
            / the login of user after reading the token */
            String jwt = Jwts.builder()
                    .setSubject("users/TzMUocMF4p")
                    .setExpiration(GeneralUtils.getDateAfterMinutes(Constants.TOKEN_MINUTES_EXPIRATION))
                    .claim("user-name", user.getLoginName())
                    .claim("permissions", Permission.getSumPermissions(user.getPermissionList()))
                    .claim("user-id",user.getId())
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
