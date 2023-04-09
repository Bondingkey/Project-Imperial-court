package com.gzc.frist.mavenpj.servlet.module;

import com.gzc.frist.mavenpj.servlet.base.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: 拿破仑
 * @Date&Time: 2023/04/06  19:20  周四
 * @Project: MavenProjectOne
 * @Write software: IntelliJ IDEA
 * @Purpose: 在此处编辑
 */
@WebServlet("/")
public class PortalServlet extends ViewBaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //你要渲染的页面的逻辑视图
        String name = "index";
        //渲染页面
        processTemplate(name,req,resp);
    }
}
