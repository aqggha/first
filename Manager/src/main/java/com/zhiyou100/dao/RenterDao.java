/*
package com.zhiyou100.dao;

import com.zhiyou100.annotation.Bean;
import com.zhiyou100.model.Renter;
import com.zhiyou100.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
@Bean
public class RenterDao {
    public Renter findById(int id) throws SQLException {
        //获取连接
        Connection connection = DBUtil.getConnection();
        String sql="select id,area,community,unit_num unitNum,floor,room_num roomNum,space,direction" +
                ",fitment,is_double_air isDoubleAir,`limit`,facility,price,status,image,address," +
                "create_time createTime,last_modified_time lastModifiedTime,remarks from t_renter where id=?";
        Renter renter = DBUtil.queryById(connection, sql, Renter.class, id);
        DBUtil.close();
        return renter;
    }

    public List<Renter> findByPage(int pageNum) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String sql="select id,area,community,unit_num unitNum,floor,room_num roomNum,space,direction" +
                ",fitment,is_double_air isDoubleAir,`limit`,facility,price,status,image,address," +
                "create_time createTime,last_modified_time lastModifiedTime,remarks from t_renter limit ?,?";

        int start=5*pageNum-5;
        int end=5*pageNum;
        List<Renter> renters = DBUtil.queryByConditions(connection, sql, Renter.class, start, end);
        DBUtil.close();
        return renters;

    }
}
*/
package com.zhiyou100.dao;

import com.zhiyou100.annotation.Bean;
import com.zhiyou100.model.Renter;
import com.zhiyou100.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Bean
public class RenterDao {
    public Renter findById(int id) throws SQLException {
        //获取连接
        Connection connection = DBUtil.getConnection();
        String sql="select id,name,phone_number phoneNumber,sex,native_place nativePlace,id_number idNumber,add_time addTime " +
                "from t_renter where id=?";
        Renter renter = DBUtil.queryById(connection, sql, Renter.class, id);
        DBUtil.close();
        return renter;
    }
    public List<Renter> findByPage(int pageNum) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String sql="select id,name,phone_number phoneNumber,sex,native_place nativePlace,id_number idNumber,add_time addTime " +
                "from t_renter limit ?,?";
        int start=20*pageNum-20;
        int end=20;
        List<Renter> renters = DBUtil.queryByConditions(connection, sql, Renter.class, start, end);
        DBUtil.close();
        return renters;

    }
    public int insert(Renter renter) throws SQLException, IllegalAccessException {
        Connection connection = DBUtil.getConnection();
        //String sql="insert into t_renter values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        int rows=DBUtil.insert(connection,"t_renter",renter);
        DBUtil.close();
        return rows;
    }
    public int update(Renter renter) throws SQLException, IllegalAccessException {
       // String sql="update t_renter set k1=v1,k2=v2,k3=v3 where id={?}";
        //return 1;
        Connection connection = DBUtil.getConnection();
        int rows = DBUtil.updateByObject(connection, "t_renter", renter);
        DBUtil.close();
        return rows;
    }
    public int delete(Integer id) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String sql="delete from t_renter where id=?";
        int rows=DBUtil.delete(connection,sql,id);
        DBUtil.close();
        return rows;
    }
}
