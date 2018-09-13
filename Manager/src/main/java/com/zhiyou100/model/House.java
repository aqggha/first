package com.zhiyou100.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class House {
   private int id;
   private String area;
   private String community;
   private int unitNum;
   private int floor;
   private String roomNum;
   private double space;
   private String direction;
   private String fitment;
   private int isDoubleAir;
   private int limit;
   private String facility;
    //跟钱相关的字段要用Decimal
   private BigDecimal price;
   private String status;
   private String image;
   private String address;
   private Date createTime;
   private Date lastModifiedTime;
   private String remark;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getArea() {
      return area;
   }

   public void setArea(String area) {
      this.area = area;
   }

   public String getCommunity() {
      return community;
   }

   public void setCommunity(String community) {
      this.community = community;
   }

   public int getUnitNum() {
      return unitNum;
   }

   public void setUnitNum(int unitNum) {
      this.unitNum = unitNum;
   }

   public int getFloor() {
      return floor;
   }

   public void setFloor(int floor) {
      this.floor = floor;
   }

   public String getRoomNum() {
      return roomNum;
   }

   public void setRoomNum(String roomNum) {
      this.roomNum = roomNum;
   }

   public double getSpace() {
      return space;
   }

   public void setSpace(double space) {
      this.space = space;
   }

   public String getDirection() {
      return direction;
   }

   public void setDirection(String direction) {
      this.direction = direction;
   }

   public String getFitment() {
      return fitment;
   }

   public void setFitment(String fitment) {
      this.fitment = fitment;
   }

   public int getIsDoubleAir() {
      return isDoubleAir;
   }

   public void setIsDoubleAir(int isDoubleAir) {
      this.isDoubleAir = isDoubleAir;
   }

   public int getLimit() {
      return limit;
   }

   public void setLimit(int limit) {
      this.limit = limit;
   }

   public String getFacility() {
      return facility;
   }

   public void setFacility(String facility) {
      this.facility = facility;
   }

   public BigDecimal getPrice() {
      return price;
   }

   public void setPrice(BigDecimal price) {
      this.price = price;
   }

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String getImage() {
      return image;
   }

   public void setImage(String image) {
      this.image = image;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public Date getCreateTime() {
      return createTime;
   }

   public void setCreateTime(Date createTime) {
      this.createTime = createTime;
   }

   public Date getLastModifiedTime() {
      return lastModifiedTime;
   }

   public void setLastModifiedTime(Date lastModifiedTime) {
      this.lastModifiedTime = lastModifiedTime;
   }

   public String getRemark() {
      return remark;
   }

   public void setRemark(String remark) {
      this.remark = remark;
   }
}
