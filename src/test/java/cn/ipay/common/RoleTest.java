package cn.ipay.common;

import org.apache.shiro.subject.Subject;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Rayest on 2016/8/12 0012.
 */
public class RoleTest {
    @Test
    public void testHasRole_role1() throws Exception {
        Subject currentUser = ShiroUtil.login("classpath:shiro_role.ini", "ray", "123456");
        System.out.println(currentUser.hasRole("role1") ? "yes": "no");

        currentUser.logout();
    }

    @Test
    public void testHasRole_role2() throws Exception {
        Subject currentUser = ShiroUtil.login("classpath:shiro_role.ini", "pay", "222222");
        System.out.println(currentUser.hasRole("role2") ? "yes": "no");

        currentUser.logout();
    }

    @Test
    public void testHasRoles() throws Exception {
        Subject currentUser = ShiroUtil.login("classpath:shiro_role.ini", "pay", "222222");
        boolean[] roles = currentUser.hasRoles(Arrays.asList("role1", "role2", "role3"));
        System.out.println(roles[0] ? "yes": "no");
        System.out.println(roles[1] ? "yes": "no");
        System.out.println(roles[2] ? "yes": "no");

        currentUser.logout();
     }

    @Test
    public void testHasAllRoles() throws Exception {
        Subject currentUser = ShiroUtil.login("classpath:shiro_role.ini", "pay", "222222");
        System.out.println(currentUser.hasAllRoles(Arrays.asList("role1", "role2")) ? "yes" : "no");

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
