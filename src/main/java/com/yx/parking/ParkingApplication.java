package com.yx.parking;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@MapperScan("com.yx.parking.mapper")
@ServletComponentScan
@SpringBootApplication
public class ParkingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParkingApplication.class, args);
    }

}
