package com.gzc.frist.mavenpj.service.impl;

import com.gzc.frist.mavenpj.dao.impl.EmpDaoImpl;
import com.gzc.frist.mavenpj.entity.Emp;
import com.gzc.frist.mavenpj.exception.LoginFailedException;
import com.gzc.frist.mavenpj.service.api.EmpService;
import com.gzc.frist.mavenpj.util.ImperialCourtConst;
import com.gzc.frist.mavenpj.util.MD5Util;

/**
 * @author: 拿破仑
 * @Date&Time: 2023/04/06  21:10  周四
 * @Project: MavenProjectOne
 * @Write software: IntelliJ IDEA
 * @Purpose: 在此处编辑
 */
public class EmpServiceImpl implements EmpService {

    private EmpDaoImpl empDao = new EmpDaoImpl();

    public Emp getEmpByLoginAccount(String loginAccount, String loginPassword) {
        //1,对密码进行加密
        String encode = MD5Util.encode(loginPassword);
        //2，根据加密后的密码和账户去数据库中查找
        Emp emp = empDao.selectEmpLoginAccount(loginAccount,encode);
        //检查查询结果
        if (emp!=null){
            //查询到返回
            return  emp;
        }else {
            //查询不到抛出异常
            throw new LoginFailedException(ImperialCourtConst.LOGIN_FAILED_MESSAGE);
        }
    }
}
