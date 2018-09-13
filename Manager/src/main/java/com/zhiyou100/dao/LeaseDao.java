/*
package com.zhiyou100.dao;

import com.zhiyou100.annotation.Bean;
import com.zhiyou100.model.Lease;
import com.zhiyou100.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
@Bean
public class LeaseDao {
    public Lease findById(int id) throws SQLException {
        //获取连接
        Connection connection = DBUtil.getConnection();
        String sql="select id,area,community,unit_num unitNum,floor,room_num roomNum,space,direction" +
                ",fitment,is_double_air isDoubleAir,`limit`,facility,price,status,image,address," +
                "create_time createTime,last_modified_time lastModifiedTime,remarks from t_lease where id=?";
        Lease lease = DBUtil.queryById(connection, sql, Lease.class, id);
        DBUtil.close();
        return lease;
    }

    public List<Lease> findByPage(int pageNum) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String sql="select id,area,community,unit_num unitNum,floor,room_num roomNum,space,direction" +
                ",fitment,is_double_air isDoubleAir,`limit`,facility,price,status,image,address," +
                "create_time createTime,last_modified_time lastModifiedTime,remarks from t_lease limit ?,?";

        int start=5*pageNum-5;
        int end=5*pageNum;
        List<Lease> leases = DBUtil.queryByConditions(connection, sql, Lease.class, start, end);
        DBUtil.close();
        return leases;

    }
}
*/
package com.zhiyou100.dao;

import com.zhiyou100.annotation.Bean;
import com.zhiyou100.model.Lease;
import com.zhiyou100.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Bean
public class LeaseDao {
    public Lease findById(int id) throws SQLException {
        //获取连接
        Connection connection = DBUtil.getConnection();
        String sql="select id,house_id houseId, renter_id renterId,amount,pay_time payTime," +
               "create_time createTime,last_modified_time lastModifiedTime,remark from t_lease where id=?";
        Lease lease = DBUtil.queryById(connection, sql, Lease.class, id);
        DBUtil.close();
        return lease;
    }
    public List<Lease> findByPage(int pageNum) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String sql="select id,house_id houseId,renter_id renterId,amount,pay_time payTime," +
                "create_time createTime,last_modified_time lastModifiedTime,remark from t_lease limit ?,?";
        int start=20*pageNum-20;
        int end=20;
        List<Lease> leases = DBUtil.queryByConditions(connection, sql, Lease.class, start, end);
        DBUtil.close();
        return leases;

    }
    public int insert(Lease lease) throws SQLException, IllegalAccessException {
        Connection connection = DBUtil.getConnection();
        //String sql="insert into t_lease values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        int rows=DBUtil.insert(connection,"t_lease",lease);
        DBUtil.close();
        return rows;
    }
    public int update(Lease lease) throws SQLException, IllegalAccessException {
       // String sql="update t_lease set k1=v1,k2=v2,k3=v3 where id={?}";
        //return 1;
        Connection connection = DBUtil.getConnection();
        int rows = DBUtil.updateByObject(connection, "t_lease", lease);
        DBUtil.close();
        return rows;
    }
    public int delete(Integer id) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String sql="delete from t_lease where id=?";
        int rows=DBUtil.delete(connection,sql,id);
        DBUtil.close();
        return rows;
    }
}
