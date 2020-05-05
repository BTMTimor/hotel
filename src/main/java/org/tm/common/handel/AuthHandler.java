package org.tm.common.handel;

import com.jfinal.handler.Handler;
import io.jboot.aop.annotation.ConfigValue;
import org.tm.common.token.MyToken;
import org.tm.common.token.MyTokenManager;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

/*
    author: Timor
    date: 2019/12/3 0003

    处理用户认证
*/
public class AuthHandler extends Handler {
    @ConfigValue("const.user.token.id")
    private String userId;
    @ConfigValue("const.visitor.id")
    private String visitorId;

    @Override
    public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] booleans) {
       Cookie cookie = getCookie(request, userId);
        if(null != cookie){
            MyToken token = MyTokenManager.validateToken(cookie.getValue());
            if(null != token){
                // 传递user_id，方便后边业务功能
                request.setAttribute(userId, token.getUid());
            }else {
                System.out.println("[AuthHandler] 游客访问!!!");
            }
        }

        // 未登录，默认使用游客身份
        if(null == request.getAttribute(userId)){
            request.setAttribute(userId, visitorId + getRandomString(16));
        }

        next.handle(target, request, response, booleans);
    }

    public Cookie getCookie(HttpServletRequest request, String name) {
        if(null == request || null == name){
            return null;
        }
        Cookie[] cookies = request.getCookies();
        if(null != cookies && cookies.length > 0){
            for (Cookie cookie : cookies) {
                String cname = cookie.getName();
                if (null != cname && cname.equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    public static String getRandomString(int length){
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < length; i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
