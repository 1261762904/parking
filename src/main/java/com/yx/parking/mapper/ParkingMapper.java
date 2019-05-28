package com.yx.parking.mapper;

import com.yx.parking.model.*;
import org.apache.ibatis.annotations.*;


import java.sql.Timestamp;
import java.util.List;

/**
 * @author yixin
 * @create 2019-05-09 19:28
 */

@Mapper
public interface ParkingMapper {

    /**
     * 通过账号查找管理员密码
     *
     * @param id
     * @return
     */
    @Select("select a_password from admin where a_id=#{id}")
    String findAdminPWByid(int id);

    /**
     * 通过账号查找管理员信息
     *
     * @param id
     * @return
     */
    @Select("select * from admin where a_id=#{id}")
    @Results(value = {@Result(column = "a_id", property = "aId"),
            @Result(column = "a_name", property = "aName"), @Result(column = "a_password", property = "aPassword"),
            @Result(column = "a_sex", property = "aSex"), @Result(column = "a_phone", property = "aPhone"),
            @Result(column = "a_role", property = "aRole")
    })
    Admin getAdminInfoById(int id);

    /**
     * 更新密码
     *
     * @param cp
     */
    @Update("update admin set a_password=#{newPassword} where a_id=#{id}")
    boolean updatePassword(ChangePassword cp);

    /**
     * 更新管理员信息
     *
     * @param admin
     */
    @Update("update admin set a_name=#{aName},a_sex=#{aSex},a_phone=#{aPhone} where a_id=#{aId}")
    boolean updateAdminInfo(Admin admin);

    /**
     * 更新管理员信息
     *
     * @param admin
     */
    @Update("update admin set a_name=#{aName},a_sex=#{aSex},a_phone=#{aPhone},a_password=#{aPassword} where a_id=#{aId}")
    boolean updateAdminInfo1(Admin admin);

    /**
     * 更新停车场信息
     *
     * @param parking
     */
    @Update("update parking set p_address=#{pAddress},p_num=#{pNum},p_pay=#{pPay},p_remainNum=#{pRemainNum}")
    boolean updateParkingInfo(Parking parking);

    /**
     * 车位加一
     *
     * @param
     */
    @Update("update parking set p_remainNum=p_remainNum+1")
    boolean updateParkingInfoa();

    /**
     * 车位减一
     *
     * @param
     */
    @Update("update parking set p_remainNum=p_remainNum-1")
    boolean updateParkingInfod();

    /**
     * 查找停车场信息
     *
     * @return
     */
    @Select("select * from parking")
    @Results(value = {@Result(column = "p_id", property = "pId"),
            @Result(column = "p_address", property = "pAddress"), @Result(column = "p_num", property = "pNum"),
            @Result(column = "p_pay", property = "pPay"), @Result(column = "p_remainNum", property = "pRemainNum"),
    })
    Parking findParkingInfo();

    /**
     * 查找所有车主的信息
     *
     * @return
     */
    @Select("select * from user limit #{offset},10")
    @Results(value = {@Result(column = "u_id", property = "uId"),
            @Result(column = "u_name", property = "uName"), @Result(column = "u_sex", property = "uSex"),
            @Result(column = "u_phone", property = "uPhone"), @Result(column = "u_license", property = "uLicense"),
            @Result(column = "u_role", property = "uRole"), @Result(column = "u_effectiveTime", property = "uEffectiveTime")
    })
    List<UserFormat> findUserInfo(int offset);


    /**
     * 车主数量
     *
     * @return
     */
    @Select("select count(*) from user")
    int getUserInfoCount();

    /**
     * 通过id查找车主信息
     *
     * @param id
     * @return
     */
    @Select("select * from user where u_id=#{id}")
    @Results(value = {@Result(column = "u_id", property = "uId"),
            @Result(column = "u_name", property = "uName"), @Result(column = "u_sex", property = "uSex"),
            @Result(column = "u_phone", property = "uPhone"), @Result(column = "u_license", property = "uLicense"),
            @Result(column = "u_role", property = "uRole"), @Result(column = "u_effectiveTime", property = "uEffectiveTime")
    })
    UserFormat findUserInfoById(int id);

