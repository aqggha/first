package com.zhiyou100.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

//contract合同
@Data
public class Contract {
    private int id;
    //合同号
    private int contractNumber;
    //房屋信息
    private String houseInfo;
    //租户信息
    private String renterInfo;
    //合同签署日期
    private Date contractTime;
    //合同开始时间
    private Date startTime;
    //合同结束时间
    private Date endTime;
    //房租总额
    private BigDecimal price;
    //付款方式
    private String payMethod;
    //押金
    private BigDecimal deposit;
    //付款期数
    private int payPeriods;
    //签署人
    private String people;
    //合同状态
    private String status;

}