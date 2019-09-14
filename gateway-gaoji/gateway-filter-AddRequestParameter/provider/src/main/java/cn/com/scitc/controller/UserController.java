/**
 * @program gateway-gaoji
 * @description: rest服务提供者
 * @author: zhoubiao
 * @create: 2019/09/14 11:21
 */

package cn.com.scitc.controller;

import cn.com.scitc.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class UserController {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @RequestMapping(value = "/SaveUser",method = RequestMethod.POST)
    public ResponseEntity<Object> SaveUser(@RequestBody UserVo user) {
        log.info("provider提供SaveUser服务" );
        user.setAddress(user.getAddress());
        user.setAge(user.getAge());
        user.setJob(user.getJob());
        user.setNickname(user.getNickname());

        return new ResponseEntity<Object>(user, HttpStatus.OK);
    }
}
