package com.yx.parking.model;

/**
 * @author yixin
 * @create 2019-05-14 21:17
 */
public class UserFormat {
    private int uId;
    private String uName;
    private String uSex;
    private String uPhone;
    private String uLicense;
    private String uRole;
    private String uEffectiveTime;

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuSex() {
        return uSex;
    }

    public void setuSex(String uSex) {
        this.uSex = uSex;
    }

    public String getuPhone() {
        return uPhone;
    }

    public void setuPhone(String uPhone) {
        this.uPhone = uPhone;
    }

    public String getuLicense() {
        return uLicense;
    }

    public void setuLicense(String uLicense) {
        this.uLicense = uLicense;
    }

    public String getuRole() {
        return uRole;
    }

    public void setuRole(String uRole) {
        this.uRole = uRole;
    }

    public String getuEffectiveTime() {
        return uEffectiveTime;
    }

    public void setuEffectiveTime(String uEffectiveTime) {
        this.uEffectiveTime = uEffectiveTime;
    }

    public UserFormat(int uId, String uName, String uSex, String uPhone, String uLicense, String uRole, String uEffectiveTime) {
        this.uId = uId;
        this.uName = uName;
        this.uSex = uSex;
        this.uPhone = uPhone;
        this.uLicense = uLicense;
        this.uRole = uRole;
        this.uEffectiveTime = uEffectiveTime;
    }

    public UserFormat() {
    }
}
