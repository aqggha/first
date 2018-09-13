package com.zhiyou100.util;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//封装了数据库连接池 和dbutils
public class DBUtil {
    //数据库连接池
    public static DataSource pool=new ComboPooledDataSource("c3p0-config.xml");
    //每个线程都拥有的独有的一个容器
    public static ThreadLocal<Connection> threadLocal=new ThreadLocal<>();
    //获取线程拥有的连接,如果有 则直接返回 如果没有则先从数据库连接池获取
    //然后放入线程池
    public static Connection getConnection() throws SQLException{

        //当前线程拥有的threadlocal
        if(threadLocal.get()==null) {
            threadLocal.set(pool.getConnection());
        }
        return threadLocal.get();
    }
    //根据id查询
    public static<T> T queryById(Connection conn, String sql,Class<T> clazz  ,Object id ) throws SQLException {
        //创建QueryRunner对象(查询生成器)准备用它来进行查询
        QueryRunner runner = new QueryRunner();
        T res = runner.query(conn,sql, new BeanHandler<T>(clazz),id);
        return res;

    }

    //根据其他条件查询
    public static <T> List<T> queryByConditions(Connection conn,String sql, Class<T> clazz, Object... conditions) throws SQLException {
        //导入dbutils包 使用这个功能  查询执行者
        QueryRunner runner = new QueryRunner();
        List<T> res = runner.query(conn,sql, new BeanListHandler<T>(clazz), conditions);
        return res;
    }
    //删除操作
    public static int delete(Connection conn,String sql,Object id) throws SQLException {
        QueryRunner runner = new QueryRunner();
        int rows = runner.update(conn, sql, id);
        return rows;
    }
    //更新操作
    public static int updateBysql(Connection conn,String sql,Object ...params) throws SQLException {
        QueryRunner runner = new QueryRunner();
        int rows = runner.update(conn,sql,params);
        return rows;
    }

    //String sql="insert into t_house (id,area....) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,)";
    public static int insert(Connection conn,String tableName,Object object) throws IllegalAccessException, SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into ").append(tableName).append("(");
        Field[] fields = object.getClass().getDeclaredFields();
        Object[] values = new Object[fields.length];
        StringBuilder placeHolder = new StringBuilder();
        for (int i = 0; i < fields.length; i++) {
            String fieldsName = fields[i].getName();
            sql.append("`").append(getDBFieldName(fieldsName)).append("`");
            placeHolder.append("?");
            if (i<fields.length-1){
                sql.append(",");
                placeHolder.append(",");
            }
            fields[i].setAccessible(true);
            values[i]=fields[i].get(object);
        }
        sql.append(") values (").append(placeHolder).append(")");
        return updateBysql(conn,sql.toString(),values);

    }

    private static String getDBFieldName(String fieldName) {
       // return new char[0];
        StringBuilder builder = new StringBuilder();
        char[] chars = fieldName.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (String.valueOf(chars[i]).toUpperCase().equals(String.valueOf(chars[i]))) {
                builder.append("_").append(String.valueOf(chars[i]).toLowerCase());
            } else {
                builder.append(chars[i]);
            }
        }
        return builder.toString();
    }
    public static int updateByObject(Connection connection,String tableName,Object object) throws IllegalAccessException, SQLException {
        //String sql="update tableName set k=?,k2=?,k3=? wher id=?";
        StringBuilder sql = new StringBuilder();
        sql.append("update ").append(tableName).append(" set ");
        Field[] fields = object.getClass().getDeclaredFields();
        //Object[] values = new Object[fields.length];

        ArrayList<Object> values = new ArrayList<>();
        int id=0;
        for (int i = 0; i < fields.length; i++) {
            String fieldName=fields[i].getName();
            fields[i].setAccessible(true);
            Object value = fields[i].get(object);
            //判断是否是id
            if(fieldName.equals("id")){
                id=(int)value;
                continue;
            }
            values.add(value);
            sql.append("`").append(getDBFieldName(fieldName)).append("`=?");
            if(i<fields.length-1){
                sql.append(",");
            }
        }
        sql.append(" where id =?");
        //得到id的值
        values.add(id);
        return updateBysql(connection,sql.toString(),values.toArray());
    }
   /* public static int update(Connection conn,String tableName,Object object) throws IllegalAccessException, SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into ").append(tableName).append( "(");
        //String sql="insert into t_house (id,area....) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,)";
        Field[] fields = object.getClass().getDeclaredFields();
        Object[] values = new Object[fields.length];
        //占位符的拼接
        StringBuilder placeHolder=new StringBuilder();

        for (int i = 0; i < fields.length; i++) {
            String fieldName = fields[i].getName();
            sql.append("`").append(getDBFieldName(fieldName)).append("`");
            placeHolder.append("?");
            if(i<fields.length-1) {
                sql.append(",");
                placeHolder.append(",");
            }
            fields[i].setAccessible(true);
            //获取字段值
            values[i]=fields[i].get(object);
        }
        sql.append(")values(").append(placeHolder).append(")");

        return update(conn, sql.toString(), values);
    }
    private static String getDBFieldName(String fieldName){
        StringBuilder dbFieldName = new StringBuilder();
        char[] chars = fieldName.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            //大写字母
          if (String.valueOf(chars[i]).toUpperCase().equals(String.valueOf(chars[i]))){
              dbFieldName.append("_").append(String.valueOf(chars[i]).toLowerCase());
          }else{
              dbFieldName.append(chars[i]);
          }
        }
        return dbFieldName.toString();
    }*/

    //处理非事务的关闭
    public static void close() throws SQLException {
        if(threadLocal.get()!=null) {
            //首先判断当前连接是否处于事物状态
            if (threadLocal.get().getAutoCommit()) {
                //归还连接
                threadLocal.get().close();
                //清除threadlocal中的连接引用
                threadLocal.remove();
            }
        }
    }
    //处理事务的关闭
    public static void txnClose() throws SQLException {
        if(threadLocal.get()!=null){
            //归还连接
            threadLocal.get().close();
            //清除threadlocal中的连接引用
            threadLocal.remove();
        }
    }
}
