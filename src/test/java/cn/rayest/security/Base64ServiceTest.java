package cn.rayest.security;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Base64ServiceTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void testEncodeBase64() throws Exception {
        String password = "123456";
        System.out.println("Base64加密：" + Base64Service.encodeBase64(password));
        // MTIzNDU2
    }


    @Test
    public void testDecodeBase64() throws Exception {
        String password = "MTIzNDU2";
        System.out.println("Base64解密：" + Base64Service.decodeBase64(password));
        // 123456
    }


} 
