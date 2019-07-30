/**
 * @program RestTemplate
 * @description: provider 对外提供服务
 * @author: zhoubiao
 * @create: 2019/07/25 21:18
 */

package cn.com.scitc.provider;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SayHelloController {

    /**
     * @description:
     * @param: 提供消费服务
     * @return: String name
     * @author: zhoubiao
     * @date: 2019-07-29
    */
    @GetMapping("/hello")
    public String hello(String name) {
        return "hello" + name + "!";
    }

    @PostMapping("/hello2")
    public String sayHello2(String name) {
        return "hello" + name + "!";
    }
}
