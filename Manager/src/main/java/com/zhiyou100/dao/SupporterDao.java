/*
package com.zhiyou100.dao;

import com.zhiyou100.annotation.Bean;
import com.zhiyou100.model.Supporter;
import com.zhiyou100.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
@Bean
public class SupporterDao {
    public Supporter findById(int id) throws SQLException {
        //获取连接
        Connection connection = DBUtil.getConnection();
        String sql="select id,area,community,unit_num unitNum,floor,room_num roomNum,space,direction" +
                ",fitment,is_double_air isDoubleAir,`limit`,facility,price,status,image,address," +
                "create_time createTime,last_modified_time lastModifiedTime,remarks from t_supporter where id=?";
        Supporter supporter = DBUtil.queryById(connection, sql, Supporter.class, id);
        DBUtil.close();
        return supporter;
    }

    public List<Supporter> findByPage(int pageNum) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String sql="select id,area,community,unit_num unitNum,floor,room_num roomNum,space,direction" +
                ",fitment,is_double_air isDoubleAir,`limit`,facility,price,status,image,address," +
                "create_time createTime,last_modified_time lastModifiedTime,remarks from t_supporter limit ?,?";

        int start=5*pageNum-5;
        int end=5*pageNum;
        List<Supporter> supporters = DBUtil.queryByConditions(connection, sql, Supporter.class, start, end);
        DBUtil.close();
        return supporters;

    }
}
*/
package com.zhiyou100.dao;

import com.zhiyou100.annotation.Bean;
import com.zhiyou100.model.Supporter;
import com.zhiyou100.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Bean
public class SupporterDao {
        public Supporter findById(int id) throws SQLException {
            Connection connection = DBUtil.getConnection();
            String sql="select id,name,id_number idNumber,phone_number phoneNumber,sex," +
                    "socket_job socketJob,add_time addTime,salary,native_place nativePlace from t_supporter where id=? ";
            Supporter supporter = DBUtil.queryById(connection, sql, Supporter.class, id);
            DBUtil.close();
            return supporter;
}
        public List<Supporter> findByPage(int pageNum) throws SQLException {
            Connection connection = DBUtil.getConnection();
            String sql="select id,name,id_number idNumber,phone_number phoneNumber,sex," +
                    "socket_job socketJob,add_time addTime,salary,native_place nativePlace from t_supporter limit ?,?";
            int start=5*pageNum-5;
            int size=5;
            List<Supporter> supporters = DBUtil.queryByConditions(connection, sql, Supporter.class, start, size);
            DBUtil.close();
            return  supporters;
        }
        public int insert(Supporter supporter) throws SQLException, IllegalAccessException {
            Connection connection = DBUtil.getConnection();
            int rows = DBUtil.insert(connection, "t_supporter", supporter);
            DBUtil.close();
            return rows;
        }
    public int delete(Integer id) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String sql = "delete from t_supporter where id=?";
        int rows = DBUtil.delete(connection, sql, id);
        return rows;
    }
        public int update(Supporter supporter) throws SQLException, IllegalAccessException {
            Connection connection = DBUtil.getConnection();
            int rows = DBUtil.updateByObject(connection, "t_supporter", supporter);
            DBUtil.close();
            return rows;
        }


}









   /* public Supporter findById(int id) throws SQLException {
        //获取连接
        Connection connection = DBUtil.getConnection();
        String sql="select id,supporter_number supporterNumber,house_info houseInfo,renter_info renterInfo," +
                "supporter_time supporterTime,start_time startTime,end_time endTime,price,pay_method payMethod," +
                "deposit,pay_periods payPeriods,people,status  from t_supporter where id=?";

        Supporter supporter = DBUtil.queryById(connection, sql, Supporter.class, id);
        DBUtil.close();
        return supporter;
    }*/
   /* public List<Supporter> findByPage(int pageNum) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String sql="select id,supporter_number supporterNumber,house_info houseInfo,renter_info renterInfo," +
                "supporter_time supporterTime,start_time startTime,end_time endTime,price,pay_method payMethod," +
                "deposit,pay_periods payPeriods,people,status from t_supporter limit ?,?";
        int start=5*pageNum-5;
        int end=5;
        List<Supporter> supporters = DBUtil.queryByConditions(connection, sql, Supporter.class, start, end);
        DBUtil.close();
        return supporters;

    }*/
   /* public int insert(Supporter supporter) throws SQLException, IllegalAccessException {
        Connection connection = DBUtil.getConnection();
        //String sql="insert into t_supporter values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        int rows=DBUtil.insert(connection,"t_supporter",supporter);
        DBUtil.close();
        return rows;
    }*/
   /* public int update(Supporter supporter) throws SQLException, IllegalAccessException {
       // String sql="update t_supporter set k1=v1,k2=v2,k3=v3 where id={?}";
        //return 1;
        Connection connection = DBUtil.getConnection();
        int rows = DBUtil.updateByObject(connection, "t_supporter", supporter);
        DBUtil.close();
        return rows;
    }*/




/*        Connection connection = DBUtil.getConnection();
        String sql="delete from t_supporter where id=?";
        int rows=DBUtil.delete(connection,sql,id);
        DBUtil.close();
        return rows;*/


