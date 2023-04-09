package com.gzc.frist.mavenpj.dao.impl;

import com.gzc.frist.mavenpj.dao.BaseDao;
import com.gzc.frist.mavenpj.dao.api.EmpDao;
import com.gzc.frist.mavenpj.entity.Emp;

/**
 * @author: 拿破仑
 * @Date&Time: 2023/04/06  08:28  周四
 * @Project: MavenProjectOne
 * @Write software: IntelliJ IDEA
 * @Purpose: 在此处编辑
 */
public class EmpDaoImpl extends BaseDao<Emp> implements EmpDao {

    public Emp selectEmpLoginAccount(String loginAccount, String encode) {

        String sql = "select emp_id empId," +
                "emp_name empName," +
                "emp_position empPosition," +
                "login_account loginAccount," +
                "login_password loginPassword " +
                "from t_emp where login_account=? and login_password=?";

        return super.getSingleBean(sql,Emp.class,loginAccount,encode);
    }
}
