//package com.scentheartsstudio.scentheartsstudio.jwt;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.io.Serial;
//import java.io.Serializable;
//import java.security.Key;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//@Component
//public class JwtTokenUtilV1 implements Serializable {
//
//    @Serial
//    private static final long serialVersionUID = -12345L;
//    private int JWT_TOKEN_VALIDITY = 30 * 60 * 1000;
//    private Key secret = Keys.secretKeyFor(SignatureAlgorithm.HS512);
//
//    private Claims getAllClaimsFromToken(String token) {
//        // return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
//        return Jwts.parserBuilder()
//                    .setSigningKey(secret)
//                    .build()
//                    .parseClaimsJws(token)
//                    .getBody();
//    }
//
//    public String getUsernameFromToken(String token) {
//        return getClaimFromToken(token, Claims::getSubject);
//    }
//
//    public Date getIssuedAtDateFromToken(String token){
//        return getClaimFromToken(token, Claims::getIssuedAt);
//    }
//
//    public Date getExpirationDateFromToken(String token) {
//        return getClaimFromToken(token, Claims::getExpiration);
//    }
//
//    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = getAllClaimsFromToken(token);
//        return claimsResolver.apply(claims);
//    }
//
//    private Boolean isTokenExpired(String token) {
//        final Date expiration = getExpirationDateFromToken(token);
//        return expiration.before(new Date());
//    }
//
//    private Boolean ignoreTokenExpiration(String token){
//        return false;
//    }
//
//    private String doGenerateToken(Map<String, Object> claims, String subject) {
//        return Jwts.builder()
//                    .setClaims(claims)
//                    .setSubject(subject)
//                    .setIssuedAt(new Date(System.currentTimeMillis()))
//                    .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
//                // .signWith(SignatureAlgorithm.HS512, secret).compact();
//                .signWith(secret, SignatureAlgorithm.HS512)
//                .compact();
//    }
//
//    public String generateToken(UserDetails userDetails) {
//        Map<String, Object> claims = new HashMap<>();
//        return doGenerateToken(claims, userDetails.getUsername());
//    }
//
//    public Boolean canTokenBeRefreshed(String token){
//        return (!isTokenExpired(token) || ignoreTokenExpiration(token));
//    }
//
//    public Boolean validateToken(String token, UserDetails userDetails) {
//        final String username = getUsernameFromToken(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
//
//}
