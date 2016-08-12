package cn.ipay.common;

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
public class ShiroUtil {
    public static Subject login(String configFile, String userName, String password){
         Factory<SecurityManager> factory = new IniSecurityManagerFactory(configFile);
         SecurityManager securityManager = factory.getInstance();
         SecurityUtils.setSecurityManager(securityManager);
         Subject currentUser = SecurityUtils.getSubject();
         UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        try{
             currentUser.login(token);
            System.out.println("�����֤�ɹ���");
        }catch (AuthenticationException exception){
            exception.printStackTrace();
            System.out.println("�����֤ʧ�ܣ�");
        }

        return currentUser;
    }
}