    /**
     * 通过id删除车主信息
     *
     * @param id
     * @return
     */
    @Delete("delete from user where u_id=#{id}")
    boolean deleteOneUser(int id);

    /**
     * 更新车主信息
     *
     * @param user
     * @return
     */
    @Update("update user set u_name=#{uName},u_sex=#{uSex},u_phone=#{uPhone},u_license=#{uLicense},u_role=#{uRole},u_effectiveTime=#{uEffectiveTime} where u_id=#{uId}")
    boolean updateOneUser(UserFormat user);

    /**
     * 条件模糊查询车主信息
     *
     * @param searchinfo
     * @param offset
     * @return
     */
    @Select("select * from user where u_name LIKE CONCAT('%',#{searchinfo},'%') or u_license LIKE CONCAT('%',#{searchinfo},'%') limit #{offset},10")
    @Results(value = {@Result(column = "u_id", property = "uId"),
            @Result(column = "u_name", property = "uName"), @Result(column = "u_sex", property = "uSex"),
            @Result(column = "u_phone", property = "uPhone"), @Result(column = "u_license", property = "uLicense"),
            @Result(column = "u_role", property = "uRole"), @Result(column = "u_effectiveTime", property = "uEffectiveTime")
    })
    List<UserFormat> findUserInfoLike1(String searchinfo, int offset);

    /**
     * 角色查询车主信息
     *
     * @param searchinfo
     * @param offset
     * @return
     */
    @Select("select * from user where u_role=#{searchinfo} limit #{offset},10")
    @Results(value = {@Result(column = "u_id", property = "uId"),
            @Result(column = "u_name", property = "uName"), @Result(column = "u_sex", property = "uSex"),
            @Result(column = "u_phone", property = "uPhone"), @Result(column = "u_license", property = "uLicense"),
            @Result(column = "u_role", property = "uRole"), @Result(column = "u_effectiveTime", property = "uEffectiveTime")
    })
    List<UserFormat> findUserInfoLike2(String searchinfo, int offset);

    /**
     * 添加车主信息
     *
     * @param user
     * @return
     */
    @Insert("insert into user(u_name,u_sex,u_phone,u_license,u_role,u_effectiveTime) values(#{uName},#{uSex},#{uPhone},#{uLicense},#{uRole},#{uEffectiveTime})")
    boolean addUserInfo(UserFormat user);

    /**
     * 得到符合条件的车主数量
     *
     * @param searchinfo
     * @return
     */
    @Select("select count(*) from user where u_name LIKE CONCAT('%',#{searchinfo},'%') or u_license LIKE CONCAT('%',#{searchinfo},'%')")
    int getUserInfoCountByCondition1(String searchinfo);

    /**
     * 得到符合条件的车主数量
     *
     * @param searchinfo
     * @return
     */
    @Select("select count(*) from user where u_role=#{searchinfo}")
    int getUserInfoCountByCondition2(String searchinfo);

    /**
     * 得到停车记录页数
     *
     * @return
     */
    @Select("select count(*) from parkingrecord")
    int getParkingRecordCount();

    /**
     * 得到page行的停车记录
     *
     * @param offset
     * @return
     */
    @Select("select pr_id,p_address,u_name,u_license,pr_startTime,pr_endTime,u_role,pr_price,pr_state from parkingrecord as pr inner join user as u on pr.u_id=u.u_id inner join parking as p on pr.p_id=p.p_id  order by pr_startTime limit #{offset},10")
    @Results(value = {@Result(column = "pr_id", property = "prId"),
            @Result(column = "p_address", property = "pAddress"), @Result(column = "u_name", property = "uName"),
            @Result(column = "u_license", property = "uLicense"), @Result(column = "pr_startTime", property = "prStartTime"),
            @Result(column = "pr_endTime", property = "prEndTime"), @Result(column = "u_role", property = "uRole"),
            @Result(column = "pr_price", property = "prPrice"), @Result(column = "pr_state", property = "prState")
    })
    List<ParkingRecordFormat> findParkingRecord(int offset);

