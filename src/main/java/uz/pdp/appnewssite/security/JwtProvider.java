package uz.pdp.appnewssite.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import uz.pdp.appnewssite.entity.Lavozim;

import java.util.Date;
import java.util.Set;

@Component
public class JwtProvider {

    long expireTime=36_000_000;
String kalitsoz="BuTokenniMaxfiySoziHechKimBilmasin";
    public String  generationToken(String username, Lavozim  lavozims){

        Date expiration= new Date(System.currentTimeMillis()+expireTime);

        String token = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiration).claim("roles",lavozims.toString())
                .signWith(SignatureAlgorithm.HS512, kalitsoz)
                .compact();
        return token;
    }

   public boolean validateToken(String token){
        try {
            Jwts.parser()
                    .setSigningKey(kalitsoz)
                    .parseClaimsJws(token);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
   }


   public String getUserNameFromToken(String token){
       String username = Jwts.parser()
               .setSigningKey(kalitsoz)
               .parseClaimsJws(token)
               .getBody()
               .getSubject();
       return username;


   }



}
