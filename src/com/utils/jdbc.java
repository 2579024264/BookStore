package com.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.util.JdbcUtils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;

public class jdbc {
        private static DruidDataSource dataSource;
        //ThreadLocal对象一般为static  因为保存的数据是一个Connection类型，所以这里的泛型使用Connection
        private static ThreadLocal<Connection>conns=new ThreadLocal<Connection>();
        static{
            try{
                Properties properties=new Properties();
                //读取jdbc.properties属性配置文件
                InputStream inputStream= JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
                //从流中加载数据
                properties.load(inputStream);
                //创建数据库连接池
                dataSource=(DruidDataSource) DruidDataSourceFactory.createDataSource(properties);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    /**
     * 获取数据库连接池中的连接
      * @return
     */
    public static Connection getConnection(){
        Connection conn=conns.get();
        if(conn==null){
            try {
                //若连接为空，则通过数据库连接池创建一个连接
                conn=dataSource.getConnection();
                //将连接保存在ThreadLocal中，供后面的jdbc使用
                conns.set(conn);
                conn.setAutoCommit(false);//设置为手动管理事务
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return conn;
    }

    /**
     * 提交事务，并关闭释放连接
     */
   public static void commitAndClose(){
       Connection connection=conns.get();
       if(connection!=null){//如果不等于null,说明之前使用过连接，操作过数据库
           try {
               connection.commit();//提交事务
           } catch (SQLException e) {
               e.printStackTrace();
           }finally{
               try {
                   connection.close();//关闭连接，释放资源
               } catch (SQLException e) {
                   e.printStackTrace();
               }
           }
       }
       //一定要执行remove操作，否则就会出错。(因为Tomcat服务器底层使用了线程池技术)
       conns.remove();

   }
   public static void rollbackAndClose(){
       Connection connection=conns.get();
       if(connection!=null){//如果不等于null,说明之前使用过连接，操作过数据库
           try {
               connection.rollback();//回滚事务
           } catch (SQLException e) {
               e.printStackTrace();
           }finally{
               try {
                   connection.close();//关闭连接，释放资源
               } catch (SQLException e) {
                   e.printStackTrace();
               }
           }
       }
       //一定要执行remove操作，否则就会出错。(因为Tomcat服务器底层使用了线程池技术)
       conns.remove();

   }

}
