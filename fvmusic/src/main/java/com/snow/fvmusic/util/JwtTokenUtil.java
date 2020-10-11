package com.snow.fvmusic.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.snow.fvmusic.generator.entities.User;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author ：snow
 * @Date ：Created in 2020-08-13
 * @Description：
 * @Modified By：
 * @Version:
 */
@Component
@Slf4j
public class JwtTokenUtil {
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATE = "create";
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 根据负载生成token
     * @param claims
     * @return
     */
    private String generateToken(Map<String, Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .setExpiration(generateExpirationDate())
                .compact();
    }

    /**
     * 生成token的过期时间
     * @return
     */
    private Date generateExpirationDate(){
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 根据用户信息生成token
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails){
        HashMap<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATE, new Date());
        return generateToken(claims);
    }

    /**
     * 从token 中获取jwt负载
     * @param token
     * @return
     */
    private Claims getClaimsFromToken(String token){
        Claims claims = null;
        try{
            claims =Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            log.info("token:{} 解释失败", token);
        }
        return claims;
    }

    /**
     * 从token中获取用户名
     * @param token
     * @return
     */
    public String getUsernameFromToken(String token){
        String username = "";
        try{
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        }catch (Exception e){
            log.info("从token:{}中获取用户名失败", token);
        }
        return username;
    }

    /**
     * 判断token是否过期
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token){
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * 获取token的过期时间
     * @param token
     * @return
     */
    private Date getExpirationDateFromToken(String token){
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * 判断token是否失效
     * @param token
     * @return
     */
    public boolean isTokenValidate(String token, String CLAIM_KEY_USERNAME){
        return getUsernameFromToken(token).equals(CLAIM_KEY_USERNAME) && !isTokenExpired(token);
    }

    /**
     * 刷新30分钟内没有刷新的token
     * @param oldToken
     * @return
     */
    public String refreshHeadToken(String oldToken){
        if (StrUtil.isEmpty(oldToken)) return null;
        String token = oldToken.substring(tokenHead.length());
        if (StrUtil.isEmpty(token)) return null;
        Claims claims = getClaimsFromToken(token);
        if (claims == null) return null;
        if (isTokenExpired(token)) return null;
        if (isRefreshJustBefore(token, 30 * 60)) return token;
        claims.put(CLAIM_KEY_CREATE, new Date());
        return generateToken(claims);

    }

    /**
     * 判断是否在指定时间内刷新过
     * @param token
     * @param time
     * @return
     */
    private boolean isRefreshJustBefore(String token, int time){
        Date create = getClaimsFromToken(token).get(CLAIM_KEY_CREATE, Date.class);
        return DateUtil.offsetSecond(create, time).before(new Date());
    }

    /**
     * 判断token是否可用
     * @param token
     * @param userDetails
     * @return
     */
    public boolean validateToken(String token, UserDetails userDetails){
        return !isTokenExpired(token) && userDetails.getUsername() != null;
    }

}
