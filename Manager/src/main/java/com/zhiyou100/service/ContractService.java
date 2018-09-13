package com.zhiyou100.service;

import com.zhiyou100.annotation.Bean;
import com.zhiyou100.annotation.Require;
import com.zhiyou100.dao.ContractDao;
import com.zhiyou100.model.Contract;

import java.sql.SQLException;
import java.util.List;

@Bean
public class ContractService {
    @Require
    ContractDao contractDao;
    public Contract findById(int id) throws SQLException {
        Contract contract = contractDao.findById(id);
        return contract;
    }

    public List<Contract> findByPage(int pageNum) throws SQLException {
        return contractDao.findByPage(pageNum);
    }
    public int insert(Contract contract) throws SQLException, IllegalAccessException {
        return contractDao.insert(contract);
    }

    public int update(Contract contract) throws SQLException, IllegalAccessException {
        return contractDao.update(contract);
    }
    public  int delete(Integer id) throws SQLException {
        return contractDao.delete(id);
    }
}
