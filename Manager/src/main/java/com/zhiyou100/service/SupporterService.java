package com.zhiyou100.service;

import com.zhiyou100.annotation.Bean;
import com.zhiyou100.annotation.Require;
import com.zhiyou100.dao.SupporterDao;
import com.zhiyou100.model.Supporter;

import java.sql.SQLException;
import java.util.List;

@Bean
public class SupporterService {
    @Require
    SupporterDao supporterDao;
    public Supporter findById(int id) throws SQLException {
        Supporter supporter = supporterDao.findById(id);
        return supporter;
    }

    public List<Supporter> findByPage(int pageNum) throws SQLException {
        return supporterDao.findByPage(pageNum);
    }
    public int insert(Supporter supporter) throws SQLException, IllegalAccessException {
        return supporterDao.insert(supporter);
    }

    public int update(Supporter supporter) throws SQLException, IllegalAccessException {
        return supporterDao.update(supporter);
    }
    public  int delete(Integer id) throws SQLException {
        return supporterDao.delete(id);
    }
}
