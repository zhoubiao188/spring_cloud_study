/**
 * @program spring-cloud-gateway-eureka
 * @description: 服务消费者
 * @author: zhoubiao
 * @create: 2019/09/08 11:30
 */

package cn.com.scitc;

import cn.com.scitc.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {
    @Autowired
    FeignService feignService;

    @RequestMapping(value = "/getUser",method = RequestMethod.POST)
    public ResponseEntity<Object> getUser(@RequestBody UserVo user) {
       Object result =  feignService.getUser(user);
        return new ResponseEntity<Object>(result, HttpStatus.OK);
    }
}
