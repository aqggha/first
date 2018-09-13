package com.zhiyou100.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zhiyou100.annotation.Bean;
import com.zhiyou100.annotation.RequestMapping;
import com.zhiyou100.annotation.Require;
import com.zhiyou100.model.Lease;
import com.zhiyou100.service.LeaseService;
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
@RequestMapping("/lease")
public class LeaseController {
    private static final Logger log=Logger.getLogger(LeaseController.class);
    @Require
    LeaseService leaseService;
    @RequestMapping("/list.do")
    public void list(HttpServletRequest req,HttpServletResponse resp ) throws IOException {
        String page=req.getParameter("page");
        int pageNum=1;
        if(!StringUtils.isBlank(page)){
            pageNum=Integer.parseInt(page);
        }
        try {
            List<Lease> leaseList = leaseService.findByPage(pageNum);
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            String json = gson.toJson(leaseList);
            ResponseUtil.responseSuccess(resp, json, 200);


        } catch (SQLException e) {
            //e.printStackTrace();
            ResponseUtil.responseFailure(resp,"service error",100);
        }
    }
    @RequestMapping("/get.do")
    public void get(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        String stringId = req.getParameter("id");

        if(StringUtils.isBlank(stringId)){
            ResponseUtil.responseFailure(resp,"id is not set ",100);
            return;
        }
        Integer id = Integer.valueOf(stringId);
        try{
            Lease lease = leaseService.findById(id);
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            String json=gson.toJson(lease);
            ResponseUtil.responseSuccess(resp,json,200);
        } catch (SQLException e) {
            log.error("error",e);
            ResponseUtil.responseFailure(resp,"server error ",100);
        }
    }
    @RequestMapping("/delete.do")
    public void delete(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        String stringId = req.getParameter("id");
        if (StringUtils.isBlank(stringId)){
            ResponseUtil.responseFailure(resp,"id is not set",100);
            return;
        }
        Integer id = Integer.valueOf(stringId);

        Lease lease = null;
        try {
            int rows = leaseService.delete(id);
            if(rows==1){
                ResponseUtil.responseSuccess(resp,"delete success",200);
            }else{
                ResponseUtil.responseFailure(resp,"delete failure",100);
            }

        } catch (SQLException e) {
            log.error("error",e);
            ResponseUtil.responseFailure(resp,"server error ",100);
        }

    }

   @RequestMapping("/insert.do")
    public void  insert(HttpServletRequest req,HttpServletResponse resp) throws IOException {
       //Lease lease = null;
       try {
           Lease  lease = BeanUtil.parseFromRequest(req, Lease.class);
           //设置时间
           lease.setCreateTime(new Date());
           lease.setLastModifiedTime(new Date());
            lease.setPayTime(new Date());
           int rows = leaseService.insert(lease);
           if (rows == 1) {
               ResponseUtil.responseSuccess(resp,"insert success",200);
           }else{
               ResponseUtil.responseFailure(resp,"insert error",100);
           }

       } catch (Exception e) {
           log.error("error",e);
           ResponseUtil.responseFailure(resp,"server error ",100);
       }
   }

    @RequestMapping("/update.do")
    public void update(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        try {
            Lease lease = BeanUtil.parseFromRequest(req, Lease.class);
            lease.setCreateTime(new Date());
            lease.setLastModifiedTime(new Date());
            lease.setPayTime(new Date());
            int rows=leaseService.update(lease);
            System.out.println(lease);
            if(rows==1){
                ResponseUtil.responseSuccess(resp,"更新成功",200);
            }else {
                ResponseUtil.responseFailure(resp,"更新失败",100);
            }
           // ResponseUtil.responseSuccess(resp,rows+"",200);
        } catch (Exception e) {
            log.error("error:" ,e);
            ResponseUtil.responseFailure(resp,"server error",100);
        }
    }
}





/*

import com.google.gson.Gson;
import com.zhiyou100.annotation.Bean;
import com.zhiyou100.annotation.RequestMapping;
import com.zhiyou100.annotation.Require;
import com.zhiyou100.model.Lease;
import com.zhiyou100.service.LeaseService;
import com.zhiyou100.util.ResponseUtil;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@Bean
@RequestMapping("/lease")
public class LeaseController {
    @Require
    LeaseService leaseService;
    @RequestMapping("/list.do")
    public void list(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String page = req.getParameter("page");
        int pageNum=1;
        if(!StringUtils.isBlank(page)){
            pageNum=Integer.parseInt(page);
        }
        try {
            List<Lease> leaseList = leaseService.findByPage(pageNum);
            String json = new Gson().toJson(leaseList);
            ResponseUtil.responseSuccess(resp,json,200);
        } catch (Exception e) {
           // e.printStackTrace();
            ResponseUtil.responseFailure(resp,"server error",100);
        }
    }
}
*/
