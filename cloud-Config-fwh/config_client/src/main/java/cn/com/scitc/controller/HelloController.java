/**
 * @program cloud-Config-fwh
 * @description:
 * @author: zhoubiao
 * @create: 2019/10/07 19:07
 */

package cn.com.scitc.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class HelloController {
    @Value("${love}")
    String love;

    @GetMapping("/love")
    public String name() {
        return love;
    }
}
