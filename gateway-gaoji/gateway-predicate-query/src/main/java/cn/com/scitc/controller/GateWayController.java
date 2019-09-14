/**
 * @program gateway-gaoji
 * @description:
 * @author: zhoubiao
 * @create: 2019/09/13 20:18
 */

package cn.com.scitc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GateWayController {
    @GetMapping(value = "/Query")
    public String QueryPage(@PathVariable("name") String name) {
        return name;
    }
}
