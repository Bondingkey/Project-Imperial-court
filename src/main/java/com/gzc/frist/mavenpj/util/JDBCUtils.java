package com.gzc.frist.mavenpj.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author: 拿破仑
 * @Date&Time: 2023/04/05  07:54  周三
 * @Project: MavenProjectOne
 * @Write software: IntelliJ IDEA
 * @Purpose: 在此处编辑
 */
public class JDBCUtils {

    //工具类中的方法最好都是静态的，静态方法只能访问静态变量，且能保证DataSource的单例性，所以用static修饰
    static DataSource dataSource;

    // 由于 ThreadLocal 对象需要作为绑定数据时 k-v 对中的 key，所以要保证唯一性
    // 加 static 声明为静态资源即可保证唯一性
    //此变量的声明主要是为了将从Filter中获取的数据库链接转递到Dao层调用，保证所有Dao中用的都是同一个数据库链接，方便事务管理
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    //在静态代码块中去初始化加载jdbc的配置文件
    static {
        try {
            //获取当前类加载器，用类加载器去读取配置文件并创建数据源
            InputStream resourceAsStream = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //用Properties对象封装配置文件
            Properties properties = new Properties();
            properties.load(resourceAsStream);
            //使用德鲁伊工厂模式创建DataSource对象
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取数据库链接
    public static  Connection getConnection(){
        Connection connection=null;
        try {
            //先从前面传过来的本地线程中获取数据库链接
             connection = threadLocal.get();

            //判断本地线程中有没有数据库链接，没有则获取一个新的数据库链接
            if (connection  == null) {

                connection = dataSource.getConnection();
                //将拿到的新链接放到本地线程供其他DAO使用，保证所有DAO用的同一个链接
                threadLocal.set(connection);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //如果本地线程有则返回本地线程中的链接
        return  connection;
    }

    public static void releaseConnection(Connection connection){

        //判断当前数据库链接是否存在，存在则关闭
        if (connection!=null){

            try {
                connection.close();
                //顺便将本地线程中的链接移除
                threadLocal.remove();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }



    }



}
