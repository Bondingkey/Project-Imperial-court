package com.gzc.frist.mavenpj.servlet.module;

import com.gzc.frist.mavenpj.entity.Memorials;
import com.gzc.frist.mavenpj.service.api.MemorialsService;
import com.gzc.frist.mavenpj.service.impl.MemorialsServiceImpl;
import com.gzc.frist.mavenpj.servlet.base.ModelBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author: 拿破仑
 * @Date&Time: 2023/04/08  15:47  周六
 * @Project: MavenProjectOne
 * @Write software: IntelliJ IDEA
 * @Purpose: 在此处编辑
 */
@WebServlet("/work")
public class WorkServlet extends ModelBaseServlet {

    private MemorialsServiceImpl memorialsService = new MemorialsServiceImpl();

    protected void showMemorialsDigestList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用Service里的方法去查询数据，并放到集合中
        List<Memorials> memorialsList = memorialsService.getAllMemorialsDigest();
        //创建Session域保存数据
        request.setAttribute("memorialsList",memorialsList);
        //页面重定向
        String workPageName = "memorials-list";
        processTemplate(workPageName,request,response);
    }


    protected void showMemorialsDetail
            (HttpServletRequest request,
             HttpServletResponse response)
            throws ServletException, IOException {
        //1，获取参数
        String memorialsId = request.getParameter("memorialsId");

        //根据请求ID去调用Service中的方法
        Memorials memorials = memorialsService.getMemorialsDetailById(memorialsId);

        //********补充功能***********

        Integer memorialsStatus = memorials.getMemorialsStatus();
        if (memorialsStatus==0){
            //往数据库中修改
            memorialsService.updateMemorialsStatusToRead(memorialsId);
            //往页面修改
            memorials.setMemorialsStatus(1);
        }
        //********补充功能***********

        //将查询到的结果保存到作用域
        request.setAttribute("memorials",memorials);

        //试图渲染
        String templateName = "memorials-detail";
        processTemplate(templateName,request,response);
    }

    protected void feedBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求参数
        String memorialsId = request.getParameter("memorialsId");
        String feedbackContent = request.getParameter("feedbackContent");

        //调用Service层的方法修改
        memorialsService.updateMemorialsFeedBack(memorialsId,feedbackContent);

        //页面重定向
        response.sendRedirect(request.getContextPath() + "/work?method=showMemorialsDigestList");
    }
}
