package com.yx.parking.model;

public class Parking {

  private int pId;
  private String pAddress;
  private int pNum;
  private int pPay;
  private int pRemainNum;

  public int getpRemainNum() {
    return pRemainNum;
  }

  public void setpRemainNum(int pRemainNum) {
    this.pRemainNum = pRemainNum;
  }

  public int getpId() {
    return pId;
  }

  public void setpId(int pId) {
    this.pId = pId;
  }

  public String getpAddress() {
    return pAddress;
  }

  public void setpAddress(String pAddress) {
    this.pAddress = pAddress;
  }

  public int getpNum() {
    return pNum;
  }

  public void setpNum(int pNum) {
    this.pNum = pNum;
  }

  public int getpPay() {
    return pPay;
  }

  public void setpPay(int pPay) {
    this.pPay = pPay;
  }
}
