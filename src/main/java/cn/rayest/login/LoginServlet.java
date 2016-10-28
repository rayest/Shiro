package cn.rayest.login;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Rayest on 2016/10/27 0027.
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("login doGet");
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("login doPost");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        Subject subject = SecurityUtils.getSubject(); // 获取当前用户
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password); // 获取 token
        try {
            subject.login(token); // 登录
            response.sendRedirect("success.jsp"); // 登录成功，则返回到成功页面
        } catch (Exception e) {
            e.printStackTrace(); // 登录错误，则打印错误信息
            request.setAttribute("errorInfo", "用户名或密码错误"); // 设置错误信息
            request.getRequestDispatcher("login.jsp").forward(request, response); // 转发到登录页面
        }


    }
}
