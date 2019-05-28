package com.yx.parking.serviceimp;

import com.yx.parking.mapper.ParkingMapper;
import com.yx.parking.model.*;
import com.yx.parking.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author yixin
 * @create 2019-05-10 10:17
 */
@Service
public class ParkingServiceImp implements ParkingService {
    @Autowired
    ParkingMapper parkingMapper;

    @Override
    public boolean getloginResult(Admin admin, HttpServletResponse resp, HttpServletRequest req) {
        boolean judge = false;
        int id = admin.getaId();
        String pagePassword = admin.getaPassword();
        String password = parkingMapper.findAdminPWByid(id);
        if (pagePassword.equals(password)) {
            Cookie aIdCookie = new Cookie("aId", String.valueOf(id));
            aIdCookie.setMaxAge(9000);
            aIdCookie.setPath("/");
            resp.addCookie(aIdCookie);
            judge = true;
        }
        return judge;
    }

    @Override
    public Admin getContextPage(HttpServletRequest req) {
        Cookie[] c = req.getCookies();
        String cookie = null;
        for (int i = 0; i < c.length; i++) {
            if ("aId".equals(c[i].getName())) {
                cookie = c[i].getValue();
            }
        }
        int id = Integer.parseInt(cookie);
        return parkingMapper.getAdminInfoById(id);
    }

    @Override
    public void clearCookie(HttpServletResponse resp) {
        Cookie aIdCookie = new Cookie("aId", null);
        aIdCookie.setPath("/");
        resp.addCookie(aIdCookie);
    }

    @Override
    public boolean ChangePassword(ChangePassword changepw) {
        boolean judge = false;
        int id = changepw.getId();
        String prePassword = changepw.getPrePassword();
        String realPerPassword = parkingMapper.findAdminPWByid(id);
        if (realPerPassword.equals(prePassword)) {
            judge = parkingMapper.updatePassword(changepw);
        }
        return judge;
    }

    @Override
    public boolean changeAdminInfo(Admin admin) {
        return parkingMapper.updateAdminInfo(admin);
    }

    @Override
    public boolean changeParkingInfo(Parking parking) {
        return parkingMapper.updateParkingInfo(parking);
    }

    @Override
    public Parking getParkingInfo() {
        return parkingMapper.findParkingInfo();
    }

    @Override
    public List<UserFormat> getUserInfo(int page) {
        int offset = (page - 1) * 10;
        return parkingMapper.findUserInfo(offset);
    }

    @Override
    public int getUserInfoPage() {
        int count = parkingMapper.getUserInfoCount();
        int page = 0;
        if (count % 10 != 0) {
            page = count / 10 + 1;
        } else {
            page = count / 10;
        }
        return page;
    }

    @Override
    public List<UserFormat> getUserInfoByCondition(String searchinfo, int page) {
        int offset = (page - 1) * 10;
        if (searchinfo.equals("会员") || searchinfo.equals("非会员") || searchinfo.equals("特殊")) {
            return parkingMapper.findUserInfoLike2(searchinfo, offset);
        } else {
            return parkingMapper.findUserInfoLike1(searchinfo, offset);
        }

    }

    @Override
    public int getUserInfoPageByCondition(String searchinfo) {
        int count = 0;
        if (searchinfo.equals("会员") || searchinfo.equals("非会员") || searchinfo.equals("特殊")) {
            count = parkingMapper.getUserInfoCountByCondition2(searchinfo);
        } else {
            count = parkingMapper.getUserInfoCountByCondition1(searchinfo);
        }
        int page = 0;
        if (count % 10 != 0) {
            page = count / 10 + 1;
        } else {
            page = count / 10;
        }
        return page;
    }

    @Override
    public UserFormat getOneUserInfo(int id) {
        return parkingMapper.findUserInfoById(id);
    }

    @Override
    public boolean deleteOneUser(int id) {
        return parkingMapper.deleteOneUser(id);
    }

    @Override
    public boolean addUserInfo(UserFormat user) {
        return parkingMapper.addUserInfo(user);
    }

