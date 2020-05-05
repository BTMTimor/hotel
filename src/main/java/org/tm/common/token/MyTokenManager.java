package org.tm.common.token;

import com.alibaba.druid.util.StringUtils;
import com.jfinal.core.Controller;


/*
    author: Timor
    date: 2019/12/4 0004
*/
public class MyTokenManager {
    public static final int COOKIE_TIME_OUT = 3*60*60;// 3h
    // token: uid # uuid # timeStamp # secondsOfTimeOut # signature

    /**
     * 获取token
     * @param controller
     * @return token对象
     */
    private static MyToken getToken(Controller controller) {
        // MyToken token = controller.getBean(MyToken.class, MyToken.TOKEN_NAME);
        String tokenString = controller.getCookie(MyToken.TOKEN_NAME);
        // System.out.println("[MyTokenManager] getToken: " + tokenString);

        MyToken token = MyToken.parseToken(tokenString);

        // System.out.println("[MyTokenManager] getToken: " + token);
        return token;
    }

    public static MyToken getToken(String tokenString) {
        return MyToken.parseToken(tokenString);
    }

    public static MyToken validateToken(String tokenString) {
        MyToken token = MyToken.parseToken(tokenString);
        if(validateToken(token)){
            return token;
        }
        return null;
    }

    private static boolean validateToken(MyToken token) {
        if(null != token) {
            return token.isValid();
        }
        return false;
    }

    public static MyToken validateToken(Controller controller) {
        return validateToken(controller, false);
    }

    /**
     * 验证token是否有效
     * @param controller controller实例
     * @param updateToken 是否自动更新token
     * @return token是否有效
     */
    public static MyToken validateToken(Controller controller, boolean updateToken) {
        MyToken token = getToken(controller);
        if (null != token){
            if (token.isValid()){
                if (updateToken){
                    token.update();
                }
                return token;
            }
        }
        return null;
    }

    public static MyToken createToken(Controller controller, String uid){
        return createToken(controller, uid, MyToken.DEFAULT_TIME_OUT);
    }

    public static MyToken createToken(Controller controller, String uid, long secondsOfTimeOut){
        if(!StringUtils.isEmpty(uid)) {
            MyToken token = MyToken.newToken(uid, secondsOfTimeOut);
            controller.setCookie(MyToken.TOKEN_NAME, token.getTokenString(), COOKIE_TIME_OUT, true);
            return token;
        }
        return null;
    }

    public static void clearToken(Controller controller){
        controller.setCookie(MyToken.TOKEN_NAME, "", 0, true);
    }
}
