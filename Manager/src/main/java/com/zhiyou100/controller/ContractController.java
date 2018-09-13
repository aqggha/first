package com.zhiyou100.controller;

import com.google.gson.Gson;

import com.zhiyou100.annotation.Bean;
import com.zhiyou100.annotation.RequestMapping;
import com.zhiyou100.annotation.Require;
import com.zhiyou100.model.Contract;
import com.zhiyou100.service.ContractService;
import com.zhiyou100.util.BeanUtil;
import com.zhiyou100.util.ResponseUtil;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Bean
@RequestMapping("/contract")
public class ContractController {
    private static final Logger log = Logger.getLogger(ContractController.class);
    @Require
    ContractService contractService;

    @RequestMapping("/list.do")
    public void list(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String page = req.getParameter("page");
        int pageNum = 1;
        if (!StringUtils.isBlank(page)) {
            pageNum = Integer.parseInt(page);
        }
        try {
            List<Contract> contractList = contractService.findByPage(pageNum);
            System.out.println(contractList);
            String json = new Gson().toJson(contractList);
            ResponseUtil.responseSuccess(resp, json, 200);

        } catch (SQLException e) {
            //e.printStackTrace();
            ResponseUtil.responseFailure(resp, "service error", 100);
        }
    }

    /*    @RequestMapping("/get.do")
        public void get(HttpServletRequest req,HttpServletResponse resp) throws IOException {
            String stringId = req.getParameter("id");

            if(StringUtils.isBlank(stringId)){
                ResponseUtil.responseFailure(resp,"id is not set ",100);
                return;
            }
            Integer id = Integer.valueOf(stringId);
            try{
                Contract contract = contractService.findById(id);
                ResponseUtil.responseSuccess(resp,new Gson().toJson(contract),200);
            } catch (SQLException e) {
                log.error("error",e);
                ResponseUtil.responseFailure(resp,"server error ",100);
            }
        }*/
    @RequestMapping("/get.do")
    public void get(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String stringId = req.getParameter("id");
        if (StringUtils.isBlank(stringId)) {
            ResponseUtil.responseFailure(resp, "id is not set", 100);
            return;
        }
        Integer id = Integer.valueOf(stringId);
        try {
            Contract contract = contractService.findById(id);
            System.out.println(contract);
            ResponseUtil.responseSuccess(resp, new Gson().toJson(contract), 200);
        } catch (SQLException e) {
            log.error("error", e);
            ResponseUtil.responseFailure(resp, "server error", 100);
        }

    }


    @RequestMapping("/delete.do")
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String stringId = req.getParameter("id");
        if (StringUtils.isBlank(stringId)) {
            ResponseUtil.responseFailure(resp, "id is not set", 100);
            return;
        }
        Integer id = Integer.valueOf(stringId);

        try {
            int rows = contractService.delete(id);
            if (rows == 1) {
                ResponseUtil.responseSuccess(resp, "delete success", 200);
            } else {
                ResponseUtil.responseFailure(resp, "delete failure", 100);
            }

        } catch (SQLException e) {
            log.error("error", e);
            ResponseUtil.responseFailure(resp, "server error ", 100);
        }

    }

    @RequestMapping("/insert.do")
    public void insert(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //Contract contract = null;
        try {
            Contract contract = BeanUtil.parseFromRequest(req, Contract.class);
            //设置时间
            contract.setContractTime(new Date());
            contract.setStartTime(new Date());
            contract.setEndTime(new Date());

            int rows = contractService.insert(contract);
            if (rows == 1) {
                ResponseUtil.responseSuccess(resp, "insert success", 200);
            } else {
                ResponseUtil.responseFailure(resp, "insert error", 100);
            }

        } catch (Exception e) {
            log.error("error", e);
            ResponseUtil.responseFailure(resp, "server error ", 100);
        }
    }

    @RequestMapping("/update.do")
    public void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            Contract contract = BeanUtil.parseFromRequest(req, Contract.class);
            contract.setStartTime(new Date());
            contract.setEndTime(new Date());
            contract.setContractTime(new Date());
            int rows = contractService.update(contract);
            System.out.println(contract);
            if (rows == 1) {
                ResponseUtil.responseSuccess(resp, "update success", 200);
            } else {
                ResponseUtil.responseFailure(resp, "update failure", 100);
            }
        } catch (Exception e) {
            log.error("error", e);
            ResponseUtil.responseFailure(resp, "server error", 100);
        }

    }

}



/*

import com.google.gson.Gson;
import com.zhiyou100.annotation.Bean;
import com.zhiyou100.annotation.RequestMapping;
import com.zhiyou100.annotation.Require;
import com.zhiyou100.model.Contract;
import com.zhiyou100.service.ContractService;
import com.zhiyou100.util.ResponseUtil;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@Bean
@RequestMapping("/contract")
public class ContractController {
    @Require
    ContractService contractService;
    @RequestMapping("/list.do")
    public void list(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String page = req.getParameter("page");
        int pageNum=1;
        if(!StringUtils.isBlank(page)){
            pageNum=Integer.parseInt(page);
        }
        try {
            List<Contract> contractList = contractService.findByPage(pageNum);
            String json = new Gson().toJson(contractList);
            ResponseUtil.responseSuccess(resp,json,200);
        } catch (Exception e) {
           // e.printStackTrace();
            ResponseUtil.responseFailure(resp,"server error",100);
        }
    }
}
*/
