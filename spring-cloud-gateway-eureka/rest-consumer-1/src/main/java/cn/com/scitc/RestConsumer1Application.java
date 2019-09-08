package cn.com.scitc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class RestConsumer1Application {

    public static void main(String[] args) {
        SpringApplication.run( RestConsumer1Application.class, args );
    }

}
