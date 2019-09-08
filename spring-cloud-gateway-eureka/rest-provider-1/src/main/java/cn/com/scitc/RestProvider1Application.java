package cn.com.scitc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RestProvider1Application {

    public static void main(String[] args) {
        SpringApplication.run( RestProvider1Application.class, args );
    }

}
