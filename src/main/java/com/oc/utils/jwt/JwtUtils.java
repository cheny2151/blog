package com.oc.utils.jwt;

import com.oc.utils.SpringUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.core.env.Environment;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JsonWebToken工具类
 */
public class JwtUtils {

    static {
        Environment environment = SpringUtils.getBean("environment", Environment.class);
        IN_DATE = environment.getProperty("jwt.indate", int.class);
        SHA256_KEY = environment.getProperty("jwt.sha256.key");
    }

    /**
     * 用户名key
     */
    private static final String USER_NAME_KEY = "sub";

    /**
     * 签证过期时间key
     */
    private static final String CREATE_DATE_KEY = "iat";

    /**
     * 签证有效时间(day)
     */
    private static int IN_DATE;

    /**
     * 签证key
     */
    private static String SHA256_KEY;

    /**
     * 生成token
     */
    public static String generateToken(JwtPrincipal user) {
        Date signDate = new Date();
        Map<String, Object> claims = generateClaims(user, signDate);
        return Jwts.builder().setClaims(claims)
                .setExpiration(generateExpiration(signDate))
                .signWith(SignatureAlgorithm.HS256, SHA256_KEY)
                .compact();
    }

    private static Map<String, Object> generateClaims(JwtPrincipal user, Date signDate) {
        HashMap<String, Object> claim = new HashMap<>();
        claim.put(USER_NAME_KEY, user.getUsername());
        claim.put(CREATE_DATE_KEY, signDate);
        return claim;
    }

    private static Date generateExpiration(Date signDate) {
        return DateUtils.addDays(signDate, IN_DATE);
    }

    /**
     * token提取Claims
     */
    private static Claims parseToken(String token) {
        if (token == null) throw new NullPointerException();
        try {
            return Jwts.parser()
                    .setSigningKey(SHA256_KEY)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 提取username
     */
    public static String parseToUsername(String token) {
        Claims claims;
        return (claims = parseToken(token)) == null ? null : claims.get(USER_NAME_KEY, String.class);
    }

    /**
     * 1,token签证未过期
     * 2,用户最后一次修改密码时间为null || token签证创建时间在修改密码时间之后
     */
    public static boolean validate(String token, JwtPrincipal auth) {
        Claims claims = parseToken(token);
        return claims.getExpiration().after(new Date()) && (auth.getLastPasswordReset() == null || new Date(claims.get(CREATE_DATE_KEY, long.class)).before(auth.getLastPasswordReset()));
    }

}
