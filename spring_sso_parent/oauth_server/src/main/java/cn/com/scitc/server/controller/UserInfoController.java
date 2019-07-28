/**
 * @program spring_sso_parent
 * @description: 获取用户信息
 * @author: zhoubiao
 * @create: 2019/07/27 14:50
 */

package cn.com.scitc.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserInfoController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @RequestMapping("/user")
    public ResponseEntity<Object> getUser(Principal principal) {
        logger.info("principal:" + principal);
        return new ResponseEntity<Object>(principal, HttpStatus.OK);
    }
}
