/**
 * @program Feign
 * @description: 申明式调用
 * @author: zhoubiao
 * @create: 2019/08/01 14:01
 */

package cn.com.scitc.controller;

import cn.com.scitc.UserDTO;
import cn.com.scitc.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    HelloService helloService;
    @GetMapping("/hello")
    public String hello(String name) {
        log.info("调用了provider");
        return helloService.hello(name);
    }

    @PostMapping("/user")
    public ResponseEntity<String> addUser(@RequestBody UserDTO userDTO ) {
        String s = helloService.addUser(userDTO);
        log.info("消费了provider的addUser");
        return new ResponseEntity<String>(s, HttpStatus.OK);
    }
    @PutMapping("/user")
    public ResponseEntity<Object> updateUser(
                                            @RequestBody UserDTO userDTO ) {
        Map<String,Object> map = new HashMap<>();
        map.put("name", userDTO.getNickname());
        map.put("id", userDTO.getId());

        log.info("消费了map:"  + map);
        return new ResponseEntity<Object>(map, HttpStatus.OK);

    }
}
