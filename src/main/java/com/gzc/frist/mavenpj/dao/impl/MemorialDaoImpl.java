package com.gzc.frist.mavenpj.dao.impl;

import com.gzc.frist.mavenpj.dao.BaseDao;
import com.gzc.frist.mavenpj.dao.api.MemorialsDao;
import com.gzc.frist.mavenpj.entity.Memorials;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author: 拿破仑
 * @Date&Time: 2023/04/06  08:28  周四
 * @Project: MavenProjectOne
 * @Write software: IntelliJ IDEA
 * @Purpose: 在此处编辑
 */
public class MemorialDaoImpl extends BaseDao<Memorials> implements MemorialsDao {
    @Override
    public List<Memorials> selectAllMemorialDigest() {

        String sql ="select memorials_id memorialsId,\n" +
                "       memorials_title memorialsTitle,\n" +
                "       concat(left(memorials_content, 10),\"...\") memorialsContentDigest,\n" +
                "       emp_name memorialsEmpName,\n" +
                "       memorials_create_time memorialsCreateTime,\n" +
                "       memorials_status memorialsStatus\n" +
                "from t_memorials m left join  t_emp e on m.memorials_emp=e.emp_id;";

        return getBeanList(sql,Memorials.class);
    }

    @Override
    public Memorials selectMemorialDetailById(String memorialsId) {
        String sql = "select memorials_id memorialsId,\n" +
                "       memorials_title memorialsTitle,\n" +
                "       memorials_content memorialsContent,\n" +
                "       emp_name memorialsEmpName,\n" +
                "       memorials_create_time memorialsCreateTime,\n" +
                "       memorials_status memorialsStatus,\n" +
                "       feedback_time feedbackTime,\n" +
                "       feedback_content feedbackContent\n" +
                "from t_memorials m left join  t_emp e on m.memorials_emp=e.emp_id\n" +
                "where memorials_id=?;";
        return getSingleBean(sql,Memorials.class,memorialsId);
    }

    @Override
    public void updateMemorialsStatusToRead(String memorialsId) {
        String sql = "update t_memorials set memorials_status=1 where memorials_id=?";
        updata(sql,memorialsId);
    }

    @Override
    public void updateMemorialsFeedBack(String memorialsId, String feedbackContent) {
        //获取修改时间
        Date date = new Date();
        String formatTime = new SimpleDateFormat("yyyy-MM-dd").format(date);

        //编写sql语句
        String sql = "update t_memorials set memorials_status=2,feedback_content=?,feedback_time=? where memorials_id=?";

        //执行
        updata(sql,feedbackContent,formatTime,memorialsId);
    }
}
