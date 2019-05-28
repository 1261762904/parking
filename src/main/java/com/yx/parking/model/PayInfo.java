package com.yx.parking.model;

/**
 * @author yixin
 * @create 2019-05-18 10:55
 */
public class PayInfo {
    private String license;
    private String time;
    private String startTime;
    private String endTime;
    private int price;
    private boolean judge;

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isJudge() {
        return judge;
    }

    public void setJudge(boolean judge) {
        this.judge = judge;
    }
}
