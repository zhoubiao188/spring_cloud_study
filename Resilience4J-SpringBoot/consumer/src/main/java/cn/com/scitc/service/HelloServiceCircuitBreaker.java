/**
 * @program Resilience4J-SpringBoot
 * @description:
 * @author: zhoubiao
 * @create: 2019/08/09 22:29
 */

package cn.com.scitc.service;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Service
//@CircuitBreaker(name = "backendA")
    public class HelloServiceCircuitBreaker {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CircuitBreakerRegistry circuitBreakerRegistry;

    public String hello(String name) {
        return restTemplate.getForObject("http://provider/hello?name={1}", String.class, name);
    }


    public String hello2(String name) {
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState( Duration.ofMillis(1000))
                .ringBufferSizeInHalfOpenState(20)
                .ringBufferSizeInClosedState(20)
                .build();
        io.github.resilience4j.circuitbreaker.CircuitBreaker circuitBreaker =
                circuitBreakerRegistry.circuitBreaker("backendA", circuitBreakerConfig);
        Try<String> result = Try.ofSupplier( CircuitBreaker.decorateSupplier(circuitBreaker
        , () -> restTemplate.getForObject( "http://provider/hello?name={1}", String.class, name )) ).recover(Exception.class, "有异常，访问失败！");

        return result.get();
    }
}
