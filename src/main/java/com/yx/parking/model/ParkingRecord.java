package com.yx.parking.model;


import java.sql.Timestamp;

public class ParkingRecord {

  private int prId;
  private int pId;
  private int uId;
  private Timestamp prStartTime;
  private Timestamp prEndTime;
  private int prPrice;
  private String prState;


  public int getPrId() {
    return prId;
  }

  public void setPrId(int prId) {
    this.prId = prId;
  }

  public int getpId() {
    return pId;
  }

  public void setpId(int pId) {
    this.pId = pId;
  }

  public int getuId() {
    return uId;
  }

  public void setuId(int uId) {
    this.uId = uId;
  }

  public Timestamp getPrStartTime() {
    return prStartTime;
  }

  public void setPrStartTime(Timestamp prStartTime) {
    this.prStartTime = prStartTime;
  }

  public Timestamp getPrEndTime() {
    return prEndTime;
  }

  public void setPrEndTime(Timestamp prEndTime) {
    this.prEndTime = prEndTime;
  }

  public int getPrPrice() {
    return prPrice;
  }

  public void setPrPrice(int prPrice) {
    this.prPrice = prPrice;
  }

  public String getPrState() {
    return prState;
  }

  public void setPrState(String prState) {
    this.prState = prState;
  }
}
