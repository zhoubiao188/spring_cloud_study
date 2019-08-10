/**
 * @program Resilience4J-SpringBoot
 * @description:
 * @author: zhoubiao
 * @create: 2019/08/09 16:53
 */

package cn.com.scitc.service;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@Retry(name = "retryBackendA")
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CircuitBreakerRegistry circuitBreakerRegistry;

    public String hello(String name) {
        return restTemplate.getForObject("http://provider/hello?name={1}", String.class, name);

    }

}
