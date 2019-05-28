package com.yx.parking.controller;

import com.yx.parking.model.*;
import com.yx.parking.serviceimp.ParkingServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

/**
 * @author yixin
 * @create 2019-05-09 19:06
 */
@Controller
@RequestMapping("/parking")
public class ParkingController {
    @Autowired
    ParkingServiceImp parkingServiceimp;


    /**
     * 登陆
     *
     * @param admin
     * @param resp
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public boolean login(@RequestBody Admin admin, HttpServletResponse resp, HttpServletRequest req) {
        return parkingServiceimp.getloginResult(admin, resp, req);
    }

    /**
     * 清除cookie
     *
     * @return
     */
    @GetMapping("/clear")
    @ResponseBody
    public void clearCookie(HttpServletResponse resp) {
        parkingServiceimp.clearCookie(resp);
    }

    /**
     * 修改管理员密码
     *
     * @param changepw
     * @return
     */
    @PostMapping("/updatepw")
    @ResponseBody
    public boolean ChangePassword(@RequestBody ChangePassword changepw) {
        return parkingServiceimp.ChangePassword(changepw);
    }

    /**
     * 得到管理员信息
     *
     * @param req
     * @return
     */
    @GetMapping
    @ResponseBody
    public Admin getContextPage(HttpServletRequest req) {
        return parkingServiceimp.getContextPage(req);
    }

    /**
     * 修改管理员信息
     *
     * @param admin
     * @return
     */
    @PutMapping("/changeAdminInfo")
    @ResponseBody
    public boolean changeAdminInfo(@RequestBody Admin admin) {
        return parkingServiceimp.changeAdminInfo(admin);
    }

    /**
     * 修改停车场信息
     *
     * @param parking
     * @return
     */
    @PostMapping("/changeParkingInfo")
    @ResponseBody
    public boolean changeParkingInfo(@RequestBody Parking parking) {
        return parkingServiceimp.changeParkingInfo(parking);
    }

    /**
     * 得到停车场信息
     *
     * @return
     */
    @GetMapping("/parkingInfo")
    @ResponseBody
    public Parking getParkingInfo() {
        return parkingServiceimp.getParkingInfo();
    }

    /**
     * 得到第page页车主信息
     *
     * @return
     */
    @GetMapping("/userInfoPage/{page}")
    @ResponseBody
    public List<UserFormat> getUserInfo(@PathVariable int page) {
        return parkingServiceimp.getUserInfo(page);
    }

    /**
     * 得到车主信息的总页数
     *
     * @return
     */
    @GetMapping("/userInfoPage")
    @ResponseBody
    public int getUserInfoPage() {
        return parkingServiceimp.getUserInfoPage();
    }

    /**
     * 条件查找车主信息
     *
     * @return
     */
    @GetMapping("/finduserInfo")
    @ResponseBody
    public List<UserFormat> getUserInfoByCondition(@RequestParam String searchinfo, @RequestParam int page) {
        return parkingServiceimp.getUserInfoByCondition(searchinfo, page);
    }

    /**
     * 得到符合条件的车主信息页数
     *
     * @return
     */
    @GetMapping("/userInfoPageBC/{searchinfo}")
    @ResponseBody
    public int getUserInfoPageByCondition(@PathVariable String searchinfo) {
        return parkingServiceimp.getUserInfoPageByCondition(searchinfo);
    }

    /**
     * 通过id获得车主信息
     *
     * @param id
     * @return
     */
    @GetMapping("/userInfo/{id}")
    @ResponseBody
    public UserFormat getOneUserInfo(@PathVariable int id) {
        return parkingServiceimp.getOneUserInfo(id);
    }

    /**
     * 删除指定车主信息
     *
     * @param id
     */
    @DeleteMapping("/userInfo/{id}")
    @ResponseBody
    public boolean deleteOneUser(@PathVariable int id) {
        return parkingServiceimp.deleteOneUser(id);
    }

    /**
     * 添加车主信息
     *
     * @param user
     * @return
     */
    @PostMapping("/userInfo")
    @ResponseBody
    public boolean addUserInfo(@RequestBody UserFormat user) {
        return parkingServiceimp.addUserInfo(user);
    }


