package cn.ipay.common.helloWorld;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * Created by Rayest on 2016/8/12 0012.
 */

public class HelloWorld {
    public static void main(String[] args) {

        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini"); // 读取配置文件，初始化 SecurityManager 工厂。配置文件 shiro.ini 之前要加上 classpath

        SecurityManager securityManager = factory.getInstance(); // 获取 SecurityManager 实例

        SecurityUtils.setSecurityManager(securityManager); // 把 SecurityManager 实例绑定到 SecurityUtils

        Subject currentUser = SecurityUtils.getSubject(); // 获取当前执行的用户

        UsernamePasswordToken token = new UsernamePasswordToken("ray", "123456"); // 创建 token 令牌: 用户/密码

        try {

            currentUser.login(token); // 身份认证( 即登录 )
            System.out.println("身份认证成功！");
        } catch (AuthenticationException exception) {
            exception.printStackTrace();
            System.out.println("身份认证失败！");
        }


        currentUser.logout(); // 退出

    }
}
