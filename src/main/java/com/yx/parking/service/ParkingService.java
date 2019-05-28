package com.yx.parking.service;

import com.yx.parking.model.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author yixin
 * @create 2019-05-10 10:17
 */
public interface ParkingService {
    /**
     * 得到登陆结果
     * @param admin
     * @param resp
     * @return
     */
    boolean getloginResult(Admin admin, HttpServletResponse resp,HttpServletRequest req);

    /**
     * 获得管理员信息
     * @param req
     * @return
     */
    Admin getContextPage(HttpServletRequest req);

    /**
     * 修改管理员密码
     * @param changepw
     * @return
     */
    boolean ChangePassword(ChangePassword changepw);

    /**
     * 修改管理员个人信息
     * @param admin
     * @return
     */
    boolean changeAdminInfo(Admin admin);

    /**
     * 修改停车场信息
     * @param parking
     * @return
     */
    boolean changeParkingInfo(Parking parking);

    /**
     * 获得停车场信息
     * @return
     */
    Parking getParkingInfo();

    /**
     * 清除cookie
     * @return
     * @param resp
     */
    void clearCookie(HttpServletResponse resp);

    /**
     * 得到page页车主信息
     * @return
     * @param page
     */
    List<UserFormat> getUserInfo(int page);

    /**
     * 通过id得到车主信息
     * @param id
     * @return
     */
    UserFormat getOneUserInfo(int id);

    /**
     * 删除车主信息
     * @param id
     * @return
     */
    boolean deleteOneUser(int id);

    /**
     * 更新车主信息
     * @param user
     * @return
     */
    boolean updateOneUser(UserFormat user);

    /**
     * 条件查找车主信息
     * @param searchinfo
     * @param page
     * @return
     */
    List<UserFormat> getUserInfoByCondition(String searchinfo, int page);

    /**
     * 添加车主信息
     * @param user
     * @return
     */
    boolean addUserInfo(UserFormat user);

    /**
     * 车主数量
     * @return
     */
    int getUserInfoPage();

    /**
     * 得到符合条件车主数量
     * @return
     * @param searchinfo
     */
    int getUserInfoPageByCondition(String searchinfo);

    /**
     * 获得停车记录数量
     * @return
     */
    int getParkingRecordPage();

    /**
     * 获得page页的停车记录
     * @param page
     * @return
     */
    List<ParkingRecordFormat> getParkingRecordInfo(int page);

    /**
     * 模拟驶入
     * @return
     * @param license
     */
    boolean driverIn(String license);

    /**
     * 模拟驶出
     * @return
     * @param license
     */
    PayInfo driverOut(String license);

    /**
     * 模糊查询停车记录
     * @param searchinfo
     * @param page
     * @return
     */
    List<ParkingRecordFormat> getParkingRecordByCondition(String searchinfo, int page);

    /**
     * 模糊查询停车记录页数
     * @param searchinfo
     * @return
     */
    int getParkingRecordPageByCondition(String searchinfo);

    /**
     * 通过id删除停车记录
     * @param id
     * @return
     */
    boolean deleteparkingRecord(int id);

    /**
     * 获得所有管理员
     * @return
     */
    List<Admin> getAllAdmin();

    /**
     * 添加管理员
     * @param admin
     * @return
     */
    boolean addAdminInfo(Admin admin);

    /**
     * 删除管理员
     * @param id
     * @return
     */
    boolean deleteOneAdmin(int id);

    /**
     * 获得指定的管理员
     * @param id
     * @return
     */
    Admin getOneAdminInfo(int id);

    /**
     * 修改管理员信息
     * @param admin
     * @return
     */
    boolean changeAdminInfo1(Admin admin);

    /**
     * 过去七天停车次数
     * @return
     * @param start
     * @param end
     */
    Echarts1 getEcharts1(String start, String end);

    /**
     * 车主角色可视化分析
     * @return
     */
    List<Echarts2> getEcharts2();
}
