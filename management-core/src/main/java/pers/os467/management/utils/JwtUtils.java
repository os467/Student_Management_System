package pers.os467.management.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


import java.util.Date;
import java.util.Objects;

/**
 * 一个简单的jwt工具
 * by os467
 */
public class JwtUtils {

    public static String key = "abc123";

    public static String generateToken(JwtUser jwtUser){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Date startTime = new Date(System.currentTimeMillis());
        //设置token失效时间:24小时 * 1年
        Date expireTime = new Date(startTime.getTime() + 60 * 1000 * 60 * 24 * 365);
        //自定义会话超时时间：24小时 * 1年
        Date outDate = new Date(startTime.getTime() + 60 * 1000 * 60 * 24 * 365);
        JwtBuilder builder = Jwts.builder();
        JwtBuilder jwtBuilder = builder.setIssuer("os467")
                .setSubject("os467")
                .setAudience("user")
                .setExpiration(expireTime)
                .claim("expiration",
                        outDate.getTime())
                .claim("username", jwtUser.getUsername())
                .claim("uid", jwtUser.getUid())
                .claim("rid", jwtUser.getRid())
                .claim("role", jwtUser.getRole())
                .signWith(signatureAlgorithm,key);
        return jwtBuilder.compact();
    }

    /**
     * 获取验证Claims
     */
    public static Claims getClaims(String token) {
        //获取claims
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    //此处的key要与之前创建的key一致
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {

        }
        return claims;
    }

    public static JwtUser checkLogin(String token){
        JwtUser jwtUser = new JwtUser();

        Claims claims = getClaims(token);
        if (claims == null) {
            return null;
        }

        Integer uid = (Integer) claims.get("uid");
        if (Objects.isNull(uid)) {
            return null;
        }

        Long expiration = (Long) claims.get("expiration");
        if (expiration < (System.currentTimeMillis())) {
            return null;
        }
        jwtUser.setUid(uid);
        jwtUser.setUsername((String) claims.get("username"));
        jwtUser.setRid((Integer) claims.get("rid"));
        jwtUser.setRole((String) claims.get("role"));
        return jwtUser;
    }

}
