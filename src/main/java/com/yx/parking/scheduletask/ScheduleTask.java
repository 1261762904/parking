package com.yx.parking.scheduletask;

import com.yx.parking.mapper.ParkingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author yixin
 * @create 2019-05-20 11:25
 */
@Component
@Configuration
@EnableScheduling
public class ScheduleTask {
    @Autowired
    ParkingMapper parkingMapper;

    // 每个月一号会删除一个季度前的状态为'完成'的停车记录，并且删除临时车主
    //@Scheduled(cron = "*/5 * * * * ?")    //eg:每隔5秒执行一次
    @Scheduled(cron = "0 0 0 1 * ?")    //eg:每个月1号执行一次
    private void configureTasks() {
        System.out.println("执行定时任务时间: " + LocalDateTime.now());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(new Date());
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(new Date());
        endCal.add(Calendar.MONTH, -3);
        System.out.println(dateFormat.format(startCal.getTime()));
        System.out.println(dateFormat.format(endCal.getTime()));
        parkingMapper.scheduleTask1(dateFormat.format(endCal.getTime()));
        parkingMapper.scheduleTask2(dateFormat.format(endCal.getTime()));
    }


}
