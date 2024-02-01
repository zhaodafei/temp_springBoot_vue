package com.example.fei.service;

import com.example.fei.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Date;


/**
 * 参考资料:\
 *    https://github.com/jwtk/jjwt
 *    https://stormpath.com/blog/jwt-java-create-verify
 */
@Component
public class TokenService {

    // 令牌自定义标识 ---------- 后期定义到配置文件中
    @Value("${token.header}")
    private String header;

    // 令牌秘钥  ---------- 后期定义到配置文件中
    @Value("${token.secret}")
    private String secret;

    // 令牌有效期（默认30分钟） ---------- 后期定义到配置文件中
    @Value("${token.expireTime}")
    private int expireTime;

    /**
     * 创建 token
     * @return {}
     */
    public String createToken(User user) {
        System.out.println(expireTime);
        System.out.println(secret);
        System.out.println(header);


        long nowMillis  = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Date exp = new Date(nowMillis + 1000 * 30); // 过期时间 30秒
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256; // 签名算法

        JwtBuilder builder = Jwts.builder()
                .setId("fei123") // 置唯一编号
                // .setIssuedAt(now)  //设置签发日期
                // .setExpiration(exp) // 设置过期时间
                // .setAudience("iot")
                // .setIssuer("fei") // 设置发行人
                .setSubject("fei_setSubject")
                /*.claim("userName", "username_01")
                .claim("userPwd", "123456")*/
                .claim("userName", user.getUsername())
                .claim("userPwd", user.getPassword())
                .signWith(signatureAlgorithm, secret); // 设置签名 使用HS256算法，并设置SecretKey(字符串)

        return builder.compact();
    }

    /**
     * 解析校验token
     * @param jwt String
     * @return {}
     */
    public Boolean parseJWT(String jwt) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(jwt)
                    .getBody();

            Object userName = claims.get("userName");
            Object userPwd = claims.get("userPwd");
            String subject = claims.getSubject();

            System.out.println(userName.toString());
            System.out.println(userPwd.toString());
            System.out.println(subject);

            return true;
        } catch (Exception e) {
            System.out.println("Token格式有误");
            return false;
        }
    }

    ///======================== 临时测试功能,不参与项目====================
    public static String fei01() {
        String token = Jwts.builder().
                setId("fei123") // 置唯一编号
                .setSubject("大飞")
                .setIssuedAt(new Date())//设置签发时间
                .claim("userName", "username_01")
                .claim("userPwd", "123456")
                .signWith(SignatureAlgorithm.HS256, "fei_secret")//设置签名秘钥
                .compact();

        System.out.println(token);
        return token;
    }
    public static Boolean fei02(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey("fei_secret")
                    .parseClaimsJws(token)
                    .getBody();
            System.out.println("id:"+claims.getId());
            System.out.println("subject:"+claims.getSubject());
            System.out.println("IssuedAt:"+claims.getIssuedAt());
            System.out.println("userName:"+claims.get("userName"));
            System.out.println("userPwd:"+claims.get("userPwd"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    // public static void main(String[] args) {
    //     // 测试fei01和fei02
    //     String s = TokenService.fei01();
    //     TokenService.fei02(s);
    // }
    ///============================================
}
