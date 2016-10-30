package cn.rayest.realm;

import cn.rayest.util.DbUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.sql.Connection;

/**
 * Created by Rayest on 2016/10/30 0030.
 */
public class UserRealm extends AuthorizingRealm {

    private UserService userService = new UserService();
    private DbUtil dbUtil = new DbUtil();

    // 2.为当前登录的用户授予角色和权限
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userName = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authcInfo = new SimpleAuthorizationInfo();
        Connection connection = null;
        try {
            connection = dbUtil.getCon();
            authcInfo.setRoles(userService.getRoles(connection, userName));
            authcInfo.setStringPermissions(userService.getPermissions(connection, userName));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                dbUtil.closeCon(connection);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    // 1.验证当前登录的用户
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String useName = (String) token.getPrincipal();
        Connection connection = null;
        try {
            connection = dbUtil.getCon();
            User user = userService.getByUserName(connection, useName);
            if (user != null) {
                AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), "xx");
                return authcInfo;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dbUtil.closeCon(connection);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
