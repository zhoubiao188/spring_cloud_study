/**
 * @program Feign
 * @description:
 * @author: zhoubiao
 * @create: 2019/08/01 14:00
 */

package cn.com.scitc.service;

import cn.com.scitc.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("provider")
public interface HelloService {
    @GetMapping("/hello")
    String hello(@RequestParam("name") String name);

    @PostMapping("/user")
    String addUser(@RequestBody UserDTO userDTO);

    @PutMapping("/user")
    String updateUser(@RequestBody UserDTO userDTO);
}
