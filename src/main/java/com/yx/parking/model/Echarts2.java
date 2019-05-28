package com.yx.parking.model;

/**
 * @author yixin
 * @create 2019-05-19 21:28
 */
public class Echarts2 {
    private String name;
    private int value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Echarts2(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public Echarts2() {
    }
}
