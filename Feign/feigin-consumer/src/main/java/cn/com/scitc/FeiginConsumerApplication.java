package cn.com.scitc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FeiginConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeiginConsumerApplication.class, args);
    }

}
