package com.gzc.frist.mavenpj.entity;

/**
 * @author: 拿破仑
 * @Date&Time: 2023/04/05  07:37  周三
 * @Project: MavenProjectOne
 * @Write software: IntelliJ IDEA
 * @Purpose: 在此处编辑
 */
public class Emp {

    private Integer empId;
    private String empName;
    private String empPosition;
    private String loginAccount;
    private String loginPassword;

    @Override
    public String toString() {
        return "emp{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", empPosition='" + empPosition + '\'' +
                ", loginAccount='" + loginAccount + '\'' +
                ", loginPassword='" + loginPassword + '\'' +
                '}';
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpPosition() {
        return empPosition;
    }

    public void setEmpPosition(String empPosition) {
        this.empPosition = empPosition;
    }

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public Emp(Integer empId, String empName, String empPosition, String loginAccount, String loginPassword) {
        this.empId = empId;
        this.empName = empName;
        this.empPosition = empPosition;
        this.loginAccount = loginAccount;
        this.loginPassword = loginPassword;
    }

    public Emp() {
    }
}
