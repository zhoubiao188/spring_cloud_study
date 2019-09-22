/**
 * @program cloudConfig
 * @description: 使用仓库配置文件
 * @author: zhoubiao
 * @create: 2019/09/22 13:55
 */

package cn.com.scitc.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Value("${document}")
    private String love;

    @GetMapping("/love")
    public String love() {
        return love;
    }
}
