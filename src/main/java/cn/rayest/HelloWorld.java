package cn.rayest;

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
        // ��ȡ�����ļ�����ʼ�� SecurityManager �����������ļ� shiro.ini ֮ǰҪ���� classpath
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        // ��ȡ SecurityManager ʵ��
        SecurityManager securityManager = factory.getInstance();
        // �� SecurityManager ʵ���󶨵� SecurityUtils
        SecurityUtils.setSecurityManager(securityManager);
        // ��ȡ��ǰִ�е��û�
        Subject currentUser = SecurityUtils.getSubject();
        // ���� token ����: �û�/����
        UsernamePasswordToken token = new UsernamePasswordToken("ray", "123456");
        try{
            // �����֤
            currentUser.login(token);
            System.out.println("�����֤�ɹ���");
        }catch (AuthenticationException exception){
            exception.printStackTrace();
            System.out.println("�����֤ʧ�ܣ�");
        }
        // �˳�
        currentUser.logout();
        
    }
}
