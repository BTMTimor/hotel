package org.tm.common.token;

import com.jfinal.kit.StrKit;
import org.tm.common.util.MyMD5Util;
import org.tm.common.util.MyUUIDUtil;

/*
    author: Timor
    date: 2019/12/6 0006
*/
public class MyToken {
    public static final String SPILT = "#";
    public static final String TOKEN_NAME = "token";
    public static final int DEFAULT_TIME_OUT = 3 * 60 * 60 * 1000;// 3h
    public static final int DEFAULT_REFRESH_LIMIT = 200;// 200毫秒
    public static final String SLAT = "wdfvb9iugf5tyhj76tr3rtghbn";

    public static final String UID = "uid";
    public static final String UUID = "uuid";
    public static final String TIMESTAMP = "timeStamp";
    public static final String TOUT = "tout";
    public static final String SIGNATURE = "signature";

    private String uid;// 用户id
    private String uuid;// 随机字符串
    private long timeStamp = 0;// token时间戳
    private int refreshLimit = DEFAULT_REFRESH_LIMIT;// 上次更新token时间戳
    private long tout = 0;// secondsOfTimeOut
    private String signature;// 签名

    public MyToken() {
    }

    private MyToken(String uid) {
        this.uid = uid;
    }

    public boolean isValid(){
        return isValid(this);
    }

    public static boolean isValid(MyToken token){
        long now = System.currentTimeMillis();
        // 是否过期
        long timeInterval = now - token.getTimeStamp();
        if (timeInterval >= token.getRefreshLimit()) {
            if (timeInterval <= token.getTout()) {
                // 是否被篡改
                String signature = token.signature();
                if (signature.equals(token.getSignature())) {
                    return true;
                } else {
                    System.out.println("[warn] token had been modify!!!");
                }
            }else {
                System.out.println("[Validate] " + now + " - " + token.getTimeStamp() + " = "
                        + timeInterval + " > " + token.getTout());
                System.out.println("[info] token is time out!!!");
//                throw new Exception("");
//                throw new FrequentlyUseException("操作过于频繁...请稍后重试！");
            }
        } else {
            System.out.println("[Validate] " + timeInterval + " < " + token.getRefreshLimit());
            System.out.println("[worn] the request is too frequent!!!");
        }
        return false;
    }

    // origin: uuid # timeStamp# refreshLimit  # secondsOfTimeOut
    private String getOrigin() {
        return uid + SPILT + uuid + SPILT + timeStamp
                + SPILT + refreshLimit  + SPILT + tout;
    }

    // origin: uuid # timeStamp # refreshLimit # secondsOfTimeOut
    private String getOriginAdnCheck() {
        if (StrKit.isBlank(uuid)) {
            uuid = MyUUIDUtil.getUUID();
        }
        if (timeStamp < 0) {
            timeStamp = System.currentTimeMillis();
        }
        if (tout < 0) {
            tout = DEFAULT_TIME_OUT;
        }
        if (refreshLimit < 0) {
            tout = DEFAULT_REFRESH_LIMIT;
        }
        return uid + SPILT + uuid + SPILT + timeStamp
                + SPILT + refreshLimit + SPILT + tout;
    }

    // token: uid # uuid # timeStamp # refreshLimit # secondsOfTimeOut # signature
    public String getTokenString() {
        if (StrKit.notBlank(uid)) {
            return getOriginAdnCheck() + SPILT + signature();
        } else {
            return null;
        }
    }

    public String signature() {
        return MyMD5Util.getMD5(getOrigin(), SLAT);
    }

    /**
     * 从字符串解析token
     * <p>
     * 后期写工具，不拼接字符串作为token，直接使用对应代号，
     * 可以直接通过反射搞定（效率可能低一点）
     * <p>
     * token: uid # uuid # timeStamp # refreshLimit# secondsOfTimeOut # signature
     *
     * @param tokenStr tokenString
     * @return MyToken
     */
    public static MyToken parseToken(String tokenStr) {
        if (StrKit.notBlank(tokenStr)) {
            //  token: uid # uuid # timeStamp # refreshLimit# secondsOfTimeOut # signature
            String[] attr = tokenStr.split(SPILT);
            if (attr.length == 6) {
                MyToken token = new MyToken(attr[0]);
                token.uuid = attr[1];
                token.timeStamp = Long.parseLong(attr[2]);
                token.refreshLimit = Integer.parseInt(attr[3]);
                token.tout = Long.parseLong(attr[4]);
                token.signature = attr[5];
                /*if (token.isValid()) {
                    // token
                    // System.out.println(token);
                } else {
                    System.out.println("[MyToken] parseToken: " + token + "is invalidate");
                }*/
                return token;
            }
        }
        return null;
    }

    /**
     * 更新token，包括timestamp、tout、uuid
     *
     * @return token
     */
    public MyToken update() {
        MyToken.updateToken(this);
        return this;
    }

    /**
     * 更新token，包括timestamp、tout、uuid
     *
     * @param token token
     * @return token
     */
    public static MyToken updateToken(MyToken token) {
        return updateToken(token, DEFAULT_REFRESH_LIMIT);
    }

    /**
     * 更新token，包括timestamp、tout、uuid
     *
     * @param token        token
     * @param refreshLimit 接口使用频率
     * @return token
     */
    public static MyToken updateToken(MyToken token, int refreshLimit) {
        token.timeStamp = System.currentTimeMillis();
        token.tout = DEFAULT_TIME_OUT;
        token.uuid = MyUUIDUtil.getUUID();
        token.refreshLimit = refreshLimit;

        return token;
    }

    /**
     * 创建token
     *
     * @param uid user_id
     * @return MyToken
     */
    public static MyToken newToken(String uid) {
        return newToken(uid, DEFAULT_TIME_OUT, DEFAULT_REFRESH_LIMIT);
    }

    /**
     * 创建token
     *
     * @param uid     user_id
     * @param timeOut token有效期
     * @return MyToken
     */
    public static MyToken newToken(String uid, long timeOut) {
        return newToken(uid, timeOut, DEFAULT_REFRESH_LIMIT);
    }

    /**
     * 创建token
     *
     * @param uid          user_id
     * @param timeOut      token有效期
     * @param refreshLimit 访问限制
     * @return MyToken
     */
    public static MyToken newToken(String uid, long timeOut, int refreshLimit) {
        MyToken token = new MyToken(uid);
        token.uuid = MyUUIDUtil.getUUID();
        token.timeStamp = System.currentTimeMillis();
        token.tout = timeOut;
        token.refreshLimit = refreshLimit;
        token.signature = token.signature();
        return token;
    }

    public String getUid() {
        return uid;
    }

    public String getUuid() {
        return uuid;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public long getTout() {
        return tout;
    }

    public String getSignature() {
        return signature;
    }

    public int getRefreshLimit() {
        return refreshLimit;
    }

    @Override
    public String toString() {
        return "MyToken{" +
                "uid='" + uid + '\'' +
                ", uuid='" + uuid + '\'' +
                ", timeStamp=" + timeStamp +
                ", tout=" + tout +
                ", signature='" + signature + '\'' +
                '}';
    }
}
