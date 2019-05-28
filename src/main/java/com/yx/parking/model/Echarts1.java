package com.yx.parking.model;

import java.util.List;

/**
 * @author yixin
 * @create 2019-05-19 17:55
 */
public class Echarts1 {
    private List<String> xName;
    private List<Integer> member;
    private List<Integer> nonmember;

    public List<String> getxName() {
        return xName;
    }

    public void setxName(List<String> xName) {
        this.xName = xName;
    }

    public List<Integer> getMember() {
        return member;
    }

    public void setMember(List<Integer> member) {
        this.member = member;
    }

    public List<Integer> getNonmember() {
        return nonmember;
    }

    public void setNonmember(List<Integer> nonmember) {
        this.nonmember = nonmember;
    }
}