    /**
     * 车主信息中有没有此车牌
     *
     * @param license
     */
    @Select("select * from user where u_license=#{license}")
    @Results(value = {@Result(column = "u_id", property = "uId"),
            @Result(column = "u_name", property = "uName"), @Result(column = "u_sex", property = "uSex"),
            @Result(column = "u_phone", property = "uPhone"), @Result(column = "u_license", property = "uLicense"),
            @Result(column = "u_role", property = "uRole"), @Result(column = "u_effectiveTime", property = "uEffectiveTime")
    })
    UserFormat findUserBylicense(String license);

    /**
     * 得到会员有效期
     *
     * @param license
     */
    @Select("select u_effectiveTime from user where u_license=#{license}")
    Timestamp findUserfBylicense(String license);

    /**
     * 陌生车牌插入车主信息
     *
     * @param license
     */
    @Insert("insert into user(u_license,u_role) values(#{uLicense},'非会员')")
    void insertUserBylicense(String license);

    @Insert("insert into user(u_name,u_license,u_role) values('特殊车牌',#{uLicense},'特殊')")
    void insertUsertBylicense(String license);

    /**
     * 插入停车记录
     *
     * @param pr
     */
    @Insert("insert into parkingrecord(p_id,u_id,pr_startTime,pr_endTime,pr_price,pr_state) values(#{pId},#{uId},#{prStartTime},#{prEndTime},#{prPrice},#{prState})")
    boolean insertParkingRecord(ParkingRecord pr);

    /**
     * 通过车牌查找状态为'停车中'的停车记录的开始时间
     *
     * @param license
     * @return
     */
    @Select("select pr_startTime from parkingrecord as pr inner join user as u on u.u_id=pr.u_id where  pr_state='停车中' and u_license=#{license}")
    Timestamp findParkingRecordByLicense(String license);


    /**
     * 通过车牌查找状态为'停车中'的停车记录
     *
     * @param license
     * @return
     */
    @Select("select pr_id,p_address,u_name,u_license,pr_startTime,pr_endTime,u_role,pr_price,pr_state from parkingrecord as pr inner join user as u on pr.u_id=u.u_id inner join parking as p on pr.p_id=p.p_id where pr_state='停车中' and u_license=#{license}")
    @Results(value = {@Result(column = "pr_id", property = "prId"),
            @Result(column = "p_address", property = "pAddress"), @Result(column = "u_name", property = "uName"),
            @Result(column = "u_license", property = "uLicense"), @Result(column = "pr_startTime", property = "prStartTime"),
            @Result(column = "pr_endTime", property = "prEndTime"), @Result(column = "u_role", property = "uRole"),
            @Result(column = "pr_price", property = "prPrice"), @Result(column = "pr_state", property = "prState")
    })
    ParkingRecordFormat findParkingRecordfByLicense(String license);


    /**
     * 更新车主状态
     *
     * @param license
     * @return
     */
    @Update("update user set u_role='非会员',u_effectiveTime=null where u_license=#{license}")
    boolean updateUserStateBylicense(String license);

    /**
     * 更新停车记录
     *
     * @param pr
     * @return
     */
    @Update("update parkingrecord set u_id=#{uId},p_id=#{pId},pr_endTime=#{prEndTime},pr_price=#{prPrice},pr_state=#{prState} where pr_id=#{prId}")
    boolean updateParkingRecord(ParkingRecord pr);