    /**
     * 更新车主信息
     *
     * @param user
     */
    @PutMapping("/userInfo")
    @ResponseBody
    public boolean updateOneUser(@RequestBody UserFormat user) {
        return parkingServiceimp.updateOneUser(user);
    }


    /**
     * 得到第page页停车记录
     *
     * @return
     */
    @GetMapping("/parkingRecordInfo/{page}")
    @ResponseBody
    public List<ParkingRecordFormat> getparkingRecordInfo(@PathVariable int page) {
        return parkingServiceimp.getParkingRecordInfo(page);
    }

    /**
     * 得到停车记录的总页数
     *
     * @return
     */
    @GetMapping("/parkingRecordPage")
    @ResponseBody
    public int getparkingRecordPage() {
        return parkingServiceimp.getParkingRecordPage();
    }

    /**
     * 条件查找车主信息
     *
     * @return
     */
    @GetMapping("/findparkingRecord")
    @ResponseBody
    public List<ParkingRecordFormat> getparkingRecordByCondition(@RequestParam String searchinfo,@RequestParam int page) {
        return parkingServiceimp.getParkingRecordByCondition(searchinfo,page);
    }

    /**
     * 得到符合条件的车主信息页数
     * @return
     */
    @GetMapping("/parkingRecordPageBC/{searchinfo}")
    @ResponseBody
    public int getparkingRecordPageByCondition(@PathVariable String searchinfo) {
        return parkingServiceimp.getParkingRecordPageByCondition(searchinfo);
    }


    /**
     * 模拟驶入
     *
     * @return
     */
    @GetMapping("/driverin/{license}")
    @ResponseBody
    public boolean driverIn(@PathVariable String license) {
        return parkingServiceimp.driverIn(license);
    }

    /**
     * 模拟驶出
     *
     * @return
     */
    @GetMapping("/driverout/{license}")
    @ResponseBody
    public PayInfo driverOut(@PathVariable String license) {
        return parkingServiceimp.driverOut(license);
    }

    @DeleteMapping("/parkingRecordInfo/{id}")
    @ResponseBody
    public boolean deleteOneparkingRecord(@PathVariable int id) {
        return parkingServiceimp.deleteparkingRecord(id);
    }


    /**
     * 通过id获得管理员信息
     *
     * @param id
     * @return
     */
    @GetMapping("/adminInfo/{id}")
    @ResponseBody
    public Admin getOneAdminInfo(@PathVariable int id) {
        return parkingServiceimp.getOneAdminInfo(id);
    }

    /**
     * 删除指定管理员信息
     *
     * @param id
     */
    @DeleteMapping("/adminInfo/{id}")
    @ResponseBody
    public boolean deleteOneAdmin(@PathVariable int id) {
        return parkingServiceimp.deleteOneAdmin(id);
    }

    /**
     * 添加管理员信息
     *
     * @param admin
     * @return
     */
    @PostMapping("/addAdminInfo")
    @ResponseBody
    public boolean addAdminInfo(@RequestBody Admin admin) {
        return parkingServiceimp.addAdminInfo(admin);
    }


    /**
     * 获得所有管理员信息
     *
     * @param
     */
    @GetMapping("/adminInfo")
    @ResponseBody
    public List<Admin> allAdmin() {
        return parkingServiceimp.getAllAdmin();
    }

    /**
     * 修改管理员信息
     *
     * @param admin
     * @return
     */
    @PutMapping("/changeAdminInfo1")
    @ResponseBody
    public boolean changeAdminInfo1(@RequestBody Admin admin) {
        return parkingServiceimp.changeAdminInfo1(admin);
    }

    /**
     * 停车记录可视化分析
     * @return
     */
    @GetMapping("/parkingRecordByEcharts")
    @ResponseBody
    public Echarts1 Echarts1(@RequestParam String start,@RequestParam String end) {
        return parkingServiceimp.getEcharts1(start,end);
    }

    /**
     *车主角色可视化分析
     * @return
     */
    @GetMapping("/userRoleByEcharts")
    @ResponseBody
    public List<Echarts2> Echarts2() {
        return parkingServiceimp.getEcharts2();
    }

}
