package com.yx.parking.model;


public class ParkingRecordFormat {

  private int prId;
  private String pAddress;
  private String uName;
  private String uLicense;
  private String prStartTime;
  private String prEndTime;
  private String uRole;
  private int prPrice;
  private String prState;

    public int getPrId() {
        return prId;
    }

    public void setPrId(int prId) {
        this.prId = prId;
    }

    public String getpAddress() {
        return pAddress;
    }

    public void setpAddress(String pAddress) {
        this.pAddress = pAddress;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuLicense() {
        return uLicense;
    }

    public void setuLicense(String uLicense) {
        this.uLicense = uLicense;
    }

    public String getPrStartTime() {
        return prStartTime;
    }

    public void setPrStartTime(String prStartTime) {
        this.prStartTime = prStartTime;
    }

    public String getPrEndTime() {
        return prEndTime;
    }

    public void setPrEndTime(String prEndTime) {
        this.prEndTime = prEndTime;
    }

    public String getuRole() {
        return uRole;
    }

    public void setuRole(String uRole) {
        this.uRole = uRole;
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
