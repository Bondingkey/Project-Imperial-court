package com.gzc.frist.mavenpj.servlet.module;

import com.gzc.frist.mavenpj.entity.Emp;
import com.gzc.frist.mavenpj.exception.LoginFailedException;
import com.gzc.frist.mavenpj.service.impl.EmpServiceImpl;
import com.gzc.frist.mavenpj.servlet.base.ModelBaseServlet;
import com.gzc.frist.mavenpj.util.ImperialCourtConst;

import javax.security.auth.login.FailedLoginException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author: 拿破仑
 * @Date&Time: 2023/04/06  20:46  周四
 * @Project: MavenProjectOne
 * @Write software: IntelliJ IDEA
 * @Purpose: 在此处编辑
 */
@WebServlet("/auth")
public class AuthServlet extends ModelBaseServlet {

    private EmpServiceImpl empService = new EmpServiceImpl();

    protected void login(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {
            //1，获取请求参数
            String loginAccount = request.getParameter("loginAccount");
            String loginPassword = request.getParameter("loginPassword");

            //2，调用EmpService的根据名称和密码查用户方法
            Emp emp = empService.getEmpByLoginAccount(loginAccount,loginPassword);

            //3，如果没有抛异常，就把查到的结果存到Session域中
            HttpSession session = request.getSession();
            session.setAttribute(ImperialCourtConst.LOGIN_EMP_ATTR_NAME,emp);

            //4，前往指定的页面试图
//            String processName="memorials-list";
//            processTemplate(processName,request,response);

            response.sendRedirect(request.getContextPath() + "/work?method=showMemorialsDigestList");

        } catch (Exception e) {
            e.printStackTrace();

            //5，如果捕获到异常，将异常信息保存到请求域
            if (e instanceof LoginFailedException){
                request.setAttribute("message",e.getMessage());
                processTemplate("index",request,response);
            }else {
                // 8、如果不是登录异常则封装为运行时异常继续抛出
                throw new RuntimeException(e);
            }
        }
    }

    protected void logout(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        //获取Session对象
        HttpSession httpSession = request.getSession();
        //将Session对话强制停止
        httpSession.invalidate();
        //回到首页
        String templateName="index";
        processTemplate(templateName,request,response);

    }


}