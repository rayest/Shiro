package cn.ipay.common;

import org.apache.shiro.subject.Subject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Rayest on 2016/8/12 0012.
 */
public class PermissionTest {
    @Test
    public void testIsPermitted() throws Exception {
        // user --> ray
        Subject currentUser = ShiroUtil.login("classpath:shiro_permission.ini", "ray", "123456");
        String isPermitted = currentUser.isPermitted("user:select") ? "yes" : "no";
        assertEquals(isPermitted, "yes");

        // user --> pay
        currentUser = ShiroUtil.login("classpath:shiro_permission.ini", "pay", "222222");
        isPermitted = currentUser.isPermitted("user:update") ? "yes" : "no";
        assertEquals(isPermitted, "no");

        boolean[] result = currentUser.isPermitted("user:select", "user:delete", "user:update", "user:add");
        assertEquals(result[0] ? "yes" : "no", "yes");
        assertEquals(result[1] ? "yes" : "no", "no");
        assertEquals(result[2] ? "yes" : "no", "no");
        assertEquals(result[3] ? "yes" : "no", "no");

        currentUser.logout();
    }
}
