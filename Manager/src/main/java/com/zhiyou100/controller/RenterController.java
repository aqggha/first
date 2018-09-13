
package com.zhiyou100.controller;

import com.google.gson.Gson;
import com.zhiyou100.annotation.Bean;

import com.zhiyou100.annotation.RequestMapping;
import com.zhiyou100.annotation.Require;
import com.zhiyou100.model.Renter;
import com.zhiyou100.service.RenterService;
import com.zhiyou100.util.BeanUtil;
import com.zhiyou100.util.ResponseUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Bean
@RequestMapping("/renter")
public class RenterController {
    private static final Logger log=Logger.getLogger(RenterController.class);
    @Require
    RenterService renterService;
    @RequestMapping("/list.do")
    public void list(HttpServletRequest req,HttpServletResponse resp ) throws IOException {
        String page=req.getParameter("page");
        int pageNum=1;
        if(!StringUtils.isBlank(page)){
            pageNum=Integer.parseInt(page);
        }
        try {
            List<Renter> renterList = renterService.findByPage(pageNum);

            String json = new Gson().toJson(renterList);
            ResponseUtil.responseSuccess(resp,json,200);

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
            Renter renter = renterService.findById(id);
            ResponseUtil.responseSuccess(resp,new Gson().toJson(renter),200);
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

        Renter renter = null;
        try {
            int rows = renterService.delete(id);
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
        //Renter renter = null;
        try {
            Renter  renter = BeanUtil.parseFromRequest(req, Renter.class);
            //设置时间
            renter.setAddTime(new Date());

            int rows = renterService.insert(renter);
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
            Renter renter = BeanUtil.parseFromRequest(req, Renter.class);
            renter.setAddTime(new Date());
            int rows=renterService.update(renter);
            System.out.println(renter);
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

