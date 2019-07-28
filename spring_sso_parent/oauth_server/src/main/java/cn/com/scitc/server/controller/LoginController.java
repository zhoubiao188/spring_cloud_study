/**
 * @program spring_sso_parent
 * @description: 自定义登陆页面
 * @author: zhoubiao
 * @create: 2019/07/27 14:47
 */

package cn.com.scitc.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
