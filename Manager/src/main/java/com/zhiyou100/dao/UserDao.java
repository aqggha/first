package com.zhiyou100.dao;

import com.zhiyou100.annotation.Bean;
import com.zhiyou100.model.User;
import com.zhiyou100.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Bean
public class UserDao {
    public User findUserByNameAndPassword(String name, String password) throws SQLException {
        Connection conn = DBUtil.getConnection();
        String sql="select * from t_user where name=? and password=?";
        List<User> users = DBUtil.queryByConditions(conn, sql, User.class, name, password);
        if(users.size()>0){
            return users.get(0);
        }else{
            return null;
        }
    }
}
