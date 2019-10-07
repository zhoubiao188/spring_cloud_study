/**
 * @program cloud-config-salt
 * @description:
 * @author: zhoubiao
 * @create: 2019/10/03 18:50
 */

package cn.com.scitc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Value("${love}")
    String love;

    @Value("${name}")
    String name;

    @GetMapping("/hello")
    public String hello() {
        return love;
    }

    @GetMapping("/name")
    public String name() {
        return name;
    }
}