    @Override
    public boolean updateOneUser(UserFormat user) {
        return parkingMapper.updateOneUser(user);
    }

    @Override
    public int getParkingRecordPage() {
        int count = parkingMapper.getParkingRecordCount();
        int page = 0;
        if (count % 10 != 0) {
            page = count / 10 + 1;
        } else {
            page = count / 10;
        }
        return page;
    }

    @Override
    public List<ParkingRecordFormat> getParkingRecordInfo(int page) {
        int offset = (page - 1) * 10;
        return parkingMapper.findParkingRecord(offset);
    }

    @Override
    public List<ParkingRecordFormat> getParkingRecordByCondition(String searchinfo, int page) {
        int offset = (page - 1) * 10;
        List<ParkingRecordFormat> list = new ArrayList<>();
        if (searchinfo.contains("-")) {
            System.out.println("时间");
            if (searchinfo.contains("~")) {
                Timestamp startTime = Timestamp.valueOf(searchinfo.substring(0, searchinfo.indexOf("~")));
                Timestamp endTime = Timestamp.valueOf(searchinfo.substring(searchinfo.indexOf("~") + 1));
                list = parkingMapper.getParkingRecordByTime(startTime, endTime, offset);
            } else if (searchinfo.contains("!") && searchinfo.indexOf("!") != 0) {
                Timestamp startTime = Timestamp.valueOf(searchinfo.substring(0, searchinfo.indexOf("!")));
                Timestamp endTime = Timestamp.valueOf(searchinfo.substring(searchinfo.indexOf("!") + 1));
                list = parkingMapper.getParkingRecordByTime1(startTime, endTime, offset);
            } else if (searchinfo.contains("!") && searchinfo.indexOf("!") == 0) {
                list = parkingMapper.getParkingRecordByStartTime1(searchinfo.substring(1), offset);
            } else {
                list = parkingMapper.getParkingRecordByStartTime(searchinfo, offset);
            }

        } else {
            System.out.println("车牌、角色、状态");
            if (searchinfo.equals("非会员") || searchinfo.equals("会员") || searchinfo.equals("特殊")) {
                list = parkingMapper.getParkingRecordByRole(searchinfo, offset);
            } else if (searchinfo.equals("停车中") || searchinfo.equals("完成")) {
                list = parkingMapper.getParkingRecordByState(searchinfo, offset);
            } else {
                list = parkingMapper.getParkingRecordByLicense(searchinfo, offset);
            }
        }
        return list;
    }

    @Override
    public int getParkingRecordPageByCondition(String searchinfo) {
        int count = 0;
        if (searchinfo.contains("-")) {
            System.out.println("时间");
            if (searchinfo.contains("~")) {
                Timestamp startTime = Timestamp.valueOf(searchinfo.substring(0, searchinfo.indexOf("~")));
                Timestamp endTime = Timestamp.valueOf(searchinfo.substring(searchinfo.indexOf("~") + 1));
                count = parkingMapper.getParkingRecordCountByTime(startTime, endTime);
            } else if (searchinfo.contains("!") && searchinfo.indexOf("!") != 0) {
                Timestamp startTime = Timestamp.valueOf(searchinfo.substring(0, searchinfo.indexOf("!")));
                Timestamp endTime = Timestamp.valueOf(searchinfo.substring(searchinfo.indexOf("!") + 1));
                count = parkingMapper.getParkingRecordCountByTime1(startTime, endTime);
            } else if (searchinfo.contains("!") && searchinfo.indexOf("!") == 0) {
                count = parkingMapper.getParkingRecordCountByStartTime1(searchinfo.substring(1));
            } else {
                count = parkingMapper.getParkingRecordCountByStartTime(searchinfo);
            }
        } else {
            System.out.println("车牌、角色、状态");
            if (searchinfo.equals("非会员") || searchinfo.equals("会员")) {
                count = parkingMapper.getParkingRecordCountByRole(searchinfo);
            } else if (searchinfo.equals("停车中") || searchinfo.equals("完成")) {
                count = parkingMapper.getParkingRecordCountByState(searchinfo);
            } else {
                count = parkingMapper.getParkingRecordCountByLicense(searchinfo);
            }
        }
        int page = 0;
        if (count % 10 != 0) {
            page = count / 10 + 1;
        } else {
            page = count / 10;
        }
        return page;
    }

