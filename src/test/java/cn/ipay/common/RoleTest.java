package cn.ipay.common;

import org.apache.shiro.subject.Subject;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by Rayest on 2016/8/12 0012.
 * 对角色的测试
 * hasRole: boolean
 * checkRole: void，对没有角色的测试抛出异常
 */
public class RoleTest {

    @Test
    public void testHasRole_role1() throws Exception {
        // subject：角色主体
        Subject currentUser = ShiroUtil.login("classpath:shiro_role.ini", "ray", "123456");
        // 判断是否有某个角色
        String hasRole = currentUser.hasRole("role1") ? "yes" : "no";
        assertEquals(hasRole, "yes");
        currentUser.logout();
    }

    @Test
    public void testHasRole_role2() throws Exception {
        Subject currentUser = ShiroUtil.login("classpath:shiro_role.ini", "pay", "222222");
        String hasRole = currentUser.hasRole("role2") ? "yes" : "no";
        assertEquals(hasRole, "no");
        currentUser.logout();
    }

    @Test
    public void testHasRoles() throws Exception {
        Subject currentUser = ShiroUtil.login("classpath:shiro_role.ini", "pay", "222222");
        boolean[] roles = currentUser.hasRoles(Arrays.asList("role1", "role2", "role3"));
        assertEquals(roles[0] ? "yes" : "no", "yes");
        assertEquals(roles[1] ? "yes" : "no", "no");
        assertEquals(roles[2] ? "yes" : "no", "no");
        currentUser.logout();
    }

    @Test
    public void testHasAllRoles() throws Exception {
        Subject currentUser = ShiroUtil.login("classpath:shiro_role.ini", "pay", "222222");
        String hasAllRoles = currentUser.hasAllRoles(Arrays.asList("role1", "role2")) ? "yes" : "no";
        assertEquals(hasAllRoles, "no");
        currentUser.logout();
    }

    @Test
    public void testCheckRole_role1() throws Exception {
        Subject currentUser = ShiroUtil.login("classpath:shiro_role.ini", "ray", "123456");
        currentUser.checkRole("role1");
        currentUser.logout();
    }

    @Test
    public void testCheckRoles() throws Exception {
        Subject currentUser = ShiroUtil.login("classpath:shiro_role.ini", "pay", "222222");
        currentUser.checkRoles(Arrays.asList("role1", "role2", "role3"));
        currentUser.checkRoles("role1", "role2", "role3");
        currentUser.logout();
    }

}
