package cn.rayest.security;

import org.apache.shiro.codec.Base64;

/**
 * Created by Rayest on 2016/10/30 0030.
 * 加密和解密
 */
public class Base64Service {

    // base64 加密
    public static String encodeBase64(String string){
        return Base64.encodeToString(string.getBytes());
    }

    // base64 解密 13020187883
    public static String decodeBase64(String string){
        return Base64.decodeToString(string.getBytes());
    }
}