    @Override
    public boolean deleteparkingRecord(int id) {
        return parkingMapper.deleteparkingRecord(id);
    }

    @Override
    public boolean driverIn(String license) {
        Parking parking = parkingMapper.findParkingInfo();
        boolean judge = false;
        if (parkingMapper.findParkingRecordfByLicense(license) != null) {
            return false;
        }
        if (parking.getpRemainNum() > 0) {
            ParkingRecord pr = new ParkingRecord();
            UserFormat user = null;
            user = parkingMapper.findUserBylicense(license);
            if (user == null) {
                int length = license.length();
                if (license.startsWith("WJ") || license.startsWith("军") || "领".equals(license.substring(length - 1, length))) {
                    parkingMapper.insertUsertBylicense(license);
                } else {
                    parkingMapper.insertUserBylicense(license);
                }
            }
            user = parkingMapper.findUserBylicense(license);
            pr.setuId(user.getuId());
            pr.setpId(parking.getpId());
            pr.setPrState("停车中");
            pr.setPrStartTime(new Timestamp(new Date().getTime()));
            judge = parkingMapper.insertParkingRecord(pr);
            if (judge == true) {
                judge = parkingMapper.updateParkingInfod();
            }
        }
        return judge;
    }

    @Override
    public PayInfo driverOut(String license) {
        PayInfo payInfo = new PayInfo();
        boolean judge = true;
        UserFormat user = parkingMapper.findUserBylicense(license);
        if (user == null) {
            judge = false;
        }
        ParkingRecordFormat parkingrecord = parkingMapper.findParkingRecordfByLicense(license);
        if (parkingrecord != null) {
            Parking parking = parkingMapper.findParkingInfo();
            Timestamp startTimeStamp = parkingMapper.findParkingRecordByLicense(license);
            long startDate = startTimeStamp.getTime();
            long endDate = new Date().getTime();
            ParkingRecord pr = new ParkingRecord();
            int price = 0;
            if ("会员".equals(parkingrecord.getuRole())) {
                Timestamp timestamp = parkingMapper.findUserfBylicense(license);
                if (timestamp.getTime() - endDate < 0) {
                    parkingMapper.updateUserStateBylicense(license);
                    price = getPrice(startDate, endDate, parking.getpPay());
                }
            } else if ("特殊".equals(parkingrecord.getuRole())) {
                price = 0;
            } else {
                price = getPrice(startDate, endDate, parking.getpPay());
            }
            pr.setPrId(parkingrecord.getPrId());
            pr.setuId(user.getuId());
            pr.setpId(parking.getpId());
            pr.setPrPrice(price);
            pr.setPrEndTime(new Timestamp(endDate));
            pr.setPrState("完成");
            boolean tf = parkingMapper.updateParkingRecord(pr);
            if (!tf) {
                judge = false;
            }
            payInfo.setLicense(license);
            payInfo.setTime(getTime(startDate, endDate));
            payInfo.setPrice(price);
            payInfo.setStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTimeStamp));
            payInfo.setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        } else {
            judge = false;
        }
        if (judge == true) {
            judge = parkingMapper.updateParkingInfoa();
        }
        payInfo.setJudge(judge);
        return payInfo;
    }

    public int getPrice(long startDate, long endDate, int pay) {
        int price = 0;
        long temp = endDate - startDate;
        long hours = temp / (1000 * 60 * 60);
        long minutes = (temp - hours * (1000 * 60 * 60)) / (1000 * 60);
        long second = (temp - hours * (1000 * 60 * 60) - minutes * (1000 * 60)) / 1000;
        if (minutes == 0 && second == 0) {
            if (hours <= 1) {
                price = 0;
            } else if (1 < hours && hours <= 2) {
                price = pay;
            } else if (2 < hours && hours <= 10) {
                price = pay * 2;
            } else if (10 < hours && hours <= 24) {
                price = pay * 5;
            } else {
                price = (int) (pay * 5 + (hours - 24));
            }
        } else {
            if (hours < 1) {
                price = 0;
            } else if (1 <= hours && hours < 2) {
                price = pay;
            } else if (2 <= hours && hours < 10) {
                price = pay * 2;
            } else if (10 <= hours && hours < 24) {
                price = pay * 5;
            } else {
                price = (int) (pay * 5 + (hours - 23));
            }
        }

        return price;
    }

    public String getTime(long startDate, long endDate) {
        long temp = endDate - startDate;
        long hours = temp / (1000 * 60 * 60);
        long minutes = (temp - hours * (1000 * 60 * 60)) / (1000 * 60);
        long second = (temp - hours * (1000 * 60 * 60) - minutes * (1000 * 60)) / 1000;
        String diffTime = "";
        if (minutes < 10) {
            diffTime = hours + ":0" + minutes;
        } else {
            diffTime = hours + ":" + minutes;
        }
        if (second < 10) {
            diffTime += ":0" + second;
        } else {
            diffTime += ":" + second;
        }
        return diffTime;
    }

    @Override
    public List<Admin> getAllAdmin() {
        return parkingMapper.getAllAdmin();
    }

    @Override
    public boolean addAdminInfo(Admin admin) {
        return parkingMapper.addAdminInfo(admin);
    }

    @Override
    public boolean deleteOneAdmin(int id) {
        return parkingMapper.deleteOneAdmin(id);
    }

    @Override
    public Admin getOneAdminInfo(int id) {
        return parkingMapper.getAdminInfoById(id);
    }

    @Override
    public boolean changeAdminInfo1(Admin admin) {
        return parkingMapper.updateAdminInfo1(admin);
    }

    @Override
    public Echarts1 getEcharts1(String start, String end) {
        Echarts1 echarts = new Echarts1();
        List<String> xname = new ArrayList<>();
        List<String> times = new ArrayList<>();
        List<Integer> member = new ArrayList<>();
        List<Integer> nonmember = new ArrayList<>();
        int count1 = 0;
        int count2 = 0;
        try {
            times = getTimesBetweenDates(start, end);
            int length = times.size();
            for (int i = 0; i < length - 1; i++) {
                xname.add(times.get(i));
                count1 = parkingMapper.getEcharts1(times.get(i), times.get(i + 1), "会员");
                ;
                count2 = parkingMapper.getEcharts1(times.get(i), times.get(i + 1), "非会员");
                ;
                member.add(count1);
                nonmember.add(count2);
                count1 = 0;
                count2 = 0;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        echarts.setMember(member);
        echarts.setxName(xname);
        echarts.setNonmember(nonmember);
        return echarts;
    }

    public List<String> getTimesBetweenDates(String startTime, String endTime) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date start = dateFormat.parse(startTime);
        Date end = dateFormat.parse(endTime);
        System.out.println(startTime + "***" + endTime);
        // 如果时间传反了
        long time1 = start.getTime();
        long time2 = end.getTime();
        Date midTime = new Date();
        if (time1 > time2) {
            midTime = start;
            start = end;
            end = midTime;
        }
        List<String> result = new ArrayList<String>();
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(start);
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(end);
        endCal.add(Calendar.DATE, +2);
        while (startCal.before(endCal)) {
            result.add(dateFormat.format(startCal.getTime()));
            System.out.println(dateFormat.format(startCal.getTime()));
            startCal.add(Calendar.DAY_OF_YEAR, 1);
        }
        return result;
    }

    @Override
    public List<Echarts2> getEcharts2() {
        List<Echarts2> echarts = new ArrayList<>();
        Echarts2 echart1 = new Echarts2("会员", parkingMapper.getEcharts2("会员"));
        Echarts2 echart2 = new Echarts2("非会员", parkingMapper.getEcharts2("非会员"));
        Echarts2 echart3 = new Echarts2("特殊", parkingMapper.getEcharts2("特殊"));
        echarts.add(echart2);
        echarts.add(echart1);
        echarts.add(echart3);
        return echarts;
    }
}
