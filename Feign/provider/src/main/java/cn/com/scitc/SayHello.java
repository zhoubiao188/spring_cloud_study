/**
 * @program Feign
 * @description:
 * @author: zhoubiao
 * @create: 2019/08/01 13:49
 */

package cn.com.scitc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SayHello {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/hello")
    public String hello(String name) {
        log.info("hello被消费掉了");
        return "hello" + name + "!";
    }
}
