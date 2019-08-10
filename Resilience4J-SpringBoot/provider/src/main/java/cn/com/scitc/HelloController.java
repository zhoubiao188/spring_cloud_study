/**
 * @program Resilience4J-SpringBoot
 * @description:
 * @author: zhoubiao
 * @create: 2019/08/09 16:37
 */

package cn.com.scitc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(String name) {
       String s = "hello" + name + "!";
       System.out.println(s + ">>>" + new Date() );
       int i = 1/ 0; //这里会出错
       return s;
    }
}
