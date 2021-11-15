package com.example.OtpGeneration.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class JwtUtils {

    public String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    public boolean validateToken(String authToken,String signingKey) {
        try {
            Jwts.parser().setSigningKey(signingKey).parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String UserNameFromJWTs(String token, String signingKey) {
        Claims claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody();
        return String.valueOf(claims.get("email"));
    }


}
