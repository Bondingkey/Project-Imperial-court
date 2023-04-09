package com.gzc.frist.mavenpj.service.api;

import com.gzc.frist.mavenpj.entity.Emp;

/**
 * @author: 拿破仑
 * @Date&Time: 2023/04/06  21:10  周四
 * @Project: MavenProjectOne
 * @Write software: IntelliJ IDEA
 * @Purpose: 在此处编辑
 */
public interface EmpService {

    public Emp getEmpByLoginAccount(String loginAccount, String loginPassword);


}
