package com.gzc.frist.mavenpj;

import com.gzc.frist.mavenpj.dao.BaseDao;
import com.gzc.frist.mavenpj.entity.Emp;
import com.gzc.frist.mavenpj.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

/**
 * @author: 拿破仑
 * @Date&Time: 2023/04/05  09:52  周三
 * @Project: MavenProjectOne
 * @Write software: IntelliJ IDEA
 * @Purpose: 在此处编辑
 */
public class ImperialCourtTest {
    BaseDao<Emp> baseDao = new BaseDao();

    @Test
    public void test2(){
        String sql = "select emp_id empId,emp_name empName,emp_position empPosition,login_account loginAccount,login_password loginPassword from t_emp where emp_id=?";
        Emp singleBean = baseDao.getSingleBean(sql, Emp.class, 1);
        System.out.println(singleBean);
    }

    @Test
    public void test3(){
        String sql = "select emp_id empId,emp_name empName,emp_position empPosition,login_account loginAccount,login_password loginPassword from t_emp";
        List<Emp> beanList = baseDao.getBeanList(sql, Emp.class);
        beanList.forEach(System.out::println);
    }

    @Test
    public void test4(){
        String sql="update t_emp set emp_position=? where emp_id=?";
        int kk = baseDao.updata(sql, "kk", 3);
        System.out.println(kk);
    }

    @Test
    public void test1(){
        Connection connection = JDBCUtils.getConnection();
        System.out.println(connection);
        JDBCUtils.releaseConnection(connection);
    }

    @Test
    public void test5(){
        String sub = "aaa.png".substring("aaa.png".lastIndexOf("."));
        System.out.println(sub);
    }

}
