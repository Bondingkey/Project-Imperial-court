package com.gzc.frist.mavenpj.dao;

import com.gzc.frist.mavenpj.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author: 拿破仑
 * @Date&Time: 2023/04/05  10:01  周三
 * @Project: MavenProjectOne
 * @Write software: IntelliJ IDEA
 * @Purpose: 在此处编辑
 */
public class BaseDao<T> {
    private QueryRunner queryRunner = new QueryRunner();

    /**
     * 查询单个对象
     * sql，查询对象类型，sql参数
     */
    public T getSingleBean(String sql, Class<T> entityClass, Object... parameters) {

        try {
            Connection connection = JDBCUtils.getConnection();
            BeanHandler<T> beanHandler = new BeanHandler<>(entityClass);
            return queryRunner.query(connection, sql, beanHandler, parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 增删改通用操作
     * sql,sql参数
     */
    public int updata(String sql,Object ...paraeters){

        try {
            Connection connection = JDBCUtils.getConnection();
            return queryRunner.update(connection, sql, paraeters);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 增删改通用操作
     * sql，查询对象类型，sql参数
     */
    public List<T> getBeanList(String sql, Class<T> entityClass, Object... parameters){
        try {
            Connection connection = JDBCUtils.getConnection();
            BeanListHandler<T> beanListHandler = new BeanListHandler<>(entityClass);
            return queryRunner.query(connection, sql, beanListHandler, parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
