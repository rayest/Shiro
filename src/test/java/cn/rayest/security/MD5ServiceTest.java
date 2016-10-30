package cn.rayest.security;

import org.junit.Test;

/**
 * Created by Rayest on 2016/10/30 0030.
 */
public class MD5ServiceTest {
    @Test
    public void testMD5() throws Exception {
        String password = "123456";
        System.out.println("MD5加密：" + MD5Service.md5(password, "rayest"));
    }
}
