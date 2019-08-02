/**
 * @program Loadbalancer
 * @description:
 * @author: zhoubiao
 * @create: 2019/08/01 09:12
 */

package cn.com.scitc.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Value("${server.port}")
    Integer port;

    @GetMapping("/hello")
    public String hello(String name) {
        return "hello" + name + ":" + port;
    }
}