    /**
     * 车牌模糊查询的停车记录行数
     *
     * @param searchinfo
     * @return
     */
    @Select("select count(*) from parkingrecord as pr inner join user as u on pr.u_id=u.u_id inner join parking as p on pr.p_id=p.p_id where u_license like CONCAT('%',#{searchinfo},'%')")
    int getParkingRecordCountByLicense(String searchinfo);

    /**
     * 时间查询停车记录行数
     *
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("select count(*) from parkingrecord where pr_startTime>=#{startTime} and pr_startTime<=#{endTime}")
    int getParkingRecordCountByTime(Timestamp startTime, Timestamp endTime);

    /**
     * 时间查询停车记录行数
     *
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("select count(*) from parkingrecord where pr_startTime>=#{startTime} and pr_endTime<=#{endTime}")
    int getParkingRecordCountByTime1(Timestamp startTime, Timestamp endTime);

    /**
     * 时间查询停车记录行数
     *
     * @param searchinfo
     * @return
     */
    @Select("select count(*) from parkingrecord where pr_startTime>=#{searchinfo}")
    int getParkingRecordCountByStartTime(String searchinfo);

    /**
     * 时间查询停车记录行数
     *
     * @param searchinfo
     * @return
     */
    @Select("select count(*) from parkingrecord where pr_endTime>=#{searchinfo}")
    int getParkingRecordCountByStartTime1(String searchinfo);

    /**
     * 状态查询停车记录行数
     *
     * @param searchinfo
     * @return
     */
    @Select("select count(*) from parkingrecord where pr_state=#{searchinfo}")
    int getParkingRecordCountByState(String searchinfo);

    /**
     * 角色查询停车记录行数
     *
     * @param searchinfo
     * @return
     */
    @Select("select count(*) from parkingrecord as pr inner join user as u on pr.u_id=u.u_id inner join parking as p on pr.p_id=p.p_id where u_role=#{searchinfo}")
    int getParkingRecordCountByRole(String searchinfo);

    /**
     * 开始时间查询停车记录
     *
     * @param startTime
     * @param endTime
     * @param offset
     * @return
     */
    @Select("select pr_id,p_address,u_name,u_license,pr_startTime,pr_endTime,u_role,pr_price,pr_state from parkingrecord as pr inner join user as u on pr.u_id=u.u_id inner join parking as p on pr.p_id=p.p_id where pr_startTime>=#{startTime} and pr_startTime<=#{endTime} order by pr_startTime limit #{offset},10")
    @Results(value = {@Result(column = "pr_id", property = "prId"),
            @Result(column = "p_address", property = "pAddress"), @Result(column = "u_name", property = "uName"),
            @Result(column = "u_license", property = "uLicense"), @Result(column = "pr_startTime", property = "prStartTime"),
            @Result(column = "pr_endTime", property = "prEndTime"), @Result(column = "u_role", property = "uRole"),
            @Result(column = "pr_price", property = "prPrice"), @Result(column = "pr_state", property = "prState")
    })
    List<ParkingRecordFormat> getParkingRecordByTime(Timestamp startTime, Timestamp endTime, int offset);

    /**
     * 结束时间查询停车记录
     *
     * @param startTime
     * @param endTime
     * @param offset
     * @return
     */
    @Select("select pr_id,p_address,u_name,u_license,pr_startTime,pr_endTime,u_role,pr_price,pr_state from parkingrecord as pr inner join user as u on pr.u_id=u.u_id inner join parking as p on pr.p_id=p.p_id where pr_endTime>=#{startTime} and pr_endTime<=#{endTime} order by pr_endTime limit #{offset},10")
    @Results(value = {@Result(column = "pr_id", property = "prId"),
            @Result(column = "p_address", property = "pAddress"), @Result(column = "u_name", property = "uName"),
            @Result(column = "u_license", property = "uLicense"), @Result(column = "pr_startTime", property = "prStartTime"),
            @Result(column = "pr_endTime", property = "prEndTime"), @Result(column = "u_role", property = "uRole"),
            @Result(column = "pr_price", property = "prPrice"), @Result(column = "pr_state", property = "prState")
    })
    List<ParkingRecordFormat> getParkingRecordByTime1(Timestamp startTime, Timestamp endTime, int offset);


