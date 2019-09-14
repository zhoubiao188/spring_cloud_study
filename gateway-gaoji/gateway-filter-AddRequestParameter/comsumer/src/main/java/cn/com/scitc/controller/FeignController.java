/**
 * @program gateway-gaoji
 * @description: 远程调用
 * @author: zhoubiao
 * @create: 2019/09/14 11:38
 */

package cn.com.scitc.controller;

import cn.com.scitc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/rest")
public class FeignController {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/SaveUser",method = RequestMethod.POST)
    public ResponseEntity<Object> SaveUser(@RequestBody Map<String,Object> user) {
      log.info("consumer消费了provider提供的SaveUser服务");
      Object result =   userService.SaveUser(user);
      return new ResponseEntity<Object>(result, HttpStatus.OK);
    }
}
