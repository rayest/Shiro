package cn.rayest.security;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * Created by Rayest on 2016/10/30 0030.
 */
public class MD5Service {
    // MD5 加密
    public static String md5(String string, String salt){
        return new Md5Hash(string, salt).toString();
    }

}