    /**
     * 开始时间查询停车记录
     *
     * @param searchinfo
     * @param offset
     * @return
     */
    @Select("select pr_id,p_address,u_name,u_license,pr_startTime,pr_endTime,u_role,pr_price,pr_state from parkingrecord as pr inner join user as u on pr.u_id=u.u_id inner join parking as p on pr.p_id=p.p_id where pr_startTime>=#{searchinfo} order by pr_startTime limit #{offset},10")
    @Results(value = {@Result(column = "pr_id", property = "prId"),
            @Result(column = "p_address", property = "pAddress"), @Result(column = "u_name", property = "uName"),
            @Result(column = "u_license", property = "uLicense"), @Result(column = "pr_startTime", property = "prStartTime"),
            @Result(column = "pr_endTime", property = "prEndTime"), @Result(column = "u_role", property = "uRole"),
            @Result(column = "pr_price", property = "prPrice"), @Result(column = "pr_state", property = "prState")
    })
    List<ParkingRecordFormat> getParkingRecordByStartTime(String searchinfo, int offset);

    /**
     * 结束时间查询停车记录
     *
     * @param searchinfo
     * @param offset
     * @return
     */
    @Select("select pr_id,p_address,u_name,u_license,pr_startTime,pr_endTime,u_role,pr_price,pr_state from parkingrecord as pr inner join user as u on pr.u_id=u.u_id inner join parking as p on pr.p_id=p.p_id where pr_endTime>=#{searchinfo} order by pr_endTime limit #{offset},10")
    @Results(value = {@Result(column = "pr_id", property = "prId"),
            @Result(column = "p_address", property = "pAddress"), @Result(column = "u_name", property = "uName"),
            @Result(column = "u_license", property = "uLicense"), @Result(column = "pr_startTime", property = "prStartTime"),
            @Result(column = "pr_endTime", property = "prEndTime"), @Result(column = "u_role", property = "uRole"),
            @Result(column = "pr_price", property = "prPrice"), @Result(column = "pr_state", property = "prState")
    })
    List<ParkingRecordFormat> getParkingRecordByStartTime1(String searchinfo, int offset);

    /**
     * 角色查询停车记录
     *
     * @param searchinfo
     * @param offset
     * @return
     */
    @Select("select pr_id,p_address,u_name,u_license,pr_startTime,pr_endTime,u_role,pr_price,pr_state from parkingrecord as pr inner join user as u on pr.u_id=u.u_id inner join parking as p on pr.p_id=p.p_id where u_role=#{searchinfo} order by pr_startTime limit #{offset},10")
    @Results(value = {@Result(column = "pr_id", property = "prId"),
            @Result(column = "p_address", property = "pAddress"), @Result(column = "u_name", property = "uName"),
            @Result(column = "u_license", property = "uLicense"), @Result(column = "pr_startTime", property = "prStartTime"),
            @Result(column = "pr_endTime", property = "prEndTime"), @Result(column = "u_role", property = "uRole"),
            @Result(column = "pr_price", property = "prPrice"), @Result(column = "pr_state", property = "prState")
    })
    List<ParkingRecordFormat> getParkingRecordByRole(String searchinfo, int offset);

