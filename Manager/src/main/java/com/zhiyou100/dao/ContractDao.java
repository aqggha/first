/*
package com.zhiyou100.dao;

import com.zhiyou100.annotation.Bean;
import com.zhiyou100.model.Contract;
import com.zhiyou100.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
@Bean
public class ContractDao {
    public Contract findById(int id) throws SQLException {
        //获取连接
        Connection connection = DBUtil.getConnection();
        String sql="select id,area,community,unit_num unitNum,floor,room_num roomNum,space,direction" +
                ",fitment,is_double_air isDoubleAir,`limit`,facility,price,status,image,address," +
                "create_time createTime,last_modified_time lastModifiedTime,remarks from t_contract where id=?";
        Contract contract = DBUtil.queryById(connection, sql, Contract.class, id);
        DBUtil.close();
        return contract;
    }

    public List<Contract> findByPage(int pageNum) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String sql="select id,area,community,unit_num unitNum,floor,room_num roomNum,space,direction" +
                ",fitment,is_double_air isDoubleAir,`limit`,facility,price,status,image,address," +
                "create_time createTime,last_modified_time lastModifiedTime,remarks from t_contract limit ?,?";

        int start=5*pageNum-5;
        int end=5*pageNum;
        List<Contract> contracts = DBUtil.queryByConditions(connection, sql, Contract.class, start, end);
        DBUtil.close();
        return contracts;

    }
}
*/
package com.zhiyou100.dao;

import com.zhiyou100.annotation.Bean;
import com.zhiyou100.model.Contract;
import com.zhiyou100.util.DBUtil;
import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Bean
public class ContractDao {
        public Contract findById(int id) throws SQLException {
            Connection connection = DBUtil.getConnection();
            String sql="select id,contract_number contractNumber,house_info houseInfo,renter_info renterInfo," +
                    "contract_time contractTime,start_time startTime,end_time endTime,price,pay_method payMethod," +
                    "deposit,pay_periods payPeriods,people,status from t_contract where id=?";
            Contract contract = DBUtil.queryById(connection, sql, Contract.class, id);
            DBUtil.close();
            return contract;
}
        public List<Contract> findByPage(int pageNum) throws SQLException {
            Connection connection = DBUtil.getConnection();
            String sql="select id,contract_number contractNumber,house_info houseInfo,renter_info renterInfo," +
                    "contract_time contractTime,start_time startTime,end_time endTime,price,pay_method payMethod," +
                    "deposit,pay_periods payPeriods,people,status from t_contract limit ?,?";
            int start=5*pageNum-5;
            int size=5;
            List<Contract> contracts = DBUtil.queryByConditions(connection, sql, Contract.class, start, size);
            DBUtil.close();
            return  contracts;
        }
        public int insert(Contract contract) throws SQLException, IllegalAccessException {
            Connection connection = DBUtil.getConnection();
            int rows = DBUtil.insert(connection, "t_contract", contract);
            DBUtil.close();
            return rows;
        }
    public int delete(Integer id) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String sql = "delete from t_contract where id=?";
        int rows = DBUtil.delete(connection, sql, id);
        return rows;
    }
        public int update(Contract contract) throws SQLException, IllegalAccessException {
            Connection connection = DBUtil.getConnection();
            int rows = DBUtil.updateByObject(connection, "t_contract", contract);
            DBUtil.close();
            return rows;
        }


}









   /* public Contract findById(int id) throws SQLException {
        //获取连接
        Connection connection = DBUtil.getConnection();
        String sql="select id,contract_number contractNumber,house_info houseInfo,renter_info renterInfo," +
                "contract_time contractTime,start_time startTime,end_time endTime,price,pay_method payMethod," +
                "deposit,pay_periods payPeriods,people,status  from t_contract where id=?";

        Contract contract = DBUtil.queryById(connection, sql, Contract.class, id);
        DBUtil.close();
        return contract;
    }*/
   /* public List<Contract> findByPage(int pageNum) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String sql="select id,contract_number contractNumber,house_info houseInfo,renter_info renterInfo," +
                "contract_time contractTime,start_time startTime,end_time endTime,price,pay_method payMethod," +
                "deposit,pay_periods payPeriods,people,status from t_contract limit ?,?";
        int start=5*pageNum-5;
        int end=5;
        List<Contract> contracts = DBUtil.queryByConditions(connection, sql, Contract.class, start, end);
        DBUtil.close();
        return contracts;

    }*/
   /* public int insert(Contract contract) throws SQLException, IllegalAccessException {
        Connection connection = DBUtil.getConnection();
        //String sql="insert into t_contract values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        int rows=DBUtil.insert(connection,"t_contract",contract);
        DBUtil.close();
        return rows;
    }*/
   /* public int update(Contract contract) throws SQLException, IllegalAccessException {
       // String sql="update t_contract set k1=v1,k2=v2,k3=v3 where id={?}";
        //return 1;
        Connection connection = DBUtil.getConnection();
        int rows = DBUtil.updateByObject(connection, "t_contract", contract);
        DBUtil.close();
        return rows;
    }*/




/*        Connection connection = DBUtil.getConnection();
        String sql="delete from t_contract where id=?";
        int rows=DBUtil.delete(connection,sql,id);
        DBUtil.close();
        return rows;*/


