package com.gzc.frist.mavenpj.dao.api;

import com.gzc.frist.mavenpj.entity.Emp;

public interface EmpDao {

    public Emp selectEmpLoginAccount(String loginAccount, String encode);
}