    /**
     * 状态查询停车记录
     *
     * @param searchinfo
     * @param offset
     * @return
     */
    @Select("select pr_id,p_address,u_name,u_license,pr_startTime,pr_endTime,u_role,pr_price,pr_state from parkingrecord as pr inner join user as u on pr.u_id=u.u_id inner join parking as p on pr.p_id=p.p_id where pr_state=#{searchinfo} order by pr_startTime limit #{offset},10")
    @Results(value = {@Result(column = "pr_id", property = "prId"),
            @Result(column = "p_address", property = "pAddress"), @Result(column = "u_name", property = "uName"),
            @Result(column = "u_license", property = "uLicense"), @Result(column = "pr_startTime", property = "prStartTime"),
            @Result(column = "pr_endTime", property = "prEndTime"), @Result(column = "u_role", property = "uRole"),
            @Result(column = "pr_price", property = "prPrice"), @Result(column = "pr_state", property = "prState")
    })
    List<ParkingRecordFormat> getParkingRecordByState(String searchinfo, int offset);

    /**
     * 车牌模糊查询停车记录
     *
     * @param searchinfo
     * @param offset
     * @return
     */
    @Select("select pr_id,p_address,u_name,u_license,pr_startTime,pr_endTime,u_role,pr_price,pr_state from parkingrecord as pr inner join user as u on pr.u_id=u.u_id inner join parking as p on pr.p_id=p.p_id where u_license like CONCAT('%',#{searchinfo},'%') order by pr_startTime limit #{offset},10")
    @Results(value = {@Result(column = "pr_id", property = "prId"),
            @Result(column = "p_address", property = "pAddress"), @Result(column = "u_name", property = "uName"),
            @Result(column = "u_license", property = "uLicense"), @Result(column = "pr_startTime", property = "prStartTime"),
            @Result(column = "pr_endTime", property = "prEndTime"), @Result(column = "u_role", property = "uRole"),
            @Result(column = "pr_price", property = "prPrice"), @Result(column = "pr_state", property = "prState")
    })
    List<ParkingRecordFormat> getParkingRecordByLicense(String searchinfo, int offset);

    /**
     * 通过id删除停车记录
     *
     * @param id
     * @return
     */
    @Delete("delete from parkingrecord where pr_id=#{id}")
    boolean deleteparkingRecord(int id);

    /**
     * 得到所有管理员信息
     *
     * @return
     */
    @Select("select * from admin where a_role='普通管理员'")
    @Results(value = {@Result(column = "a_id", property = "aId"),
            @Result(column = "a_name", property = "aName"), @Result(column = "a_password", property = "aPassword"),
            @Result(column = "a_sex", property = "aSex"), @Result(column = "a_phone", property = "aPhone"),
            @Result(column = "a_role", property = "aRole")
    })
    List<Admin> getAllAdmin();

    /**
     * 添加管理员信息
     *
     * @param admin
     * @return
     */
    @Insert("insert into admin(a_id,a_name,a_password,a_sex,a_phone,a_role) values(#{aId},#{aName},#{aPassword},#{aSex},#{aPhone},'普通管理员')")
    boolean addAdminInfo(Admin admin);

    /**
     * 删除指定的管理员
     *
     * @param id
     * @return
     */
    @Delete("delete from admin where a_id=#{id}")
    boolean deleteOneAdmin(int id);


    /**
     * 一定时间内的停车记录
     *
     * @param start
     * @param end
     * @param role
     * @return
     */
    @Select("select count(*) from parkingrecord as pr inner join user as u on pr.u_id=u.u_id inner join parking as p on pr.p_id=p.p_id where pr_startTime>=#{start} and pr_startTime<=#{end} and u_role=#{role}")
    int getEcharts1(String start, String end, String role);


    /**
     * 车主角色占比
     *
     * @param role
     * @return
     */
    @Select("select count(*) from user where u_role=#{role}")
    int getEcharts2(String role);

    /**
     * 删除指定范围的停车记录
     *
     * @param format
     * @return
     */
    @Delete("delete from parkingrecord where pr_startTime<#{format} and pr_state='完成'")
    boolean scheduleTask1(String format);

    /**
     * 删除临时车主
     *
     * @param
     * @return
     */
    @Delete("delete from user where u_name is null and u_id not in (select distinct(u_id) from parkingrecord where pr_startTime>=#{format});")
    boolean scheduleTask2(String format);
}
