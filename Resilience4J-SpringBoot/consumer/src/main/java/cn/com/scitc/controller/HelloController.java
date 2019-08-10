/**
 * @program Resilience4J-SpringBoot
 * @description:
 * @author: zhoubiao
 * @create: 2019/08/09 21:01
 */

package cn.com.scitc.controller;

import cn.com.scitc.service.HelloService;
import cn.com.scitc.service.HelloServiceCircuitBreaker;
import cn.com.scitc.service.HelloServiceRateLimiter;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
public class HelloController {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    HelloService helloService;

    @Autowired
    HelloServiceCircuitBreaker serviceCircuitBreaker;

    @Autowired
    HelloServiceRateLimiter helloServiceRateLimiter;

    @GetMapping("/hello")
    public String hello(String name) {
        return helloService.hello( name );
    }

    @GetMapping("/hello2")
    public String hello2(String name) {
        RetryConfig config  = RetryConfig.custom()
                .maxAttempts(3)
                .waitDuration( Duration.ofMillis(5000))
                .build();
        Retry retry = Retry.of("id", config);
        Try<String> result = Try.ofSupplier(Retry.decorateSupplier(
                retry, ()-> helloService.hello(name)
        ));

        return result.get();
    }

    @GetMapping("/hello3")
    public String hello3(String name) {
        return serviceCircuitBreaker.hello( name );
    }

    @GetMapping("/hello4")
    public String hello4(String name) {
       return serviceCircuitBreaker.hello2( name );
    }


    @GetMapping("/rl")
    public void rateLimiter(String name) {
        for(int i = 0; i < 5; i++) {
            log.info(name);
            String hello = helloServiceRateLimiter.hello( name );
        }
    }

    @GetMapping("/rl2")
    public void rateLimiter2(String name) {
        helloServiceRateLimiter.hello2(name);
    }

}
