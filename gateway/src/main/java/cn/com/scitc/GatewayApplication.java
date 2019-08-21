package cn.com.scitc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run( GatewayApplication.class, args );
    }

    /**
     * 注解方式
     */

//    @Bean
//    public RouteLocator redirectRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route("path_router", r -> r.path("/get")
//                .uri("http://httpbin.org"))
//                .build();
//    }

}
