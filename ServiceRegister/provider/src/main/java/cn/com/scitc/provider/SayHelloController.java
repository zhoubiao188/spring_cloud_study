/**
 * @program ServerRegister
 * @description: 服务提供者 提供一个接口
 * @author: zhoubiao
 * @create: 2019/07/25 10:26
 */

package cn.com.scitc.provider;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SayHelloController {
    @GetMapping("/sayHello")
    public String sayHello(String name) {
        return "hello :" + name +  "!";
    }

}
