/**
 * @program ServerRegister
 * @description: 消费者去消费服务提供者的接口
 * @author: zhoubiao
 * @create: 2019/07/25 10:31
 */

package cn.com.scitc.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class SayUserHelloController {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/sayHello")
    public String sayHello(String name) {
        List<ServiceInstance> list = discoveryClient.getInstances("provider");
        ServiceInstance instance = list.get(0);

        String host = instance.getHost();
        int port = instance.getPort();

        String s = restTemplate.getForObject("http://" + host  + ":" + port + "/sayHello?name={1}" ,String.class ,name);
        return s;
    }
}
