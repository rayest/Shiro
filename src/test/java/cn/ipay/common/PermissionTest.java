package cn.ipay.common;

import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * Created by Rayest on 2016/8/12 0012.
 */
public class PermissionTest {
    @Test
    public void testIsPermitted() throws Exception {
        Subject currentUser = ShiroUtil.login("classpath:shiro_permission.ini", "ray", "123456");
        System.out.println(currentUser.isPermitted("user:select") ? "yes" : "no");

        currentUser = ShiroUtil.login("classpath:shiro_permission.ini", "pay", "222222");
        System.out.println(currentUser.isPermitted("user:update") ? "yes" : "no");
        boolean[] result = currentUser.isPermitted("user:select", "user:delete", "user:update", "user:add");
        System.out.println(result[0] ? "yes" : "no");
        System.out.println(result[1] ? "yes" : "no");
        System.out.println(result[2] ? "yes" : "no");
        System.out.println(result[3] ? "yes" : "no");

        currentUser.logout();
    }


}
