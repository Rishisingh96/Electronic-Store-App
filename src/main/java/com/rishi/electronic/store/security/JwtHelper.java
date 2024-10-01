package com.rishi.electronic.store.security;

//this is used to perform jwt operations
//jwt generate
//username nikalna ho taken

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtHelper {

    // 1. Token validity (5 hours)
    public static final long TOKEN_VALIDITY = 5 * 60 * 60 * 1000;

    // 2. Secret key (now converted to SecretKey using Keys.hmacShaKeyFor)
    public static final String SECRET = "rishisinghhowareyouwhatareyoudoingherewhathappendmydarrishijkfasjfkajfaksfjdklererjkjsadkfjklajskljfldkjfkjaklfdj";
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    // Retrieve username from JWT token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    // Retrieve a specific claim from the token
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // For retrieving any information from token, we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .verifyWith(SECRET_KEY)  // Use verifyWith to verify the signature
                .build()
                .parseClaimsJws(token)
                .getBody();  // Extract the claims (payload) from the token
    }

    // Check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    // Retrieve expiration date from JWT token
    private Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    // Generate token for user
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    // Actually generate the token with claims, subject, issue date, and expiration
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS512) // signWith now takes a SecretKey and SignatureAlgorithm
                .compact();
    }

//this is old version of token
    /*
    //requirement

    //1.validity
    public static final long TOKEN_VALIDITY = 5*60*60*1000;

    //2.secret key
    public static final String SECRET_KEY = "rishisinghhowareyouwhatareyoudoingherewhathappendmydarrishijkfasjfkajfaksfjdklererjkjsadkfjklajskljfldkjfkjaklfdj";


    //retrieve username from jwt token
    public  String getUsernameFromToken(String token) {
        return getClaimFromToken(token,Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //for retrieving any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getPayload();
    }

    //check if the token has expired
    private  Boolean isTokenExpired(String token){
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //retrieve expiration date from jwt token
    private Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }
    //generate token from user
    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims,userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return
                Jwts.builder()
                        .setClaims(claims)
                        .setSubject(subject)
                        .setIssuedAt(new Date(System.currentTimeMillis()))
                        .setExpiration(new Date(System.currentTimeMillis()+TOKEN_VALIDITY))
                        .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    } */
}
