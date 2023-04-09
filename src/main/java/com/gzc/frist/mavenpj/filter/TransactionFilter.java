package com.gzc.frist.mavenpj.filter;

import com.gzc.frist.mavenpj.util.JDBCUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: 拿破仑
 * @Date&Time: 2023/04/06  15:59  周四
 * @Project: MavenProjectOne
 * @Write software: IntelliJ IDEA
 * @Purpose: 在此处编辑
 */
public class TransactionFilter implements Filter {

    private static Set<String> staticResourceExtNameSet;

    static {
        staticResourceExtNameSet=new HashSet<>();
        staticResourceExtNameSet.add(".png");
        staticResourceExtNameSet.add(".jpg");
        staticResourceExtNameSet.add(".css");
        staticResourceExtNameSet.add(".js");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //排除静态资源
        HttpServletRequest request  = (HttpServletRequest) servletRequest;
        String servletPath = request.getServletPath();

        if (servletPath.contains(".")) {
            String extName = servletPath.substring(servletPath.lastIndexOf("."));

            if (staticResourceExtNameSet.contains(extName)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }

        Connection connection=null;

        try {
            //获取链接
            connection = JDBCUtils.getConnection();
            //关闭自动提交
            connection.setAutoCommit(false);
            //放行后执行核心操作
            filterChain.doFilter(servletRequest,servletResponse);
            //提交事务
            connection.commit();
        }catch (Exception e){
            //回滚事务
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            //页面显示:将这里捕获到的异常发送给指定页面
            String message = e.getMessage();
            request.setAttribute("systemMessage",message);
            request.getRequestDispatcher("/").forward(request ,servletResponse);

        }finally {
            //释放资源
            JDBCUtils.releaseConnection(connection);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    @Override
    public void destroy() {
    }
}
