/*
package com.zhiyou100.dao;

import com.zhiyou100.annotation.Bean;
import com.zhiyou100.model.House;
import com.zhiyou100.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
@Bean
public class HouseDao {
    public House findById(int id) throws SQLException {
        //获取连接
        Connection connection = DBUtil.getConnection();
        String sql="select id,area,community,unit_num unitNum,floor,room_num roomNum,space,direction" +
                ",fitment,is_double_air isDoubleAir,`limit`,facility,price,status,image,address," +
                "create_time createTime,last_modified_time lastModifiedTime,remarks from t_house where id=?";
        House house = DBUtil.queryById(connection, sql, House.class, id);
        DBUtil.close();
        return house;
    }

    public List<House> findByPage(int pageNum) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String sql="select id,area,community,unit_num unitNum,floor,room_num roomNum,space,direction" +
                ",fitment,is_double_air isDoubleAir,`limit`,facility,price,status,image,address," +
                "create_time createTime,last_modified_time lastModifiedTime,remarks from t_house limit ?,?";

        int start=5*pageNum-5;
        int end=5*pageNum;
        List<House> houses = DBUtil.queryByConditions(connection, sql, House.class, start, end);
        DBUtil.close();
        return houses;

    }
}
*/
package com.zhiyou100.dao;

import com.zhiyou100.annotation.Bean;
import com.zhiyou100.model.House;
import com.zhiyou100.util.BeanUtil;
import com.zhiyou100.util.DBUtil;
import sun.awt.geom.AreaOp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Bean
public class HouseDao{
    public House findById(int id) throws SQLException {
        //获取连接
        Connection connection = DBUtil.getConnection();
        String sql="select id,area,community,unit_num unitNum,floor,room_num roomNum,space,direction" +
                ",fitment,is_double_air isDoubleAir,`limit`,facility,price,status,image,address," +
                "create_time createTime,last_modified_time lastModifiedTime,remark from t_house where id=?";
        House house = DBUtil.queryById(connection, sql, House.class, id);
        DBUtil.close();
        return house;
    }
    public List<House> findByPage(int pageNum) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String sql="select id,area,community,unit_num unitNum,floor,room_num roomNum,space,direction" +
                ",fitment,is_double_air isDoubleAir,`limit`,facility,price,status,image,address," +
                "create_time createTime,last_modified_time lastModifiedTime,remark from t_house limit ?,?";
        int start=5*pageNum-5;
        int end=5;
        List<House> houses = DBUtil.queryByConditions(connection, sql, House.class, start, end);
        DBUtil.close();
        return houses;

    }
    public int insert(House house) throws SQLException, IllegalAccessException {
        Connection connection = DBUtil.getConnection();
        //String sql="insert into t_house values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        int rows=DBUtil.insert(connection,"t_house",house);
        DBUtil.close();
        return rows;
    }
    public int update(House house) throws SQLException, IllegalAccessException {
       // String sql="update t_house set k1=v1,k2=v2,k3=v3 where id={?}";
        //return 1;
        Connection connection = DBUtil.getConnection();
        int rows = DBUtil.updateByObject(connection, "t_house", house);
        DBUtil.close();
        return rows;
    }
    public int delete(Integer id) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String sql="delete from t_house where id=?";
        int rows=DBUtil.delete(connection,sql,id);
        DBUtil.close();
        return rows;
    }
}
